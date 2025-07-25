package com.hiscope.verified_doctor.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "appointments")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    
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
    private String userName;

    @Column(nullable = false)
    private String doctorName;
    
    @Column(nullable = false)
    private String appointmentDateTime;
    
    @Column(nullable = false)
    private String appointmentType; 
    
    @Column(nullable = false)
    private String status; 
    
    @Column(columnDefinition = "TEXT")
    private String notes;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
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