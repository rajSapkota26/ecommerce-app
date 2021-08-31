package com.IntermTermProject.com.dashboard.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.IntermTermProject.com.R;
import com.IntermTermProject.com.dashboard.model.UserProfile;
import com.IntermTermProject.com.dashboard.server.api.ApiRegister;
import com.IntermTermProject.com.dashboard.server.service.UserProfileService;
import com.IntermTermProject.com.loginsignup.SignUpActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewProfileActivity extends AppCompatActivity {
    private TextView name, email, mobile, region, city, area, address;
    private FirebaseUser user;
    private UserProfileService service;
    private UserProfile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        name = findViewById(R.id.vaddress_ac);
        email = findViewById(R.id.vpp_email);
        mobile = findViewById(R.id.vpp_mobile);
        region = findViewById(R.id.vpp_region);
        city = findViewById(R.id.vpp_city);
        area = findViewById(R.id.vpp_area);
        address = findViewById(R.id.vpp_deliveryAddress);
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user==null){
            startActivity(new Intent(this, SignUpActivity.class));
        }
        profile = new UserProfile();
        service = ApiRegister.getUserProfileService();
        Call<UserProfile> ppcall = service.getProfile(user.getUid());
        ppcall.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                profile = response.body();
                name.setText(profile.getFullName());
                email.setText(profile.getEmail());
                region.setText(profile.getRegion());
                city.setText(profile.getCity());
                area.setText(profile.getArea());
                address.setText(profile.getAddress());
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ViewProfileActivity.this, DashBoardActivity.class));
        finish();
    }
}