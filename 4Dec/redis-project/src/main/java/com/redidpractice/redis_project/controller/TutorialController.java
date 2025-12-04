package com.redidpractice.redis_project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.redidpractice.redis_project.model.Tutorial;
import com.redidpractice.redis_project.service.TutorialService;
import com.redidpractice.redis_project.repository.TutorialRepository;

@RestController
@RequestMapping("/api/tutorials")
public class TutorialController {

    private final TutorialService tutorialService;
    private final TutorialRepository tutorialRepository;

    public TutorialController(TutorialService tutorialService,
                              TutorialRepository tutorialRepository) {
        this.tutorialService = tutorialService;
        this.tutorialRepository = tutorialRepository;
    }

    // ---------- CREATE ----------
    @PostMapping
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
        Tutorial saved = tutorialRepository.save(tutorial);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // ---------- READ: ALL (CACHED) ----------
    @GetMapping
    public ResponseEntity<List<Tutorial>> getAllTutorials() {
        List<Tutorial> tutorials = tutorialService.findAll(); // cached
        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }

    // ---------- READ: BY ID (CACHED) ----------
    @GetMapping("/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable String id) {
        Optional<Tutorial> tutorialOpt = tutorialService.findById(id); // cached
        return tutorialOpt
                .map(tutorial -> new ResponseEntity<>(tutorial, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // ---------- READ: SEARCH BY TITLE (CACHED) ----------
    @GetMapping("/search")
    public ResponseEntity<List<Tutorial>> getByTitle(@RequestParam String title) {
        List<Tutorial> tutorials = tutorialService.findByTitleContaining(title); // cached
        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }

    // ---------- READ: PUBLISHED ONLY (CACHED) ----------
    @GetMapping("/published")
    public ResponseEntity<List<Tutorial>> getPublishedTutorials() {
        List<Tutorial> tutorials = tutorialService.findByPublished(true); // cached
        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }

    // ---------- UPDATE ----------
    @PutMapping("/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable String id,
                                                   @RequestBody Tutorial tutorialRequest) {
        Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

        if (tutorialData.isPresent()) {
            Tutorial t = tutorialData.get();
            t.setTitle(tutorialRequest.getTitle());
            t.setDescription(tutorialRequest.getDescription());
            t.setPublished(tutorialRequest.isPublished());
            Tutorial updated = tutorialRepository.save(t);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // ---------- DELETE ONE ----------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTutorial(@PathVariable String id) {
        if (tutorialRepository.existsById(id)) {
            tutorialRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // ---------- DELETE ALL ----------
    @DeleteMapping
    public ResponseEntity<Void> deleteAllTutorials() {
        tutorialRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
