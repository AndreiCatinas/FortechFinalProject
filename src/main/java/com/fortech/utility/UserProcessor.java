package com.fortech.utility;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

import org.apache.commons.text.RandomStringGenerator;
import org.springframework.stereotype.Component;

@Component
public class UserProcessor {
	
	/**
	 * 
	 * @param length
	 * @return Random generated password 
	 */
	public static String generatePassword(Integer length) {
		RandomStringGenerator generator = new RandomStringGenerator.Builder()
		        .withinRange('0', 'z')
		        .filteredBy(LETTERS, DIGITS)
		        .build();
		return generator.generate(length);
	}
}
