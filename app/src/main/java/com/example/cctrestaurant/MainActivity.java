package com.example.cctrestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private int pizzaQuantity = 0;
    private int hotDogQuantity = 0;
    private int burgerQuantity = 0;
    private int carbonaraQuantity = 0;

    private double pizzaPrice = 14.99;
    private double hotDogPrice = 10.99;
    private double burgerPrice = 12.99;
    private double carbonaraPrice = 19.99;

    private TextView pizzaQuantityTextView;
    private TextView hotDogQuantityTextView;
    private TextView burgerQuantityTextView;
    private TextView carbonaraQuantityTextView;
    private Button checkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pizzaQuantityTextView = findViewById(R.id.pizzaQuantityTextView);
        hotDogQuantityTextView = findViewById(R.id.hotDogQuantityTextView);
        burgerQuantityTextView = findViewById(R.id.burguerQuantityTextView);
        carbonaraQuantityTextView = findViewById(R.id.carbonaraQuantityTextView);
        checkoutButton = findViewById(R.id.checkoutButton);

        updateCheckoutButton();

        checkoutButton.setOnClickListener(v -> checkout());
    }

    private void updateCheckoutButton() {
        double total = (pizzaQuantity * pizzaPrice) +
                (hotDogQuantity * hotDogPrice) +
                (burgerQuantity * burgerPrice) +
                (carbonaraQuantity * carbonaraPrice);
        checkoutButton.setText(String.format("Checkout: Total: €%.2f", total));
    }

    public void increasePizzaQuantity(View view) {
        pizzaQuantity++;
        pizzaQuantityTextView.setText(String.valueOf(pizzaQuantity));
        updateCheckoutButton();
    }

    public void decreasePizzaQuantity(View view) {
        if (pizzaQuantity > 0) {
            pizzaQuantity--;
            pizzaQuantityTextView.setText(String.valueOf(pizzaQuantity));
            updateCheckoutButton();
        }
    }

    public void increaseHotDogQuantity(View view) {
        hotDogQuantity++;
        hotDogQuantityTextView.setText(String.valueOf(hotDogQuantity));
        updateCheckoutButton();
    }

    public void decreaseHotDogQuantity(View view) {
        if (hotDogQuantity > 0) {
            hotDogQuantity--;
            hotDogQuantityTextView.setText(String.valueOf(hotDogQuantity));
            updateCheckoutButton();
        }
    }

    public void increaseBurguerQuantity(View view) {
        burgerQuantity++;
        burgerQuantityTextView.setText(String.valueOf(burgerQuantity));
        updateCheckoutButton();
    }

    public void decreaseBurguerQuantity(View view) {
        if (burgerQuantity > 0) {
            burgerQuantity--;
            burgerQuantityTextView.setText(String.valueOf(burgerQuantity));
            updateCheckoutButton();
        }
    }

    public void increaseCarbonaraQuantity(View view) {
        carbonaraQuantity++;
        carbonaraQuantityTextView.setText(String.valueOf(carbonaraQuantity));
        updateCheckoutButton();
    }

    public void decreaseCarbonaraQuantity(View view) {
        if (carbonaraQuantity > 0) {
            carbonaraQuantity--;
            carbonaraQuantityTextView.setText(String.valueOf(carbonaraQuantity));
            updateCheckoutButton();
        }
    }

    public void checkout() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String surname = intent.getStringExtra("surname");
        String address = intent.getStringExtra("address");
        String phone = intent.getStringExtra("phone");

        StringBuilder items = new StringBuilder();
        if (pizzaQuantity > 0) items.append("Pizza: ").append(pizzaQuantity).append(" x €").append(pizzaPrice).append("\n");
        if (hotDogQuantity > 0) items.append("Hot Dog: ").append(hotDogQuantity).append(" x €").append(hotDogPrice).append("\n");
        if (burgerQuantity > 0) items.append("Burger: ").append(burgerQuantity).append(" x €").append(burgerPrice).append("\n");
        if (carbonaraQuantity > 0) items.append("Carbonara: ").append(carbonaraQuantity).append(" x €").append(carbonaraPrice).append("\n");

        double totalPrice = (pizzaQuantity * pizzaPrice) +
                (hotDogQuantity * hotDogPrice) +
                (burgerQuantity * burgerPrice) +
                (carbonaraQuantity * carbonaraPrice);

        Intent checkoutIntent = new Intent(MainActivity.this, CheckoutActivity.class);
        checkoutIntent.putExtra("name", name + " " + surname);
        checkoutIntent.putExtra("address", address);
        checkoutIntent.putExtra("phone", phone);
        checkoutIntent.putExtra("items", items.toString());
        checkoutIntent.putExtra("totalPrice", totalPrice);
        startActivity(checkoutIntent);
    }
}
