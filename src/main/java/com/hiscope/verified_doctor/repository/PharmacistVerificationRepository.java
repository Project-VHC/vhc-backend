package com.hiscope.verified_doctor.repository;

import com.hiscope.verified_doctor.entity.PharmacistVerification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacistVerificationRepository extends JpaRepository<PharmacistVerification, Long> {
    PharmacistVerification findByEmail(String email);
}
