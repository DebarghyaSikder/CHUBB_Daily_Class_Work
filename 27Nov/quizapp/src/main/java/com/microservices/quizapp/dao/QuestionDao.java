package com.microservices.quizapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.microservices.quizapp.model.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer>{   // We nend to specify the class name that maps with the table and the PK type 
	List<Question> findByCategory(String category);

	@Query(value="SELECT * FROM question q Where q.category=:category ORDER BY RAND() LIMIT :numQ", nativeQuery=true)             // fetch question from the Question table
	List<Question> findRandomQuestionsByCategory(String category,int numQ);

}
