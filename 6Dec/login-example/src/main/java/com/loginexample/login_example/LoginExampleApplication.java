package com.loginexample.login_example;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class LoginExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginExampleApplication.class, args);
	}

}
