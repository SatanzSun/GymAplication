package com.example.mysqlproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.mysqlproject.R;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
            Intent homeIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(homeIntent);
            finish();
        }

        },SPLASH_TIME_OUT);
}


}
