package com.microservices.quizapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public ResponseEntity<List<Question>> getAllQuestions() {
		
		// In response entity we pass two things-data and status code
		return questionService.getAllQuestions();   // returning the data itsel that we are getting from the service as service is giving response
	} // getAllQuestions actually returns ResponseEntity
	
	
	@GetMapping("category/{category}")
	public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
		return questionService.getQuestionsByCategory(category);
		
	}
	
	// If we receive json on server side then we don't need to manually change it- Spring will ask us to just specify the json and it will convert it to object
	// We will send the data from client side to server in the body of the Request
	 
	@PostMapping("add")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {
		
		// Now after receiving the question, need to add that inn database

	
		// We should return things coming from the Dao
		return questionService.addQuestion(question);
	}
	
	@DeleteMapping("deleteQuestion/{id}")
	public String deleteQuestion(@PathVariable Integer id) {
		return questionService.deleteQuestion(id);
	}
	
	 @PutMapping("updateQuestion/{id}")
		public String modifyQuestion(@RequestBody Question question) {
			return questionService.modifyQuestions(question);	
		}
	
	
	
	
	
}
