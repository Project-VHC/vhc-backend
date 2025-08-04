package com.hiscope.verified_doctor.service;

import com.hiscope.verified_doctor.Exception.EmailException;
import com.hiscope.verified_doctor.dto.LoginDto;
import com.hiscope.verified_doctor.entity.Diagnostics;
import com.hiscope.verified_doctor.repository.DiagnosticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiagnosticsService {

    @Autowired
    private DiagnosticsRepository diagnosticsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Register Diagnostics
    public Diagnostics registerDiagnostics(Diagnostics diagnostics) {
        if (diagnosticsRepository.existsByEmail(diagnostics.getEmail())) {
            throw new RuntimeException("Email is Already Taken! Please use a different Email");
        }

        if (!FormVallidation.isValidPassword(diagnostics.getPassword())) {
            throw new RuntimeException("Password not under given validation");
        }

        diagnostics.setPassword(passwordEncoder.encode(diagnostics.getPassword()));
        return diagnosticsRepository.save(diagnostics);
    }

    // Login Diagnostics
    public ResponseEntity<String>loginDiagnostics(LoginDto loginDto) {
        Diagnostics diagnostics = diagnosticsRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new EmailException("Email Not Found"));

        if (passwordEncoder.matches(loginDto.getPassword(), diagnostics.getPassword())) {
            return ResponseEntity.ok("Login Success. Welcome " + diagnostics.getEmail());
        }

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Login Failed Incorrect Password");
    }

    // Change Password
    public String changePassword(String email, LoginDto loginDto) {
        Diagnostics diagnostics = diagnosticsRepository.findByEmail(email)
                .orElseThrow(() -> new EmailException("Email Not Found"));

        String oldPassword = loginDto.getOldPassword();
        String hashedPassword = diagnostics.getPassword();

        if (passwordEncoder.matches(oldPassword, hashedPassword)) {
            if (!FormVallidation.isValidPassword(loginDto.getPassword())) {
                throw new RuntimeException("Password not under given validation");
            }

            diagnostics.setPassword(passwordEncoder.encode(loginDto.getPassword()));
            diagnosticsRepository.save(diagnostics);
            return "Password Changed Successfully";
        }

        return "Incorrect Old Password";
    }

    // Delete Profile
    public boolean deleteProfile(String email) {
        Optional<Diagnostics> diagnostics = diagnosticsRepository.findByEmail(email);

        if (diagnostics.isPresent()) {
            diagnosticsRepository.delete(diagnostics.get());
            return true;
        }

        return false;
    }
}
