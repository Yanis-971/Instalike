package com.devshome.instalikeapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devshome.instalikeapi.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findUserById(Long id);


}
