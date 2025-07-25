package com.hiscope.verified_doctor.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionCreateDto {
	
	private String doctorEmail;
	
	private String userEmail;
	
	private String medicationName;
	
	private String dosage;
	
	private String instructions;
	
	private LocalDateTime validUntil;

}
