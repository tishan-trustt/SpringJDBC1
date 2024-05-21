package com.tishan;

import com.tishan.model.Student;
import com.tishan.service.StudentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class SpringJdbcex1Application {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(SpringJdbcex1Application.class, args);

		Student student = context.getBean(Student.class);
		student.setRollNo(101);
		student.setName("Tishan");
		student.setMarks(80);

		StudentService studentService = context.getBean(StudentService.class);
		studentService.addStudent(student);
		List<Student> students = studentService.getStudents();
		System.out.println(students);
	}

}
