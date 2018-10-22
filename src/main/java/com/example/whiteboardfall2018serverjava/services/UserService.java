package com.example.whiteboardfall2018serverjava.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.whiteboardfall2018serverjava.models.User;

@RestController
public class UserService {
	
	List<User> users = new ArrayList<User>();
	/*Return a list of all the users*/
	@GetMapping("/api/user")
	public List<User> findAllUsers() {
		return users;
	}
	
	/*Return a user with user.id equal to id parameter*/
	@GetMapping("/api/user/{id}")
	public User findUserById(@PathVariable ("id") String id) {
		User reqdUser = null;
		for(User u : users)
		{
			if(u.getUserId().equals(id))
			{
				reqdUser = u;
			}
		}
		return reqdUser;
	}
	
	/*
	If user.username does not already exist, 
	then add the user to the list of registered users 
	and make the user the current user */
	
	@PostMapping("/api/register")
	public User register(
			@RequestBody User user,
			HttpSession session) {
		
		for(User u : users)
		{
			if(u.getUsername().equals(user.getUsername()))
			{
				return null;
			}
		}
		String uId= System.currentTimeMillis()+"";
		user.setUserId(uId);
		session.setAttribute("currentUser", user);
		users.add(user);
		return user;
	}
	
	/* Return the current user */
	
	@GetMapping("/api/profile")
	public User profile(HttpSession session) {
		User currentUser = (User)session.getAttribute("currentUser");	
		return currentUser;
	}
	
	/* Remove the current user from the session */
	@PostMapping("/api/logout")
	public void logout(HttpSession session) {
		session.invalidate();
	}
	
	/* If user.username and password exist
	  then make the user the current user */
	@PostMapping("/api/login")
	public User login(
			@RequestBody User credentials,
			HttpSession session) {
	 for (User user : users) {
	  if( user.getUsername().equals(credentials.getUsername())
	   && user.getPassword().equals(credentials.getPassword())) {
	    session.setAttribute("currentUser", user);
	    return user;
	  }
	 }
	 return null;
	}
}
