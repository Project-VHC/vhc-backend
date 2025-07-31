package com.hiscope.verified_doctor.entity;


import java.io.File;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "doctor_verifications")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorVerification {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "doctor_email")
    @JsonIgnore
    private Doctor doctor;
    
    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String email;

    @Column(unique = true, nullable = true)
    private  Long mobileNumber;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String personalAddress;


    
    @Column(nullable = false)
    private String medicalLicenseNumber;

    @Column(nullable = false)
    private String medicalLicenseNumberExpiryDate;
    
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] medicalLicenseFile;
    
    @Column(nullable = false)
    private String medicalSpeciality;
    
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] boardCertificateFile; 
    
    @Column(nullable = false)
    private String educationalBackground;
    
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] educationalCertificateFile;
    
    @Column(nullable = false)
    private String hospitalCurrentWorking;
    
    @Column(nullable = false)
    private Integer experience;
    
    @Column(nullable = false)
    private String hospitalOrClinic;
    
    private String disciplinaryActions;
    
    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "LONGTEXT")
    private String qrCodeSvg;

    @Column(columnDefinition = "TEXT")
    private String verificationUrl;

    @Column(nullable = false)
  private String hospitalAddress;

    @Column(nullable = false)
    private String launguage;
    
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] doctorPhoto;
    
    @Column(nullable = false)
    private String verificationStatus = "PENDING";// PENDING, APPROVED, REJECTED
    
    @Column(name = "submitted_at")
    private java.time.LocalDateTime submittedAt;
    
    @Column(name = "verified_at")
    private java.time.LocalDateTime verifiedAt;
    
    @PrePersist
    protected void onCreate() {
        submittedAt = java.time.LocalDateTime.now();
    }
}
