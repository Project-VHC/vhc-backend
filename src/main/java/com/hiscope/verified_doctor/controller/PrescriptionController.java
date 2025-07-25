package com.hiscope.verified_doctor.controller;

import com.hiscope.verified_doctor.dto.DoctorDto;
import com.hiscope.verified_doctor.dto.PrescriptionCreateDto;
import com.hiscope.verified_doctor.dto.PrescriptionDto;
import com.hiscope.verified_doctor.entity.Doctor;
import com.hiscope.verified_doctor.entity.Prescription;
import com.hiscope.verified_doctor.entity.User;
import com.hiscope.verified_doctor.service.PrescriptionService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/prescriptions")
public class PrescriptionController {

	
    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @PostMapping("/save")
    public Prescription createPrescription(@RequestBody PrescriptionCreateDto dto) {
    	Prescription prescription = new Prescription();
    	
    	Doctor doctor = new Doctor();
    	doctor.setEmail(dto.getDoctorEmail());
    	prescription.setDoctor(doctor);
    	
    	User user = new User();
    	user.setEmail(dto.getUserEmail());
    	prescription.setUser(user);
    	
    	prescription.setDosage(dto.getDosage());
    	prescription.setMedicationName(dto.getMedicationName());
    	prescription.setInstructions(dto.getInstructions());
    	prescription.setValidUntil(dto.getValidUntil());
        return prescriptionService.savePrescription(prescription);
    }

    @GetMapping("/user/{useremail}/{doctoremail}")
    public PrescriptionDto getUserAppointment(
            @PathVariable String useremail,
            @PathVariable String doctoremail) {
        
        return prescriptionService.getPrescriptionByUserAndDoctorEmail(useremail, doctoremail);
    }

}
