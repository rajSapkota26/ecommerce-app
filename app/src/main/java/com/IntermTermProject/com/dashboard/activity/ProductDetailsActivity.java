package com.IntermTermProject.com.dashboard.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.IntermTermProject.com.R;
import com.IntermTermProject.com.dashboard.adapter.ProductAdapter;
import com.IntermTermProject.com.dashboard.model.MyCart;
import com.IntermTermProject.com.dashboard.model.Product;
import com.IntermTermProject.com.dashboard.server.api.ApiRegister;
import com.IntermTermProject.com.dashboard.server.service.MyCartService;
import com.IntermTermProject.com.dashboard.server.service.ProductService;
import com.IntermTermProject.com.loginsignup.LoginActivity;
import com.IntermTermProject.com.loginsignup.SignUpActivity;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends AppCompatActivity {
    private RecyclerView  proRecycler;
    private List<Product> products;
    private ProductAdapter productAdapter;
    private ProductService productService;
    private MyCartService cartService;
    private ProgressDialog dialog;
    private NestedScrollView nsv;
    private ImageView pdtlProductImage;
    private RelativeLayout relat450;
    private LinearLayout linear360,linear460;
    private Product product;
    TextView pName,price,discount,pSpecification,pdelivery,pService,pDetails;
    private long pId;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        pId=getIntent().getLongExtra("pId",0);
        proRecycler = findViewById(R.id.pdlProductRecyclerview);
        price = findViewById(R.id.pdlProductRate);
        discount = findViewById(R.id.pdlProductDiscount);
        pName = findViewById(R.id.pdlProductDiscription);
        pSpecification = findViewById(R.id.proSpecification);
        pdelivery = findViewById(R.id.pdelivery);
        pService = findViewById(R.id.pService);
        pDetails = findViewById(R.id.pDetails);
        nsv = findViewById(R.id.nsv);
        pdtlProductImage = findViewById(R.id.pdtl_product_image);
        relat450 = findViewById(R.id.pdl_product_service);
        linear360 = findViewById(R.id.linear360);
        linear460 = findViewById(R.id.linear460);
        productService = ApiRegister.getProductService();
        dialog = new ProgressDialog(this);
        dialog.setMessage("loading...");
        dialog.show();
        user =  FirebaseAuth.getInstance().getCurrentUser();
        Call<Product> productCall= productService.getProduct(pId);

        productCall.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                product=response.body();
                dialog.dismiss();
                pName.setText(product.getName());
                price.setText("Rs: "+product.getPrice());
                discount.setText("Rs: "+product.getDiscount());
                Glide.with(ProductDetailsActivity.this).load(product.getImage()).placeholder(R.drawable.sample_image).into(pdtlProductImage);
                pService.setText(Html.fromHtml(product.getSewa()));
                pdelivery.setText(Html.fromHtml(product.getDelivery()));
                pSpecification.setText(Html.fromHtml(product.getSpecification()));
                pDetails.setText(Html.fromHtml(product.getDescription()));
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(ProductDetailsActivity.this, "Internal server error", Toast.LENGTH_SHORT).show();

            }
        });



        getProductList();
    }



    public void clickOnStore(View view) {
    }

    public void clickOnChat(View view) {
    }

    public void buyNowClicked(View view) {
        Intent intent=new Intent(this,CreatePreOrdersActivity.class);
        intent.putExtra("pId",product.getId());
        startActivity(intent);
    }

    public void addtoCartClicked(View view) {
        dialog.show();
        cartService=ApiRegister.getMyCartService();
         if (user !=null){
             String uid=user.getUid();
//             proceed addtocard function
             MyCart cart=new MyCart();
             cart.setProductId(product.getId());
             cart.setProductImage(product.getImage());
             cart.setProductName(product.getName());
             cart.setProductPrice(product.getPrice());
             cart.setProductDiscount(product.getDiscount());
             cart.setUserId(uid);
             cart.setQuantities(1);
             Call<MyCart> addTocart=  cartService.addTocart(cart);
             addTocart.enqueue(new Callback<MyCart>() {
                 @Override
                 public void onResponse(Call<MyCart> call, Response<MyCart> response) {
                   if (response.isSuccessful()){
                     dialog.dismiss();
                    Toast.makeText(ProductDetailsActivity.this, "Product Added to your cart", Toast.LENGTH_SHORT).show();

                 }
                 }

                 @Override
                 public void onFailure(Call<MyCart> call, Throwable t) {
                     dialog.dismiss();
                     Toast.makeText(ProductDetailsActivity.this, "Internal server error", Toast.LENGTH_SHORT).show();

                 }
             });

         }else{
            startActivity(new Intent(ProductDetailsActivity.this, SignUpActivity.class));
            finish();
         }
    }
    public void overviewScrool(View view) {
        scrollToParticular(pdtlProductImage.getTop());
    }

    public void ratingScrool(View view) {
        scrollToParticular(relat450.getTop());
    }

    public void productDetailScrool(View view) {
        scrollToParticular(linear360.getBottom());
    }

    public void recommendationScrool(View view) {
        scrollToParticular(linear460.getTop());
    }

    public void specificationPopUp(View view) {
        setPopupMessage(product.getSpecification());
    }

    public void deliveryPopUp(View view) {
        setPopupMessage(product.getDelivery());
    }

    public void servicePopUp(View view) {
        setPopupMessage(product.getSewa());
    }
    private void getProductList() {
        products = new ArrayList<>();

        Call<List<Product>> callbackProduct = productService.getProductList();

        callbackProduct.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products = response.body();
                dialog.dismiss();
                System.out.println(products.toString());
                productAdapter = new ProductAdapter(ProductDetailsActivity.this, products);
                proRecycler.setLayoutManager(new GridLayoutManager(ProductDetailsActivity.this, 2));
                proRecycler.setAdapter(productAdapter);
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(ProductDetailsActivity.this, "Internal server error", Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void scrollToParticular(int bottom) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    nsv.smoothScrollTo(0,bottom);
                    while(true) {
                        sleep(200);

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
    }
    private void setPopupMessage(String msg){
         ProgressDialog myDialog;
        myDialog = new ProgressDialog(ProductDetailsActivity.this);
        myDialog.setMessage( (Html.fromHtml(msg)));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            myDialog.setIndeterminateDrawable(getDrawable(R.drawable.home_icon));
        }
        myDialog.setCancelable(false);
        myDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDialog.dismiss();//dismiss dialog
            }
        });
        myDialog.show();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,DashBoardActivity.class));
        finish();
    }
}