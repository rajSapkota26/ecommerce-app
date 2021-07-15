package com.IntermTermProject.com.dashboard.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.IntermTermProject.com.R;
import com.IntermTermProject.com.dashboard.adapter.ShopsAdapter;
import com.IntermTermProject.com.dashboard.model.Shop;

import java.util.ArrayList;
import java.util.List;

public class ShopsFragments extends Fragment {
    private List<Shop> shops;
    private ShopsAdapter adapter;
    private RecyclerView recyclerView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_shop, container, false);
        recyclerView=root.findViewById(R.id.shop_shopRecycler);
        shops=new ArrayList<>();
        shops.add(new Shop("Shop 1",""));
        shops.add(new Shop("Shop 2",""));
        shops.add(new Shop("Shop 3",""));
        shops.add(new Shop("Shop 4",""));
        shops.add(new Shop("Shop 5",""));
        shops.add(new Shop("Shop 6",""));
        shops.add(new Shop("Shop 7",""));
        shops.add(new Shop("Shop 8",""));
        shops.add(new Shop("Shop 9",""));
        shops.add(new Shop("Shop 10",""));


        adapter = new ShopsAdapter(getContext(),shops);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 5));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return root;
    }
}