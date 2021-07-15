package com.IntermTermProject.com.dashboard.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.IntermTermProject.com.R;
import com.IntermTermProject.com.dashboard.adapter.PreOderAdapter;
import com.IntermTermProject.com.dashboard.model.PreOrders;
import com.IntermTermProject.com.dashboard.server.api.ApiRegister;
import com.IntermTermProject.com.dashboard.server.service.PreOrderService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewPreOrdersActivity extends AppCompatActivity {
    private List<PreOrders> myorders;
    private RecyclerView recyclerView;
    private PreOderAdapter adapter;
    private TextView text_ord;
    private PreOrderService service;
    private ProgressDialog dialog;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_orders_view);
        recyclerView = findViewById(R.id.pre_orderRecycler);
        text_ord = findViewById(R.id.text_preOrder);
        dialog = new ProgressDialog(this);
        dialog.setMessage("loading...");
        dialog.show();
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            getordList();
        } else {
            Toast.makeText(ViewPreOrdersActivity.this, "Please login first", Toast.LENGTH_SHORT).show();
        }
    }

    private void getordList() {
        myorders = new ArrayList<>();
        service = ApiRegister.getPreOrderService();
        Call<List<PreOrders>> callOrder = service.getAllPreOrdersByUserId(user.getUid());
        callOrder.enqueue(new Callback<List<PreOrders>>() {
            @Override
            public void onResponse(Call<List<PreOrders>> call, Response<List<PreOrders>> response) {
                myorders = response.body();
                dialog.dismiss();
                if (myorders!=null){
                    text_ord.setVisibility(View.GONE);
                    adapter = new PreOderAdapter(ViewPreOrdersActivity.this, myorders);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ViewPreOrdersActivity.this));
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }else {
                    text_ord.setText("Currently you have No Orders");
                }
            }

            @Override
            public void onFailure(Call<List<PreOrders>> call, Throwable t) {
                dialog.dismiss();
                text_ord.setText("Internal server erorr");
            }
        });
    }

    @Override
    public void onBackPressed() {
       startActivity(new Intent(ViewPreOrdersActivity.this,DashBoardActivity.class));
       finish();
    }
}