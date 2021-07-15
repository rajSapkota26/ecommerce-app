package com.IntermTermProject.com.dashboard.server.service;

import com.IntermTermProject.com.dashboard.model.MyCart;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MyCartService {

    // add my cart
    @POST("myCart/")
    Call<MyCart>addTocart(@Body MyCart cart);

//delete cart
    @DELETE("myCart/{id}")
    Call<MyCart>deleteCart(@Path("id") long id);

//get all cart according userid
    @GET("myCart/all/{id}")
    Call<List<MyCart>>  getAllCartByUserId(@Path("id") String id);

//get single cart detail
    @GET("myCart/{id}")
    Call<MyCart> getCartById(@Path("id") long id);
}
