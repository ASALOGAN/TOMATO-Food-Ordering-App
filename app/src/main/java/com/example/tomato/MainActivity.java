package com.example.tomato;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.tomato.Adapters.MainAdapter;
import com.example.tomato.Mdls.MainModel;
import com.example.tomato.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<MainModel> list = new ArrayList<>();

        list.add(new MainModel(R.drawable.classiczinger, "Classic Zinger","170.48","Signature chicken burger made with a crunchy chicken fillet, veggies & a delicious mayo sauce"));
        list.add(new MainModel(R.drawable.krisper, "2 Chicken Krisper Burgers","208.57","2 Delicious chicken value burgers - at only 109 each!"));
        list.add(new MainModel(R.drawable.krispercombo, "Veg-Non-Veg Krispers Combo","348.57","Pack of 4 burgers - 2 veg & 2 chicken value burgers at a deal price !"));
        list.add(new MainModel(R.drawable.vegkrisper, "2 Veg Krisper Burgers","138.10","2 Delicious veg value burgers - at a deal price"));
        list.add(new MainModel(R.drawable.vegkrispermeal, "2 Veg Krispers Meal","248.57","2 Veg value burgers, crispy medium fries & 2 delicious dips at a deal price!"));
        list.add(new MainModel(R.drawable.tandoorizinger, "Tandoori Zinger Burger","180","Chicken zinger with a delicious tandoori sauce"));
        list.add(new MainModel(R.drawable.mixeddouble, "Mixed Zinger Doubles","308.57","Best-seller combo of classic chicken zinger & tandoori zinger"));
        list.add(new MainModel(R.drawable.buddymeal, "Buddy Meal","460","Share 2 Classic Chicken Zingers & a Medium Popcorn in this delightful combo for 2"));
        list.add(new MainModel(R.drawable.vegzinger, "Veg Zinger Burger","160","Signature veg burger with crispy patties, veggies & a tangy sauce"));

        MainAdapter adapter = new MainAdapter(list , this);
        binding.recyclerview.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.orders:
                startActivity(new Intent(MainActivity.this, OrderActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}