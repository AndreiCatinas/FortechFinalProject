package com.fortech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fortech.entity.Token;
import com.fortech.entity.User;
import com.fortech.repository.TokenRepository;

@Service
public class TokenService {

	@Autowired
	private TokenRepository tokenRep;

	/**
	 * 
	 * @param id
	 * @return Token by id
	 */
	public Token getToken(Integer id) {
		return tokenRep.findOne(id);
	}
	
	/**
	 * 
	 * @param token
	 * @return Token by token string
 	 */
	public Token getToken(String token) {
		return tokenRep.findByToken(token);
	}
	
	/**
	 * 
	 * @param user
	 * @return Token by user
	 */
	public Token getToken(User user) {
		return tokenRep.findByUser(user);
	}
	
	/**
	 * Create new token
	 * 
	 * @param token
	 */
	public void addToken(Token token) {
		tokenRep.save(token);
	}
	
	/**
	 * Delete token
	 * 
	 * @param token
	 */
	public void deleteToken(Token token) {
		tokenRep.delete(token);
	}
}
