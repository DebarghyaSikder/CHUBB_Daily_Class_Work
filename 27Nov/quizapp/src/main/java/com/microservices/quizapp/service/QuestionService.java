package com.microservices.quizapp.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.quizapp.Question;
import com.microservices.quizapp.dao.QuestionDao;

@Service
public class QuestionService {        // service is just fetching data from DAO layer

	@Autowired
	QuestionDao questionDao;       // I need QuestionDao objects here so that I can get all the questions
	
	public List<Question> getAllQuestions() {
		// TODO Auto-generated method stub
		return questionDao.findAll();    // finding the list of questions
	}

	public List<Question> getQuestionsByCategory(String category) {
		
		// When we are using Dao since we are using Jpa repository, instead of using Get we will use Find
		
		return questionDao.findByCategory(category);
	}

	public String addQuestion(Question question) {
		questionDao.save(question);
		return "success";
		
	}

	public String deleteQuestion(Integer id) {
		questionDao.deleteById(id);
		return "Deleted"+id+"th question";
	}

	public String modifyQuestions(Question question) {
		questionDao.save(question);
		return "Question Modified";
	}

}
