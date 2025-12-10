package com.security.SpringSecurity;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class StudentController {
	private List<Student> students=new ArrayList<>(List.of(
				new Student(1,"Dev",60),
				new Student(2,"Karan",65)
			));
			
	@GetMapping("/students")
	public List<Student> getStudents(){
		return students;
		
	}

}
