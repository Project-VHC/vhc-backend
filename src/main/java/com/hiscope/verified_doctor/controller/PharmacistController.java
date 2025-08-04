package com.hiscope.verified_doctor.controller;

import com.hiscope.verified_doctor.dto.LoginDto;
import com.hiscope.verified_doctor.dto.PharmacistDto;
import com.hiscope.verified_doctor.entity.Pharmacist;
import com.hiscope.verified_doctor.service.PharmacistService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/pharmacist")
public class PharmacistController {

    @Autowired
    private PharmacistService pharmacistService;

    // Register Pharmacist
    @PostMapping("/register")
    public Pharmacist registerPharmacist(@RequestBody Pharmacist pharmacist) {
        return pharmacistService.registerPharmacist(pharmacist);
    }

    // Login Pharmacist
    @PostMapping("/login")
    public ResponseEntity<String> loginPharmacist(@RequestBody LoginDto loginDto) {
        return pharmacistService.loginPharmacist(loginDto);
    }

    // Change Password
    @PutMapping("/changePassword/{email}")
    public String changePassword(@PathVariable String email, @RequestBody LoginDto loginDto) {
        return pharmacistService.changePassword(email, loginDto);
    }

    // Delete Profile
    @DeleteMapping("/deleteprofile/{email}")
    public ResponseEntity<String> deleteProfile(@PathVariable String email) {
        boolean deleted = pharmacistService.deleteProfile(email);

        if (deleted) {
            return ResponseEntity.ok("Pharmacist with email " + email + " deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Pharmacist not found with email: " + email);
        }
    }

    // Get Pharmacist + Appointments Data (custom DTO)
    @GetMapping("/getDetails/{email}")
    public ResponseEntity<PharmacistDto> getPharmacistDetails(@PathVariable String email) {
        PharmacistDto pharmacistDto = pharmacistService.getPharmacistDetails(email);
        return ResponseEntity.ok(pharmacistDto);
    }
}
