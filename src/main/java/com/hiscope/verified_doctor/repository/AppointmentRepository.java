package com.hiscope.verified_doctor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hiscope.verified_doctor.entity.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	List<Appointment> findByDoctorId(Long doctorId);

	List<Appointment> findByUserId(Long userId);
	
	Appointment findTopByUser_EmailOrderByCreatedAtDesc(String userEmail);

	Appointment findTopByDoctor_IdAndUser_IdOrderByCreatedAtDesc(Long id, Long id2);


}
