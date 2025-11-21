package com.example.webflux.service;

import org.springframework.stereotype.Service;

import com.example.webflux.model.Tutorial;
import com.example.webflux.repository.TutorialRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TutorialService {

    private final TutorialRepository repository;

    public TutorialService(TutorialRepository repository) {
        this.repository = repository;
    }

    public Flux<Tutorial> getAll(String title) {
        if (title != null && !title.isBlank()) {
            return repository.findByTitleContainingIgnoreCase(title);
        }
        return repository.findAll();
    }

    public Mono<Tutorial> getById(Long id) {
        return repository.findById(id);
    }

    public Flux<Tutorial> getPublished() {
        return repository.findByPublished(true);
    }

    public Mono<Tutorial> create(Tutorial t) {
        if (t.getPublished() == null) {
            t.setPublished(false);
        }
        return repository.save(t);
    }

    public Mono<Tutorial> update(Long id, Tutorial t) {
        return repository.findById(id)
                .flatMap(existing -> {
                    existing.setTitle(t.getTitle());
                    existing.setDescription(t.getDescription());
                    existing.setPublished(t.getPublished());
                    return repository.save(existing);
                });
    }

    public Mono<Void> delete(Long id) {
        return repository.deleteById(id);
    }

    public Mono<Void> deleteAll() {
        return repository.deleteAll();
    }
}
