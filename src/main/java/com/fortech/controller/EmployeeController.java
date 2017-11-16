package com.fortech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fortech.entity.Employee;
import com.fortech.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(method = RequestMethod.GET, value = "/employee")
	public List<Employee> getAllEmployees() {
		return employeeService.getAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/employee/{email:.+}")
	public Employee getEmployee(@PathVariable String email) {
		return employeeService.getEmployee(email);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/employee")
	public void addEmployee(@RequestBody Employee employee) {
		employeeService.addEmployee(employee);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/employee/{email:.+}")
	public void updateEmployee(@RequestBody Employee employee, @PathVariable String email) {
		employeeService.updateEmployee(employee, employeeService.getEmployee(email));
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "employee/{email:.+}")
	public void deleteEmployee(@PathVariable String email) {
		employeeService.deleteEmployee(email);
	}
}
