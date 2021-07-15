package com.IntermTermProject.com.dashboard.server.service;

import com.IntermTermProject.com.slider.SliderItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SliderService {


    //get all slider
    @GET("slider/")
    Call<List<SliderItem>> getAllSlider();


}
