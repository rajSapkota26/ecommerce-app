package com.IntermTermProject.com.dashboard.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.IntermTermProject.com.R;
import com.IntermTermProject.com.dashboard.activity.AddUserProfileActivity;
import com.IntermTermProject.com.dashboard.activity.CreatePreOrdersActivity;
import com.IntermTermProject.com.dashboard.model.MyCart;
import com.IntermTermProject.com.dashboard.model.UserProfile;
import com.IntermTermProject.com.dashboard.server.api.ApiRegister;
import com.IntermTermProject.com.dashboard.server.service.MyCartService;
import com.IntermTermProject.com.dashboard.server.service.UserProfileService;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.viewHolder> {
    private Context context;
    private List<MyCart> myCarts;
    FirebaseUser user;
     UserProfile userProfile=new UserProfile();

    public MyCartAdapter(Context context, List<MyCart> myCarts, FirebaseUser user) {
        this.context = context;
        this.myCarts = myCarts;
        this.user=user;
    }

    @NonNull
    @Override
    public MyCartAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_mycart, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCartAdapter.viewHolder holder, int position) {
        MyCart cart = myCarts.get(position);

        Glide.with(context).load(cart.getProductImage()).placeholder(R.drawable.sample_image).into(holder.image);
        holder.name.setText("Product:"+cart.getProductName());

        holder.qnty.setText("Items:"+String.valueOf(cart.getQuantities()));

        int pPrice=(Integer.parseInt(cart.getProductPrice())*cart.getQuantities());
        int pdis=(Integer.parseInt(cart.getProductDiscount())*cart.getQuantities());
        int totalprice=pPrice-pdis;
        holder.price.setText("Rs:"+pPrice+"-"+pdis+"= Rs:"+totalprice);

        holder.ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context.getApplicationContext(),CreatePreOrdersActivity.class);
                intent.putExtra("crd","crd");
                intent.putExtra("pId",cart.getProductId());
                intent.putExtra("qty",cart.getQuantities());
                context.startActivity(intent);

            }
        });
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyCartService service= ApiRegister.getMyCartService();
                Call<MyCart> deletecart= service.deleteCart(cart.getId());
                deletecart.enqueue(new Callback<MyCart>() {
                    @Override
                    public void onResponse(Call<MyCart> call, Response<MyCart> response) {
                        Toast.makeText(context, "Cart removed..", Toast.LENGTH_SHORT).show();
                        myCarts.remove(position);
                    notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<MyCart> call, Throwable t) {
                        Toast.makeText(context, "something went wrong..", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return myCarts.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView name, qnty, price;
        Button ok, cancel;
        ImageView image;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.cart_name);
            qnty= itemView.findViewById(R.id.cart_items);
            price = itemView.findViewById(R.id.cart_price);
            ok = itemView.findViewById(R.id.cart_orderNow);
            cancel = itemView.findViewById(R.id.remove_cart);
            image = itemView.findViewById(R.id.cart_image);
        }
    }
}
