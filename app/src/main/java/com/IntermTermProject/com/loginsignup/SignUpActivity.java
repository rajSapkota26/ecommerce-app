package com.IntermTermProject.com.loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.IntermTermProject.com.R;
import com.hbb20.CountryCodePicker;

public class SignUpActivity extends AppCompatActivity {
    private CountryCodePicker countryCode;
    private EditText phone;
    private Button signButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        countryCode = findViewById(R.id.ccpCountryCode);
        phone = findViewById(R.id.etxPhoneNumber);
        signButton = findViewById(R.id.signupBtn);

        countryCode.registerCarrierNumberEditText(phone);

        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignUpActivity.this,LoginActivity.class);
                intent.putExtra("mobile",countryCode.getFullNumberWithPlus());
                Toast.makeText(SignUpActivity.this, countryCode.getFullNumberWithPlus(), Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });


    }


}