package com.microservices.quizapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservices.quizapp.dao.QuestionDao;
import com.microservices.quizapp.dao.QuizDao;
import com.microservices.quizapp.model.Question;
import com.microservices.quizapp.model.QuestionWrapper;
import com.microservices.quizapp.model.Quiz;

@Service
public class QuizService {

	@Autowired
	QuizDao quizDao;
	
	// We will get the questions from database using QuestionDao
	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		
		List<Question> questions=questionDao.findRandomQuestionsByCategory(category,numQ);
		
		// creating the quiz
		Quiz quiz=new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);  
		quizDao.save(quiz); // saving the quiz
		
		return new ResponseEntity<>("Success", HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		// To fetch the quiz object from the database we use QuizDao
		
		Optional<Quiz> quiz=quizDao.findById(id); // Optional data- means the data of that id might come or not
		List<Question> questionsFromDB=quiz.get().getQuestions();
		List<QuestionWrapper> questionsForUser=new ArrayList<>();
		
		//converting each question to question wrapper
		for(Question q:questionsFromDB) {
			QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
			questionsForUser.add(qw);
		}
		
		return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
	}
}
