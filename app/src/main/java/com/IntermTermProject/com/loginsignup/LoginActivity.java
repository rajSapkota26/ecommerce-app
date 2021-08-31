package com.IntermTermProject.com.loginsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.IntermTermProject.com.R;
import com.IntermTermProject.com.dashboard.activity.DashBoardActivity;
import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {
    private PinView otp;
    private Button loginBtn;
    private String phoneNum;
    private FirebaseAuth mAuth;
    private String otpId;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        otp = findViewById(R.id.pin_view);
        loginBtn = findViewById(R.id.loginBtn);
        phoneNum = getIntent().getStringExtra("mobile");
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("verifying Phone number");
        progressDialog.setMessage("please wait process on progress...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        // Force reCAPTCHA flow
        FirebaseAuth.getInstance().getFirebaseAuthSettings().forceRecaptchaFlowForTesting(false);
        getOtpCode();
        Toast.makeText(LoginActivity.this, otpId, Toast.LENGTH_SHORT).show();


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                if (TextUtils.isEmpty(otp.getText().toString().trim())) {
                    Toast.makeText(LoginActivity.this, "otp cant be empty", Toast.LENGTH_SHORT).show();
                } else if (otp.getText().toString().length() != 6) {
                    Toast.makeText(LoginActivity.this, "otp must be 6 character long..", Toast.LENGTH_SHORT).show();
                } else {
                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(otpId, otp.getText().toString().trim());
                    signInWithPhoneAuthCredential(phoneAuthCredential);
                }
            }
        });


    }

    private void getOtpCode() {

        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phoneNum)       // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(this)                 // Activity (for callback binding)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        inboxstyle_Notification(s);
                        otpId = s;

                        Toast.makeText(LoginActivity.this, otpId, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        Toast.makeText(LoginActivity.this, otpId, Toast.LENGTH_SHORT).show();
                        signInWithPhoneAuthCredential(phoneAuthCredential);

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        progressDialog.dismiss();
                        inboxstyle_Notification(otpId);
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }).build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = task.getResult().getUser();
                            startActivity(new Intent(LoginActivity.this, DashBoardActivity.class));
                            finish();
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "Invalid otp", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private void inboxstyle_Notification(String otpcode) {
        String channelId = "myShop";
        int notificationId = 3;
        NotificationCompat.Builder builder = new NotificationCompat.Builder( this, channelId);
        builder.setSmallIcon(R.drawable.sample_image)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.sample_image))
                //tou can add lines with different number of messages arrived
                .setStyle(new NotificationCompat.InboxStyle().addLine(otpcode).addLine("with in 1 minute"+otpcode).setBigContentTitle("Please submit your OTP code").setSummaryText("Inbox"))
                .setAutoCancel(true);

        Uri path = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(path);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
            builder.setChannelId(channelId);
        }

        notificationManager.notify(notificationId, builder.build());
    }


}
