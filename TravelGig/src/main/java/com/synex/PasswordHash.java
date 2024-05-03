package com.synex;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHash {

	public static void main(String[] args) {
		testBCryptHash();

	}

	private static void testBCryptHash() {
		String password = "steve";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		System.out.println(hashedPassword);
		
	}

}
