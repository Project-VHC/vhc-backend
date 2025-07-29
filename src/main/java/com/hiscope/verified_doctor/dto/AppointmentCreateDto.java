package com.hiscope.verified_doctor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentCreateDto {

	 	private String doctorEmail;
	    private String userEmail;
	    private String appointmentDateTime;
	    private String appointmentType;
	    private String status;
	    private String notes;
}
