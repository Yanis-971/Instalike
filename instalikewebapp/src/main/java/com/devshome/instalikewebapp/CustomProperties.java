package com.devshome.instalikewebapp;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "com.devshome.webapp")
public class CustomProperties {
	
	private String apiUrl = "http://localhost:9090/";

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}
	
	

}
