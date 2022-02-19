package com.example.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    public static final String EXTRA_MESSAGE = "com.example.android.activities.extra.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
}
    public void launchSecondActivity(View view){
        Intent openSecondActivity = new Intent(this,SecondActivity.class);
        String passage = "Chill and Display";
        openSecondActivity.putExtra(EXTRA_MESSAGE, passage);
        startActivity(openSecondActivity);

    }
}