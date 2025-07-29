package com.hiscope.verified_doctor.repository;

import com.hiscope.verified_doctor.entity.Diagnostics;
import com.hiscope.verified_doctor.entity.DiagnosticsVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiagnosticsVerificationRepository extends JpaRepository<DiagnosticsVerification, Long> {

    Optional<DiagnosticsVerification> findByDiagnostics(Diagnostics diagnostics);






}
