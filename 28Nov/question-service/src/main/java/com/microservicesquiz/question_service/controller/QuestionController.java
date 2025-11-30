package com.microservicesquiz.question_service.controller;


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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservicesquiz.question_service.model.Question;
import com.microservicesquiz.question_service.model.QuestionWrapper;
import com.microservicesquiz.question_service.model.Response;
import com.microservicesquiz.question_service.service.QuestionService;

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
	
	 // generate
	 // The quiz service will ask the question service for questions, but they will not have access to each other's database
	 // getQuestions(questionid)
	 // getScore
	 
	 @GetMapping("generate")
	 public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName, @RequestParam Integer numQuestions){  // passing these 2 parameters from the quiz service
		 return questionService.getQuestionsForQuiz(categoryName, numQuestions);
	 }
	 
	 @PostMapping("getQuestions")   // if a quiz service is requesting the questions for a particular id
	 public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
		 return questionService.getQuestionsByCategory(questionIds);
	 }
	 
	 // To get the score
	 @PostMapping("getScore")
	 public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
		 return questionService.getScore(responses);
	 }
	 
	
	
	
	
}

