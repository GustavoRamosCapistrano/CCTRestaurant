package com.example.cctrestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog; // Create an alert to tell the user something
import android.os.Bundle;
import android.view.LayoutInflater; // The inflator is it injects information into a dialog
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//    Contains the start of the information flow
        setupPurchaseButton(R.id.buyPizza, R.id.pizza, R.id.pizzaPrice);


    }


    //Create a method that will take in the information
//    Product info == Button Number , Name , Price
    private void setupPurchaseButton(int buttonId, int productName, int productPrice){

        Button purchaseButton = findViewById(buttonId);

        purchaseButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                // We have to capture the Textview values
                TextView productNameTextView = findViewById(productName);
                TextView productPriceTextView = findViewById(productPrice);

                //  and convert them to a String
                String productName = productNameTextView.getText().toString();
                String productPrice = productPriceTextView.getText().toString();

                // Create an alert dialog to display

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                // Inflate a dialog with the form
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.purchase_form, null);
                builder.setView(dialogView);

                // Display the built View of the Dialog
                AlertDialog dialog = builder.create();
                dialog.show();

                Button submitButton = dialogView.findViewById(R.id.purchaseButton);

                submitButton.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v){

                        //Capture user info
                        EditText namePurchase = dialogView.findViewById(R.id.purchaseName);
                        EditText addressPurchase = dialogView.findViewById(R.id.purchaseAddress);
                        EditText quantityPurchase = dialogView.findViewById(R.id.purchaseAmount);

                        // Convert them to strings
                        String name = namePurchase.getText().toString();
                        String address = addressPurchase.getText().toString();
                        String quantity = quantityPurchase.getText().toString();

                        // Validate that the user has filled the form
                        if(name.isEmpty() ||  address.isEmpty() || quantity.isEmpty()){

                            Toast.makeText(getApplicationContext(), "Please fill in all the fields!", Toast.LENGTH_SHORT).show();

                        }else{

                            String purchaseDetails = "Purchased " + productName +
                                    " Price: " + productPrice +
                                    " for: " + name +
                                    " at " + address +
                                    " amount " + quantity;

                            Toast.makeText(getApplicationContext(), purchaseDetails, Toast.LENGTH_LONG).show();

                            dialog.dismiss();


                        }
                    }
                });
            }
        });
    }
}










































