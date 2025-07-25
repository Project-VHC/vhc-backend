package com.hiscope.verified_doctor.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hiscope.verified_doctor.dto.LoginDto;
import com.hiscope.verified_doctor.entity.User;
import com.hiscope.verified_doctor.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public User registerUser(User user) {
		if(userRepository.existsByEmail(user.getEmail()))
		{
			throw new RuntimeException("Email is Already Taken! Please use a different Email");
		}
		if(!FormVallidation.isValidPassword(user.getPassword())) {
			throw new RuntimeException("Password not under given validtion");
		}
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		return userRepository.save(user);
		
		
	}
	
	public String loginUser(LoginDto loginDto) {
		User user=userRepository.findByEmail(loginDto.getEmail()).orElseThrow(()->new RuntimeException("Email Not Found"));
		
		if(passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
			return "Login Success welcome " + user.getEmail();
		}
		
		return "Login Failed Incorret Password";
	}
	
	
	public String changePassword(String email, LoginDto loginDto) {
		
		User user = userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("Email Not Found"));
		
		String oldPassword = loginDto.getOldPassword();
		String hashedPassword = user.getPassword();
		if(passwordEncoder.matches(oldPassword, hashedPassword)) {
			if(!FormVallidation.isValidPassword(loginDto.getPassword())) {
				throw new RuntimeException("Password not under given validtion");
			}
		user.setPassword(passwordEncoder.encode(loginDto.getPassword()));
		userRepository.save(user);
		}
		else {
			return "incorrect old password";
		}
		
		return "Password Changed Successfully";
	}
	

	public boolean deleteProfile(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		
		if(user.isPresent()) {
			userRepository.delete(user.get());
			return true;
		}
		return false;
	}

	public User getUserByEmail(String email) {
		User user =  userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found by given email"));
		return user;
	}
}
