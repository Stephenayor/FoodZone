package com.example.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView = findViewById(R.id.display_message);

        Intent openSecondActivity = getIntent();
        String message = openSecondActivity.getStringExtra(MainActivity.EXTRA_MESSAGE);
        textView.setText(message);
    }


}