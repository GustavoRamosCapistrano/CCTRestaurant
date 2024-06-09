package com.example.cctrestaurant;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class CheckoutActivity extends AppCompatActivity {

    private static final int REQUEST_NOTIFICATION_PERMISSION = 1;
    private static final int EDIT_ORDER_REQUEST_CODE = 2;
    private static final String CHANNEL_ID = "order_channel";

    private TextView itemsTextView;
    private TextView totalPriceTextView;

    private final double pizzaPrice = 14.99;
    private final double hotDogPrice = 10.99;
    private final double burgerPrice = 12.99;
    private final double carbonaraPrice = 19.99;
    private final double saladPrice = 9.99;

    @SuppressLint({"MissingInflatedId", "DefaultLocale"})
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        TextView nameTextView = findViewById(R.id.nameTextView);
        EditText surnameEditText = findViewById(R.id.surnameEditText);
        TextView addressTextView = findViewById(R.id.addressTextView);
        TextView phoneTextView = findViewById(R.id.phoneTextView);
        itemsTextView = findViewById(R.id.itemsTextView);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);
        Button editOrderButton = findViewById(R.id.editOrderButton);
        Button confirmButton = findViewById(R.id.confirmButton);

        Intent intent = getIntent();
        String fullName = intent.getStringExtra("fullName");
        String address = intent.getStringExtra("address");
        String phone = intent.getStringExtra("phone");
        int pizzaQuantity = intent.getIntExtra("pizzaQuantity", 0);
        int hotDogQuantity = intent.getIntExtra("hotDogQuantity", 0);
        int burgerQuantity = intent.getIntExtra("burgerQuantity", 0);
        int carbonaraQuantity = intent.getIntExtra("carbonaraQuantity", 0);
        int saladQuantity = intent.getIntExtra("saladQuantity", 0);

        nameTextView.setText(fullName);
        addressTextView.setText(address);
        phoneTextView.setText(phone);

        StringBuilder itemsBuilder = new StringBuilder();
        if (pizzaQuantity > 0) {
            itemsBuilder.append("Pizza: ").append(pizzaQuantity).append(" X ").append(pizzaPrice).append("\n");
        }
        if (hotDogQuantity > 0) {
            itemsBuilder.append("Hot Dog: ").append(hotDogQuantity).append(" X ").append(hotDogPrice).append("\n");
        }
        if (burgerQuantity > 0) {
            itemsBuilder.append("Burger: ").append(burgerQuantity).append(" X ").append(burgerPrice).append("\n");
        }
        if (carbonaraQuantity > 0) {
            itemsBuilder.append("Carbonara: ").append(carbonaraQuantity).append(" X ").append(carbonaraPrice).append("\n");
        }
        if (saladQuantity > 0) {
            itemsBuilder.append("Salad: ").append(saladQuantity).append(" X ").append(saladPrice).append("\n");
        }
        String items = itemsBuilder.toString();
        itemsTextView.setText(items);

        double totalPrice = (pizzaQuantity * pizzaPrice) + (hotDogQuantity * hotDogPrice) +
                (burgerQuantity * burgerPrice) + (carbonaraQuantity * carbonaraPrice) +
                (saladQuantity * saladPrice);
        totalPriceTextView.setText(String.format("€%.2f", totalPrice));

        editOrderButton.setOnClickListener(v -> {
            Intent editIntent = new Intent(CheckoutActivity.this, MainActivity.class);
            editIntent.putExtra("pizzaQuantity", pizzaQuantity);
            editIntent.putExtra("hotDogQuantity", hotDogQuantity);
            editIntent.putExtra("burgerQuantity", burgerQuantity);
            editIntent.putExtra("carbonaraQuantity", carbonaraQuantity);
            editIntent.putExtra("saladQuantity", saladQuantity);
            startActivityForResult(editIntent, EDIT_ORDER_REQUEST_CODE);
        });

        confirmButton.setOnClickListener(v -> {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                showNotification();
                navigateToFeedbackActivity();
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.POST_NOTIFICATIONS)) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, REQUEST_NOTIFICATION_PERMISSION);
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, REQUEST_NOTIFICATION_PERMISSION);
                }
            }
        });

        createNotificationChannel();
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_ORDER_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            int pizzaQuantity = data.getIntExtra("pizzaQuantity", 0);
            int hotDogQuantity = data.getIntExtra("hotDogQuantity", 0);
            int burgerQuantity = data.getIntExtra("burgerQuantity", 0);
            int carbonaraQuantity = data.getIntExtra("carbonaraQuantity", 0);
            int saladQuantity = data.getIntExtra("saladQuantity", 0);

            StringBuilder itemsBuilder = new StringBuilder();
            if (pizzaQuantity > 0) {
                itemsBuilder.append("Pizza: ").append(pizzaQuantity).append(" X ").append(pizzaPrice).append("\n");
            }
            if (hotDogQuantity > 0) {
                itemsBuilder.append("Hot Dog: ").append(hotDogQuantity).append(" X ").append(hotDogPrice).append("\n");
            }
            if (burgerQuantity > 0) {
                itemsBuilder.append("Burger: ").append(burgerQuantity).append(" X ").append(burgerPrice).append("\n");
            }
            if (carbonaraQuantity > 0) {
                itemsBuilder.append("Carbonara: ").append(carbonaraQuantity).append(" X ").append(carbonaraPrice).append("\n");
            }
            if (saladQuantity > 0) {
                itemsBuilder.append("Salad: ").append(saladQuantity).append(" X ").append(saladPrice).append("\n");
            }
            String items = itemsBuilder.toString();
            itemsTextView.setText(items);

            double totalPrice = (pizzaQuantity * pizzaPrice) + (hotDogQuantity * hotDogPrice) +
                    (burgerQuantity * burgerPrice) + (carbonaraQuantity * carbonaraPrice) +
                    (saladQuantity * saladPrice);
            totalPriceTextView.setText(String.format("€%.2f", totalPrice));
        }
    }

    private void showNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_order)
                .setContentTitle("Order Confirmed")
                .setContentText("Your order has been placed successfully.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        notificationManager.notify(1, builder.build());

        showCustomToast();
    }

    private void showCustomToast() {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.ic_order);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(imageView);
        toast.show();

        navigateToFeedbackActivity();
    }

    private void navigateToFeedbackActivity() {
        Intent intent = new Intent(CheckoutActivity.this, FeedbackActivity.class);
        startActivity(intent);
    }

    private void createNotificationChannel() {
        CharSequence name = "Order Channel";
        String description = "Channel for order notifications";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(description);

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_NOTIFICATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showNotification();
                navigateToFeedbackActivity();
            } else {
                Toast.makeText(this, "Permission denied to post notifications", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
