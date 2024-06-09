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

    private EditText nameEditText;
    private EditText surnameEditText;
    private EditText addressEditText;
    private EditText phoneEditText;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch termsSwitch;
    private RadioGroup paymentRadioGroup;

    private int pizzaQuantity;
    private int hotDogQuantity;
    private int burgerQuantity;
    private int carbonaraQuantity;
    private int saladQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        nameEditText = findViewById(R.id.nameEditText);
        surnameEditText = findViewById(R.id.surnameEditText);
        addressEditText = findViewById(R.id.addressEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        Button checkoutButton = findViewById(R.id.checkoutButton);
        termsSwitch = findViewById(R.id.termsSwitch);
        paymentRadioGroup = findViewById(R.id.paymentRadioGroup);

        Intent intent = getIntent();
        pizzaQuantity = intent.getIntExtra("pizzaQuantity", 0);
        hotDogQuantity = intent.getIntExtra("hotDogQuantity", 0);
        burgerQuantity = intent.getIntExtra("burgerQuantity", 0);
        carbonaraQuantity = intent.getIntExtra("carbonaraQuantity", 0);
        saladQuantity = intent.getIntExtra("saladQuantity", 0);

        checkoutButton.setOnClickListener(v -> {
            if (isFormValid()) {
                proceedToCheckout();
            } else {
                Toast.makeText(HomeActivity.this, "Please fill all fields and agree to the terms and conditions", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isFormValid() {
        return !nameEditText.getText().toString().isEmpty() &&
                !surnameEditText.getText().toString().isEmpty() &&
                !addressEditText.getText().toString().isEmpty() &&
                !phoneEditText.getText().toString().isEmpty() &&
                termsSwitch.isChecked() &&
                paymentRadioGroup.getCheckedRadioButtonId() != -1;
    }

    private void proceedToCheckout() {
        String fullName = nameEditText.getText().toString() + " " + surnameEditText.getText().toString();

        Intent checkoutIntent = new Intent(HomeActivity.this, CheckoutActivity.class);
        checkoutIntent.putExtra("fullName", fullName);
        checkoutIntent.putExtra("address", addressEditText.getText().toString());
        checkoutIntent.putExtra("phone", phoneEditText.getText().toString());
        checkoutIntent.putExtra("pizzaQuantity", pizzaQuantity);
        checkoutIntent.putExtra("hotDogQuantity", hotDogQuantity);
        checkoutIntent.putExtra("burgerQuantity", burgerQuantity);
        checkoutIntent.putExtra("carbonaraQuantity", carbonaraQuantity);
        checkoutIntent.putExtra("saladQuantity", saladQuantity);
        startActivity(checkoutIntent);
    }
}
