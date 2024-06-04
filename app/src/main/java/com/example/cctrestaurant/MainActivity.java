package com.example.cctrestaurant;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Increase pizza quantity
    public void increasePizzaQuantity(View view) {
        TextView quantityTextView = findViewById(R.id.pizzaQuantityTextView);
        int currentQuantity = Integer.parseInt(quantityTextView.getText().toString());
        currentQuantity++;
        quantityTextView.setText(String.valueOf(currentQuantity));
    }

    // Decrease pizza quantity
    public void decreasePizzaQuantity(View view) {
        TextView quantityTextView = findViewById(R.id.pizzaQuantityTextView);
        int currentQuantity = Integer.parseInt(quantityTextView.getText().toString());
        if (currentQuantity > 0) {
            currentQuantity--;
            quantityTextView.setText(String.valueOf(currentQuantity));
        }
    }
    // Increase Hot Dog quantity
    public void increaseHotDogQuantity(View view) {
        TextView quantityTextView = findViewById(R.id.hotDogQuantityTextView);
        int currentQuantity = Integer.parseInt(quantityTextView.getText().toString());
        currentQuantity++;
        quantityTextView.setText(String.valueOf(currentQuantity));
    }

    // Decrease Hot Dog quantity
    public void decreaseHotDogQuantity(View view) {
        TextView quantityTextView = findViewById(R.id.hotDogQuantityTextView);
        int currentQuantity = Integer.parseInt(quantityTextView.getText().toString());
        if (currentQuantity > 0) {
            currentQuantity--;
            quantityTextView.setText(String.valueOf(currentQuantity));
        }
    }
    // Increase Burguer quantity
    public void increaseBurguerQuantity(View view) {
        TextView quantityTextView = findViewById(R.id.burguerQuantityTextView);
        int currentQuantity = Integer.parseInt(quantityTextView.getText().toString());
        currentQuantity++;
        quantityTextView.setText(String.valueOf(currentQuantity));
    }

    // Decrease Burguer quantity
    public void decreaseBurguerQuantity(View view) {
        TextView quantityTextView = findViewById(R.id.burguerQuantityTextView);
        int currentQuantity = Integer.parseInt(quantityTextView.getText().toString());
        if (currentQuantity > 0) {
            currentQuantity--;
            quantityTextView.setText(String.valueOf(currentQuantity));
        }
    }
    // Increase Carbonara quantity
    public void increaseCarbonaraQuantity(View view) {
        TextView quantityTextView = findViewById(R.id.carbonaraQuantityTextView);
        int currentQuantity = Integer.parseInt(quantityTextView.getText().toString());
        currentQuantity++;
        quantityTextView.setText(String.valueOf(currentQuantity));
    }

    // Decrease Carbonara quantity
    public void decreaseCarbonaraQuantity(View view) {
        TextView quantityTextView = findViewById(R.id.carbonaraQuantityTextView);
        int currentQuantity = Integer.parseInt(quantityTextView.getText().toString());
        if (currentQuantity > 0) {
            currentQuantity--;
            quantityTextView.setText(String.valueOf(currentQuantity));
        }
    }
    public void checkout (View view){

    }
}

























