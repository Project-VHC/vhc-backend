package com.hiscope.verified_doctor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hiscope.verified_doctor.entity.Appointment;
import com.hiscope.verified_doctor.entity.Prescription;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long>{

	List<Prescription> findByUserId(Long id);

	List<Prescription> findByUserIdAndDoctorId(Long id, Long id2);

}
