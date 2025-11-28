package com.microservices.quizapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.quizapp.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer>{   // We nend to specify the class name that maps with the table and the PK type 
	

}
