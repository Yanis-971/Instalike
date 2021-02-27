package com.devshome.instalikewebapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
