package com.travel_world.traveling.feature.onboarding.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.travel_world.traveling.R;
import com.travel_world.traveling.databinding.ActivityOnboardingBinding;

public class OnboardingActivity extends AppCompatActivity {

    private  ActivityOnboardingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnboardingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}