package com.hiscope.verified_doctor.controller;


import com.hiscope.verified_doctor.entity.PharmacistVerification;
import com.hiscope.verified_doctor.service.PharmacistVerificationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/pharmacistverification")
public class PharmacistVerificationController {

    @Autowired
    private PharmacistVerificationService pharmacistVerificationService;

    @PostMapping("/save")
    public ResponseEntity<?> saveVerification(
            @RequestParam String email,
            @RequestParam String fullName,
            @RequestParam String medicalLicenseNumber,
            @RequestParam MultipartFile medicalLicenseFile,
            @RequestParam String medicalLicenseNumberExpiryDate,
            @RequestParam String medicalSpeciality,
            @RequestParam MultipartFile boardCertificateFile,
            @RequestParam String educationalBackground,
            @RequestParam MultipartFile educationalCertificateFile,
            @RequestParam String hospitalCurrentWorking,
            @RequestParam Integer experience,
            @RequestParam String hospitalOrClinic,
            @RequestParam(required = false) String disciplinaryActions,
            @RequestParam String description,
            @RequestParam String personalAddress,
            @RequestParam Long mobileNumber,
            @RequestParam String gender,
            @RequestParam String launguage,
            @RequestParam String hospitalAddress,
            @RequestParam MultipartFile pharmacistPhoto
    ) {
        try {
            String response = pharmacistVerificationService.saveVerification(
                    email, fullName, medicalLicenseNumber, medicalLicenseFile,
                    medicalLicenseNumberExpiryDate, medicalSpeciality, boardCertificateFile,
                    educationalBackground, educationalCertificateFile, hospitalCurrentWorking,
                    experience, hospitalOrClinic, disciplinaryActions, description,
                    personalAddress, mobileNumber, gender, launguage, hospitalAddress,
                    pharmacistPhoto
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error registering pharmacist: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<PharmacistVerification>> getAllPharmacists() {
        return ResponseEntity.ok(pharmacistVerificationService.getAllPharmacists());
    }

    @GetMapping("/get/{email}")
    public ResponseEntity<PharmacistVerification> getPharmacistDetailByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(pharmacistVerificationService.getPharmacistByEmail(email));
    }
}