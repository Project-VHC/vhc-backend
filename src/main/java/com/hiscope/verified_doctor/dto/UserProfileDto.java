package com.hiscope.verified_doctor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto {

    private String email;

    private String phoneNumber;

    private String Address;

    private String gender;

    private Integer age;
}
