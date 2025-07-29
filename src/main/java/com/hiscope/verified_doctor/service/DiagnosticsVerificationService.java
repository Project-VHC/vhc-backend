package com.hiscope.verified_doctor.service;

import com.hiscope.verified_doctor.entity.Diagnostics;
import com.hiscope.verified_doctor.entity.DiagnosticsVerification;
import com.hiscope.verified_doctor.repository.DiagnosticsRepository;
import com.hiscope.verified_doctor.repository.DiagnosticsVerificationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DiagnosticsVerificationService {

    @Autowired
    private DiagnosticsVerificationRepository diagnosticsVerificationRepository;

    @Autowired
    private DiagnosticsRepository diagnosticsRepository;

    @Transactional
    public String saveVerification(
            String email,
            String fullName,
            String diagnosticsLicenseNumber,
            MultipartFile diagnosticsLicenseFile,
            String diagnosticsLicenseNumberExpiryDate,
            String medicalSpeciality,
            MultipartFile boardCertificateFile,
            String educationalBackground,
            MultipartFile educationalCertificateFile,
            String hospitalCurrentWorking,
            Integer experience,
            String hospitalOrClinic,
            String disciplinaryActions,
            String description,
            String personalAddress,
            Long mobileNumber,
            String gender,
            String launguage,
            String hospitalAddress,
            MultipartFile diagnosticsPhoto
    ) {

        Diagnostics diagnostics = diagnosticsRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Diagnostics email not found: " + email));

        DiagnosticsVerification verification = new DiagnosticsVerification();

        try {
            verification.setEmail(diagnostics.getEmail());
            verification.setDiagnostics(diagnostics);
            verification.setFullName(fullName);
            verification.setDiagnosticsLicenseNumber(diagnosticsLicenseNumber);
            verification.setDiagnosticsLicenseFile(diagnosticsLicenseFile.getBytes());
            verification.setDiagnosticsLicenseNumberExpiryDate(diagnosticsLicenseNumberExpiryDate);
            verification.setMedicalSpeciality(medicalSpeciality);
            verification.setBoardCertificateFile(boardCertificateFile.getBytes());
            verification.setEducationalBackground(educationalBackground);
            verification.setEducationalCertificateFile(educationalCertificateFile.getBytes());
            verification.setHospitalCurrentWorking(hospitalCurrentWorking);
            verification.setExperience(experience);
            verification.setHospitalOrClinic(hospitalOrClinic);
            verification.setDisciplinaryActions(disciplinaryActions);
            verification.setDescription(description);
            verification.setPersonalAddress(personalAddress);
            verification.setMobileNumber(mobileNumber);
            verification.setGender(gender);
            verification.setLaunguage(launguage);
            verification.setHospitalAddress(hospitalAddress);
            verification.setDiagnosticsPhoto(diagnosticsPhoto.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("File processing error: " + e.getMessage());
        }

        diagnosticsVerificationRepository.save(verification);

        return "Diagnostics verification submitted successfully";
    }

    @Transactional
    public List<DiagnosticsVerification> getAllDiagnostics() {
        return diagnosticsVerificationRepository.findAll();
    }

    @Transactional
    public DiagnosticsVerification getDiagnosticsByEmail(String email) {
        Diagnostics diagnostics = diagnosticsRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Diagnostics email not found: " + email));

        return diagnosticsVerificationRepository.findByDiagnostics(diagnostics)
                .orElseThrow(() -> new RuntimeException("Verification data not found for: " + email));
    }
}