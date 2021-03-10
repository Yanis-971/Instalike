package com.devshome.instalikeapi.controller;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

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
	
	//get user
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
	@PutMapping("/update/{id}")
	public User updateUser(@PathVariable("id") Long id, @RequestBody User user, @RequestParam("file") MultipartFile image) throws IOException {
		User u = userService.findUser(id);
		if(u != null) {	
			if(user.getUsername() != null) {
				u.setUsername(user.getUsername());
			}
			if(user.getMail() != null) {
				u.setMail(user.getMail());
			}
			if(user.getPassword() != null) {
				u.setPassword(user.getPassword());
			}
			if(image != null) {
				u.setImageUrl(image.getBytes());
				
			}
			
			return userService.updateUser(u);
			
		}
		else {
			return user;
		}
		
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
