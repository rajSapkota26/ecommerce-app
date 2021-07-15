package com.mbs.serviceImplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbs.model.UserProfile;
import com.mbs.repository.UserProfileRepository;
import com.mbs.service.UserProfileService;

@Service
public class UserProfileServiceImpl implements UserProfileService{
	
	@Autowired
	private  UserProfileRepository repository;

	@Override
	public UserProfile getUserProfile(Long id) {
		
		return this.repository.findById(id).get();
	}

	@Override
	public UserProfile saveUserProfile(UserProfile profile) {
		
		return this.repository.save(profile);
	}

	@Override
	public UserProfile updateUserProfile(UserProfile profile) {
		
		return this.repository.save(profile);
	}

	@Override
	public void deleteUserProfile(Long id) {
		this.repository.deleteById(id);
		
	}

	@Override
	public UserProfile getUserProfileByUserId(String id) {
		// TODO Auto-generated method stub
		return this.repository.findByUserId(id);
	}

}
