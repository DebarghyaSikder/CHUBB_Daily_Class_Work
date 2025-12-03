package com.microservicesquiz.quiz_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservicesquiz.quiz_service.dao.QuizDao;
import com.microservicesquiz.quiz_service.feign.QuizInterface;
import com.microservicesquiz.quiz_service.model.QuestionWrapper;
import com.microservicesquiz.quiz_service.model.Quiz;
import com.microservicesquiz.quiz_service.model.Response;


@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;
    
    @Autowired
    QuizInterface quizInterface; // We will not use Rest template but this to make call to the question service

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        		
        		// Feign service prevents hardcoding all port numbers and other values. It helps to declare what we want and to declare what are the APIS I want exposed. It help to connect wit the service we want
        		// By using Eureka we are solving the problem of IP address and port no. and by Feign we can request directly to the service with the service name
        		
    	// We need the ids and not the actual questions and user the quiz interface to call the method
    	List<Integer> questions=quizInterface.getQuestionsForQuiz(category, numQ).getBody();   // it gives response entity but to get only list of questions nI will use getBody

    	Quiz quiz=new Quiz();
    	quiz.setTitle(title);
    	quiz.setQuestionIds(questions);
    	// saving the quiz in the database
    	quizDao.save(quiz);
    	
    	// Quiz service ask the questions to the Question service with the help of feign client
    	
        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
          Quiz quiz=quizDao.findById(id).get();
          // Before getting the questions from the question service first get the question nos. by specifying ht equiz id
          List<Integer> questionIds=quiz.getQuestionIds();

          quizInterface.getQuestionsFromId(questionIds);
          
          ResponseEntity<List<QuestionWrapper>> questions=quizInterface.getQuestionsFromId(questionIds);  // we got the list of questions
          
          return questions; // returning the Response object

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        // quiz interface will call the getScore method and it will retrun the score in ResponseEntity format
    	
    	ResponseEntity<Integer> score=quizInterface.getScore(responses);
    	
		return score;
    }
}