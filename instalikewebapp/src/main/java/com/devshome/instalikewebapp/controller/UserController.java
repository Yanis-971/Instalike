package com.devshome.instalikewebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import com.devshome.instalikewebapp.model.User;
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
	public String account(@PathVariable("id") Long id, Model model) {
		User user = userService.getUser(id);
		model.addAttribute("user", user);
		return "account";
	}
	
	//friends Page
		@GetMapping("/contacts/{id}")
		public String friends(@PathVariable("id") Long id, Model model) {
			User user = userService.getUser(id);
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
		public ModelAndView UpdateUser(@ModelAttribute User user) {
			userService.UpdateUser(user);
			return new ModelAndView("redirect:/Account/"+user.getId());		
		}
		
	//Add Friends
	@PostMapping("/friends")
	public ModelAndView AddFriend(@ModelAttribute User user, Long id1, Long id2) {
		userService.AddFriend(id1, id2);
		return new ModelAndView("redirect:/Account/"+user.getId());		

		
		}
}
