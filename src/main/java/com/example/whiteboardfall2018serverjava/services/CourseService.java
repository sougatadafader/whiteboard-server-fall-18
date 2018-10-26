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
import com.example.whiteboardfall2018serverjava.models.Lesson;
import com.example.whiteboardfall2018serverjava.models.Module;
import com.example.whiteboardfall2018serverjava.models.Topic;
import com.example.whiteboardfall2018serverjava.models.User;

@RestController
@CrossOrigin(origins = "https://shrouded-cove-74042.herokuapp.com/" , allowCredentials = "true" , allowedHeaders = "*")
public class CourseService {
	List<Course> courses = new ArrayList<Course>();
	List<Course> resultCourses = new ArrayList<Course>();

	/* Retrieves all courses authored by the currently logged in author */
	@GetMapping("/api/course")
	public List<Course> findAllCourses(HttpSession session) {
		User currentUser = (User) session.getAttribute("currentUser");
		if(currentUser!=null)
		{
			courses = currentUser.getCourses();
		}
		if (courses.isEmpty()) {
			long ctr =0;
			Course c1 = new Course(System.currentTimeMillis() +ctr, "cs5610");
			//Course c2 = new Course(System.currentTimeMillis() , "cs5200");
			
			
			Module m1 = new Module(System.currentTimeMillis() +ctr+1, "Module 1");
			Module m2 = new Module(System.currentTimeMillis() +ctr+2, "Module 2");
			Lesson l1 = new Lesson(System.currentTimeMillis() +ctr+3, "Lesson 1");
			Lesson l2 = new Lesson(System.currentTimeMillis() +ctr+4, "Lesson 2");
			Topic t1 = new Topic(System.currentTimeMillis() +ctr+5,"Topic a");
			Topic t2 = new Topic(System.currentTimeMillis() +ctr+6,"Topic b");
			List<Module> module1 = new ArrayList<Module>();
			
			List<Topic> topics1 = new ArrayList<Topic>();
			List<Topic> topics2 = new ArrayList<Topic>();
			topics1.add(t1);
			topics2.add(t2);
			l1.setTopics(topics1);
			l2.setTopics(topics2);
			List<Lesson> lesson1 = new ArrayList<Lesson>();
			List<Lesson> lesson2 = new ArrayList<Lesson>();
			lesson1.add(l1);
			lesson2.add(l2);
			m1.setLessons(lesson1);
			m2.setLessons(lesson2);
			module1.add(m1);
			module1.add(m2);
			
			c1.setModules(module1);
			courses.add(c1);
			
			
			
			/*courses.add(c1);
			ModuleService m1 = new ModuleService();
			m1.findModulesForCourse(c1.getId(), session);
			courses.add(c2);*/
			resultCourses = new ArrayList<Course>(courses);
			currentUser.setCourses(resultCourses);
			return resultCourses;
		} 
		return courses;
	}

	/* Creates new course with current logged in faculty as its author */

	@PostMapping("/api/course")
	public Course createCourse(@RequestBody Course course, HttpSession session) {
		course.setId(System.currentTimeMillis());
		User currentUser = (User) session.getAttribute("currentUser");
		courses = currentUser.getCourses();
		long ctr =0;
		Module m1 = new Module(System.currentTimeMillis() +ctr+1, "Module 1");
		Module m2 = new Module(System.currentTimeMillis() +ctr+2, "Module 2");
		Lesson l1 = new Lesson(System.currentTimeMillis() +ctr+3, "Lesson 1");
		Lesson l2 = new Lesson(System.currentTimeMillis() +ctr+4, "Lesson 2");
		Topic t1 = new Topic(System.currentTimeMillis() +ctr+5,"Topic a");
		Topic t2 = new Topic(System.currentTimeMillis() +ctr+6,"Topic b");
		List<Module> module1 = new ArrayList<Module>();
		
		List<Topic> topics1 = new ArrayList<Topic>();
		List<Topic> topics2 = new ArrayList<Topic>();
		topics1.add(t1);
		topics2.add(t2);
		l1.setTopics(topics1);
		l2.setTopics(topics2);
		List<Lesson> lesson1 = new ArrayList<Lesson>();
		List<Lesson> lesson2 = new ArrayList<Lesson>();
		lesson1.add(l1);
		lesson2.add(l2);
		m1.setLessons(lesson1);
		m2.setLessons(lesson2);
		module1.add(m1);
		module1.add(m2);
		
		course.setModules(module1);
		if ( courses.isEmpty() || !(courses.contains(course)) ) {
			
			courses.add(course);
			return course;
		} else {
			
			return null;
		}
	}

	/* Retrieves course whose id is cid */
	@GetMapping("/api/course/{cid}")
	public Course findCourseById(@PathVariable("cid") long cid, HttpSession session) {
		User currentUser = (User) session.getAttribute("currentUser");
		courses = currentUser.getCourses();
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
	public Course updateCourse(@PathVariable("cid") long cid, @RequestBody Course course, HttpSession session) {
		User currentUser = (User) session.getAttribute("currentUser");
		courses = currentUser.getCourses();
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
	public void deleteCourse(@PathVariable("cid") long cid,HttpSession session) {
		User currentUser = (User) session.getAttribute("currentUser");
		courses = currentUser.getCourses();
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
