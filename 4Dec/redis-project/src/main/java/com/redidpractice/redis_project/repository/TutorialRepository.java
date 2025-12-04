package com.redidpractice.redis_project.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.redidpractice.redis_project.model.Tutorial;

@Repository
public interface TutorialRepository extends MongoRepository<Tutorial, String> {

    List<Tutorial> findByTitleContaining(String title);

    List<Tutorial> findByPublished(boolean isPublished);
}
