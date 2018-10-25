package com.example.whiteboardfall2018serverjava.models;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String userId;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private List<Course> courses = new ArrayList<Course>();
	public String getUserId() {
		
		return userId;
	}
	public void setUserId(String userId) {
		
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
}
