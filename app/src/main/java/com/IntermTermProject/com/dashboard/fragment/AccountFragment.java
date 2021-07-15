package com.IntermTermProject.com.dashboard.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.IntermTermProject.com.R;
import com.IntermTermProject.com.dashboard.activity.ViewPreOrdersActivity;
import com.IntermTermProject.com.dashboard.activity.ViewProfileActivity;
import com.IntermTermProject.com.loginsignup.SignUpActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountFragment extends Fragment {
private TextView address_order,account,profile;
private FirebaseUser user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_account, container, false);
        address_order=root.findViewById(R.id.address_order);
        account=root.findViewById(R.id.pp_account);
        profile=root.findViewById(R.id.address_ac1);
        user= FirebaseAuth.getInstance().getCurrentUser();

        if (user !=null){
            getvalues();
        }else {
            startActivity(new Intent(getContext(), SignUpActivity.class));
            getActivity().finish();
        }


        return root;
    }

    private void getvalues() {
        account.setText(user.getPhoneNumber());
        address_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ViewPreOrdersActivity.class));
                getActivity().finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ViewProfileActivity.class));
                getActivity().finish();
            }
        });
    }
}