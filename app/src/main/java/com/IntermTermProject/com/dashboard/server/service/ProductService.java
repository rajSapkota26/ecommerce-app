package com.IntermTermProject.com.dashboard.server.service;

import com.IntermTermProject.com.dashboard.model.Product;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductService {

    //get allproduct list
    @GET("product/")
    Call<List<Product>> getProductList();

    //get allproduct according category
    @GET("product/all/{id}")
    Call<List<Product>>  getAllProductByCat(@Path("id") long id);

    //get single product
    @GET("product/{id}")
    Call<Product> getProduct(@Path("id") long id);
}
