package com.devshome.instalikewebapp.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.devshome.instalikewebapp.model.User;
import com.devshome.instalikewebapp.model.Post;

import com.devshome.instalikewebapp.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//Home
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("user", new User());
		return "home";
	}
	
	//Account Page
	@GetMapping("/Account/{id}")
	public String account(@PathVariable("id") Long id, Model model){
		User user = userService.getUser(id);
		
		String base64Encoded = Base64.encodeBase64String(user.getImageUrl());	
		user.setImageBase64(base64Encoded);
		for (Post p : user.getPosts()) {
			String base64Encoded2 = Base64.encodeBase64String(p.getContent());	
			p.setConBase64(base64Encoded2);
		}
		model.addAttribute("user", user);
		model.addAttribute("post", new Post());

		
		return "account";
	}
	
	//friends Page
		@GetMapping("/contacts/{id}")
		public String friends(@PathVariable("id") Long id, Model model) {
			User user = userService.getUser(id);
			
			String base64Encoded = Base64.encodeBase64String(user.getImageUrl());	
			user.setImageBase64(base64Encoded);
			
			for (User f : user.getFriends()) {
				String base64Encoded2 = Base64.encodeBase64String(f.getImageUrl());	
				f.setImageBase64(base64Encoded2);
			}
			model.addAttribute("user", user);
			return "friends";
		}
	
	//User Log In
	@PostMapping("/logUser")
	public ModelAndView LogUser(@ModelAttribute User user) {
		User u = userService.LogUser(user);
		if(u == null)
			return new ModelAndView("redirect:/");		
		else if(u.getId() != null) 
			return new ModelAndView("redirect:/Account/"+u.getId());
		else
			return new ModelAndView("redirect:/");		
	}
	
	//Add User
	@PostMapping("/addUser")
	public ModelAndView AddUser(@ModelAttribute User user) {
		User u = userService.AddUser(user);
		if(u == null)
			return new ModelAndView("redirect:/");		
		else if(u.getId() != null) 
			return new ModelAndView("redirect:/Account/"+u.getId());
		else
			return new ModelAndView("redirect:/");		
	}
	
	//Update User
		@PostMapping("/updateUser")
		public ModelAndView UpdateUser(@ModelAttribute User user,@RequestParam(value="image") MultipartFile image) throws IOException {
			userService.UpdateUser(user,image);
			return new ModelAndView("redirect:/Account/"+user.getId());		
		}
		
	//Add Friends
	@PostMapping("/friends")
	public ModelAndView AddFriend(@ModelAttribute User user, @RequestParam(value="id",required=false) Long id1,@RequestParam(value="id2",required=false) Long id2) {
		userService.AddFriend(id1, id2);
		return new ModelAndView("redirect:/Account/"+user.getId());		
		}


	//Add a Post
	@PostMapping("/addPost/{iduser}")
	public ModelAndView addPost(@PathVariable("iduser") Long id, @ModelAttribute Post post,@RequestParam(value="image2") MultipartFile image) throws IOException {
		userService.addPost(id,post,image);
		return new ModelAndView("redirect:/Account/"+id);		
	}
	}

