package com.hiscope.verified_doctor.service;

public class FormVallidation {
	
	 public static  boolean isValidPassword(String password) {
	        if (password.length() < 8 || password.length() > 15) {
	            return false;
	        }

	        String upperCasePattern = ".*[A-Z].*";
	        String lowerCasePattern = ".*[a-z].*";
	        String digitPattern = ".*\\d.*";
	        String specialCharPattern = ".*[^a-zA-Z0-9].*";

	        if (!password.matches(upperCasePattern)) throw new RuntimeException("password must have at least one UpperCase letter");
	        if (!password.matches(lowerCasePattern)) throw new RuntimeException("password must have at least one Lowercase letter");
	        if (!password.matches(digitPattern)) throw new RuntimeException("password must have at least one Digital Number");
	        if (!password.matches(specialCharPattern)) throw new RuntimeException("password must have at least one special character");

	        for (int i = 0; i < password.length() - 3; i++) {
	            char ch = password.charAt(i);
	            if (password.charAt(i + 1) == ch &&
	                password.charAt(i + 2) == ch ){
	            	 throw new RuntimeException("password doesn't have three consecutive");
	            }
	        }

	        return true;
	    }

}
