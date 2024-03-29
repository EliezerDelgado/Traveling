package com.travel_world.traveling.feature.login.fragments;

import static com.travel_world.traveling.data.constants.Keys.RESULT_LOGIN;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.travel_world.traveling.R;
import com.travel_world.traveling.data.constants.UserRegex;
import com.travel_world.traveling.domain.User;
import com.travel_world.traveling.databinding.FragmentRegisterBinding;
import com.travel_world.traveling.feature.login.interfaces.OnListenerRegister;
import com.travel_world.traveling.utils.AskPermissions;
import com.travel_world.traveling.utils.Intents;
import com.travel_world.traveling.utils.UtilsStrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class RegisterFragment extends Fragment {
    private FragmentRegisterBinding binding;
    private  ArrayList<String> arrayAges;

    private final ActivityResultLauncher<Intent> resultCamera = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result ->
                binding.buttonPhotoRegister.setImageURI(Intents.getCameraImagenReturn())
            );

    private OnListenerRegister listener;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnListenerRegister)
            listener = (OnListenerRegister) context;
        else
            throw  new ClassCastException(context + " must implement listener");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listener.showToolbar();
        arrayAges = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.age_range)));
        setValueSpinnerAges();
        buttonListener();
        inputListener();
    }

    private void buttonListener() {
        binding.buttonPhotoRegister.setOnClickListener(v ->
            launchCamera()
        );
        binding.buttonPrivacyRegister.setOnClickListener(v->
                startActivity(Intents.openPage(getString(R.string.web_developer_google)))
        );
        binding.buttonConfirmRegister.setOnClickListener(v->
            returnToLoginScreen()
        );
    }

    private void returnToLoginScreen() {
        User u = getUser();
        NavHostFragment.findNavController(this).getCurrentBackStackEntry().getSavedStateHandle().set(RESULT_LOGIN, u);
        NavHostFragment.findNavController(this).navigateUp();
    }

    private User getUser() {
        User user = new User();
        user.setName(Objects.requireNonNull(binding.nameTextRegister.getText()).toString());
        user.setLastName(Objects.requireNonNull(binding.lastNameTextRegister.getText()).toString());
        user.setPassword(binding.lastNameTextRegister.getText().toString());
        user.setAgeRange(binding.agesRangeListRegister.getText().toString());
        return user;
    }

    private void launchCamera() {
        AskPermissions.askPermissionCamera(getActivity());
        if(AskPermissions.isPermissionCameraOn(getActivity()))
            resultCamera.launch(Intents.openCamera(requireActivity()));
        else
            Toast.makeText(requireContext(), getString(R.string.error_permission_camera), Toast.LENGTH_SHORT).show();
    }

    private void inputListener() {
        binding.nameTextRegister.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!UtilsStrings.rergularExpressions(s.toString(), UserRegex.REGEX_NAME))
                    binding.nameRegister.setError(getString(R.string.error_name_register));
                else
                    binding.nameRegister.setError(null);
                isAllFineToConfirm();
            }
        });

        binding.lastNameTextRegister.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!UtilsStrings.rergularExpressions(s.toString(), UserRegex.REGEX_LAST_NAME))
                    binding.lastNameRegister.setError(getString(R.string.error_last_name_register));
                else
                    binding.lastNameRegister.setError(null);
                isAllFineToConfirm();
            }
        });
        binding.agesRangeListRegister.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (arrayAges.indexOf(s.toString()) < arrayAges.indexOf(getResources().getString(R.string.age_range_adult)) && !s.toString().isEmpty())
                    binding.agesRangeRegister.setError(getResources().getText(R.string.error_agesRange_lower_years));

                else
                    binding.agesRangeRegister.setError(null);
                isAllFineToConfirm();
            }
        });
    }

    private void isAllFineToConfirm() {
        boolean correctName = binding.nameRegister.getError() == null
                && !Objects.requireNonNull(binding.nameTextRegister.getText()).toString().isEmpty();

        boolean correctLastName = binding.lastNameRegister.getError() == null
                && !Objects.requireNonNull(binding.lastNameTextRegister.getText()).toString().isEmpty();

        boolean correctAgeRange = binding.agesRangeRegister.getError() == null
                && !binding.agesRangeListRegister.getText().toString().isEmpty();
        binding.buttonConfirmRegister.setEnabled(correctName && correctLastName && correctAgeRange);
    }
    private void setValueSpinnerAges() {
        ArrayAdapter<CharSequence> adapterSpinnerAges = ArrayAdapter.createFromResource(requireContext(), R.array.age_range, android.R.layout.simple_spinner_dropdown_item);
        binding.agesRangeListRegister.setAdapter(adapterSpinnerAges);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
        arrayAges = null;
        listener = null;
    }
}