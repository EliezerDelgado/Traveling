package com.travel_world.traveling.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.travel_world.traveling.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActionBarOff();
    }
    private void setActionBarOff() {
        getSupportActionBar().hide();
    }
}