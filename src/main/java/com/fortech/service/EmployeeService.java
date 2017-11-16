package com.fortech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fortech.entity.Employee;
import com.fortech.entity.User;
import com.fortech.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	UserService userService;

	public List<Employee> getAll() {
		List<Employee> employees = new ArrayList<>();
		employeeRepository.findAll().forEach(employees::add);
		return employees;
	}

	public Employee getEmployee(String email) {
		return employeeRepository.findOneByEmail(email);
	}

	public void addEmployee(Employee employee) {
		User user = new User(employee.getEmail(), "user");
		userService.addUser(user);
		employee.setUser(user);
		employeeRepository.save(employee);
	}

	public void deleteEmployee(String email) {
		employeeRepository.delete(getEmployee(email));
	}

	public void updateEmployee(Employee oldEmployee, Employee newEmployee) {

		if (oldEmployee.getFirstname() != null) {
			newEmployee.setFirstname(oldEmployee.getFirstname());
		}
		if (oldEmployee.getLastname() != null) {
			newEmployee.setLastname(oldEmployee.getLastname());
		}
		if (oldEmployee.getDepartment() != null) {
			newEmployee.setDepartment(oldEmployee.getDepartment());
		}
		if (oldEmployee.getEmail() != null) {
			newEmployee.setEmail(oldEmployee.getEmail());
		}
		if (oldEmployee.getPhone() != null) {
			newEmployee.setPhone(oldEmployee.getPhone());
		}
		newEmployee.setActive(oldEmployee.isActive());
		employeeRepository.save(newEmployee);
	}
}
