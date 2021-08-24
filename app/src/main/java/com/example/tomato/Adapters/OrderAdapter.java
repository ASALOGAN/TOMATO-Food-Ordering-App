package com.example.tomato.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tomato.DetailActivity;
import com.example.tomato.Mdls.OrderModel;
import com.example.tomato.R;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.viewHolder>{

    ArrayList<OrderModel> list;
    Context context;

    public OrderAdapter(ArrayList<OrderModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_sample, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        final OrderModel model = list.get(position);
        holder.orderImage.setImageResource(model.getOrderImage());
        holder.soldItemName.setText(model.getSoldItemName());
        holder.price.setText(model.getPrice());
        holder.orderNumber.setText(model.getOrderNumber());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("id", Integer.parseInt(model.getOrderNumber()));
                intent.putExtra("type", 2);
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete Item")
                        .setMessage("Are you sure to delete this item?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DataBase helper = new DataBase(context);
                                if (helper.deleteOrder(model.getOrderNumber()) > 0){
                                    Toast.makeText(context,"Deleted", Toast.LENGTH_SHORT).show();
                                }
                                else
                                    Toast.makeText(context,"Error", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView orderImage;
        TextView soldItemName, price, orderNumber;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            orderImage = itemView.findViewById(R.id.orderImage);
            soldItemName = itemView.findViewById(R.id.orderItemName);
            price = itemView.findViewById(R.id.Price);
            orderNumber = itemView.findViewById(R.id.orderNumber);

        }
    }
}
