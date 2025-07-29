package com.hiscope.verified_doctor.service;

import com.hiscope.verified_doctor.entity.Doctor;
import com.hiscope.verified_doctor.entity.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PharmacistRepository extends JpaRepository<Pharmacist, Long> {

    boolean existsByEmail(String email);

    Optional<Pharmacist> findByEmail(String email);
}
