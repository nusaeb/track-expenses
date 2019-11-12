package com.trackexpenses.user.exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String userId) {
		super("User not found! Id: " + userId);
	}
}
