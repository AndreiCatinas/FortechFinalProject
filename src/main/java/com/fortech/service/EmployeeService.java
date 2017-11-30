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

	/**
	 * 
	 * @return List<Employee> of all employees
	 */
	public List<Employee> getAll() {
		List<Employee> employees = new ArrayList<>();
		employeeRepository.findAll().forEach(employees::add);
		return employees;
	}
	
	/**
	 * 
	 * @param active
	 * @return List<Employees> of active or inactive employees
	 */
	public List<Employee> getAllActive(Boolean active) {
		List<Employee> employees = new ArrayList<>();
		employeeRepository.findByActive(active).forEach(employees::add);
		return employees;
	}
	
	/**
	 * 
	 * @param id
	 * @return Employee by id
	 */
	public Employee getEmployee(Integer id) {
		return employeeRepository.findOne(id);
	}

	/**
	 * 
	 * @param email
	 * @return Employee by email
	 */
	public Employee getEmployee(String email) {
		return employeeRepository.findOneByEmail(email);
	}

	/**
	 * Add employee
	 * @param employee
	 */
	public void addEmployee(Employee employee) {
		employeeRepository.save(employee);
	}
	
	/**
	 * Delete employee
	 * @param employee
	 */
	public void deleteEmployee(Employee employee) {
		employee.setActive(false);
		employeeRepository.save(employee);
	}

	/**
	 * Update employee
	 * @param employee
	 */
	public void updateEmployee(Employee employee) {
		employeeRepository.save(employee);
	}
}
