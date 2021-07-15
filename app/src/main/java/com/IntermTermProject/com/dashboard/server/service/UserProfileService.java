package com.IntermTermProject.com.dashboard.server.service;

import com.IntermTermProject.com.dashboard.model.UserProfile;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserProfileService {

//save userprofile
    @POST("userProfile/")
    Call<UserProfile>addProfile(@Body UserProfile pp);

    //update userprofile

    @PUT("userProfile/")
    Call<UserProfile> updateProfile(@Body UserProfile pp);

//get user profile by userid
    @GET("userProfile/userid/{id}")
    Call<UserProfile> getProfile(@Path("id") String id);
}
