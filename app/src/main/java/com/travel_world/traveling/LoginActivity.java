package com.travel_world.traveling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.travel_world.traveling.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding loginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());
        setBindRepo();
    }

    private void setBindRepo() {
        loginBinding.setUser(new User());
    }
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.button_login_forgot:
                Snackbar.make(view,getString(R.string.button_login_forgot_onclick),Snackbar.LENGTH_LONG).show();
                break;
            case R.id.button_login_register:
                Snackbar.make(view,getString(R.string.button_login_register_onclick),Snackbar.LENGTH_LONG).show();
                break;
        }
    }
}