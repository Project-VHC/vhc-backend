package com.hiscope.verified_doctor.repository;

import com.hiscope.verified_doctor.entity.Pharmacist;
import com.hiscope.verified_doctor.entity.PharmacistVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PharmacistVerificationRepository extends JpaRepository<PharmacistVerification, Long> {


    Optional<PharmacistVerification> findByPharmacist(Pharmacist pharmacist);

}
