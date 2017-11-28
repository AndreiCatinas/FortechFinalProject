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

	public Token getToken(Integer id) {
		return tokenRep.findOne(id);
	}
	
	public Token getToken(String token) {
		return tokenRep.findByToken(token);
	}
	
	public Token getToken(User user) {
		return tokenRep.findByUser(user);
	}
	
	public void addToken(Token token) {
		tokenRep.save(token);
	}
	
	public void deleteToken(Token token) {
		tokenRep.delete(token);
	}

}
