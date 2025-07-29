package com.hiscope.verified_doctor.repository;

import com.hiscope.verified_doctor.entity.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PharmacistRepository extends JpaRepository<Pharmacist, Long> {

    Optional<Pharmacist> findByEmail(String email);
    boolean existsByEmail(String email);

}
