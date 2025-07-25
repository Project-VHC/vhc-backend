package com.hiscope.verified_doctor.controller;

import com.hiscope.verified_doctor.dto.AppointmentCreateDto;
import com.hiscope.verified_doctor.dto.DoctorDto;
import com.hiscope.verified_doctor.entity.Appointment;
import com.hiscope.verified_doctor.entity.Doctor;
import com.hiscope.verified_doctor.entity.User;
import com.hiscope.verified_doctor.service.AppointmentService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/Appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> createAppointment(@RequestBody AppointmentCreateDto dto) {
    	Appointment appointment = new Appointment();
        Doctor doctor = new Doctor();
        doctor.setEmail(dto.getDoctorEmail());
        appointment.setDoctor(doctor);

        
        User user = new User();
        user.setEmail(dto.getUserEmail());
        appointment.setUser(user);

        
        appointment.setAppointmentDateTime(dto.getAppointmentDateTime());
        appointment.setAppointmentType(dto.getAppointmentType());
        appointment.setStatus(dto.getStatus());
        appointment.setNotes(dto.getNotes());
        
        try {
             Appointment a =  appointmentService.saveAppointment(appointment);
            return ResponseEntity.ok(a);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
        }
    }

    
    
    @GetMapping("/get")
    public List<Appointment> getAppointments() {
        return appointmentService.getAllAppointments();
    }
    
    
    
    @GetMapping("/doctor/{email}")
    public DoctorDto getDoctorAppointment(@PathVariable String email) {
    	return appointmentService.getAppointmentsByDoctorEmail(email);
    }
    
    
    
    @GetMapping("/user/{email}")
    public DoctorDto getUserAppointment(@PathVariable String email) {
    	return appointmentService.getAppointmentsByUserEmail(email);
    }
    
    
    @PutMapping("/updatestatus")
    public ResponseEntity<String> updateAppointmentStatus(@RequestBody AppointmentCreateDto dto) {
        String result = appointmentService.updateAppointmentStatusByDoctor(dto.getDoctorEmail(), dto.getUserEmail(), dto.getStatus());
        return ResponseEntity.ok(result);
    }
    
}
