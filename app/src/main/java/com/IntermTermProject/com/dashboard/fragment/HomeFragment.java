package com.IntermTermProject.com.dashboard.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.IntermTermProject.com.R;
import com.IntermTermProject.com.dashboard.adapter.CategoriesAdapter;
import com.IntermTermProject.com.dashboard.adapter.ProductAdapter;
import com.IntermTermProject.com.dashboard.model.Categories;
import com.IntermTermProject.com.dashboard.model.Product;
import com.IntermTermProject.com.dashboard.server.api.ApiRegister;
import com.IntermTermProject.com.dashboard.server.service.CategoryService;
import com.IntermTermProject.com.dashboard.server.service.ProductService;
import com.IntermTermProject.com.dashboard.server.service.SliderService;
import com.IntermTermProject.com.slider.ImageSliderAdapter;
import com.IntermTermProject.com.slider.SliderItem;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    //slider
    private SliderView sliderView;
    private ImageSliderAdapter adapter;
    private List<SliderItem> sliderItem;
    private RecyclerView categoryRecycler, proRecycler;

    private List<Categories> categories;
    private List<Product> products;

    private ProductAdapter productAdapter;
    private CategoriesAdapter categoriesAdapter;

    private CategoryService categoryService;
    private ProductService productService;
    private SliderService sliderService;

    private ProgressDialog dialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        categoryRecycler = root.findViewById(R.id.shopRecycler);
        proRecycler = root.findViewById(R.id.proRecycler);
        sliderView = root.findViewById(R.id.imageSlider);
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("loading...");
        dialog.show();

        getCategoriesList();
        getProductList();
        setSliderItems();
        return root;
    }

    private void setSliderItems() {
        //image slider
        sliderItem = new ArrayList<>();
        sliderService = ApiRegister.getSliderService();
        Call<List<SliderItem>> loadSlider = sliderService.getAllSlider();
        loadSlider.enqueue(new Callback<List<SliderItem>>() {
            @Override
            public void onResponse(Call<List<SliderItem>> call, Response<List<SliderItem>> response) {
                sliderItem = response.body();
                dialog.dismiss();
                adapter = new ImageSliderAdapter(getContext(), sliderItem);
                sliderView.setIndicatorAnimation(IndicatorAnimationType.DROP); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                sliderView.startAutoCycle();
                sliderView.setSliderAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<SliderItem>> call, Throwable t) {
                Toast.makeText(getContext(), "Server error", Toast.LENGTH_SHORT).show();
                Log.d("problem", "onFailure: "+t.getMessage());
            }
        });


    }


    private void getProductList() {
        products = new ArrayList<>();
        productService = ApiRegister.getProductService();
        Call<List<Product>> callbackProduct = productService.getProductList();

        callbackProduct.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products = response.body();
                dialog.dismiss();

                productAdapter = new ProductAdapter(getContext(), products);
                proRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
                proRecycler.setAdapter(productAdapter);
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getContext(), "Internal server error", Toast.LENGTH_SHORT).show();
                Log.d("problem", "onFailure: "+t.getMessage());
            }
        });


    }

    private void getCategoriesList() {
        categories = new ArrayList<>();
        categoryService = ApiRegister.getCategoryService();
        Call<List<Categories>> callbackCategory = categoryService.getCatList();
        callbackCategory.enqueue(new Callback<List<Categories>>() {
            @Override
            public void onResponse(Call<List<Categories>> call, Response<List<Categories>> response) {
                categories = response.body();
                dialog.dismiss();
                System.out.println(categories.toString());
                categoriesAdapter = new CategoriesAdapter(getContext(), categories);
                categoryRecycler.setLayoutManager(new GridLayoutManager(getContext(), 4));
                categoryRecycler.setAdapter(categoriesAdapter);
                categoriesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Categories>> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getContext(), "Internal server error", Toast.LENGTH_SHORT).show();
                Log.d("problem", "onFailure: "+t.getMessage());
            }
        });


    }


}