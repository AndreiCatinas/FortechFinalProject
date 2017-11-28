package com.fortech.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.fortech.entity.User;

/*
 * PasswordMatch - annotation
 * User - what it validates
 */
public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, User>{

	@Override
	public void initialize(PasswordMatch arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isValid(User user, ConstraintValidatorContext cVc) {
		String plainPassword = user.getPlainPassword();
		String repeatPassword = user.getRepeatPassword();
		
		if (plainPassword == null || plainPassword.length() == 0) {
			return true;
		}
		
		if (plainPassword == null || !plainPassword.equals(repeatPassword)) {
			return false;
		}
		
		return true;
	}

}
