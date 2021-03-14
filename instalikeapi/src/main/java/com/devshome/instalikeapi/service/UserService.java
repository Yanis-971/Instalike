package com.devshome.instalikeapi.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.devshome.instalikeapi.model.Post;
import com.devshome.instalikeapi.model.User;
import com.devshome.instalikeapi.repository.UserRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Service
public class UserService {
	
	@Autowired
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	//Add User
	public User addUser(User user) {
		return userRepository.save(user);
	}
	
	//Find all Users
	public List<User> findAllUsers(){
		return userRepository.findAll();
	}
	
	//Find User
	public User findUser(Long id) {
		return userRepository.findUserById(id);
	}
	
	//Find User by username and password
	public User findUserLog(User user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
		
	}
	
	//Update User
	public User updateUser(User user) {
		return userRepository.save(user);
	}
	
	//Delete User
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
	
	//Add Friend
	public User addFriend(Long x, Long y){
        User a =userRepository.findUserById(x);
        User b =userRepository.findUserById(y);
        a.getFriends().add(b);
        userRepository.save(a);
        return a;
    }
	
	//Convert Json String to object User
	public User getJson(String user,MultipartFile image) {
		User newUserObject = new User();
		try {
			ObjectMapper objmap = new ObjectMapper();
			objmap.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			objmap.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

			newUserObject = objmap.readValue(user, User.class);
			newUserObject.setImageUrl(image.getBytes());
			System.out.println(newUserObject.toString());
		}catch(IOException e) {
			System.out.printf("Error on maping Object User",e.toString());
		}
		
		return newUserObject;
	}
	
	//Add a Post
	public User addPost(User user,Post post) {
		user.getPosts().add(post);
		userRepository.save(user);
		return user;
	}


}
	

