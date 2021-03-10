package com.devshome.instalikewebapp.model;

import java.util.List;

public class User {
	
	private Long id;
	private String Username;
	private String Mail;
	private String Password;
	private byte ImageUrl;
	private List<User> Friends;
	
	
	
	public List<User> getFriends() {
		return Friends;
	}
	public void setFriends(List<User> friends) {
		Friends = friends;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getMail() {
		return Mail;
	}
	public void setMail(String mail) {
		Mail = mail;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	public byte getImageUrl() {
		return ImageUrl;
	}
	public void setImageUrl(byte imageUrl) {
		ImageUrl = imageUrl;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", Username=" + Username + ", Mail=" + Mail + ", Password=" + Password + ", ImageUrl="
				+ ImageUrl + ", Friends=" + Friends + "]";
	}
	
	
	
	

}
