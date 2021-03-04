package com.devshome.instalikewebapp.repository;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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
		
		log.debug("Request : Get All Users" + response.getStatusCode().toString());
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
		
		log.debug(" Request : Get User " + response.getStatusCode().toString());
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
			
			log.debug("Request : Log User" + response.getStatusCode().toString());
			return response.getBody();
		}
	
	// Add User
		public User AddUser(User user){
			
			String baseUrl = customProperties.getApiUrl();
			String getUsersUrl = baseUrl + "/User/add";
			
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<User> request = new HttpEntity<User>(user);
			ResponseEntity<User> response = restTemplate.exchange(
					getUsersUrl,
					HttpMethod.POST,
					request,
					User.class
					);
			
			log.debug("Request : Add User " + response.getStatusCode().toString());
			return response.getBody();
		}
		
		
		// Update User
				public User UpdateUser(User user){
					
					String baseUrl = customProperties.getApiUrl();
					String getUsersUrl = baseUrl + "/User/update/" + user.getId();
					
					RestTemplate restTemplate = new RestTemplate();
					HttpEntity<User> request = new HttpEntity<User>(user);
					ResponseEntity<User> response = restTemplate.exchange(
							getUsersUrl,
							HttpMethod.PUT,
							request,
							User.class
							);
					
					log.debug("Request : Update User " + response.getStatusCode().toString());
					return response.getBody();
				}
				
				
				
				// Add Friends
				public void AddFriends(Long id1,Long id2){
					
					
					String baseUrl = customProperties.getApiUrl();
					String getUsersUrl = baseUrl + "/User/Friends";
					RestTemplate restTemplate = new RestTemplate();
					
					HttpHeaders headers = new HttpHeaders();
					headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
					
					UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getUsersUrl)
							.queryParam("id1", id1)
							.queryParam("id2", id2);
					
					
					//HttpEntity<User> request = new HttpEntity<User>(user,headers);
					
					ResponseEntity<Void> response = restTemplate.exchange(
							builder.build().toString(),
							HttpMethod.POST,
							null,
							Void.class
							);
					
					log.debug("Request : Add a Friend " + response.getStatusCode().toString());
					
				}
				
		

	
}
