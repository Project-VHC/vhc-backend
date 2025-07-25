package com.hiscope.verified_doctor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hiscope.verified_doctor.entity.Doctor;
import com.hiscope.verified_doctor.entity.User;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>{

	boolean existsByEmail(String email);

	Optional<Doctor> findByEmail(String email);

}
