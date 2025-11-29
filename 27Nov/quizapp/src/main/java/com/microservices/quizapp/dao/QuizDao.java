package com.microservices.quizapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.quizapp.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
