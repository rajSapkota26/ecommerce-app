package com.IntermTermProject.com.dashboard.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.IntermTermProject.com.R;
import com.IntermTermProject.com.dashboard.model.ImageUpload;
import com.IntermTermProject.com.dashboard.model.UserProfile;
import com.IntermTermProject.com.dashboard.server.api.ApiRegister;
import com.IntermTermProject.com.dashboard.server.service.ImageUploads;
import com.IntermTermProject.com.dashboard.server.service.UserProfileService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddUserProfileActivity extends AppCompatActivity {
    private EditText name, mobile, email, region, city, area, address;
    private UserProfile profile;
    private FirebaseUser user;
    private ProgressDialog dialog;
    private UserProfileService profileService;
    private CircleImageView profileImage;
    private ImageUploads uploads;
    private ImageUpload imageurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        name = findViewById(R.id.address_ac);
        mobile = findViewById(R.id.pp_mobile);
        email = findViewById(R.id.pp_email);
        region = findViewById(R.id.pp_region);
        city = findViewById(R.id.pp_city);
        area = findViewById(R.id.pp_area);
        profileImage = findViewById(R.id.profileImage);
        address = findViewById(R.id.pp_deliveryAddress);
        user = FirebaseAuth.getInstance().getCurrentUser();
        dialog = new ProgressDialog(this);
        dialog.setMessage("loading...");
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImageFromGalary();
            }
        });


    }

    private void selectImageFromGalary() {
        mGetContent.launch("image/*");
    }

    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {
                    try {

                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                        profileImage.setImageBitmap(bitmap);
                        saveToServer(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            });

    private void saveToServer(Bitmap bit) {
        uploads = ApiRegister.uploadImage();
        imageurl=new ImageUpload();
        String filename = user.getUid()+"pp";
        //create a file to write bitmap data

        Log.d("imageName", "saveToServer: " + filename);
        File f = new File(this.getCacheDir(), filename);
        try {
            f.createNewFile();
            Bitmap bitmap = bit;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 0 /*ignored for PNG*/, bos);

//write the bytes in file
            byte[] bitmapdata = bos.toByteArray();
            FileOutputStream fos = new FileOutputStream(f);

//Convert bitmap to byte array
            fos.write(bitmapdata);
            fos.flush();
            fos.close();


        } catch (Exception e) {
            e.printStackTrace();
        }


        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), f);
        MultipartBody.Part body = MultipartBody.Part.createFormData("upload", f.getName(), reqFile);
        Call<ImageUpload> upl = uploads.upload(body);
        upl.enqueue(new Callback<ImageUpload>() {
            @Override
            public void onResponse(Call<ImageUpload> call, Response<ImageUpload> response) {
                if (response.isSuccessful()){
                    imageurl=response.body();
                    Log.d("uploadmsg", "onResponse: " + imageurl.getName());
                }

            }

            @Override
            public void onFailure(Call<ImageUpload> call, Throwable t) {
                Log.d("uploadmsg", "onResponse: " + t.getMessage());
            }
        });


    }


    public void saveProfile(View view) {
        dialog.show();
        profile = new UserProfile();
        profile.setFullName(name.getText().toString());
        profile.setUserId(user.getUid());
        profile.setArea(area.getText().toString());
        profile.setCity(city.getText().toString());
        profile.setRegion(region.getText().toString());
        profile.setDaddress(address.getText().toString());
        profile.setPhone(mobile.getText().toString());
        profile.setEmail(email.getText().toString());
        savePP(profile);

    }

    public void savePP(UserProfile profile) {
        profileService = ApiRegister.getUserProfileService();
        Call<UserProfile> proCall = profileService.addProfile(profile);
        proCall.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                dialog.dismiss();
                startActivity(new Intent(AddUserProfileActivity.this, DashBoardActivity.class));
                finish();
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });


    }
}