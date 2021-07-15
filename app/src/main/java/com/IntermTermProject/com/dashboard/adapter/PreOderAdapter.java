package com.IntermTermProject.com.dashboard.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.IntermTermProject.com.dashboard.activity.SelectGateWayActivity;
import com.IntermTermProject.com.dashboard.model.PreOrders;
import com.IntermTermProject.com.dashboard.server.api.ApiRegister;
import com.IntermTermProject.com.dashboard.server.service.PreOrderService;
import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreOderAdapter extends RecyclerView.Adapter<PreOderAdapter.viewHolder> {
    private Context context;
    private List<PreOrders> preOrders;

    public PreOderAdapter(Context context, List<PreOrders> preOrders) {
        this.context = context;
        this.preOrders = preOrders;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_orders, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        PreOrders orders = preOrders.get(position);
        Glide.with(context).load(orders.getProductImage()).placeholder(R.drawable.sample_image).into(holder.image);
        holder.name.setText("OrderId: " + String.valueOf(orders.getId()));
        holder.item.setText("Qty:" + String.valueOf(orders.getQuantities()));
        holder.status.setText("Status: " + orders.getStatus());

        holder.ordView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.trace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreOrderService service= ApiRegister.getPreOrderService();
              Call<PreOrders> deleteOrd= service.deletePreOrders(orders.getId());
              deleteOrd.enqueue(new Callback<PreOrders>() {
                  @Override
                  public void onResponse(Call<PreOrders> call, Response<PreOrders> response) {
                      Toast.makeText(context, "order deleted..", Toast.LENGTH_SHORT).show();
                      preOrders.remove(position);
                      notifyDataSetChanged();
                  }

                  @Override
                  public void onFailure(Call<PreOrders> call, Throwable t) {

                  }
              });
            }
        });
        holder.pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SelectGateWayActivity.class);
                intent.putExtra("oId", orders.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return preOrders.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView name, item, status;
        Button ordView, trace, remove, pay;
        ImageView image;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.order_id);
            item = itemView.findViewById(R.id.order_items);
            status = itemView.findViewById(R.id.order_status);
            ordView = itemView.findViewById(R.id.order_view);
            trace = itemView.findViewById(R.id.order_trace);
            remove = itemView.findViewById(R.id.order_remove);
            pay = itemView.findViewById(R.id.order_payproceed);
            image = itemView.findViewById(R.id.order_image);
        }
    }
}
