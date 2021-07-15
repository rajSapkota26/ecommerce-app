package com.IntermTermProject.com.dashboard.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.IntermTermProject.com.R;
import com.IntermTermProject.com.dashboard.model.Orders;
import com.IntermTermProject.com.dashboard.model.PreOrders;
import com.IntermTermProject.com.dashboard.server.api.ApiRegister;
import com.IntermTermProject.com.dashboard.server.service.PreOrderService;
import com.esewa.android.sdk.payment.ESewaConfiguration;
import com.esewa.android.sdk.payment.ESewaPayment;
import com.esewa.android.sdk.payment.ESewaPaymentActivity;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Iterator;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectGateWayActivity extends AppCompatActivity {
    private static final String CONFIG_ENVIRONMENT = ESewaConfiguration.ENVIRONMENT_TEST;
    private static final int REQUEST_CODE_PAYMENT = 1;
    private ESewaConfiguration eSewaConfiguration;

    private static final String MERCHANT_ID = "JB0BBQ4aD0UqIThFJwAKBgAXEUkEGQUBBAwdOgABHD4DChwUAB0R";
    private static final String MERCHANT_SECRET_KEY = "BhwIWQQADhIYSxILExMcAgFXFhcOBwAKBgAXEQ==";
    private PreOrders preOrders;
    private PreOrderService orderService;
    private Long oId;
    private TextView total, tax, totalwithTax;
    private int totalAmounttopay;
    String pname="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_gate_way);
        oId = getIntent().getLongExtra("oId", 0);
        total = findViewById(R.id.totalAmount);
        tax = findViewById(R.id.totalTax);
        totalwithTax = findViewById(R.id.totalIncludeTax);

        eSewaConfiguration = new ESewaConfiguration()
                .clientId(MERCHANT_ID)
                .secretKey(MERCHANT_SECRET_KEY)
                .environment(CONFIG_ENVIRONMENT);

        getPreorderDetail();

        Log.d("oiD", String.valueOf(oId));
    }

    private void getPreorderDetail() {
        orderService = ApiRegister.getPreOrderService();
        Call<PreOrders> ordersCall = orderService.getPreOrdersById(oId);
        ordersCall.enqueue(new Callback<PreOrders>() {
            @Override
            public void onResponse(Call<PreOrders> call, Response<PreOrders> response) {
                preOrders = response.body();
                setValue();
            }

            @Override
            public void onFailure(Call<PreOrders> call, Throwable t) {

            }
        });
    }

    private void setValue() {
        total.setText("Total Amount:"+preOrders.getPrice());
        int taxa = (Integer.parseInt(preOrders.getPrice()) * 13) / 100;
        totalAmounttopay = taxa + Integer.parseInt(preOrders.getPrice());
        tax.setText("Tax:"+String.valueOf(taxa));
        totalwithTax.setText("Total Amount(include tax):"+String.valueOf(totalAmounttopay));
        pname=preOrders.getProductName();
    }

    public void payBalance(View view) {
        doSomeOperations(String.valueOf(totalAmounttopay),pname);

    }


    private void doSomeOperations(String valueOf,String pname) {
        ESewaPayment eSewaPayment = new ESewaPayment(valueOf,pname, "unique_" + System.nanoTime(), "http://com.example.com");

        Intent intent = new Intent(SelectGateWayActivity.this, ESewaPaymentActivity.class);
        intent.putExtra(ESewaConfiguration.ESEWA_CONFIGURATION, eSewaConfiguration);
        intent.putExtra(ESewaPayment.ESEWA_PAYMENT, eSewaPayment);
        someActivityResultLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        if (result == null) return;

                        Intent data = result.getData();
                        String message = data.getStringExtra(ESewaPayment.EXTRA_RESULT_MESSAGE);
                        Gson g = new Gson();
                        Orders object=g.fromJson(message, Orders.class);

                        Log.d("sms",message);
                        Log.d("sms2",object.toString());
                        Toast.makeText(SelectGateWayActivity.this, object.getMessage().getSuccessMessage(), Toast.LENGTH_SHORT).show();
                          updateOrders(object);
//
                    }else{
                        startActivity(new Intent(SelectGateWayActivity.this,DashBoardActivity.class));
                        finish();
                    }
                }
            });

    private void updateOrders(Orders object) {
        preOrders.setPaymentAmount(object.getTotalAmount());
        preOrders.setPaymentDate(object.getTransactionDetails().getDate());
        preOrders.setPaymentProductId(object.getProductId());
        preOrders.setPaymentStatus(object.getTransactionDetails().getStatus());
        preOrders.setPaymentSuccessMessage(object.getMessage().getSuccessMessage());
        orderService = ApiRegister.getPreOrderService();
      Call<PreOrders> updateOrd=  orderService.updatePreOrders(preOrders);
      updateOrd.enqueue(new Callback<PreOrders>() {
          @Override
          public void onResponse(Call<PreOrders> call, Response<PreOrders> response) {
              if (response.isSuccessful()){
                  startActivity(new Intent(SelectGateWayActivity.this,DashBoardActivity.class));
                  finish();
              }
          }

          @Override
          public void onFailure(Call<PreOrders> call, Throwable t) {

          }
      });

    }

}