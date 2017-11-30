package com.fortech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fortech.entity.User;
import com.fortech.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * 
	 * @return List<User> of all users existent
	 */
	public List<User> getAll() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

	/**
	 * 
	 * @return List<User> of users set to active == true
	 */
	public List<User> getAllActive() {
		List<User> users = new ArrayList<>();
		userRepository.findByRole("ROLE_ADMIN").forEach(users::add);
		userRepository.findByRole("ROLE_USER").forEach(users::add);
		return users;
	}

	/**
	 * 
	 * @return List<User> of users set to active == false
	 */
	public List<User> getAllInactive() {
		List<User> users = new ArrayList<>();
		userRepository.findByRole("ROLE_INACTIVE").forEach(users::add);
		return users;
	}

	/**
	 * 
	 * @param username
	 *            - find by
	 * @return User
	 */
	public User getUser(String username) {
		return userRepository.findByUsername(username);
	}

	/**
	 * 
	 * @param id
	 *            - find by
	 * @return User
	 */
	public User getUser(Integer id) {
		return userRepository.findOne(id);
	}

	/**
	 * 
	 * @param user
	 *            - add and set inactive
	 */
	public void registerUser(User user) {
		user.setRole("ROLE_INACTIVE");
		userRepository.save(user);
	}

	/**
	 * 
	 * @param user
	 *            - add to database
	 */
	public void addUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);

	}

	/**
	 * 
	 * @param id
	 *            - id of user to delete
	 */
	public void deleteUser(Integer id) {
		userRepository.delete(id);
	}

	/**
	 * 
	 * @param user
	 *            - user to update
	 */
	public void updateUser(User user) {
		User toUpdate = userRepository.findOne(user.getId());
		if (user.getPassword() != null) {
			toUpdate.setPassword(user.getPassword());
		}
		if (user.getUsername() != null) {
			toUpdate.setUsername(user.getUsername());
		}
		if (user.getRole() != null) {
			toUpdate.setRole(user.getRole());
		}
		userRepository.save(toUpdate);
	}

	/**
	 * Spring security
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

		if (user == null) {
			return null;
		}

		List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole());
		String password = user.getPassword();

		return new org.springframework.security.core.userdetails.User(username, password, auth);
	}
}
