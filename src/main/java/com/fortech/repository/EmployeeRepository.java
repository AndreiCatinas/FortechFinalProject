package com.fortech.repository;

import org.springframework.data.repository.CrudRepository;

import com.fortech.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

	public Employee findOneByEmail(String email);

}
