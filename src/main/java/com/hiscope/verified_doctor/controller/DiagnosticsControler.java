package com.hiscope.verified_doctor.controller;

import com.hiscope.verified_doctor.dto.LoginDto;
import com.hiscope.verified_doctor.entity.Diagnostics;
import com.hiscope.verified_doctor.service.DiagnosticsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/diagnostics")
public class DiagnosticsControler {

    @Autowired
    private DiagnosticsService diagnosticsService;

    @PostMapping("/register")
    public Diagnostics registerDiagnostics(@RequestBody Diagnostics diagnostics) {
        return diagnosticsService.registerDiagnostics(diagnostics);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginDiagnostics(@RequestBody LoginDto loginDto) {
        return diagnosticsService.loginDiagnostics(loginDto);
    }

    @PutMapping("/changePassword/{email}")
    public String changePassword(@PathVariable String email, @RequestBody LoginDto loginDto) {
        return diagnosticsService.changePassword(email, loginDto);
    }

    @DeleteMapping("/deleteprofile/{email}")
    public ResponseEntity<String> deleteProfile(@PathVariable String email) {
        boolean deleted = diagnosticsService.deleteProfile(email);
        if (deleted) {
            return ResponseEntity.ok("Diagnostics user with email " + email + " deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Diagnostics user not found with email: " + email);
        }
    }
}
