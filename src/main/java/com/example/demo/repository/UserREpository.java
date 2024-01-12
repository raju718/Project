package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;

public interface UserREpository extends JpaRepository<User, Integer> {

	public User findByEmail(String email);
	

}