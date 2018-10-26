package com.example.whiteboardfall2018serverjava.models;


public class Topic {
	private long id;
	private String title;

	public Topic(long i, String string) {
		id = i;
		title = string;
	}

	public Topic() {
	}

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

}
