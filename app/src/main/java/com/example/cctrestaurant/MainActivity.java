package com.example.cctrestaurant;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declare UI elements
    private TextView pizzaQuantityTextView;
    private TextView hotDogQuantityTextView;
    private TextView burgerQuantityTextView;
    private TextView carbonaraQuantityTextView;
    private TextView saladQuantityTextView;
    private Button checkoutButton;

    // Variables to hold item quantities
    private int pizzaQuantity = 0;
    private int hotDogQuantity = 0;
    private int burgerQuantity = 0;
    private int carbonaraQuantity = 0;
    private int saladQuantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        pizzaQuantityTextView = findViewById(R.id.pizzaQuantityTextView);
        hotDogQuantityTextView = findViewById(R.id.hotDogQuantityTextView);
        burgerQuantityTextView = findViewById(R.id.burgerQuantityTextView);
        carbonaraQuantityTextView = findViewById(R.id.carbonaraQuantityTextView);
        saladQuantityTextView = findViewById(R.id.saladQuantityTextView);
        checkoutButton = findViewById(R.id.checkoutButton);

        // Set up onClickListeners for increase and decrease buttons
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

        findViewById(R.id.increaseBurgerQuantity).setOnClickListener(v -> {
            burgerQuantity++;
            updateQuantities();
        });

        findViewById(R.id.decreaseBurgerQuantity).setOnClickListener(v -> {
            if (burgerQuantity > 0) burgerQuantity--;
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

        findViewById(R.id.increaseSaladQuantity).setOnClickListener(v -> {
            saladQuantity++;
            updateQuantities();
        });

        findViewById(R.id.decreaseSaladQuantity).setOnClickListener(v -> {
            if (saladQuantity > 0) saladQuantity--;
            updateQuantities();
        });

        // Set onClickListener for checkoutButton
        checkoutButton.setOnClickListener(v -> {
            // Create an intent to start the HomeActivity
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            // Pass item quantities to HomeActivity
            intent.putExtra("pizzaQuantity", pizzaQuantity);
            intent.putExtra("hotDogQuantity", hotDogQuantity);
            intent.putExtra("burgerQuantity", burgerQuantity);
            intent.putExtra("carbonaraQuantity", carbonaraQuantity);
            intent.putExtra("saladQuantity", saladQuantity);
            startActivity(intent); // Start the HomeActivity
        });

        // Restore the state if it was saved before
        if (savedInstanceState != null) {
            pizzaQuantity = savedInstanceState.getInt("pizzaQuantity", 0);
            hotDogQuantity = savedInstanceState.getInt("hotDogQuantity", 0);
            burgerQuantity = savedInstanceState.getInt("burgerQuantity", 0);
            carbonaraQuantity = savedInstanceState.getInt("carbonaraQuantity", 0);
            saladQuantity = savedInstanceState.getInt("saladQuantity", 0);
            updateQuantities();
        }
    }

    // Method to update UI with the current quantities and calculate the total price
    @SuppressLint("DefaultLocale")
    private void updateQuantities() {
        // Update TextViews with current quantities
        pizzaQuantityTextView.setText(String.valueOf(pizzaQuantity));
        hotDogQuantityTextView.setText(String.valueOf(hotDogQuantity));
        burgerQuantityTextView.setText(String.valueOf(burgerQuantity));
        carbonaraQuantityTextView.setText(String.valueOf(carbonaraQuantity));
        saladQuantityTextView.setText(String.valueOf(saladQuantity));

        // Prices for items
        double saladPrice = 9.99;
        double carbonaraPrice = 19.99;
        double burgerPrice = 12.99;
        double hotDogPrice = 10.99;
        double pizzaPrice = 14.99;

        // Calculate total price
        double totalPrice = (pizzaQuantity * pizzaPrice) + (hotDogQuantity * hotDogPrice) +
                (burgerQuantity * burgerPrice) + (carbonaraQuantity * carbonaraPrice) +
                (saladQuantity * saladPrice);

        // Update checkout button text with the total price
        checkoutButton.setText(String.format("Total: €%.2f", totalPrice));
    }

    // Save the current state of the activity (e.g., item quantities)
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("pizzaQuantity", pizzaQuantity);
        outState.putInt("hotDogQuantity", hotDogQuantity);
        outState.putInt("burgerQuantity", burgerQuantity);
        outState.putInt("carbonaraQuantity", carbonaraQuantity);
        outState.putInt("saladQuantity", saladQuantity);
    }
}
