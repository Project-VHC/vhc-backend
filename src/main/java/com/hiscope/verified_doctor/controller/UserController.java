package com.hiscope.verified_doctor.controller;

import com.hiscope.verified_doctor.dto.UserProfileDto;
import com.hiscope.verified_doctor.entity.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hiscope.verified_doctor.dto.LoginDto;
import com.hiscope.verified_doctor.entity.User;
import com.hiscope.verified_doctor.service.UserService;

import lombok.AllArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

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
	public ResponseEntity<String> loginDoctor(@RequestBody LoginDto loginDto) {

		return userService.loginUser(loginDto);
	}



	//user profile put endpoint
	@PutMapping("/userProfile/update")
	public ResponseEntity<String> userProfileUpdate(@ModelAttribute UserProfileDto userProfileDto,
													@RequestParam(required = false) MultipartFile imageFile) {

		UserProfile user = new UserProfile();
		user.setEmail(userProfileDto.getEmail());
		user.setPhoneNumber(userProfileDto.getPhoneNumber());
		user.setAddress(userProfileDto.getAddress());
		user.setGender(userProfileDto.getGender());
		user.setAge(userProfileDto.getAge());

		try {
			if (imageFile != null && !imageFile.isEmpty()) {
				user.setUserImage(imageFile.getBytes());
			} else {
				user.setUserImage(null);
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error uploading image: " + e.getMessage());
		}

		return ResponseEntity.ok(userService.userProfileUpdate(user));
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
