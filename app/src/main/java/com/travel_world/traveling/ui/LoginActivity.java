package com.travel_world.traveling.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.travel_world.traveling.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements OnListenerLogin, OnListenerRegister {

    private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addFragments();
    }
    private void addFragments()
    {
        getSupportFragmentManager().beginTransaction().add(binding.loginFragmentLayout.getId(),new LoginFragment())
            .commitAllowingStateLoss();
    }

    @Override
    public void remplaceFragmentRegister() {
        getSupportFragmentManager().beginTransaction().replace(binding.loginFragmentLayout.getId(),new RegisterFragment()).addToBackStack(null)
                .commitAllowingStateLoss();
    }

    @Override
    public void ocultToolbar() {
        binding.barLayoutLogin.setVisibility(View.GONE);
    }

    @Override
    public void showToolbar() {
        binding.barLayoutLogin.setVisibility(View.VISIBLE);
    }
}