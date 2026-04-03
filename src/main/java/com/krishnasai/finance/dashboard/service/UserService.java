package com.krishnasai.finance.dashboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.krishnasai.finance.dashboard.entity.User;
import com.krishnasai.finance.dashboard.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
//	Saving the User
	public User createUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return repository.save(user);
	}
	
//	Fetching the Record
	public List<User> getAllUser(){
		return repository.findAll();
	}
}
