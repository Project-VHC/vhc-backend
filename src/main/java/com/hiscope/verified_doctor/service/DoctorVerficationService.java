package com.hiscope.verified_doctor.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.hiscope.verified_doctor.config.SvgQrGenerator;
import com.hiscope.verified_doctor.dto.VerificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hiscope.verified_doctor.repository.DoctorRepository;
import com.hiscope.verified_doctor.repository.DoctorVerificationRepository;

import jakarta.transaction.Transactional;

import com.hiscope.verified_doctor.entity.*;

@Service
public class DoctorVerficationService {
	
	@Autowired
	private  DoctorVerificationRepository doctorVerificationRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	
	@Transactional
	public String saveVerification(String email,
									String fullName,
									String medicalLicenseNumber,
									MultipartFile medicalLicenseFile,
									String medicalSpeciality,
									MultipartFile boardCertificateFile,
									 String educationalBackground,
									 MultipartFile educationalCertificateFile,
									 String hospitalCurrentWorking,
									 Integer experience,String hospitalOrClinic,
									 String disciplinaryActions,String description,
									 String personalAddress, Long mobileNumber,String gender,
									 String launguage,String hospitalAddress, String medicalLicenseNumberExpiryDate,
									 MultipartFile doctorPhoto) {
		
		
		DoctorVerification doctorVerification = new DoctorVerification();
		Doctor doctor = doctorRepository.findByEmail(email).orElseThrow(()->new RuntimeException("Email Not Found"));		 
		
		try {
			doctorVerification.setEmail(doctor.getEmail());
			doctorVerification.setDoctor(doctor);
			doctorVerification.setFullName(fullName);
			doctorVerification.setMedicalLicenseNumber(medicalLicenseNumber);
			doctorVerification.setMedicalLicenseFile(medicalLicenseFile.getBytes());
			doctorVerification.setMedicalSpeciality(medicalSpeciality);
			doctorVerification.setBoardCertificateFile(boardCertificateFile.getBytes());
			doctorVerification.setEducationalBackground(educationalBackground);
			doctorVerification.setEducationalCertificateFile(educationalCertificateFile.getBytes());
			doctorVerification.setHospitalCurrentWorking(hospitalCurrentWorking);
			doctorVerification.setExperience(experience);
			doctorVerification.setHospitalOrClinic(hospitalOrClinic);
			doctorVerification.setDisciplinaryActions(disciplinaryActions);
			doctorVerification.setDescription(description);
			doctorVerification.setPersonalAddress(personalAddress);
			doctorVerification.setMobileNumber(mobileNumber);
			doctorVerification.setGender(gender);
			doctorVerification.setLaunguage(launguage);
			doctorVerification.setHospitalAddress(hospitalAddress);
			doctorVerification.setMedicalLicenseNumberExpiryDate(medicalLicenseNumberExpiryDate);
			doctorVerification.setDoctorPhoto(doctorPhoto.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	 doctorVerificationRepository.save(doctorVerification);
	 
	 return " Doctor verification submitted succesfully";
		
	}
	
	@Transactional
	public List<DoctorVerification> getAllDoctor() {
		return doctorVerificationRepository.findAll();
		
	}
	
	@Transactional
	public DoctorVerification getDoctorByEmail(String email) {
		  Doctor doctor = doctorRepository.findByEmail(email)
	                .orElseThrow(() -> new RuntimeException("Doctor email not found: " + email));

	        return doctorVerificationRepository.findByDoctor(doctor)
	                .orElseThrow(() -> new RuntimeException("Verification data not found for: " + email));
	}


	@Transactional
	public void verification(VerificationDto dto) {
		DoctorVerification doctor = doctorVerificationRepository.findByEmail(dto.getEmail())
				.orElseThrow(() -> new RuntimeException("Email not found"));

		//update status
     doctor.setVerificationStatus(dto.getVerificationStatus());

		String baseUrl = "doctorID/";
		String fullUrl = baseUrl + dto.getEmail();
		doctor.setVerificationUrl(fullUrl);
		String qrSvg = SvgQrGenerator.generateSvg(fullUrl, 200, 200);
		doctor.setQrCodeSvg(qrSvg);
		//save  the changes
		doctorVerificationRepository.save(doctor);


	}

	public String getQrCode(String email) {
		DoctorVerification doctor = doctorVerificationRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Email not found"));
		return doctor.getQrCodeSvg();
	}
}
