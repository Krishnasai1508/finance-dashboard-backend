package com.krishnasai.finance.dashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krishnasai.finance.dashboard.entity.User;
import com.krishnasai.finance.dashboard.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
//	Create User
	@PostMapping
	public User createUser(@Valid @RequestBody User user) {
		return service.createUser(user);
	}
	
//	Fetch All Users
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	public List<User> getUsers(){
		return service.getAllUser();
	}
	
}
