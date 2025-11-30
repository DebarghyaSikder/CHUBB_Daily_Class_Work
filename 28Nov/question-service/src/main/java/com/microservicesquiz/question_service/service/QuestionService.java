package com.microservicesquiz.question_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.microservicesquiz.question_service.dao.QuestionDao;
import com.microservicesquiz.question_service.model.Question;
import com.microservicesquiz.question_service.model.QuestionWrapper;
import com.microservicesquiz.question_service.model.Response;


@Service
public class QuestionService {        // service is just fetching data from DAO layer

	@Autowired
	QuestionDao questionDao;       // I need QuestionDao objects here so that I can get all the questions
	
	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
		return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);    // finding the list of questions
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);    // returning an empty
		}

	public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
		
		// When we are using Dao since we are using Jpa repository, instead of using Get we will use Find
		
		try {
			return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);    // finding the list of questions
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);    // returning an empty

			
	}

	public ResponseEntity<String> addQuestion(Question question) {
		questionDao.save(question);
		return new ResponseEntity<>("success", HttpStatus.CREATED);  // since its data on server it should be CEATED
		
	}

	public String deleteQuestion(Integer id) {
		questionDao.deleteById(id);
		return "Deleted"+id+"th question";
	}

	public String modifyQuestions(Question question) {
		questionDao.save(question);
		return "Question Modified";
	}

	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {
		List<Integer> questions=questionDao.findRandomQuestionsByCategory(categoryName,numQuestions);
		return new ResponseEntity<>(questions, HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestionsByCategory(List<Integer> questionIds) {
		List<QuestionWrapper> wrappers=new ArrayList<>();
		List<Question> questions=new ArrayList<>();
		
		// Iterate between the ids and get one question at a time. Passing the questionId value and getting data from the database
		for(Integer id:questionIds) {
			questions.add(questionDao.findById(id).get());
		}
		
		// Now we do not want questions but the wrapper as we do not want the answer
		for(Question question: questions) {
			QuestionWrapper wrapper=new QuestionWrapper();
			wrapper.setId(question.getId());
			wrapper.setQuestionTitle(question.getQuestionTitle());
			wrapper.setOption1(question.getOption1());
			wrapper.setOption2(question.getOption2());
			wrapper.setOption3(question.getOption3());
			wrapper.setOption4(question.getOption4());
			
			// We created one question wrapper at a time. And now adding it to the list
			wrappers.add(wrapper);
			
		}
		
		return new ResponseEntity<>(wrappers, HttpStatus.OK);
	}
	
	// To calculate score
	public ResponseEntity<Integer> getScore(List<Response> responses){
		int right=0;
		
		for(Response response: responses) {
			Question question=questionDao.findById(response.getId()).get();
			if(response.getResponse().equals(question.getRightAnswer())) // comparing response with the right answer
				right++;
		}
		return new ResponseEntity<>(right, HttpStatus.OK);
	}
	

}