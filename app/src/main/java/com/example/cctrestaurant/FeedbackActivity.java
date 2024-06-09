package com.example.cctrestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class FeedbackActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private TextInputEditText feedbackEditText;
    private Button sendFeedbackButton, skipFeedbackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        ratingBar = findViewById(R.id.ratingBar);
        feedbackEditText = findViewById(R.id.feedbackEditText);
        sendFeedbackButton = findViewById(R.id.sendFeedbackButton);
        skipFeedbackButton = findViewById(R.id.skipFeedbackButton);

        sendFeedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFeedback();
            }
        });

        skipFeedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity();
            }
        });
    }

    private void sendFeedback() {
        // Handle sending feedback
        float rating = ratingBar.getRating();
        String feedback = feedbackEditText.getText().toString();

        // Process the feedback here (e.g., send to a server or save locally)

        // Show toast message
        Toast.makeText(this, "Thank you for your feedback!", Toast.LENGTH_SHORT).show();

        goToMainActivity();
    }

    private void goToMainActivity() {
        Intent intent = new Intent(FeedbackActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
