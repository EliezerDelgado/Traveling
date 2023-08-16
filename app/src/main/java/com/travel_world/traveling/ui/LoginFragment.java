package com.travel_world.traveling.ui;

import static com.travel_world.traveling.data.constants.Keys.KEY_USER;
import static com.travel_world.traveling.data.constants.Keys.RESULT_LOGIN;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
    /*
    private ActivityResultLauncher<Intent> resultRegister = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if( result.getResultCode() == Activity.RESULT_OK)
                {
                    this.user = result.getData().getExtras().getParcelable(KEY_USER);
                }
            });*/
    private OnListenerLogin listener;


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
        listener.ocultToolbar();
        user = new User();
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
            listener.remplaceFragmentRegister();
            getParentFragmentManager().setFragmentResultListener(RESULT_LOGIN, this, (requestKey, result) -> {
                user = result.getParcelable(KEY_USER);
                Log.d("ELI", user.getName());
            });
            //resultRegister.launch(Intents.intentActivity(requireContext(), RegisterFragment.class));
        });
        binding.buttonLogin.setOnClickListener(v->
                startActivityToHomeActivity()
        );
        binding.buttonLoginForgot.setOnClickListener(v->{
            Snackbar.make(getView(),getString(R.string.button_login_forgot_onclick),Snackbar.LENGTH_LONG).show();
        });
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
                break;
            case R.id.button_login_register:
                Snackbar.make(view,getString(R.string.button_login_register_onclick),Snackbar.LENGTH_LONG).show();
                break;
        }
    }
}