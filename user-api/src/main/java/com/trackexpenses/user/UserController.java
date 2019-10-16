package com.trackexpenses.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/users")
	public List<UserInfo> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@RequestMapping("/users/{userId}")
	public UserInfo getUser(@PathVariable String userId) {
		return userService.getUser(userId);
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/users")
	public void addUser(@RequestBody UserInfo user) {
		userService.addUser(user);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/users/{userId}")
	public void updateUser(@RequestBody UserInfo user) {
		userService.updateUser(user);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/users/{userId}")
	public void deleteUser(@PathVariable String userId) {
		userService.deleteUser(userId);
	}

}
