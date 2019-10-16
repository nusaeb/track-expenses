package com.trackexpenses.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<UserInfo> getAllUsers(){
		List<UserInfo> users = new ArrayList<UserInfo>();
		userRepository.findAll().forEach(users::add);
		return users;
	}
	
	public UserInfo getUser(String userId) {		
		return userRepository.findById(userId).get();
	}
	
	public void addUser(UserInfo user) {
		userRepository.save(user);
	}
	
	public void updateUser(UserInfo user) {
		userRepository.save(user);
	}
	
	public void deleteUser(String userId) {
		userRepository.deleteById(userId);
	}

}
