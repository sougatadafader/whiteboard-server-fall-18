package com.example.whiteboardfall2018serverjava.models;

import java.util.ArrayList;
import java.util.List;

public class Course {
	  private long id;
	  private String title;
	  private List<Module> modules = new ArrayList<Module>();
	  
	public Course(long i, String string) {
		setId(i);
		setTitle(string);
	}
	public Course() {}
	public long getId() {
		return id;
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
	public List<Module> getModules() {
		return modules;
	}
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	  
}


