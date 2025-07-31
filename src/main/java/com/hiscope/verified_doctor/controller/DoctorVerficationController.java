package com.hiscope.verified_doctor.controller;

import java.util.List;

import com.hiscope.verified_doctor.dto.VerificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.hiscope.verified_doctor.entity.DoctorVerification;
import com.hiscope.verified_doctor.service.DoctorVerficationService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/doctorverfication")
public class DoctorVerficationController {

	@Autowired
	private DoctorVerficationService doctorVerficationService;
	
	@PostMapping("/save")
	public ResponseEntity<?> saveVerfication(@RequestParam String email,
			@RequestParam String fullName,
			@RequestParam String medicalLicenseNumber,
			@RequestParam MultipartFile medicalLicenseFile,
			@RequestParam String medicalSpeciality,
			@RequestParam MultipartFile boardCertificateFile,
			@RequestParam String educationalBackground,
			@RequestParam MultipartFile educationalCertificateFile,
			@RequestParam String hospitalCurrentWorking,
			@RequestParam Integer experience,String hospitalOrClinic,
			@RequestParam String disciplinaryActions,String description,
			@RequestParam String personalAddress, @RequestParam Long mobileNumber,@RequestParam String gender,
			@RequestParam String launguages,@RequestParam String hospitalAddress,@RequestParam String medicalLicenseNumberExpiryDate,
			@RequestParam MultipartFile doctorPhoto
			) {
		
		try {
           String doctor = doctorVerficationService.saveVerification(email,fullName,medicalLicenseNumber,medicalLicenseFile,
            		medicalSpeciality,boardCertificateFile,educationalBackground,educationalCertificateFile,hospitalCurrentWorking,
            		experience,hospitalOrClinic,disciplinaryActions,description,personalAddress,mobileNumber,gender,
				   launguages,hospitalAddress,medicalLicenseNumberExpiryDate,doctorPhoto
                    );
            return ResponseEntity.ok(doctor);
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error registering doctor: " + e.getMessage());
        }
		
	}
	 @GetMapping("/all")
	    public ResponseEntity<List<DoctorVerification>> getAllDoctors() {
	        return ResponseEntity.ok(doctorVerficationService.getAllDoctor());
	    }
	
	 @GetMapping("/get/{email}")
	 public ResponseEntity<DoctorVerification> getDoctordetailByEmail(@PathVariable("email") String email){
		 
		 return ResponseEntity.ok(doctorVerficationService.getDoctorByEmail(email));
	 }

	 @PostMapping("/verificationStatusUpdate")
	 public ResponseEntity<String>  verification(@RequestBody VerificationDto dto) {

		 doctorVerficationService.verification(dto);
		 return ResponseEntity.ok("Verification status updated successfully");

	 }

	 @GetMapping("/getQrCode/{email}")
	 public ResponseEntity<String> getQrCode(@PathVariable String email){
		 String svg = doctorVerficationService.getQrCode(email);

		 return ResponseEntity
				 .ok()
				 .header("Content-Type", "image/svg+xml")
				 .body(svg);

	 }

}
