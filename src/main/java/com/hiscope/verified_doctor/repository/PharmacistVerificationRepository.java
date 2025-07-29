package com.hiscope.verified_doctor.repository;

import com.hiscope.verified_doctor.entity.PharmacistVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacistVerificationRepository extends JpaRepository<PharmacistVerification, Long> {
}
