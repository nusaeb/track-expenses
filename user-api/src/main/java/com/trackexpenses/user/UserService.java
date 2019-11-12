package com.trackexpenses.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.trackexpenses.user.exception.UserNotFoundException;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<UserInfo> getAllUsers(){
		List<UserInfo> users = new ArrayList<UserInfo>();
		userRepository.findAll().forEach(users::add);
		return users;
	}
	
	public UserInfo getUser(String userId) {
		Objects.requireNonNull(userId, "Cannot find user with null id!");
		return userRepository.findById(userId)
				.orElseThrow(
						() -> new UserNotFoundException(userId));
	}
	
	public UserInfo addUser(UserInfo user) {
		Objects.requireNonNull(user, "Cannot add null user!");
		return userRepository.save(user);
	}
	
	public UserInfo updateUser(UserInfo user) {
		Objects.requireNonNull(user, "Cannot update null user!");
		return userRepository.save(user);
	}
	
	public String deleteUser(String userId) {
		Objects.requireNonNull(userId, "Cannot delete user with null id!");
		
		try {
			userRepository.deleteById(userId);
			return "user deleted";		
		}
		catch(EmptyResultDataAccessException ex) {
			throw new UserNotFoundException(userId);
		}
	}

}
