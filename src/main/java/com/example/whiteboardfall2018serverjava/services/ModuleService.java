package com.example.whiteboardfall2018serverjava.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public List<Module> findModulesForCourse(@PathVariable("courseId") int courseId) {
		Course course = courseService.findCourseById(courseId);
		if (modules.isEmpty()) {
			Module m1 = new Module(123, "Module 1");
			Module m2 = new Module(234, "Module 2");
			modules.add(m1);
			modules.add(m2);
			course.setModules(modules);
			return modules;
		}

		modules = course.getModules();

		return modules;
	}

	@PostMapping("/api/course/{courseId}/module")
	public List<Module> createModule(@RequestBody Module module, @PathVariable("courseId") int courseId) {
		Course course = courseService.findCourseById(courseId);
		modules = course.getModules();
		int uId = (int) System.currentTimeMillis();
		module.setId(uId);
		modules.add(module);
		return modules;
	}

	/* Retrieves Module whose id is mid */
	@GetMapping("/api/module/{mid}")
	public Module findModuleById(@PathVariable("mid") int mid, HttpSession session) {
		List<Course> courses = courseService.findAllCourses(session);
		Module reqdModule = null;
		for (Course c : courses) {
			modules = c.getModules();
			for (Module m : modules) {
				if (m.getId() == mid) {
					reqdModule = m;
					break;
				}
			}
		}
		return reqdModule;
	}

	/* Updates Module whose id is mid */
	@PutMapping("/api/module/{mid}")
	public Module updateCourse(@PathVariable("mid") int mid, @RequestBody Module module, HttpSession session) {

		List<Course> courses = courseService.findAllCourses(session);
		int ctr = 0;
		module.setId(mid);
		for (Course c : courses) {
			modules = c.getModules();
			for (Module m : modules) {
				if (m.getId() == mid) {
					modules.set(ctr, module);
					break;
				}
				ctr++;
			}
			return modules.get(ctr);

		}
		return null;

	}
	/* Deletes Module whose id is mid */
	@DeleteMapping("/api/module/{mid}")
	public void deleteModule(@PathVariable("mid") int mid, HttpSession session) {
		List<Course> courses = courseService.findAllCourses(session);
		Module reqdModule = null;
		for (Course c : courses) {
			modules = c.getModules();
			for (Module m : modules) {
				if (m.getId() == mid) {
					reqdModule = m;
					break;
				}
			}
		}
		if(reqdModule!=null)
		{
			
			for(Course c: courses)
			{
				modules = c.getModules();
				List<Module> resultModules = (List<Module>) modules.stream().filter(x -> x.getId() != mid)
						.collect(Collectors.toList());
				c.setModules(resultModules);
			}
		}
	}
}