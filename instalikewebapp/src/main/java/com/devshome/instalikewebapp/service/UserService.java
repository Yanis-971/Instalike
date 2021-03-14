package com.devshome.instalikewebapp.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.devshome.instalikewebapp.model.Post;
import com.devshome.instalikewebapp.model.User;
import com.devshome.instalikewebapp.repository.UserProxy;

@Service
public class UserService {

	@Autowired
	private UserProxy userProxy;
	
	public List<User> getAllUsers(){ 
		return userProxy.getAllUsers();
	}
	
	public User getUser(Long id) {
		return userProxy.getUser(id);
	}
	
	public User LogUser(User user) {
		return userProxy.LogUser(user);
	}
	
	public User AddUser(User user ) {
		return userProxy.AddUser(user);
	}
	
	public User UpdateUser(User user, MultipartFile image) throws IOException {
		return userProxy.UpdateUser(user,image);
	}
	
	public void AddFriend(Long id1, Long id2) {
		userProxy.AddFriends(id1, id2);
	}

	public void addPost(Long id, Post post, MultipartFile image) throws IOException {
		userProxy.addPost(id,post,image);
	}
	
}
