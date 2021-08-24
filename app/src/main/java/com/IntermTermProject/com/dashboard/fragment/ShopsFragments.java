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
import com.IntermTermProject.com.dashboard.adapter.ShopsAdapter;
import com.IntermTermProject.com.dashboard.model.Categories;
import com.IntermTermProject.com.dashboard.model.Shop;
import com.IntermTermProject.com.dashboard.server.api.ApiRegister;
import com.IntermTermProject.com.dashboard.server.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopsFragments extends Fragment {
//    private List<Shop> shops;
//    private ShopsAdapter adapter;
    private RecyclerView recyclerView;
    private List<Categories> categories;
    private CategoryService categoryService;
    private ProgressDialog dialog;
    private CategoriesAdapter categoriesAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_shop, container, false);
        recyclerView=root.findViewById(R.id.shop_shopRecycler);
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("loading...");
        dialog.show();
        getCategoriesList();

        return root;
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
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
                recyclerView.setAdapter(categoriesAdapter);
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