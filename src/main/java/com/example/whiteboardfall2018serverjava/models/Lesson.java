package com.example.whiteboardfall2018serverjava.models;

import java.util.ArrayList;
import java.util.List;

public class Lesson {
	private long id;
	private String title;

	private List<Topic> topics = new ArrayList<Topic>();

	public List<Topic> getTopics() {
		return topics;
	}
	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	public Lesson(long i, String string) {
		id = i;
		title = string;
	}

	public Lesson() {
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
