package com.devshome.instalikeapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devshome.instalikeapi.model.User;
import com.devshome.instalikeapi.service.UserService;

@RestController
@RequestMapping("/User")
public class UserController {
	
	@Autowired
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	//add user
	@PostMapping("/add")
	public User addUser(@RequestBody User user){
		return userService.addUser(user);
	}
	
	//get all user
	@GetMapping("/all")
	public List<User> findallUsers(){
		return userService.findAllUsers();
	}
	
	//get all user
	@GetMapping("/{id}")
	public User findUser(@PathVariable("id") Long id ) {
		return userService.findUser(id);
	}
	//Get user by Username and Password
	@PostMapping
	public User findUserLog(@RequestBody User user) {
		return userService.findUserLog(user);
	}
	
	//Update User
	@PutMapping("/update")
	public User updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}
	
	//Delete User
	@DeleteMapping("/delete/{id}")
	public void deleteUser(@PathVariable("id") Long id) {
		userService.deleteUser(id);
	}
	
	//Add a friend
	@PostMapping("/Friends")
    public User addFriendApi(@RequestParam("id1") Long x,@RequestParam("id2") Long y){
        return userService.addFriend(x,y);
    }
	
	

}
