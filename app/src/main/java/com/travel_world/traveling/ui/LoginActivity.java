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

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding loginBinding;
    private User user;
    private ActivityResultLauncher<Intent> resultRegister = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if( result.getResultCode() == Activity.RESULT_OK)
                {
                    this.user = result.getData().getExtras().getParcelable(KEY_USER);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());
        user = new User();
        setBindRepo();
        buttonListener();
        inputListener();
    }

    private void buttonListener() {
        loginBinding.buttonLoginRegister.setOnClickListener(v ->
            resultRegister.launch(Intents.intentActivity(this, RegisterActivity.class))
        );
        loginBinding.buttonLogin.setOnClickListener(v->
                startActivityToHomeActivity()
        );
    }

    private void startActivityToHomeActivity() {
        if(loginBinding.nameTextLogin.getText().toString().equals(user.getName())
                && loginBinding.passwordTextLogin.getText().toString().equals(user.getPassword())) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(KEY_USER, this.user);
            startActivity(Intents.intentActivityWithExtras(this, HomeActivity.class, bundle));
        }
        else
            showErrorLoginMessage();

    }

    private void showErrorLoginMessage()
    {
        AlertDialogs.createSimpleInformativeDialog(this,
                getResources().getString(R.string.error_login_incorrect_login_or_password),
                getResources().getString(R.string.confirm_message_login)
        ).show();
    }

    private void inputListener() {
        loginBinding.nameTextLogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                loginBinding.buttonLogin.setEnabled(
                        !s.toString().isEmpty()
                                && !loginBinding.passwordTextLogin.getText().toString().isEmpty()
                );
            }
        });

        loginBinding.passwordTextLogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                loginBinding.buttonLogin.setEnabled(
                        !s.toString().isEmpty()
                                && !loginBinding.nameTextLogin.getText().toString().isEmpty()
                );
            }
        });
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