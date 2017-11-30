package com.fortech.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fortech.entity.Token;
import com.fortech.entity.User;

@Repository
public interface TokenRepository extends CrudRepository<Token, Integer> {

	public Token findByToken(String token);
	public Token findByUser(User user);
}
