package com.fortech.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fortech.entity.Employee;
import com.fortech.entity.Token;
import com.fortech.entity.User;
import com.fortech.service.EmailService;
import com.fortech.service.EmployeeService;
import com.fortech.service.TokenService;
import com.fortech.service.UserService;
import com.fortech.utility.UserProcessor;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private TokenService tokenService;

	/*
	 * Get all users
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/users/all")
	public ModelAndView getAllUsers(ModelAndView modelAndView) {
		modelAndView.getModel().put("users", userService.getAll());
		modelAndView.setViewName("/users/allUsers");
		return modelAndView;
	}

	/*
	 * Get all active users
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/users/active")
	public ModelAndView getAllActiveUsers(ModelAndView modelAndView) {
		modelAndView.getModel().put("users", userService.getAllActive());
		modelAndView.setViewName("/users/allUsers");
		return modelAndView;
	}

	/*
	 * Get all inactive users
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/users/inactive")
	public ModelAndView getAllInactiveUsers(ModelAndView modelAndView) {
		modelAndView.getModel().put("users", userService.getAllInactive());
		modelAndView.setViewName("/users/allUsers");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/users/add/{employeeId}")
	public ModelAndView addUserForm(ModelAndView modelAndView, @PathVariable Integer employeeId) {
		modelAndView.getModel().put("employee", employeeId);
		modelAndView.getModel().put("user", new User());
		modelAndView.setViewName("/users/addUser");
		return modelAndView;
	}
	
	/*
	 * Add user to employee
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/users/add/{employeeId}")
	public ModelAndView addUser(ModelAndView modelAndView, @ModelAttribute User user,
			@PathVariable Integer employeeId) {
		user.setPassword(UserProcessor.generatePassword(8));
		userService.addUser(user);
		Employee employee = employeeService.getEmployee(employeeId);
		employee.setUser(user);
		employeeService.updateEmployee(employee);
		Token token = new Token();
		token.setUser(user);
		tokenService.addToken(token);
		emailService.sendRecoveryEmail(employee.getEmail(), token.getToken());
		modelAndView.setViewName("redirect:/users/active");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/users/add/")
	public ModelAndView adminAddUserForm(ModelAndView modelAndView) {
		modelAndView.getModel().put("user", new User());
		modelAndView.setViewName("/users/addUser");
		return modelAndView;
	}
	
	/*
	 * Add user
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/users/add/")
	public ModelAndView adminAddUser(ModelAndView modelAndView, @Valid @ModelAttribute User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("users/addUser");
			return modelAndView;
		}
		user.setPassword(UserProcessor.generatePassword(8));
		userService.addUser(user);
		modelAndView.setViewName("redirect:/users/active");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/users/update/{id}")
	public ModelAndView updateUserView(ModelAndView modelAndView, @PathVariable Integer id) {
		modelAndView.getModel().put("user", userService.getUser(id));
		modelAndView.setViewName("/users/updateUser");
		return modelAndView;
	}

	/*
	 * Update user
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/users/update/{id}")
	public ModelAndView updateUser(ModelAndView modelAndView, @ModelAttribute User user) {
		userService.updateUser(user);
		modelAndView.setViewName("redirect:/users/all");
		return modelAndView;
	}

	/*
	 * Delete user by id
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/users/delete/{id}")
	public ModelAndView deleteUser(ModelAndView modelAndView, @PathVariable Integer id) {
		userService.deleteUser(id);
		modelAndView.setViewName("redirect:/users/all");
		return modelAndView;
	}

}
