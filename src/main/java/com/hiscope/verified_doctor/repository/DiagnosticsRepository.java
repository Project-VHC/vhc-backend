package com.hiscope.verified_doctor.repository;

import com.hiscope.verified_doctor.entity.Diagnostics;
import com.hiscope.verified_doctor.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiagnosticsRepository extends JpaRepository<Diagnostics, Long> {
    boolean existsByEmail(String email);
    Optional<Diagnostics> findByEmail(String email);


}
