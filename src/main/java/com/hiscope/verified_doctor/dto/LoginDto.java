package com.hiscope.verified_doctor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class LoginDto {

	private String email;
	private String password;
	private String oldPassword;
}
