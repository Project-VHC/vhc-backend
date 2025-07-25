package com.hiscope.verified_doctor.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.hiscope.verified_doctor.entity.Appointment;
import com.hiscope.verified_doctor.entity.Prescription;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionDto {
	
	private String doctorName;
	
	private String patientName;
	
	private List<Prescription> data;

}
