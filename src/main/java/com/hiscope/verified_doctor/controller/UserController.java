package com.hiscope.verified_doctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiscope.verified_doctor.dto.LoginDto;
import com.hiscope.verified_doctor.entity.User;
import com.hiscope.verified_doctor.service.UserService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public User registerUser(@RequestBody User user) {
		return userService.registerUser(user);
		
	}
	@PostMapping("/login")
	public String loginDoctor(@RequestBody LoginDto loginDto) {
		return userService.loginUser(loginDto);
	}
	
	@PutMapping("/changePassword/{email}")
	public String changePassword(@PathVariable String email,@RequestBody LoginDto loginDto) {
		return userService.changePassword(email,loginDto);
	}
	
	@GetMapping("/get/{email}")
	public ResponseEntity<?> getUserByEmail(@PathVariable("email") String email){
		return ResponseEntity.ok(userService.getUserByEmail(email));
	}
	
	@DeleteMapping("/deleteprofile/{email}")
	public ResponseEntity<String> deletedProfile(@PathVariable String email) {
		boolean deleted= userService.deleteProfile(email);
		
		if(deleted) {
			 return ResponseEntity.ok("User with email " + email + " deleted successfully.");
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user is  not found with this "+email+" email");
		}
		
	}
	
}
