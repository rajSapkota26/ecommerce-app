package com.IntermTermProject.com.dashboard.server.service;


import com.IntermTermProject.com.dashboard.model.Categories;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CategoryService {

    //get all category list
    @GET("category/")
    Call<List<Categories>> getCatList();

    //get single category by id
    @GET("category/{id}")
    Call<Categories> getcategory( @Path("id") long id);

//
}
