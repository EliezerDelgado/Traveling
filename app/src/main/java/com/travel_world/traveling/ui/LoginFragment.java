package com.travel_world.traveling.ui;

import static com.travel_world.traveling.data.constants.Keys.KEY_USER;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.travel_world.traveling.R;
import com.travel_world.traveling.data.model.User;
import com.travel_world.traveling.databinding.FragmentLoginBinding;
import com.travel_world.traveling.utils.AlertDialogs;
import com.travel_world.traveling.utils.Intents;

public class LoginFragment extends Fragment {
    private User user;
    private FragmentLoginBinding binding;
    private ActivityResultLauncher<Intent> resultRegister = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if( result.getResultCode() == Activity.RESULT_OK)
                {
                    this.user = result.getData().getExtras().getParcelable(KEY_USER);
                }
            });


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        user = new User();
        buttonListener();
        inputListener();
    }

    private void buttonListener() {
        binding.buttonLoginRegister.setOnClickListener(v ->
                resultRegister.launch(Intents.intentActivity(requireContext(), RegisterFragment.class))
        );
        binding.buttonLogin.setOnClickListener(v->
                startActivityToHomeActivity()
        );
    }

    private void startActivityToHomeActivity() {
        if(binding.nameTextLogin.getText().toString().equals(user.getName())
                && binding.passwordTextLogin.getText().toString().equals(user.getPassword())) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(KEY_USER, this.user);
            startActivity(Intents.intentActivityWithExtras(requireContext(), HomeActivity.class, bundle));
        }
        else
            showErrorLoginMessage();

    }

    private void showErrorLoginMessage()
    {
        AlertDialogs.createSimpleInformativeDialog(requireActivity(),
                getResources().getString(R.string.error_login_incorrect_login_or_password),
                getResources().getString(R.string.confirm_message_login)
        ).show();
    }

    private void inputListener() {
        binding.nameTextLogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                binding.buttonLogin.setEnabled(
                        !s.toString().isEmpty()
                                && !binding.passwordTextLogin.getText().toString().isEmpty()
                );
            }
        });

        binding.passwordTextLogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                binding.buttonLogin.setEnabled(
                        !s.toString().isEmpty()
                                && !binding.nameTextLogin.getText().toString().isEmpty()
                );
            }
        });
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