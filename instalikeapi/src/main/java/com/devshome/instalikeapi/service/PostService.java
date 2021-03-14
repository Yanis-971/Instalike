package com.devshome.instalikeapi.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.devshome.instalikeapi.model.Post;
import com.devshome.instalikeapi.model.User;
import com.devshome.instalikeapi.repository.PostRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	
	public PostService(PostRepository postRepository) {
		super();
		this.postRepository = postRepository;
	}

	//Add Post
	public Post addPost(Post post) {
		return postRepository.save(post);
	}
	
	//Find All Post
	public List<Post> allPost(Post post) {
		return postRepository.findAll();
	}
	
	//Find Post By id
	public Optional<Post> findPost(Long id) {
		return postRepository.findById(id);
	}
	
	//Update Post
	public Post updatePost(Post post) {
		return postRepository.save(post);
	}
	
	//Delete Post
	public void deletePost(Long id) {
		postRepository.deleteById(id);
	}
	
	//Convert Json String to object Post
		public Post getJson(User user,String post,MultipartFile image) {
			Post newPostObject = new Post();
			try {
				ObjectMapper objmap = new ObjectMapper();
				objmap.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				newPostObject = objmap.readValue(post, Post.class);
				newPostObject.setContent(image.getBytes());
				newPostObject.setType(image.getContentType());
				newPostObject.setName(image.getOriginalFilename());
				
			}catch(IOException e) {
				System.out.printf("Error on maping Object Post",e.toString());
			}
			newPostObject.setUser(user);
			postRepository.save(newPostObject);
			return newPostObject;
		}
}
