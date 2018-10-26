package com.example.whiteboardfall2018serverjava.models;

import java.util.ArrayList;
import java.util.List;

public class Module {
	  private long id;
	  private String title;
	  private List<Lesson> lessons = new ArrayList<Lesson>();
	  
	public Module(long i, String string) {
		id = i; title = string;
	}
	public Module() {}
	public long getId() {
		return id;
	}
	public List<Lesson> getLessons() {
		return lessons;
	}
	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	  
}
