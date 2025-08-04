package com.hiscope.verified_doctor.service;

import com.hiscope.verified_doctor.Exception.EmailException;
import com.hiscope.verified_doctor.dto.LoginDto;
import com.hiscope.verified_doctor.dto.PharmacistDto;
import com.hiscope.verified_doctor.entity.Appointment;
import com.hiscope.verified_doctor.entity.Pharmacist;
import com.hiscope.verified_doctor.repository.AppointmentRepository;
import com.hiscope.verified_doctor.repository.PharmacistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PharmacistService {

    @Autowired
    private PharmacistRepository pharmacistRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Register Pharmacist
    public Pharmacist registerPharmacist(Pharmacist pharmacist) {
        if (pharmacistRepository.existsByEmail(pharmacist.getEmail())) {
            throw new RuntimeException("Email is already taken! Use a different email.");
        }

        if (!FormVallidation.isValidPassword(pharmacist.getPassword())) {
            throw new RuntimeException("Password does not meet validation requirements.");
        }

        pharmacist.setPassword(passwordEncoder.encode(pharmacist.getPassword()));
        return pharmacistRepository.save(pharmacist);
    }

    // Login Pharmacist
    public ResponseEntity<String> loginPharmacist(LoginDto loginDto) {
        Pharmacist pharmacist = pharmacistRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new EmailException("Email not found"));

        if (passwordEncoder.matches(loginDto.getPassword(), pharmacist.getPassword())) {
            return ResponseEntity.ok("Login Success. Welcome " + pharmacist.getEmail());
        }

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Login Failed Incorrect Password");
    }

    // Change Password
    public String changePassword(String email, LoginDto loginDto) {
        Pharmacist pharmacist = pharmacistRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email not found"));

        String oldPassword = loginDto.getOldPassword();
        if (passwordEncoder.matches(oldPassword, pharmacist.getPassword())) {

            if (!FormVallidation.isValidPassword(loginDto.getPassword())) {
                throw new RuntimeException("Password does not meet validation requirements.");
            }

            pharmacist.setPassword(passwordEncoder.encode(loginDto.getPassword()));
            pharmacistRepository.save(pharmacist);
            return "Password Changed Successfully";
        }

        return "Incorrect Old Password";
    }

    // Delete Profile
    public boolean deleteProfile(String email) {
        Optional<Pharmacist> pharmacist = pharmacistRepository.findByEmail(email);

        if (pharmacist.isPresent()) {
            pharmacistRepository.delete(pharmacist.get());
            return true;
        }

        return false;
    }

    // Get Pharmacist + Appointment Data
    public PharmacistDto getPharmacistDetails(String email) {
        Pharmacist pharmacist = pharmacistRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Pharmacist not found: " + email));

        List<Appointment> appointments = appointmentRepository.findByPharmacist(pharmacist);

        PharmacistDto dto = new PharmacistDto();
        dto.setUserName(pharmacist.getUsername());
        dto.setPatientName("Linked with Pharmacist: " + pharmacist.getUsername()); // Optional
        dto.setData(appointments);

        return dto;
    }
}