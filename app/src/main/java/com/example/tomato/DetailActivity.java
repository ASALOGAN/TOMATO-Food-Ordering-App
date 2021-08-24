package com.example.tomato;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tomato.Adapters.DataBase;
import com.example.tomato.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final DataBase helper = new DataBase(this);

        if (getIntent().getIntExtra("type", 0) == 1) {

            final int image = getIntent().getIntExtra("image", 0);
            final double price = Double.parseDouble(getIntent().getStringExtra("price"));
            final String name = getIntent().getStringExtra("name");
            final String description = getIntent().getStringExtra("desc");

            binding.detailImage.setImageResource(image);
            binding.detailPrice.setText(String.format("%.2f", price));
            binding.foodName.setText(name);
            binding.detailDescription.setText(description);


            binding.Orderbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    boolean isInserted = helper.insertOrder(
                            binding.nameBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            price,
                            image,
                            name,
                            description,
                            Integer.parseInt(binding.quantity.getText().toString())
                    );

                    if (isInserted)
                        Toast.makeText(DetailActivity.this, "Data Success", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(DetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            int id = getIntent().getIntExtra("id", 0);
            Cursor cursor = helper.getOrderById(id);
            int image = cursor.getInt(4);

            binding.detailImage.setImageResource(image);
            binding.detailPrice.setText(String.format("%.2f", cursor.getDouble(3)));
            binding.foodName.setText(cursor.getString(5));
            binding.detailDescription.setText(cursor.getString(6));

            binding.nameBox.setText(cursor.getString(1));
            binding.phoneBox.setText(cursor.getString(2));
            binding.Orderbtn.setText("Update");
            binding.Orderbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isUpdated = helper.updateOder(
                            binding.nameBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            Double.parseDouble(binding.detailPrice.getText().toString()),
                            image,
                            binding.detailDescription.getText().toString(),
                            binding.foodName.getText().toString(),
                            1,
                            id
                    );
                    if (isUpdated)
                        Toast.makeText(DetailActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(DetailActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}