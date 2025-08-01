package com.hiscope.verified_doctor.service;

import java.util.Optional;

import com.hiscope.verified_doctor.Exception.EmailException;
import com.hiscope.verified_doctor.Exception.PasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hiscope.verified_doctor.dto.LoginDto;
import com.hiscope.verified_doctor.entity.Doctor;
import com.hiscope.verified_doctor.entity.User;
import com.hiscope.verified_doctor.repository.DoctorRepository;

@Service
public class DoctorService {
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Doctor registerDoctor(Doctor doctor) {
		
		if(doctorRepository.existsByEmail(doctor.getEmail()))
		{
			throw new EmailException("email is already taken! use a different email");
		}
		if(!FormVallidation.isValidPassword(doctor.getPassword())) {
			throw new PasswordException("Password not under given validtion");
		}
			doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
		
		return doctorRepository .save(doctor);
	}
	
	public String loginDoctor(LoginDto loginDto) {
		Doctor doctor=doctorRepository.findByEmail(loginDto.getEmail()).orElseThrow(()->  new  EmailException("Email Not Found"));
		
		if(passwordEncoder.matches(loginDto.getPassword(), doctor.getPassword())) {
			return "Login Success welcome " ;
		}
		
		throw new PasswordException("Login Failed Incorret Password");
	}
	
	public String changePassword(String email, LoginDto loginDto) {
		
		Doctor doctor = doctorRepository.findByEmail(email).orElseThrow(()->new RuntimeException("Email Not Found"));
		
		String oldPassword = loginDto.getOldPassword();
		String hashedPassword = doctor.getPassword();
		if(passwordEncoder.matches(oldPassword, hashedPassword)) {
			if(!FormVallidation.isValidPassword(loginDto.getPassword())) {
				throw new PasswordException("Password not under given validtion");
			}
		doctor.setPassword(passwordEncoder.encode(loginDto.getPassword()));
		doctorRepository.save(doctor);
		}
		else {
			throw new PasswordException( "incorrect old password");
		}
		return "Password Changed Successfully";
	}

	public boolean deleteProfile(String email) {
		Optional<Doctor> doctor = doctorRepository.findByEmail(email);
		
		if(doctor.isPresent()) {
			doctorRepository.delete(doctor.get());
			return true;
		}
		return false;
	}

}
