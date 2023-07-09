package com.travel_world.traveling.ui;

import static com.travel_world.traveling.data.constants.AgeRange.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;

import com.google.android.material.textfield.TextInputLayout;
import com.travel_world.traveling.R;
import com.travel_world.traveling.data.constants.UserRegex;
import com.travel_world.traveling.databinding.ActivitySignUpBinding;
import com.travel_world.traveling.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;

public class SignUpActivity extends AppCompatActivity {
    private ActivitySignUpBinding binding;
    private ArrayAdapter adapterSpinnerAges;
    private  ArrayList<String> arrayAges;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBarColor();
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        arrayAges = new ArrayList<>(Arrays.asList(BABY,CHILD,TEENAGER,ADULT));
        setValueSpinnerAges();
        inputListener();
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
                if(!Utils.rergularExpressions(s.toString(), UserRegex.REGEX_NAME))
                    binding.nameSignup.setError(getString(R.string.error_name_signup));
                else
                    binding.nameSignup.setError(null);
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
                if(!Utils.rergularExpressions(s.toString(), UserRegex.REGEX_LAST_NAME))
                    binding.nameSignup.setError(getString(R.string.error_last_name_signup));
                else
                    binding.nameSignup.setError(null);
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

    private void setBarColor() {
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(
                getResources().getColor(R.color.teal_700,getTheme()))
        );
        getWindow().setStatusBarColor(getResources().getColor(R.color.teal_900,getTheme()));
    }

    private void setValueSpinnerAges() {
        adapterSpinnerAges = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arrayAges);
        binding.agesRangeListSignup.setAdapter(adapterSpinnerAges);
    }
}