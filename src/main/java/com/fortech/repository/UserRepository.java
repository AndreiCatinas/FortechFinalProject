package com.fortech.repository;

import org.springframework.data.repository.CrudRepository;

import com.fortech.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	public User findByUsername(String username);

}
