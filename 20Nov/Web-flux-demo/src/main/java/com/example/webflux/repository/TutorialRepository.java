package com.example.webflux.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.example.webflux.model.Tutorial;

import reactor.core.publisher.Flux;

@Repository
public interface TutorialRepository extends ReactiveCrudRepository<Tutorial, Long> {

    Flux<Tutorial> findByPublished(boolean published);

    Flux<Tutorial> findByTitleContainingIgnoreCase(String title);
}
