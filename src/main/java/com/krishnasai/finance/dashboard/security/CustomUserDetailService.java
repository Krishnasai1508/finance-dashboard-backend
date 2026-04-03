package com.krishnasai.finance.dashboard.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.krishnasai.finance.dashboard.entity.Status;
import com.krishnasai.finance.dashboard.entity.User;
import com.krishnasai.finance.dashboard.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepository repository;
	
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = repository.findByEmail(email)
								.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		
		if(user.getStatus() != Status.ACTIVE) {
			throw new UsernameNotFoundException("User is inactive");
		}
		
		return new org.springframework.security.core.userdetails.User(
				user.getEmail(),
				user.getPassword(),
				Collections.singleton(() -> "ROLE_" + user.getRole().name())
				);
	}
}
