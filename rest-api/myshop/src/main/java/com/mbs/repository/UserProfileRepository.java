package com.mbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mbs.model.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile,Long>{

	UserProfile findByUserId(String id);
}
