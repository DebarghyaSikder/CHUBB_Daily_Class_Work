package com.microservices.quizapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

//This is my model class- matching with my table design
// CLass fields are table columns
// No. of objects we have for the class, each object can represent a row

@Data

// for mapping the table to get mapped with the class- use @entity

@Entity
public class Question {

	// for declaring the id as PK
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String questionTitle;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String rightAnswer;
	private String difficultylevel;
	private String category;
	
	
}
