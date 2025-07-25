package com.hiscope.verified_doctor.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "prescriptions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prescription {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "doctor_email", nullable = false)
    private Doctor doctor;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_email", nullable = false)
    private User user;
    
    @Column(nullable = false)
    private String medicationName;
    
    @Column(nullable = false)
    private String dosage;
    
    private String instructions;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @Column(name = "valid_until")
    private LocalDateTime validUntil;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
