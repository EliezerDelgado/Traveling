package com.travel_world.traveling.ui;

import static com.travel_world.traveling.data.constants.Keys.KEY_USER;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.travel_world.traveling.R;
import com.travel_world.traveling.data.model.User;
import com.travel_world.traveling.databinding.ActivityLoginBinding;
import com.travel_world.traveling.utils.AlertDialogs;
import com.travel_world.traveling.utils.Intents;

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