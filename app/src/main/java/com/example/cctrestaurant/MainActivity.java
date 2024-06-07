package com.example.cctrestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView pizzaQuantityTextView;
    private TextView hotDogQuantityTextView;
    private TextView burguerQuantityTextView;
    private TextView carbonaraQuantityTextView;
    private Button checkoutButton;

    private int pizzaQuantity = 0;
    private int hotDogQuantity = 0;
    private int burguerQuantity = 0;
    private int carbonaraQuantity = 0;

    private double pizzaPrice = 14.99;
    private double hotDogPrice = 10.99;
    private double burguerPrice = 12.99;
    private double carbonaraPrice = 19.99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pizzaQuantityTextView = findViewById(R.id.pizzaQuantityTextView);
        hotDogQuantityTextView = findViewById(R.id.hotDogQuantityTextView);
        burguerQuantityTextView = findViewById(R.id.burguerQuantityTextView);
        carbonaraQuantityTextView = findViewById(R.id.carbonaraQuantityTextView);
        checkoutButton = findViewById(R.id.checkoutButton);

        findViewById(R.id.increasePizzaQuantity).setOnClickListener(v -> {
            pizzaQuantity++;
            updateQuantities();
        });

        findViewById(R.id.decreasePizzaQuantity).setOnClickListener(v -> {
            if (pizzaQuantity > 0) pizzaQuantity--;
            updateQuantities();
        });

        findViewById(R.id.increaseHotDogQuantity).setOnClickListener(v -> {
            hotDogQuantity++;
            updateQuantities();
        });

        findViewById(R.id.decreaseHotDogQuantity).setOnClickListener(v -> {
            if (hotDogQuantity > 0) hotDogQuantity--;
            updateQuantities();
        });

        findViewById(R.id.increaseBurguerQuantity).setOnClickListener(v -> {
            burguerQuantity++;
            updateQuantities();
        });

        findViewById(R.id.decreaseBurguerQuantity).setOnClickListener(v -> {
            if (burguerQuantity > 0) burguerQuantity--;
            updateQuantities();
        });

        findViewById(R.id.increaseCarbonaraQuantity).setOnClickListener(v -> {
            carbonaraQuantity++;
            updateQuantities();
        });

        findViewById(R.id.decreaseCarbonaraQuantity).setOnClickListener(v -> {
            if (carbonaraQuantity > 0) carbonaraQuantity--;
            updateQuantities();
        });

        checkoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            intent.putExtra("pizzaQuantity", pizzaQuantity);
            intent.putExtra("hotDogQuantity", hotDogQuantity);
            intent.putExtra("burguerQuantity", burguerQuantity);
            intent.putExtra("carbonaraQuantity", carbonaraQuantity);
            startActivity(intent);
        });

        // Restore the state if it was saved before
        if (savedInstanceState != null) {
            pizzaQuantity = savedInstanceState.getInt("pizzaQuantity", 0);
            hotDogQuantity = savedInstanceState.getInt("hotDogQuantity", 0);
            burguerQuantity = savedInstanceState.getInt("burguerQuantity", 0);
            carbonaraQuantity = savedInstanceState.getInt("carbonaraQuantity", 0);
            updateQuantities();
        }
    }

    private void updateQuantities() {
        pizzaQuantityTextView.setText(String.valueOf(pizzaQuantity));
        hotDogQuantityTextView.setText(String.valueOf(hotDogQuantity));
        burguerQuantityTextView.setText(String.valueOf(burguerQuantity));
        carbonaraQuantityTextView.setText(String.valueOf(carbonaraQuantity));

        double totalPrice = (pizzaQuantity * pizzaPrice) + (hotDogQuantity * hotDogPrice) +
                (burguerQuantity * burguerPrice) + (carbonaraQuantity * carbonaraPrice);

        checkoutButton.setText(String.format("Checkout: Total: €%.2f", totalPrice));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("pizzaQuantity", pizzaQuantity);
        outState.putInt("hotDogQuantity", hotDogQuantity);
        outState.putInt("burguerQuantity", burguerQuantity);
        outState.putInt("carbonaraQuantity", carbonaraQuantity);
    }
}
