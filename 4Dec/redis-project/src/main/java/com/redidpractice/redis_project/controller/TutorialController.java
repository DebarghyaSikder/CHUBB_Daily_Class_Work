package com.redidpractice.redis_project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.redidpractice.redis_project.model.Tutorial;
import com.redidpractice.redis_project.service.TutorialService;

@RestController
@RequestMapping("/api/tutorials")
public class TutorialController {

    private final TutorialService tutorialService;

    public TutorialController(TutorialService tutorialService) {
        this.tutorialService = tutorialService;
    }

    // ---------- CREATE ----------
    @PostMapping
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
        Tutorial saved = tutorialService.save(tutorial);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // ---------- READ: ALL (CACHED) ----------
    @GetMapping
    public ResponseEntity<List<Tutorial>> getAllTutorials() {
        List<Tutorial> tutorials = tutorialService.findAll();
        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }

    // ---------- READ: BY ID (CACHED) ----------
    @GetMapping("/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable String id) {
        Optional<Tutorial> tutorialOpt = tutorialService.findById(id);
        return tutorialOpt
                .map(tutorial -> new ResponseEntity<>(tutorial, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // ---------- READ: SEARCH BY TITLE (CACHED) ----------
    @GetMapping("/search")
    public ResponseEntity<List<Tutorial>> getByTitle(@RequestParam String title) {
        List<Tutorial> tutorials = tutorialService.findByTitleContaining(title);
        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }

    // ---------- READ: PUBLISHED ONLY (CACHED) ----------
    @GetMapping("/published")
    public ResponseEntity<List<Tutorial>> getPublishedTutorials() {
        List<Tutorial> tutorials = tutorialService.findByPublished(true);
        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }

    // ---------- UPDATE ----------
    @PutMapping("/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable String id,
                                                   @RequestBody Tutorial tutorialRequest) {
        Optional<Tutorial> existing = tutorialService.findById(id);

        if (existing.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Tutorial t = existing.get();
        t.setTitle(tutorialRequest.getTitle());
        t.setDescription(tutorialRequest.getDescription());
        t.setPublished(tutorialRequest.isPublished());

        Tutorial updated = tutorialService.save(t); // cache evicted
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // ---------- DELETE ONE ----------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTutorial(@PathVariable String id) {
        if (!tutorialService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        tutorialService.deleteById(id); // cache evicted
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // ---------- DELETE ALL ----------
    @DeleteMapping
    public ResponseEntity<Void> deleteAllTutorials() {
        tutorialService.deleteAll(); // cache evicted
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
