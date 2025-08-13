package com.hiscope.verified_doctor.repository;

import com.hiscope.verified_doctor.entity.User;
import com.hiscope.verified_doctor.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByUser(User user);

}
