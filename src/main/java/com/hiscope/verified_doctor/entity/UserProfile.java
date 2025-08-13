package com.hiscope.verified_doctor.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String email;

    @Column(nullable = true)
    private String phoneNumber;

    @Column(nullable = true)
    private String Address;

    @Column(nullable = true)
    private String gender;

    @Column(nullable = true)
    private Integer age;

    @Lob
    @Column(columnDefinition = "LONGBLOB", nullable = true)
    private byte[] userImage;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;
}
