package com.hiscope.verified_doctor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hiscope.verified_doctor.entity.Doctor;
import com.hiscope.verified_doctor.entity.DoctorVerification;

@Repository
public interface DoctorVerificationRepository extends JpaRepository<DoctorVerification, Long>{

	Optional<DoctorVerification> findByEmail(String email);
	Optional<DoctorVerification> findByDoctor(Doctor doctor);

	boolean existsByEmail(String email);



}
