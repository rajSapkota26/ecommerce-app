package com.IntermTermProject.com.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.IntermTermProject.com.R;
import com.IntermTermProject.com.dashboard.activity.DashBoardActivity;
import com.IntermTermProject.com.loginsignup.SignUpActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    startActivity(new Intent(SplashActivity.this, DashBoardActivity.class));
                    finish();
//                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                    if (user != null) {
//                        // User is signed in
//
//                    } else {
//                        // No user is signed in
//                        startActivity(new Intent(SplashActivity.this, SignUpActivity.class));
//                        finish();
//                    }
                }
            }
        }).start();
    }
}