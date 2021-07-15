package com.IntermTermProject.com.dashboard.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.IntermTermProject.com.R;
import com.IntermTermProject.com.dashboard.adapter.ProductAdapter;
import com.IntermTermProject.com.dashboard.model.Product;
import com.IntermTermProject.com.dashboard.server.api.ApiRegister;
import com.IntermTermProject.com.dashboard.server.service.ProductService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductViewActivity extends AppCompatActivity {
    private RecyclerView  proRecycler;
    private List<Product> products;
    private ProductAdapter productAdapter;
    private ProductService productService;

    private ProgressDialog dialog;
    private long cId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);
        proRecycler = findViewById(R.id.pvRecycler);
        dialog = new ProgressDialog(this);
        dialog.setMessage("loading...");
        dialog.show();
       cId=getIntent().getLongExtra("cId",0);
        getProductList();
    }
    private void getProductList() {
        products = new ArrayList<>();
        productService = ApiRegister.getProductService();
        Call<List<Product>>  callbackProduct = productService.getAllProductByCat(cId);

        callbackProduct.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products = response.body();
                dialog.dismiss();
                System.out.println(products.toString());
                productAdapter = new ProductAdapter(ProductViewActivity.this, products);
                proRecycler.setLayoutManager(new GridLayoutManager(ProductViewActivity.this, 2));
                proRecycler.setAdapter(productAdapter);
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(ProductViewActivity.this, "Internal server error", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,DashBoardActivity.class));
        finish();
    }
}