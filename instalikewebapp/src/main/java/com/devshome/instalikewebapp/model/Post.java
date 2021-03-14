package com.devshome.instalikewebapp.model;

import java.util.Arrays;

public class Post {
	
	private Long id;
	private String Title;
	private String Description;
	private byte[] Content;
	private String ConBase64;
	private String Type;
	private String Name;
	
	
	
	

	public String getConBase64() {
		return ConBase64;
	}
	public void setConBase64(String conBase64) {
		ConBase64 = conBase64;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public byte[] getContent() {
		return Content;
	}
	public void setContent(byte[] content) {
		Content = content;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	@Override
	public String toString() {
		return "Post [id=" + id + ", Title=" + Title + ", Description=" + Description + ", Content="
				+ Arrays.toString(Content) + ", ConBase64=" + ConBase64 + ", Type=" + Type + ", Name=" + Name + "]";
	}
	
	
	
	
	

	
}
