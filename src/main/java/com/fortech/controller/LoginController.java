package com.fortech.controller;

import javax.servlet.http.HttpServletResponse;
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

@RestController
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	EmailService emailService;

	
	/**
	 * Login page
	 * 
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public ModelAndView login(ModelAndView modelAndView) {
		modelAndView.getModel().put("user", new User());
		modelAndView.setViewName("login");
		return modelAndView;
	}

	/**
	 * Registration page
	 * 
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/register")
	public ModelAndView register(ModelAndView modelAndView) {
		modelAndView.getModel().put("user", new User());
		modelAndView.setViewName("/users/registerUser");
		return modelAndView;
	}

	/**
	 * Register user
	 * 
	 * @param modelAndView
	 * @param user
	 * @param bindingResult
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public ModelAndView saveUser(ModelAndView modelAndView, @ModelAttribute @Valid User user,
			BindingResult bindingResult, HttpServletResponse response){
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("/users/registerUser");
			return modelAndView;
		}
		userService.registerUser(user);
		modelAndView.setViewName("redirect:/login");
		return modelAndView;
	}

	/**
	 * Home page
	 * 
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/home")
	public ModelAndView home(ModelAndView modelAndView) {
		modelAndView.setViewName("home");
		return modelAndView;
	}

	/**
	 * Recover password page
	 * 
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/recover")
	public ModelAndView recoverPasswordForm(ModelAndView modelAndView) {
		modelAndView.getModel().put("user", new User());
		modelAndView.setViewName("/users/recover");
		return modelAndView;
	}

	/**
	 * Creates password reset token for user, and sends email with recovery link
	 * 
	 * @param modelAndView
	 * @param user
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/recover")
	public ModelAndView recoverPassword(ModelAndView modelAndView, @ModelAttribute User user) {
		Employee employee;
		User tokenUser;
		modelAndView.setViewName("/users/recover");
		try {
			employee = employeeService.getEmployee(user.getUsername());
			tokenUser = employee.getUser();
		} catch (NullPointerException e) {
			modelAndView.getModel().put("exists", false);
			return modelAndView;
		}
		Token token = new Token();
		token.setUser(tokenUser);
		tokenService.addToken(token);
		emailService.sendRecoveryEmail(employee.getEmail(), token.getToken());
		modelAndView.getModel().put("exists", true);
		modelAndView.getModel().put("email", user.getUsername());
		return modelAndView;
	}

	/**
	 * Password reset page
	 * 
	 * @param modelAndView
	 * @param tokenStr
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/reset/{tokenStr}")
	public ModelAndView resetPasswordForm(ModelAndView modelAndView, @PathVariable String tokenStr) {
		Token token = tokenService.getToken(tokenStr);
		if (token == null) {
			modelAndView.setViewName("redirect:/notfound");
			return modelAndView;
		}
		User user = token.getUser();
		System.out.println(user.getUsername());
		modelAndView.getModel().put("user", user);
		modelAndView.setViewName("/users/resetPassword");
		return modelAndView;
	}

	/**
	 * Password reset
	 * 
	 * @param modelAndView
	 * @param user
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/reset")
	public ModelAndView resetPassword(ModelAndView modelAndView, @ModelAttribute @Valid User user,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("/users/resetPassword");
			return modelAndView;
		}
		userService.updateUser(user);
		tokenService.deleteToken(tokenService.getToken(user));
		modelAndView.setViewName("redirect:/login");
		return modelAndView;
	}
}
