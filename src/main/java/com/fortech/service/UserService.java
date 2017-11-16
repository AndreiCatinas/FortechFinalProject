package com.fortech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fortech.entity.User;
import com.fortech.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAll() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

	public User getUser(String username) {
		return userRepository.findByUsername(username);
	}

	public void addUser(User user) {
		userRepository.save(user);
	}

	public void deleteUser(String username) {
		userRepository.delete(getUser(username));
	}

	public void updateUser(User oldUser, User newUser) {

		if (oldUser.getUsername() != null) {
			newUser.setUsername(oldUser.getUsername());
		}
		if (oldUser.getPassword() != null) {
			newUser.setPassword(oldUser.getPassword());
		}
		if (oldUser.getRole() != null) {
			newUser.setRole(oldUser.getRole());
		}
		newUser.setActive(oldUser.isActive());
		userRepository.save(newUser);
	}
}
