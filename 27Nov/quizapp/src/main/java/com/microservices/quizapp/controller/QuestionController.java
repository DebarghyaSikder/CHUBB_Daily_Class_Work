package com.microservices.quizapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.quizapp.Question;
import com.microservices.quizapp.service.QuestionService;
import java.util.*;

@RestController
@RequestMapping("question")   // question is by default for the entire controller
public class QuestionController {
	
	@Autowired
	QuestionService questionService;   // I need object of service and since we are using Spring framework so use Autowired
	
	@GetMapping("allQuestions")
	public List<Question> getAllQuestions() {
		return questionService.getAllQuestions();   // returning object of service layer or service class
	}
	
	@GetMapping("category/{category}")
	public List<Question> getQuestionsByCategory(@PathVariable String category){
		return questionService.getQuestionsByCategory(category);
		
	}
	
	// If we receive json on server side then we don't need to manually change it- Spring will ask us to just specify the json and it will convert it to object
	// We will send the data from client side to server in the body of the Request
	
	
	public String addQuestion(@RequestBody Question question) {
		
		// Now after receiving the question, need to add that inn database

		questionService.addQuestion(question);
		return "success";
	}
	
	
	
}
