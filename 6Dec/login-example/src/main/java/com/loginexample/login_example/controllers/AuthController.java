package com.loginexample.login_example.controllers;

import java.util.*;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.loginexample.login_example.models.*;
import com.loginexample.login_example.payload.request.*;
import com.loginexample.login_example.payload.response.*;
import com.loginexample.login_example.repository.*;
import com.loginexample.login_example.security.jwt.JwtUtils;
import com.loginexample.login_example.security.services.UserDetailsImpl;



import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import com.loginexample.login_example.models.VerificationToken;
import com.loginexample.login_example.models.PasswordResetToken;
import com.loginexample.login_example.repository.VerificationTokenRepository;
import com.loginexample.login_example.repository.PasswordResetTokenRepository;
import com.loginexample.login_example.service.EmailService;

import org.springframework.beans.factory.annotation.Value;



@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final EmailService emailService;
    
    @Value("${app.frontend.base-url:http://localhost:8080}")
    private String baseUrl;

    public AuthController(AuthenticationManager authenticationManager,
                          UserRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder encoder,
                          JwtUtils jwtUtils,
                          VerificationTokenRepository verificationTokenRepository,
                          PasswordResetTokenRepository passwordResetTokenRepository,
                          EmailService emailService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.verificationTokenRepository = verificationTokenRepository;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.emailService = emailService;
    }
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest,
                                              jakarta.servlet.http.HttpServletResponse response) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));
        
        
        

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        
     // fetch full User to check emailVerified
        User user = userRepository.findById(userDetails.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.isEmailVerified()) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(new MessageResponse("Please verify your email before logging in."));
        }

        var jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        response.addCookie(jwtCookie);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        UserInfoResponse userInfo = new UserInfoResponse(
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles
        );

        return ResponseEntity.ok(userInfo);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                             signUpRequest.getEmail(),
                             encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        
     // generate verification token valid for 24 hours
        String token = UUID.randomUUID().toString();
        Instant expiry = Instant.now().plus(24, ChronoUnit.HOURS);

        VerificationToken verificationToken =
                new VerificationToken(token, user, expiry);
        verificationTokenRepository.save(verificationToken);

        String verifyLink = baseUrl + "/api/auth/verify-email?token=" + token;

        emailService.sendEmail(
                user.getEmail(),
                "Please verify your email",
                "Hi " + user.getUsername() +
                        ",\n\nPlease verify your email by clicking the link below:\n" +
                        verifyLink +
                        "\n\nThis link will expire in 24 hours."
        );
        
        
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser(jakarta.servlet.http.HttpServletResponse response) {
        var cookie = jwtUtils.getCleanJwtCookie();
        response.addCookie(cookie);
        return ResponseEntity.ok(new MessageResponse("You've been signed out!"));
    }
    
    @GetMapping("/verify-email")
    public ResponseEntity<?> verifyEmail(@RequestParam("token") String token) {

        var tokenOpt = verificationTokenRepository.findByToken(token);
        if (tokenOpt.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Invalid verification token"));
        }

        VerificationToken verificationToken = tokenOpt.get();

        if (verificationToken.getExpiryDate().isBefore(Instant.now())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Verification token has expired"));
        }

        User user = verificationToken.getUser();
        user.setEmailVerified(true);
        userRepository.save(user);

        verificationTokenRepository.delete(verificationToken);

        return ResponseEntity.ok(new MessageResponse("Email verified successfully!"));
    }
}
