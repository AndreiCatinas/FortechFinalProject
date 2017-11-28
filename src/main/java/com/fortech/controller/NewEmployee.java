package com.fortech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fortech.entity.Employee;
import com.fortech.service.EmployeeService;

@RestController
public class NewEmployee {

	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/add")
	public ModelAndView employeeForm(ModelAndView mav) {
		mav.setViewName("registration");
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public ModelAndView addEmployee(ModelAndView mav ,@RequestBody Employee employee) {
		mav.setViewName("newemployee");
		employeeService.addEmployee(employee);
		return mav;
	}
}
