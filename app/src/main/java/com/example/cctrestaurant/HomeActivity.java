package com.example.cctrestaurant;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    // Declare UI elements
    private EditText nameEditText;
    private EditText surnameEditText;
    private EditText addressEditText;
    private EditText phoneEditText;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch termsSwitch;
    private RadioGroup paymentRadioGroup;

    // Variables to hold item quantities
    private int pizzaQuantity;
    private int hotDogQuantity;
    private int burgerQuantity;
    private int carbonaraQuantity;
    private int saladQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize UI elements
        nameEditText = findViewById(R.id.nameEditText);
        surnameEditText = findViewById(R.id.surnameEditText);
        addressEditText = findViewById(R.id.addressEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        Button checkoutButton = findViewById(R.id.checkoutButton);
        termsSwitch = findViewById(R.id.termsSwitch);
        paymentRadioGroup = findViewById(R.id.paymentRadioGroup);

        // Retrieve item quantities from the intent
        Intent intent = getIntent();
        pizzaQuantity = intent.getIntExtra("pizzaQuantity", 0);
        hotDogQuantity = intent.getIntExtra("hotDogQuantity", 0);
        burgerQuantity = intent.getIntExtra("burgerQuantity", 0);
        carbonaraQuantity = intent.getIntExtra("carbonaraQuantity", 0);
        saladQuantity = intent.getIntExtra("saladQuantity", 0);

        // Set onClickListener for checkoutButton
        checkoutButton.setOnClickListener(v -> {
            if (isFormValid()) {
                proceedToCheckout();
            } else {
                // Show a toast message if form is invalid
                Toast.makeText(HomeActivity.this, "Please fill all fields and agree to the terms and conditions", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to validate form fields
    private boolean isFormValid() {
        return !nameEditText.getText().toString().isEmpty() &&
                !surnameEditText.getText().toString().isEmpty() &&
                !addressEditText.getText().toString().isEmpty() &&
                !phoneEditText.getText().toString().isEmpty() &&
                termsSwitch.isChecked() &&
                paymentRadioGroup.getCheckedRadioButtonId() != -1;
    }

    // Method to proceed to the checkout activity
    private void proceedToCheckout() {
        // Concatenate name and surname
        String fullName = nameEditText.getText().toString() + " " + surnameEditText.getText().toString();

        // Create an intent to start the CheckoutActivity
        Intent checkoutIntent = new Intent(HomeActivity.this, CheckoutActivity.class);
        checkoutIntent.putExtra("fullName", fullName);
        checkoutIntent.putExtra("address", addressEditText.getText().toString());
        checkoutIntent.putExtra("phone", phoneEditText.getText().toString());
        checkoutIntent.putExtra("pizzaQuantity", pizzaQuantity);
        checkoutIntent.putExtra("hotDogQuantity", hotDogQuantity);
        checkoutIntent.putExtra("burgerQuantity", burgerQuantity);
        checkoutIntent.putExtra("carbonaraQuantity", carbonaraQuantity);
        checkoutIntent.putExtra("saladQuantity", saladQuantity);
        startActivity(checkoutIntent); // Start the CheckoutActivity
    }
}
