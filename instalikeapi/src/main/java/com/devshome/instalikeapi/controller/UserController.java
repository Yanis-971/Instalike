package com.devshome.instalikeapi.controller;

import java.io.IOException;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.devshome.instalikeapi.model.Post;
import com.devshome.instalikeapi.model.User;
import com.devshome.instalikeapi.service.PostService;
import com.devshome.instalikeapi.service.UserService;

@RestController
@RequestMapping("/User")
public class UserController {
	
	@Autowired
	private final UserService userService;
	
	@Autowired
	private final PostService postService;
	
	public UserController(UserService userService, PostService postService) {
		super();
		this.userService = userService;
		this.postService = postService;
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
		User u = userService.findUser(id);
		
		return u;
	}
	
	//Get user by Username and Password
	@PostMapping
	public User findUserLog(@RequestBody User user) {
		return userService.findUserLog(user);
	}
	
	//Update User
	@PutMapping(value ="/update/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	public User updateUser(@PathVariable("id") Long id, @RequestPart("user") String userJson, @RequestPart("image") MultipartFile image){
		
		User user = userService.getJson(userJson,image);
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
				u.setImageUrl(user.getImageUrl());
				
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
	
	//Add a Post (Works for update too)
	@PostMapping("/Post/{iduser}")
	public User addPost(@PathVariable("iduser") Long id,@RequestPart("post") String stringPost, @RequestPart("image") MultipartFile file ) throws IOException {
		User user = userService.findUser(id);
		Post post = postService.getJson(user,stringPost, file);
		return userService.addPost(user, post);
		
	}

}
