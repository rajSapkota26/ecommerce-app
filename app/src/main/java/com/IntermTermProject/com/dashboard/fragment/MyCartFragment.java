package com.IntermTermProject.com.dashboard.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.IntermTermProject.com.R;
import com.IntermTermProject.com.dashboard.adapter.MyCartAdapter;
import com.IntermTermProject.com.dashboard.adapter.ProductAdapter;
import com.IntermTermProject.com.dashboard.model.MyCart;
import com.IntermTermProject.com.dashboard.server.api.ApiRegister;
import com.IntermTermProject.com.dashboard.server.service.MyCartService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyCartFragment extends Fragment {
    private List<MyCart> myCarts;
    private MyCartAdapter adapter;
    private RecyclerView recyclerView;
    private MyCartService service;
    private ProgressDialog dialog;
    FirebaseUser user;
    TextView text_mcart;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mycart, container, false);

        recyclerView = root.findViewById(R.id.cart_mycartRecycler);
        text_mcart = root.findViewById(R.id.text_mcart);
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("loading...");
        dialog.show();
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            getcartList();
        } else {
            Toast.makeText(getContext(), "Please login first", Toast.LENGTH_SHORT).show();
        }

        return root;
    }

    private void getcartList() {
        myCarts = new ArrayList<>();
        service = ApiRegister.getMyCartService();
        Call<List<MyCart>> callcart = service.getAllCartByUserId(user.getUid());

        callcart.enqueue(new Callback<List<MyCart>>() {
            @Override
            public void onResponse(Call<List<MyCart>> call, Response<List<MyCart>> response) {
                myCarts = response.body();
                dialog.dismiss();
                if (myCarts != null) {
                    adapter = new MyCartAdapter(getContext(), myCarts,user);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }else {
                    text_mcart.setText("List is empty");
                }

            }

            @Override
            public void onFailure(Call<List<MyCart>> call, Throwable t) {
                dialog.dismiss();
                text_mcart.setText("Internal server error");

            }
        });
    }
}