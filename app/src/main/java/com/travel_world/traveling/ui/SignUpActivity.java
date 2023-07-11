package com.travel_world.traveling.ui;

import static com.travel_world.traveling.data.constants.AgeRange.*;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.travel_world.traveling.R;
import com.travel_world.traveling.data.constants.UserRegex;
import com.travel_world.traveling.databinding.ActivitySignUpBinding;
import com.travel_world.traveling.utils.Intents;
import com.travel_world.traveling.utils.AskPermissions;
import com.travel_world.traveling.utils.UtilsStrings;

import java.util.ArrayList;
import java.util.Arrays;

public class SignUpActivity extends AppCompatActivity {
    private ActivitySignUpBinding binding;
    private ArrayAdapter adapterSpinnerAges;
    private  ArrayList<String> arrayAges;

    private ActivityResultLauncher<Intent> resultCamera = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                binding.photoButtonSignup.setImageURI(Intents.getCameraImagenReturn());
            });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBarColor();
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        arrayAges = new ArrayList<>(Arrays.asList(BABY,CHILD,TEENAGER,ADULT));
        setValueSpinnerAges();
        buttonListener();
        inputListener();
    }
    private void buttonListener() {
        int i = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) ;
        int j = PackageManager.PERMISSION_GRANTED;
        binding.photoButtonSignup.setOnClickListener(v -> {
            AskPermissions.askPermissionCamera(this);
            if(AskPermissions.isPermissionCameraOn(this))
                resultCamera.launch(Intents.openCamera(this));
            else
                Toast.makeText(this, getString(R.string.error_permission_camera), Toast.LENGTH_SHORT).show();
        });
        binding.privacyButtonSignup.setOnClickListener(v->{
            startActivity(Intents.openPage(getString(R.string.web_developer_google)));
        });
    }

    private void inputListener() {
        binding.nameTextSignup.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!UtilsStrings.rergularExpressions(s.toString(), UserRegex.REGEX_NAME))
                    binding.nameSignup.setError(getString(R.string.error_name_signup));
                else
                    binding.nameSignup.setError(null);
                isAllFineToConfirm();
            }
        });

        binding.lastNameTextSignup.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!UtilsStrings.rergularExpressions(s.toString(), UserRegex.REGEX_LAST_NAME))
                    binding.lastNameSignup.setError(getString(R.string.error_last_name_signup));
                else
                    binding.lastNameSignup.setError(null);
                isAllFineToConfirm();
            }
        });
        binding.agesRangeListSignup.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (arrayAges.indexOf(s.toString()) < arrayAges.indexOf(ADULT) && s.toString() != "")
                {
                    binding.agesRangeSignup.setError(getResources().getText(R.string.error_agesRange_lower_years));
                }
                else
                    binding.agesRangeSignup.setError(null);
            }
        });
    }

    private void isAllFineToConfirm() {
        String name = binding.nameTextSignup.getEditableText().toString();
        String lastName = binding.lastNameTextSignup.getEditableText().toString();
        boolean correctName = binding.nameSignup.getError() == null;
        boolean correctLastName = binding.lastNameSignup.getError() == null;
        if(!name.isEmpty()
                && !lastName.isEmpty()
                && correctName
                && correctLastName)
            binding.confirmSignup.setEnabled(true);
        else
            binding.confirmSignup.setEnabled(false);

    }

    private void setBarColor() {
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(
                getResources().getColor(R.color.teal_700,getTheme()))
        );
        getSupportActionBar().setTitle(getString(R.string.sign_up_activty_name));
        getWindow().setStatusBarColor(getResources().getColor(R.color.teal_900,getTheme()));
    }

    private void setValueSpinnerAges() {
        adapterSpinnerAges = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arrayAges);
        binding.agesRangeListSignup.setAdapter(adapterSpinnerAges);
    }
}