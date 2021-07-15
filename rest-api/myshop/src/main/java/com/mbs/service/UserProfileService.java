package com.mbs.service;

import com.mbs.model.UserProfile;

public interface UserProfileService {
	

	public UserProfile getUserProfile(Long id);
	public UserProfile getUserProfileByUserId(String id);
	

	public UserProfile saveUserProfile(UserProfile profile);

	public UserProfile updateUserProfile(UserProfile profile);

	public void deleteUserProfile(Long id);
}
