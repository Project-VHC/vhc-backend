package com.hiscope.verified_doctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hiscope.verified_doctor.dto.LoginDto;
import com.hiscope.verified_doctor.entity.Doctor;
import com.hiscope.verified_doctor.service.DoctorService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;
	
	@PostMapping("/register")
	public Doctor registerDoctor(@RequestBody Doctor doctor) {
		
		return doctorService.registerDoctor(doctor);
	}
	@PostMapping("/login")
	public String loginDoctor(@RequestBody LoginDto loginDto) {
		return doctorService.loginDoctor(loginDto);
	}
	
	@PutMapping("/changePassword/{email}")
	public String changePassword(@PathVariable String email,@RequestBody LoginDto loginDto) {
		return doctorService.changePassword(email,loginDto);
	}
	
	@DeleteMapping("/deleteprofile/{email}")
	public ResponseEntity<String> deletedProfile(@PathVariable String email) {
		boolean deleted= doctorService.deleteProfile(email);
		
		if(deleted) {
			 return ResponseEntity.ok("User with email " + email + " deleted successfully.");
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user is  not found with this "+email+" email");
		}
		
	}
	
	
}
