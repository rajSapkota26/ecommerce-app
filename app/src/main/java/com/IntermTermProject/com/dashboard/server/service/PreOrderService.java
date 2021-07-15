package com.IntermTermProject.com.dashboard.server.service;

import com.IntermTermProject.com.dashboard.model.MyCart;
import com.IntermTermProject.com.dashboard.model.PreOrders;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PreOrderService {

    //save preorders

    @POST("preOrder/")
    Call<PreOrders> addPreOrders(@Body PreOrders cart);
//update preorders
    @PUT("preOrder/")
    Call<PreOrders> updatePreOrders(@Body PreOrders cart);

//delete orders
    @DELETE("preOrder/{id}")
    Call<PreOrders>deletePreOrders(@Path("id") long id);

    //get allorder list by user id

    @GET("preOrder/all/{id}")
    Call<List<PreOrders>>  getAllPreOrdersByUserId(@Path("id") String id);

//get single order detail
    @GET("preOrder/{id}")
    Call<PreOrders> getPreOrdersById(@Path("id") long id);
}
