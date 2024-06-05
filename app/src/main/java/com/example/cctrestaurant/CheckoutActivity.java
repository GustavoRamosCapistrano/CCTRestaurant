package com.example.cctrestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CheckoutActivity extends AppCompatActivity {

    private TextView nameTextView;
    private TextView addressTextView;
    private TextView phoneTextView;
    private TextView itemsTextView;
    private TextView totalPriceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        nameTextView = findViewById(R.id.nameTextView);
        addressTextView = findViewById(R.id.addressTextView);
        phoneTextView = findViewById(R.id.phoneTextView);
        itemsTextView = findViewById(R.id.itemsTextView);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String address = intent.getStringExtra("address");
        String phone = intent.getStringExtra("phone");
        String items = intent.getStringExtra("items");
        double totalPrice = intent.getDoubleExtra("totalPrice", 0.0);

        nameTextView.setText("Name: " + name);
        addressTextView.setText("Address for delivery: " + address);
        phoneTextView.setText("Phone: " + phone);
        itemsTextView.setText("Items: \n" + items);
        totalPriceTextView.setText(String.format("Total Price: €%.2f", totalPrice));
    }
}
