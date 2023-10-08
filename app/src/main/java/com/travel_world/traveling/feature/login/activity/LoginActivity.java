package com.travel_world.traveling.feature.login.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.travel_world.traveling.databinding.ActivityLoginBinding;
import com.travel_world.traveling.feature.login.interfaces.OnListenerLogin;
import com.travel_world.traveling.feature.login.interfaces.OnListenerRegister;

public class LoginActivity extends AppCompatActivity implements OnListenerLogin, OnListenerRegister {

    private ActivityLoginBinding binding;
    private CoordinatorLayout.LayoutParams paramsConstraint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        paramsConstraint = (CoordinatorLayout.LayoutParams) binding.contraintLoginActivity.getLayoutParams();
        setContentView(binding.getRoot());
    }
    @Override
    public void ocultToolbar() {
        binding.barLayoutLogin.setVisibility(View.GONE);
        paramsConstraint.setBehavior(null);
        binding.contraintLoginActivity.requestLayout();
    }

    @Override
    public void showToolbar() {
        binding.barLayoutLogin.setVisibility(View.VISIBLE);
        paramsConstraint.setBehavior(new AppBarLayout.ScrollingViewBehavior());
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}