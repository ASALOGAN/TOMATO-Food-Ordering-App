package com.example.tomato;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.tomato.Adapters.DataBase;
import com.example.tomato.Adapters.OrderAdapter;
import com.example.tomato.Mdls.OrderModel;
import com.example.tomato.databinding.ActivityMainBinding;
import com.example.tomato.databinding.ActivityOrderBinding;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    ActivityOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        DataBase helper = new DataBase(this);
        ArrayList<OrderModel> list =  helper.getOrder();

        OrderAdapter adapter = new OrderAdapter(list, this);
        binding.orderRecyclerview.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.orderRecyclerview.setLayoutManager(layoutManager);
    }
}