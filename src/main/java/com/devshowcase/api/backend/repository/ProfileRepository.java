package com.devshowcase.api.backend.repository;

import com.devshowcase.api.backend.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}