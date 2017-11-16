package com.fortech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fortech.entity.User;
import com.fortech.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET, value = "/user")
	public List<User> getAllUsers() {
		return userService.getAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/user/{username}")
	public User getUser(@PathVariable String email) {
		return userService.getUser(email);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/user")
	public void addUser(@RequestBody User user) {
		userService.addUser(user);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/user/{username}")
	public void updateUser(@RequestBody User user, @PathVariable String username) {
		userService.updateUser(user, userService.getUser(username));
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "user/{username}")
	public void deleteUser(@PathVariable String username) {
		userService.deleteUser(username);
	}
}
