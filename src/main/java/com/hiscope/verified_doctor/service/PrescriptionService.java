package com.hiscope.verified_doctor.service;

import com.hiscope.verified_doctor.dto.DoctorDto;
import com.hiscope.verified_doctor.dto.PrescriptionDto;
import com.hiscope.verified_doctor.entity.Doctor;
import com.hiscope.verified_doctor.entity.User;
import com.hiscope.verified_doctor.entity.Prescription;
import com.hiscope.verified_doctor.repository.DoctorRepository;
import com.hiscope.verified_doctor.repository.UserRepository;
import com.hiscope.verified_doctor.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;

    public PrescriptionService(PrescriptionRepository prescriptionRepository,
                               DoctorRepository doctorRepository,
                               UserRepository userRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
    }

    public Prescription savePrescription(Prescription prescription) {
        String doctorEmail = prescription.getDoctor().getEmail();
        String userEmail = prescription.getUser().getEmail();

        Doctor doctor = doctorRepository.findByEmail(doctorEmail)
                .orElseThrow(() -> new RuntimeException("Doctor not found: " + doctorEmail));

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found: " + userEmail));

        prescription.setDoctor(doctor);
        prescription.setUser(user);

        return prescriptionRepository.save(prescription);
    }

    public PrescriptionDto getPrescriptionByUserAndDoctorEmail(String useremail, String doctoremail) {
        // Find doctor by email
        Doctor doctor = doctorRepository.findByEmail(doctoremail)
                .orElseThrow(() -> new RuntimeException("Doctor not found: " + doctoremail));

        // Find user by email
        User user = userRepository.findByEmail(useremail)
                .orElseThrow(() -> new RuntimeException("User not found: " + useremail));

        // Fetch prescriptions where both user and doctor match
        List<Prescription> prescriptions = prescriptionRepository.findByUserIdAndDoctorId(user.getId(), doctor.getId());

        if (prescriptions.isEmpty()) {
            throw new RuntimeException("No prescriptions found for the given doctor and patient combination.");
        }
        PrescriptionDto dto = new PrescriptionDto();
        dto.setDoctorName(doctor.getUsername());
        dto.setPatientName(user.getUsername());
        dto.setData(prescriptions);

        return dto;
    }

}
