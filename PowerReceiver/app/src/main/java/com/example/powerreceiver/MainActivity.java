package com.example.powerreceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static final String ACTION_CUSTOM_BROADCAST =
            BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";
    private CustomReceiver customReceiver = new CustomReceiver();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        this.registerReceiver(customReceiver,intentFilter);

        IntentFilter headsetIntentFilter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        this.registerReceiver(customReceiver,headsetIntentFilter);

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(customReceiver,
                        new IntentFilter(ACTION_CUSTOM_BROADCAST));
    }

    public void sendCustomBroadcast(View view) {
        Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);
    }

    @Override
    protected void onDestroy() {
        this.unregisterReceiver(customReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(customReceiver);
        super.onDestroy();
    }

}