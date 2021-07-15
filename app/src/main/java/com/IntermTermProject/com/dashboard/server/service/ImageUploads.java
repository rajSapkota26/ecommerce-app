package com.IntermTermProject.com.dashboard.server.service;

import com.IntermTermProject.com.dashboard.model.ImageUpload;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ImageUploads {

    @Multipart
    @POST("uploading")
    Call<ImageUpload> upload(@Part MultipartBody.Part file);
}
