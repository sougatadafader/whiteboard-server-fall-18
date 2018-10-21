package com.example.whiteboardfall2018serverjava.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.whiteboardfall2018serverjava.models.Course;
import com.example.whiteboardfall2018serverjava.models.Module;
import com.example.whiteboardfall2018serverjava.models.User;

@RestController
@CrossOrigin(origins = "*")
public class ModuleService {
	@Autowired
	CourseService courseService;
	private List<Module> modules = new ArrayList<Module>();
	@GetMapping("/api/course/{courseId}/module")
	public List<Module> findModulesForCourse(
			@PathVariable("courseId") int courseId) {
		Course course = courseService.findCourseById(courseId);
		if(modules.isEmpty())
		{
			Module m1 = new Module(123, "Module 1");
			Module m2 = new Module(234, "Module 2");
			modules.add(m1);
			modules.add(m2);
			course.setModules(modules);
			return modules;
		}
			
		modules.addAll(course.getModules());
		
		return modules;
	}
	
	@PostMapping("/api/course/{courseId}/module")
	public List<Module> createModule(@RequestBody Module module, @PathVariable("courseId") int courseId) {
		Course course = courseService.findCourseById(courseId);
		modules = course.getModules();
		int uId= (int)System.currentTimeMillis();
		module.setId(uId);
		modules.add(module);
		return modules;
	}
}