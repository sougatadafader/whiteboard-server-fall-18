package com.example.whiteboardfall2018serverjava.models;

public class Course {
	  private int id;
	  private String title;
	  private User author;
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public Course(int i, String string) {
		id = i; title = string;
	}
	public Course() {}
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
