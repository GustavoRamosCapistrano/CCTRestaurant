package com.example.cctrestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Button detailButton = findViewById(R.id.detail_button);
        detailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });

        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button checkoutButton = findViewById(R.id.checkout_button);
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText foodItem = findViewById(R.id.food_item);
                EditText quantity = findViewById(R.id.quantity);
                EditText price = findViewById(R.id.price);

                String item = foodItem.getText().toString();
                String qty = quantity.getText().toString();
                String prc = price.getText().toString();

                if(item.isEmpty() || qty.isEmpty() || prc.isEmpty()) {
                    Toast.makeText(OrderActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(OrderActivity.this, "Order Placed: " + item + ", Quantity: " + qty + ", Price: " + prc, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
