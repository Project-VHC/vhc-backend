package com.hiscope.verified_doctor.service;

import com.hiscope.verified_doctor.entity.Pharmacist;
import com.hiscope.verified_doctor.entity.PharmacistVerification;
import com.hiscope.verified_doctor.repository.PharmacistRepository;
import com.hiscope.verified_doctor.repository.PharmacistVerificationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PharmacistVerificationService {

    @Autowired
    private PharmacistVerificationRepository pharmacistVerificationRepository;

    @Autowired
    private PharmacistRepository pharmacistRepository;

    @Transactional
    public String saveVerification(
            String email,
            String fullName,
            String medicalLicenseNumber,
            MultipartFile medicalLicenseFile,
            String medicalLicenseNumberExpiryDate,
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
            MultipartFile pharmacistPhoto
    ) {
        // 1. Fetch pharmacist
        Pharmacist pharmacist = pharmacistRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Pharmacist email not found: " + email));

        // 2. Construct verification entity
        PharmacistVerification verification = new PharmacistVerification();

        try {
            verification.setEmail(email);
            verification.setPharmacist(pharmacist);
            verification.setFullName(fullName);
            verification.setMedicalLicenseNumber(medicalLicenseNumber);
            verification.setMedicalLicenseFile(medicalLicenseFile.getBytes());
            verification.setMedicalLicenseNumberExpiryDate(medicalLicenseNumberExpiryDate);
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
            verification.setPharmacistPhoto(pharmacistPhoto.getBytes());

        } catch (IOException e) {
            throw new RuntimeException("File processing error: " + e.getMessage());
        }

        // 3. Save and return
        pharmacistVerificationRepository.save(verification);
        return "Pharmacist verification submitted successfully";
    }

    @Transactional
    public List<PharmacistVerification> getAllPharmacists() {
        return pharmacistVerificationRepository.findAll();
    }

    @Transactional
    public PharmacistVerification getPharmacistByEmail(String email) {
        Pharmacist pharmacist = pharmacistRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Pharmacist email not found: " + email));

        return pharmacistVerificationRepository.findByPharmacist(pharmacist)
                .orElseThrow(() -> new RuntimeException("Verification data not found for: " + email));
    }
}