package com.travel_world.traveling.feature.login.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.travel_world.traveling.databinding.ActivityLoginBinding;
import com.travel_world.traveling.feature.login.fragments.LoginFragment;
import com.travel_world.traveling.feature.login.interfaces.OnListenerLogin;
import com.travel_world.traveling.feature.login.interfaces.OnListenerRegister;
import com.travel_world.traveling.feature.login.fragments.RegisterFragment;

public class LoginActivity extends AppCompatActivity implements OnListenerLogin, OnListenerRegister {

    private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
    @Override
    public void ocultToolbar() {
        binding.barLayoutLogin.setVisibility(View.GONE);
    }

    @Override
    public void showToolbar() {
        binding.barLayoutLogin.setVisibility(View.VISIBLE);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}