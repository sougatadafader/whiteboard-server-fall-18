package com.example.whiteboardfall2018serverjava.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.whiteboardfall2018serverjava.models.Course;

@RestController
@CrossOrigin(origins = "*")
public class CourseService {
	 List<Course> courses = null;
	 @GetMapping("/api/course")
	 public List<Course> findAllCourses() {
		 if(courses == null) {
			 courses = new ArrayList<Course>();
			 Course c1 = new Course(123, "cs5610");
			 Course c2 = new Course(234, "cs5200");
			 courses.add(c1);
			 courses.add(c2);
		 }
	     return courses;
	 }
}
