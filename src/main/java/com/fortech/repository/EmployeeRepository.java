package com.fortech.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fortech.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

	public Employee findOneByEmail(String email);
	public List<Employee> findByActive(Boolean active);
	public Employee findByUserUsername(String username);
}
