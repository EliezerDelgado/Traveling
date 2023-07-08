package com.travel_world.traveling;

import static com.travel_world.traveling.AgeRange.*;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import com.travel_world.traveling.databinding.ActivitySignUpBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class SignUpActivity extends AppCompatActivity {
    private ActivitySignUpBinding binding;
    private ArrayAdapter adapterSpinnerAges;
    private  ArrayList<String> arrayAges;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        arrayAges = new ArrayList<>(Arrays.asList(BABY,CHILD,TEENAGER,ADULT));
        setValueSpinnerAges();
    }
    private void setValueSpinnerAges() {
        adapterSpinnerAges = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arrayAges);
        binding.agesRangeListSignup.setAdapter(adapterSpinnerAges);
    }
}