package com.travel_world.traveling.ui;
import static com.travel_world.traveling.data.constants.Keys.KEY_USER;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.travel_world.traveling.R;
import com.travel_world.traveling.data.constants.UserRegex;
import com.travel_world.traveling.data.model.User;
import com.travel_world.traveling.databinding.FragmentRegisterBinding;
import com.travel_world.traveling.utils.Intents;
import com.travel_world.traveling.utils.AskPermissions;
import com.travel_world.traveling.utils.UtilsStrings;

import java.util.ArrayList;
import java.util.Arrays;

public class RegisterFragment extends Fragment {
    private FragmentRegisterBinding binding;
    private ArrayAdapter adapterSpinnerAges;
    private  ArrayList<String> arrayAges;

    private ActivityResultLauncher<Intent> resultCamera = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                binding.buttonPhotoRegister.setImageURI(Intents.getCameraImagenReturn());
            });
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrayAges = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.age_range)));
        setValueSpinnerAges();
        buttonListener();
        inputListener();
    }

    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    private void buttonListener() {
        binding.buttonPhotoRegister.setOnClickListener(v -> {
            launchCamera();

        });
        binding.buttonPrivacyRegister.setOnClickListener(v->
                startActivity(Intents.openPage(getString(R.string.web_developer_google)))
        );
        binding.buttonConfirmRegister.setOnClickListener(v->{
            returnToLoginScreen();
        });
    }

    private void returnToLoginScreen() {
        User u = getUser();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_USER,u);
        /*
        setResult(Activity.RESULT_OK,new Intent().putExtras(bundle));
        finish();
         */
    }

    private User getUser() {
        User user = new User();
        user.setName(binding.nameTextRegister.getText().toString());
        user.setLastName(binding.lastNameTextRegister.getText().toString());
        user.setPassword(binding.lastNameTextRegister.getText().toString());
        user.setAgeRange(binding.agesRangeListRegister.getText().toString());
        return user;
    }

    private void launchCamera() {
        AskPermissions.askPermissionCamera(getActivity());
        if(AskPermissions.isPermissionCameraOn(getActivity()))
            resultCamera.launch(Intents.openCamera(getActivity()));
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
                && !binding.nameTextRegister.getText().toString().isEmpty();

        boolean correctLastName = binding.lastNameRegister.getError() == null
                && !binding.lastNameTextRegister.getText().toString().isEmpty();

        boolean correctAgeRange = binding.agesRangeRegister.getError() == null
                && !binding.agesRangeListRegister.getText().toString().isEmpty();

        if(correctName && correctLastName    && correctAgeRange)
            binding.buttonConfirmRegister.setEnabled(true);
        else
            binding.buttonConfirmRegister.setEnabled(false);

    }
    private void setValueSpinnerAges() {
        adapterSpinnerAges =  ArrayAdapter.createFromResource(requireContext(),R.array.age_range,android.R.layout.simple_spinner_dropdown_item);
        binding.agesRangeListRegister.setAdapter(adapterSpinnerAges);
    }
}