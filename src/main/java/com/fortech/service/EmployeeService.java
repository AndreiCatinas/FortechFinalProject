package com.fortech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fortech.entity.Employee;
import com.fortech.repository.EmployeeRepository;
import com.fortech.utility.UserProcessor;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	UserService userService;
	@Autowired
	UserProcessor userProc;

	public List<Employee> getAll() {
		List<Employee> employees = new ArrayList<>();
		employeeRepository.findAll().forEach(employees::add);
		return employees;
	}
	
	public List<Employee> getAllActive(Boolean active) {
		List<Employee> employees = new ArrayList<>();
		employeeRepository.findByActive(active).forEach(employees::add);
		return employees;
	}
	
	public Employee getEmployee(Integer id) {
		return employeeRepository.findOne(id);
	}

	public Employee getEmployee(String email) {
		return employeeRepository.findOneByEmail(email);
	}

	public void addEmployee(Employee employee) {
	/*	User user = new User(employee.getEmail(), "user");
		userService.addUser(user);
		employee.setUser(user);*/
		employeeRepository.save(employee);
	}
	
	public void deleteEmployee(Employee employee) {
		employee.setActive(false);
		employeeRepository.save(employee);
	}

	public void updateEmployee(Employee employee) {
/*		User user = userService.getUser(employee.getEmail());
		employee.setUser(user);*/
		employeeRepository.save(employee);
	}
}
