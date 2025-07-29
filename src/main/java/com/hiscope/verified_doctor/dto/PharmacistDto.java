package com.hiscope.verified_doctor.dto;

import com.hiscope.verified_doctor.entity.Appointment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PharmacistDto {

    private String userName;

    private String patientName;

    private List<Appointment> data;
}
