package com.hiscope.verified_doctor.controller;

import com.hiscope.verified_doctor.entity.DiagnosticsVerification;
import com.hiscope.verified_doctor.service.DiagnosticsVerificationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/diagnosticsverification")
public class DiagnosticsVerificationController {

    @Autowired
    private DiagnosticsVerificationService diagnosticsVerificationService;

    @PostMapping("/save")
    public ResponseEntity<?> saveVerification(
            @RequestParam String email,
            @RequestParam String fullName,
            @RequestParam String diagnosticsLicenseNumber,
            @RequestParam MultipartFile diagnosticsLicenseFile,
            @RequestParam String diagnosticsLicenseNumberExpiryDate,
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
            @RequestParam MultipartFile diagnosticsPhoto
    ) {
        try {
            String response = diagnosticsVerificationService.saveVerification(
                    email, fullName, diagnosticsLicenseNumber, diagnosticsLicenseFile,
                    diagnosticsLicenseNumberExpiryDate, medicalSpeciality, boardCertificateFile,
                    educationalBackground, educationalCertificateFile, hospitalCurrentWorking,
                    experience, hospitalOrClinic, disciplinaryActions, description,
                    personalAddress, mobileNumber, gender, launguage, hospitalAddress,
                    diagnosticsPhoto
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error registering diagnostics: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<DiagnosticsVerification>> getAllDiagnostics() {
        return ResponseEntity.ok(diagnosticsVerificationService.getAllDiagnostics());
    }

    @GetMapping("/get/{email}")
    public ResponseEntity<DiagnosticsVerification> getDiagnosticsDetailByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(diagnosticsVerificationService.getDiagnosticsByEmail(email));
    }
}
