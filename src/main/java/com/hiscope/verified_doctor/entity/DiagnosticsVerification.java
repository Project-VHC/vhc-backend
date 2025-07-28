package com.hiscope.verified_doctor.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "diagnosticsVerifications")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosticsVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "diagnostics_id")
    @JsonIgnore
    private Diagnostics diagnostics;

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
    private String diagnosticsLicenseNumber;

    @Column(nullable = false)
    private String diagnosticsLicenseNumberExpiryDate;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] diagnosticsLicenseFile;

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

    @Column(nullable = false)
    private String hospitalAddress;

    @Column(nullable = false)
    private String launguage;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] doctorPhoto;

    @Column(nullable = false)
    private String verificationStatus = "PENDING"; // PENDING, APPROVED, REJECTED

    @Column(name = "submitted_at")
    private java.time.LocalDateTime submittedAt;

    @Column(name = "verified_at")
    private java.time.LocalDateTime verifiedAt;

    @PrePersist
    protected void onCreate() {
        submittedAt = java.time.LocalDateTime.now();
    }
}


