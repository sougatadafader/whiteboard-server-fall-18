package com.example.whiteboardfall2018serverjava.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.whiteboardfall2018serverjava.models.Course;
import com.example.whiteboardfall2018serverjava.models.User;

@RestController
@CrossOrigin(origins = "*")
public class CourseService {
	List<Course> courses = new ArrayList<Course>();
	List<Course> resultCourses = new ArrayList<Course>();

	/* Retrieves all courses authored by the currently logged in author */
	@GetMapping("/api/course")
	public List<Course> findAllCourses(HttpSession session) {
		User currentUser = (User) session.getAttribute("currentUser");
		if (courses.isEmpty()) {

			Course c1 = new Course(123, "cs5610");
			c1.setAuthor(currentUser);
			Course c2 = new Course(234, "cs5200");
			c2.setAuthor(currentUser);
			courses.add(c1);
			courses.add(c2);
			resultCourses = new ArrayList<Course>(courses);

		} else {
			resultCourses = (List<Course>) courses.stream().filter(x -> x.getAuthor() == currentUser)
					.collect(Collectors.toList());
			/*
			 * for(Course c : courses) { if(c.getAuthor()==currentUser) {
			 * resultCourses.add(c); } }
			 */

		}
		return resultCourses;
	}

	/* Creates new course with current logged in faculty as its author */

	@PostMapping("/api/course")
	public Course createCourse(@RequestBody Course course, HttpSession session) {
		int uId= (int)System.currentTimeMillis();
		course.setId(uId);
		User currentUser = (User) session.getAttribute("currentUser");
		course.setAuthor(currentUser);
		if ( courses.isEmpty() || !(courses.contains(course)) ) {
			
			courses.add(course);
			return course;
		} else {
			
			return null;
		}
	}

	/* Retrieves course whose id is cid */
	@GetMapping("/api/course/{cid}")
	public Course findCourseById(@PathVariable("cid") int cid) {
		Course reqdCourse = null;
		for (Course c : courses) {
			if (c.getId() == cid) {
				reqdCourse = c;
			}
		}
		return reqdCourse;
	}

	/* Updates course whose id is cid */
	@PutMapping("/api/course/{cid}")
	public Course updateCourse(@PathVariable("cid") int cid, @RequestBody Course course) {
		int ctr = 0;
		course.setId(cid);
		for (Course c : courses) {
			if (c.getId() == cid) {
				courses.set(ctr, course);
				break;
			}
			ctr++;
		}
		return courses.get(ctr);
	}

	/* Removes course whose id is cid */
	@DeleteMapping("/api/course/{cid}")
	public void deleteCourse(@PathVariable("cid") int cid) {
		Course reqdCourse = null;
		if (courses != null) {
			for (Course c : courses) {
				if (c.getId() == cid) {
					reqdCourse = c;
				}
			}
			if (reqdCourse != null) {
				courses.remove(reqdCourse);
			}
		}

	}
}
