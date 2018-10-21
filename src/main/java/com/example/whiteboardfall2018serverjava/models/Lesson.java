package com.example.whiteboardfall2018serverjava.models;

import java.util.ArrayList;
import java.util.List;

public class Lesson {
	private int id;
	private String title;

	/*
	 * private List<Topic> lessons = new ArrayList<Lesson>();
	 * 
	 * 
	 * public List<Lesson> getLessons() { return lessons; } public void
	 * setLessons(List<Lesson> lessons) { this.lessons = lessons; }
	 */
	public Lesson(int i, String string) {
			id = i; title = string;
		}

	public Lesson() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
