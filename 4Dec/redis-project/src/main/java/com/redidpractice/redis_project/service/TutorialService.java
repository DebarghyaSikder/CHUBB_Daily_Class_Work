package com.redidpractice.redis_project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.redidpractice.redis_project.model.Tutorial;
import com.redidpractice.redis_project.repository.TutorialRepository;

@Service
public class TutorialService {

    private final TutorialRepository tutorialRepository;

    public TutorialService(TutorialRepository tutorialRepository) {
        this.tutorialRepository = tutorialRepository;
    }

    @Cacheable("tutorials")
    public List<Tutorial> findAll() {
        doLongRunningTask();
        return tutorialRepository.findAll();
    }

    @Cacheable("tutorials")
    public List<Tutorial> findByTitleContaining(String title) {
        doLongRunningTask();
        return tutorialRepository.findByTitleContaining(title);
    }

    @Cacheable("tutorial")
    public Optional<Tutorial> findById(String id) {  
        doLongRunningTask();
        return tutorialRepository.findById(id);
    }

    @Cacheable("published_tutorials")
    public List<Tutorial> findByPublished(boolean isPublished) {
        doLongRunningTask();
        return tutorialRepository.findByPublished(isPublished);
    }

    private void doLongRunningTask() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
