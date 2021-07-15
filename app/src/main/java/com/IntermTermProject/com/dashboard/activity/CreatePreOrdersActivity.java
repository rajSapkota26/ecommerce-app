package com.IntermTermProject.com.dashboard.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.IntermTermProject.com.R;
import com.IntermTermProject.com.dashboard.model.PreOrders;
import com.IntermTermProject.com.dashboard.model.Product;
import com.IntermTermProject.com.dashboard.model.UserProfile;
import com.IntermTermProject.com.dashboard.server.api.ApiRegister;
import com.IntermTermProject.com.dashboard.server.service.MyCartService;
import com.IntermTermProject.com.dashboard.server.service.PreOrderService;
import com.IntermTermProject.com.dashboard.server.service.ProductService;
import com.IntermTermProject.com.dashboard.server.service.UserProfileService;
import com.IntermTermProject.com.loginsignup.SignUpActivity;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePreOrdersActivity extends AppCompatActivity {
    private Product product;
    private PreOrders orders;
    private UserProfile profile;
    private ProductService productService;
    private PreOrderService preOrderService;
    private UserProfileService profileService;
    private MyCartService cartService;
    private FirebaseUser user;
    private Long pId;
    private Long orderId;
    private int qty = 1;
    private ProgressDialog dialog;
    private TextView proName, proColorAndBrand, proPriceAndDis, proQuantity, totalPrice, totalDiscount;
    private TextView subtotal, subtotal_item, shippingFee, cAddress, cMobile, cEmail, finalPrice;
    private ImageView create_ordimage;
   private int fp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pre_orders);
        user = FirebaseAuth.getInstance().getCurrentUser();
        profileService = ApiRegister.getUserProfileService();
        dialog = new ProgressDialog(this);
        dialog.setMessage("loading...");
        profile = new UserProfile();
        if (user == null) {
            startActivity(new Intent(CreatePreOrdersActivity.this, SignUpActivity.class));
        }
        getProfile();


    }

    private void initValue() {
        preOrderService = ApiRegister.getPreOrderService();
        productService = ApiRegister.getProductService();
        pId = getIntent().getLongExtra("pId", 0);
        proName = findViewById(R.id.create_proname);
        proColorAndBrand = findViewById(R.id.create_proBrandAndColor);
        proPriceAndDis = findViewById(R.id.create_proPriceAndDis);
        proQuantity = findViewById(R.id.create_ordQunty);
        totalPrice = findViewById(R.id.create_itemsAndPrice);
        totalDiscount = findViewById(R.id.create_totaldis);
        create_ordimage = findViewById(R.id.create_ordimage);
        subtotal = findViewById(R.id.sub_totalPrice);
        subtotal_item = findViewById(R.id.subtotal_item);
        shippingFee = findViewById(R.id.shipping_charge);
        cAddress = findViewById(R.id.delivery_address);
        cMobile = findViewById(R.id.customer_mobile);
        cEmail = findViewById(R.id.customer_email);
        finalPrice = findViewById(R.id.final_price);


        if (!TextUtils.isEmpty(getIntent().getStringExtra("crd"))) {
            qty = getIntent().getIntExtra("qty", 0);
            proQuantity.setText("Qty: " + qty);
        } else {
            proQuantity.setText("Qty:" + qty);
        }
        getProduct();
    }

    private void getProduct() {
        product = new Product();
        Call<Product> callPro = productService.getProduct(pId);
        callPro.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                product = response.body();
                setDetails();
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });

    }

    private void setDetails() {
        int totalPri = Integer.parseInt(product.getPrice()) * qty;
        int totalDis = Integer.parseInt(product.getDiscount()) * qty;

        proName.setText(product.getName());
        Glide.with(CreatePreOrdersActivity.this).load(product.getImage()).placeholder(R.drawable.sample_image).into(create_ordimage);
        proPriceAndDis.setText("Rs: " + product.getPrice() + "\n-Rs: " + product.getDiscount());
        proColorAndBrand.setText("Brand: " + product.getBrand() + ", Color:" + product.getColor());
        Toast.makeText(this, product.getName(), Toast.LENGTH_SHORT).show();
        totalPrice.setText(String.valueOf(qty) + " items(s), total: Rs " + String.valueOf(totalPri));
        totalDiscount.setText("total discount: Rs " + String.valueOf(totalDis));
        subtotal_item.setText("Sub total (" + qty + ") items");
        subtotal.setText(String.valueOf(totalPri - totalDis));
        shippingFee.setText(String.valueOf(product.getCategory().getDeliveryCharge()));
         fp = totalPri - totalDis + product.getCategory().getDeliveryCharge();
        finalPrice.setText("Total Rs: " + String.valueOf(fp));
    }


    private void getProfile() {
        String id = user.getUid();
        Log.d("myError", id);
        Call<UserProfile> callProfile = profileService.getProfile(id);
        callProfile.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response.isSuccessful()) {
                    profile = response.body();
                    if (profile.getAddress() == null) {
                        startActivity(new Intent(CreatePreOrdersActivity.this, AddUserProfileActivity.class));

                    } else {
                        initValue();
                        setDeliveryAddress();
                    }

                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                startActivity(new Intent(CreatePreOrdersActivity.this, AddUserProfileActivity.class));

            }
        });
    }

    private void setDeliveryAddress() {
        cAddress.setText(profile.getAddress());
        cMobile.setText("Mobile:" + profile.getPhone());
        cEmail.setText("Email:" + profile.getEmail());
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, DashBoardActivity.class));
        finish();
    }

    public void proceedToPay(View view) {
        dialog.show();
        savePreOrders();
    }

    private void savePreOrders() {
        orders = new PreOrders();
        orders.setUserId(user.getUid());
        orders.setPrice(String.valueOf(fp));
        orders.setProductId(product.getId());
        orders.setProductName(product.getName());
        orders.setProductImage(product.getImage());
        orders.setStatus("pending");
        orders.setQuantities(qty);
        Call<PreOrders> saveOrd = preOrderService.addPreOrders(orders);
        saveOrd.enqueue(new Callback<PreOrders>() {
            @Override
            public void onResponse(Call<PreOrders> call, Response<PreOrders> response) {
//                orders = response.body();
//                orderId = orders.getId();
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    orders = response.body();
                    Intent intent = new Intent(CreatePreOrdersActivity.this, SelectGateWayActivity.class);
                    intent.putExtra("oId", orders.getId());
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<PreOrders> call, Throwable t) {

            }
        });

    }
}