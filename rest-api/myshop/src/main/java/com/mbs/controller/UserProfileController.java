package com.mbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mbs.model.UserProfile;
import com.mbs.service.UserProfileService;

@RestController
@CrossOrigin("*")
public class UserProfileController {
	
	@Autowired
	private UserProfileService service;

	//add userProfile
    @PostMapping("/userProfile")
    public ResponseEntity<?> adduserProfile(@RequestBody UserProfile profile) {
    	profile.setAddress(profile.getRegion()+"-"+profile.getCity()+","+profile.getArea());
    	UserProfile pp = this.service.saveUserProfile(profile);
        return ResponseEntity.ok(pp);
    }
   
   
    //get single userProfile
    @GetMapping("/userProfile/userid/{id}")
    public UserProfile getuserProfile(@PathVariable("id") String id) {
        return this.service.getUserProfileByUserId(id);
    }
    //get single userProfile
    @GetMapping("/userProfile/{id}")
    public UserProfile getuserProfilesingle(@PathVariable("id") Long id) {
    	return this.service.getUserProfile(id);
    }
    //update userProfile
    @PutMapping("/userProfile")
    public UserProfile updateuserProfile(@RequestBody UserProfile pp) {
        return service.updateUserProfile(pp);
    }

    //delete userProfile
    @DeleteMapping("/userProfile/{id}")
    public void deleteuserProfile(@PathVariable("id") Long id){
        service.deleteUserProfile(id);

    }

}
