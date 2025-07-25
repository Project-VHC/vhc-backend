package com.hiscope.verified_doctor.dto;

import java.util.List;

import com.hiscope.verified_doctor.entity.Appointment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {

	private String userName;
	
	private String patientName;
	
	private List<Appointment> data;
}
