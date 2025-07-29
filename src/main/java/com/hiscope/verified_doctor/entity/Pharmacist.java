package com.hiscope.verified_doctor.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pharmacist")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Pharmacist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String role = " Pharmacist";

    private boolean active = true;

    private boolean verified = false;

    @OneToOne(mappedBy = "pharmacist", cascade = CascadeType.ALL)
    private PharmacistVerification verification;

    @Column(name = "created_at")
    private java.time.LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = java.time.LocalDateTime.now();
    }
}

