package com.devshome.instalikeapi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
	private Long id;
	private String Username;
	private String Mail;
	private String Password;
	private String ImageUrl;
	@OneToMany
	private List<User> Friends;
	
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
	public String getImageUrl() {
		return ImageUrl;
	}
	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}
	public List<User> getFriends() {
		return Friends;
	}
	public void setFriends(List<User> friends) {
		Friends = friends;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", Username=" + Username + ", Mail=" + Mail + ", Password=" + Password + ", ImageUrl="
				+ ImageUrl + ", Friends=" + Friends + "]";
	}
	
	
	

}
