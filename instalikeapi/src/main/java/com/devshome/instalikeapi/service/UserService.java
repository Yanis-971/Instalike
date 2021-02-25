package com.devshome.instalikeapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devshome.instalikeapi.model.User;
import com.devshome.instalikeapi.repository.UserRepository;

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
	//Update User
	public User updateUser(User user) {
		User u= userRepository.findUserById(user.getId());
		user.setId(u.getId());
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
	
	
	
	
	

}
