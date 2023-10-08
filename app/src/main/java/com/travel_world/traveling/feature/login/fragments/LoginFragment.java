package com.travel_world.traveling.feature.login.fragments;

import static com.travel_world.traveling.data.constants.Keys.KEY_USER;
import static com.travel_world.traveling.data.constants.Keys.RESULT_LOGIN;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.Snackbar;
import com.travel_world.traveling.R;
import com.travel_world.traveling.domain.User;
import com.travel_world.traveling.databinding.FragmentLoginBinding;
import com.travel_world.traveling.feature.login.interfaces.OnListenerLogin;
import com.travel_world.traveling.utils.AlertDialogs;

public class LoginFragment extends Fragment {

    private User user;
    private FragmentLoginBinding binding;
    private OnListenerLogin listener;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = new User();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater,container,false);
        listener.ocultToolbar();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonListener();
        inputListener();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnListenerLogin)
            listener = (OnListenerLogin) context;
        else
            throw  new ClassCastException(context + " must implement listener");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    private void buttonListener() {
        binding.buttonLoginRegister.setOnClickListener(v ->
        {
            NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_registerFragment);
            NavHostFragment.findNavController(this).getCurrentBackStackEntry().getSavedStateHandle().getLiveData(RESULT_LOGIN).observe(requireActivity(), o -> {
                user = (User)o;
                if( user != null) {
                    Log.d("ELI", user.getName());
                }
            });
        });
        binding.buttonLogin.setOnClickListener(v->
                goToHomeActivity()
        );
        binding.buttonLoginForgot.setOnClickListener(v->
            Snackbar.make(binding.constraintLayoutLoginFragment,getString(R.string.button_login_forgot_onclick),Snackbar.LENGTH_LONG).show()
        );
    }

    private void goToHomeActivity() {
        if(binding.nameTextLogin.getText()!= null && binding.passwordTextLogin.getText() != null) {
            if (binding.nameTextLogin.getText().toString().equals(user.getName())
                    && binding.passwordTextLogin.getText().toString().equals(user.getPassword())) {
                LoginFragmentDirections.ActionLoginFragmentToHomeActivity action = LoginFragmentDirections.actionLoginFragmentToHomeActivity(user);
                NavHostFragment.findNavController(this).navigate(action);
            } else
                showErrorLoginMessage();
        }

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
                if(binding.passwordTextLogin.getText() != null) {
                    binding.buttonLogin.setEnabled(
                            !s.toString().isEmpty()
                                    && !binding.passwordTextLogin.getText().toString().isEmpty()
                    );
                }
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
                if(binding.nameTextLogin.getText() != null) {
                    binding.buttonLogin.setEnabled(
                            !s.toString().isEmpty()
                                    && !binding.nameTextLogin.getText().toString().isEmpty()
                    );
                }
            }
        });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
        user = null;
        listener = null;
    }
}