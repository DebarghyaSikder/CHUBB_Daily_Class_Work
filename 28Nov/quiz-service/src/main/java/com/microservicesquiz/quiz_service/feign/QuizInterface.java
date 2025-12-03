package com.microservicesquiz.quiz_service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.microservicesquiz.quiz_service.model.QuestionWrapper;
import com.microservicesquiz.quiz_service.model.Response;


// In the annotation mention which service we are connecting to
@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

	// Quiz service need to tell Question service which services or methods it will access(only 3 methods out of many)
	
	
	@GetMapping("question/generate")
	 public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName, @RequestParam Integer numQuestions);
	 
	 @PostMapping("question/getQuestions")   // if a quiz service is requesting the questions for a particular id
	 public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds);
	 
	 // To get the score
	 @PostMapping("question/getScore")
	 public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
