package com.javamastermind.student;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.javamastermind.student.model.Student;
import com.javamastermind.student.service.StudentService;

@SpringBootTest
class StudentServiceApplicationTests {
	
	
	@Autowired
	StudentService studentService;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void addOrUpdateStudent(){
		Student student = new Student();
		student.setId(1);
	    student.setName("Lahiru");
	    student.setAge("34");
		assertNotNull(studentService.addOrUpdateStudent(student));
		
	}

}
