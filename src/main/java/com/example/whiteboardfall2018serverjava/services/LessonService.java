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
import com.example.whiteboardfall2018serverjava.models.Lesson;
import com.example.whiteboardfall2018serverjava.models.Module;

@RestController
@CrossOrigin(origins = "https://shrouded-cove-74042.herokuapp.com/" , allowCredentials = "true" , allowedHeaders = "*")
public class LessonService {
	@Autowired
	ModuleService moduleService;
	@Autowired
	CourseService courseService;
	private List<Lesson> lessons = new ArrayList<Lesson>();

	@GetMapping("/api/module/{mId}/lesson")
	public List<Lesson> findLessonsForModule(@PathVariable("mId") long mId, HttpSession session) {
		Module module = moduleService.findModuleById(mId, session);
		if (lessons.isEmpty()) {
			Lesson l1 = new Lesson(System.currentTimeMillis(), "Lesson 1");
			Lesson l2 = new Lesson(System.currentTimeMillis(), "Lesson 2");
			TopicService t1 =  new TopicService();
			t1.findAllTopics(l1.getId(), session);
		
			lessons.add(l1);
			TopicService t2 =  new TopicService();
			t2.findAllTopics(l2.getId(), session);
			lessons.add(l2);
			module.setLessons(lessons);
			return lessons;
		}
		lessons = module.getLessons();
		return lessons;
	}

	@PostMapping("/api/module/{mId}/lesson")
	public List<Lesson> createModule(@RequestBody Lesson lesson, @PathVariable("mId") long mId, HttpSession session) {
		Module module = moduleService.findModuleById(mId, session);
		lessons = module.getLessons();
		lesson.setId(System.currentTimeMillis());
		lessons.add(lesson);
		return lessons;
	}

	/* Retrieves Lesson whose id is lid */
	@GetMapping("/api/lesson/{lid}")
	public Lesson findLessonById(@PathVariable("lid") long lid, HttpSession session) {
		List<Course> courses = courseService.findAllCourses(session);
		List<Module> modules = new ArrayList<Module>();
		Lesson reqdLesson = null;
		for (Course c : courses) {
			modules = c.getModules();
			for (Module m : modules) {
				lessons = m.getLessons();
				for (Lesson l : lessons) {
					if (l.getId() == lid) {
						reqdLesson = l;
						break;
					}
				}

			}
		}
		return reqdLesson;
		
	}

	@PutMapping("/api/lesson/{lid}")
	public Lesson updateLesson(@PathVariable("lid") long lid, @RequestBody Lesson lesson, HttpSession session) {
		List<Course> courses = new ArrayList<Course>(courseService.findAllCourses(session));
		List<Module> modules = new ArrayList<Module>();

		for (Course c : courses) {
			modules = c.getModules();
			for (Module m : modules) {
				lessons = m.getLessons();
				int ctr = 0;
				for (Lesson l : lessons) {
					if (l.getId() == lid) {
						lessons.set(ctr, lesson);
						return lessons.get(ctr);
					}
					ctr++;
				}

			}
		}
		return null;
	}

	/* Deletes Lesson whose id is lid */
	@DeleteMapping("/api/lesson/{lid}")
	public void deleteModule(@PathVariable("lid") long lid, HttpSession session) {
		List<Course> courses = courseService.findAllCourses(session);
		List<Module> modules = new ArrayList<Module>();
		Lesson reqdLesson = null;
		for (Course c : courses) {
			modules = c.getModules();
			for (Module m : modules) {
				lessons = m.getLessons();
				for (Lesson l : lessons) {
					if (l.getId() == lid) {
						reqdLesson = l;
						break;
					}
				}

			}
		}
		if (reqdLesson != null) {
			for (Course c : courses) {
				modules = c.getModules();
				{
					for (Module m : modules) {
						lessons = m.getLessons();
						List<Lesson> resultLessons = (List<Lesson>) lessons.stream().filter(x -> x.getId() != lid)
								.collect(Collectors.toList());
						m.setLessons(resultLessons);
					}
				}
			}
		}
	}
}