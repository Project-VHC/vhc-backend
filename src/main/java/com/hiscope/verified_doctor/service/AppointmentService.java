package com.hiscope.verified_doctor.service;

import com.hiscope.verified_doctor.dto.DoctorDto;
import com.hiscope.verified_doctor.entity.Appointment;
import com.hiscope.verified_doctor.entity.Doctor;
import com.hiscope.verified_doctor.entity.User;
import com.hiscope.verified_doctor.entity.Prescription;
import com.hiscope.verified_doctor.repository.AppointmentRepository;
import com.hiscope.verified_doctor.repository.DoctorRepository;
import com.hiscope.verified_doctor.repository.UserRepository;
import com.hiscope.verified_doctor.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,
                               DoctorRepository doctorRepository,
                               UserRepository userRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
    }

    public Appointment saveAppointment(Appointment appointment) {
        String doctorEmail = appointment.getDoctor().getEmail();
        String userEmail = appointment.getUser().getEmail();

        Doctor doctor = doctorRepository.findByEmail(doctorEmail)
                .orElseThrow(() -> new RuntimeException("Doctor not found: " + doctorEmail));

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found: " + userEmail));

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime twentyFourHoursAgo = now.minusHours(24);

        Appointment recentAppointment = appointmentRepository.findTopByUser_EmailOrderByCreatedAtDesc(userEmail);

        if (recentAppointment != null && recentAppointment.getCreatedAt().isAfter(twentyFourHoursAgo)) {
            throw new RuntimeException("You cannot book a new appointment within 24 hours of your last appointment.");
        }

        appointment.setDoctor(doctor);
        appointment.setDoctorName(doctor.getUsername());

        appointment.setUser(user);
        appointment.setUserName(user.getUsername());

        return appointmentRepository.save(appointment);
    }




    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
    
    
    
    public DoctorDto getAppointmentsByUserEmail(String email) {
    	 User user = userRepository.findByEmail(email)
                 .orElseThrow(() -> new RuntimeException("User not found: " + email));
    	
    	 DoctorDto dto = new DoctorDto();
    	 dto.setUserName(user.getUsername());
    	 dto.setData(appointmentRepository.findByUserId(user.getId()));
        return dto;
    }
    
    
    

    public DoctorDto getAppointmentsByDoctorEmail(String email) {
    	Doctor doctor = doctorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Doctor not found: " + email));
    	
    	 DoctorDto dto = new DoctorDto();
    	 	dto.setUserName(doctor.getUsername());
    	 	dto.setData(appointmentRepository.findByDoctorId(doctor.getId()));
        return dto;
    }

    
    
    public String updateAppointmentStatusByDoctor(String doctorEmail, String userEmail, String newStatus) {
    	Doctor doctor = doctorRepository.findByEmail(doctorEmail)
                .orElseThrow(() -> new RuntimeException("Doctor not found: " + doctorEmail));
    	
    	 User user = userRepository.findByEmail(userEmail)
                 .orElseThrow(() -> new RuntimeException("User not found: " + userEmail));
    	
        Appointment appointment = appointmentRepository.findTopByDoctor_IdAndUser_IdOrderByCreatedAtDesc(doctor.getId(), user.getId());

        if (appointment == null) {
            return "No appointment found.";
        }
        
        String status = newStatus.toUpperCase();
        
        switch (status) {
        case ("SCHEDULED") :{
        	if(appointment.getStatus().equals("PENDING")) {
        		appointment.setStatus(newStatus);
          	 appointmentRepository.save(appointment);
          	 return "Appointment status updated to: " + newStatus;
        	}
        	if(appointment.getStatus().equals("CANCEL")) {
        		return "Appointment status is already: " + appointment.getStatus() + " at " + appointment.getUpdatedAt() + " patient ";
        	}
        	 return "Appointment status is already: " + appointment.getStatus() + "at" + appointment.getAppointmentDateTime();
        	}
        case ("CANCEL"):{
        	appointment.setStatus(newStatus);
       	 appointmentRepository.save(appointment);
            return "Appointment status updated to: " + newStatus;
        }
        }


        
        LocalDateTime createdAt = appointment.getCreatedAt();
        if (createdAt.plusDays(2).isBefore(LocalDateTime.now())) {
            appointment.setStatus("rejected");
            appointmentRepository.save(appointment);
            return "Appointment automatically rejected (more than 2 days old).";
        }
		return status;

    }


}
