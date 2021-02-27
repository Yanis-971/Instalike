package com.devshome.instalikewebapp.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.devshome.instalikewebapp.CustomProperties;
import com.devshome.instalikewebapp.model.User;

@Component
public class UserProxy {
	
	@Autowired
	private CustomProperties customProperties;

	Logger log = LoggerFactory.getLogger(UserProxy.class);
	
	//List of Users
	public List<User> getAllUsers(){
		
		String baseUrl = customProperties.getApiUrl();
		String getUsersUrl = baseUrl + "/User/all";
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<User>> response = restTemplate.exchange(
				getUsersUrl,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<User>>() {}
				);
		
		log.debug("Get All Users call" + response.getStatusCode().toString());
		return response.getBody();
	}
	
	//Get an User
	public User getUser(Long id){
		
		String baseUrl = customProperties.getApiUrl();
		String getUsersUrl = baseUrl + "/User/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<User> response = restTemplate.exchange(
				getUsersUrl,
				HttpMethod.GET,
				null,
				User.class
				);
		
		log.debug("Get User call" + response.getStatusCode().toString());
		return response.getBody();
	}
	
	//Get User with username and id
		public User LogUser(User user){
			
			String baseUrl = customProperties.getApiUrl();
			String getUsersUrl = baseUrl + "/User";
			
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<User> request = new HttpEntity<User>(user);
			ResponseEntity<User> response = restTemplate.exchange(
					getUsersUrl,
					HttpMethod.POST,
					request,
					User.class
					);
			
			log.debug("Get Log User call" + response.getStatusCode().toString());
			return response.getBody();
		}
	

	
}
