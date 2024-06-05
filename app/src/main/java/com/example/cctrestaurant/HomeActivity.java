package com.example.cctrestaurant;

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
    private Button checkoutButton;
    private Switch termsSwitch;
    private RadioGroup paymentRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        nameEditText = findViewById(R.id.nameEditText);
        surnameEditText = findViewById(R.id.surnameEditText);
        addressEditText = findViewById(R.id.addressEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        checkoutButton = findViewById(R.id.checkoutButton);
        termsSwitch = findViewById(R.id.termsSwitch);
        paymentRadioGroup = findViewById(R.id.paymentRadioGroup);

        checkoutButton.setOnClickListener(v -> {
            if (isFormValid()) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                intent.putExtra("name", nameEditText.getText().toString());
                intent.putExtra("surname", surnameEditText.getText().toString());
                intent.putExtra("address", addressEditText.getText().toString());
                intent.putExtra("phone", phoneEditText.getText().toString());
                startActivity(intent);
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
}
