package com.microservices.quizapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.quizapp.Question;
import com.microservices.quizapp.service.QuestionService;
import java.util.*;

@RestController
@RequestMapping("question")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;   // I need object of service and since we are using Spring framework so use Autowired
	
	@GetMapping("allQuestions")
	public List<Question> getAllQuestions() {
		return questionService.getAllQuestions();   // returning object of service layer or service class
	}
	
	
}
