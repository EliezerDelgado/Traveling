package com.travel_world.traveling.ui;
import static com.travel_world.traveling.data.constants.Keys.KEY_USER;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.travel_world.traveling.R;
import com.travel_world.traveling.data.constants.UserRegex;
import com.travel_world.traveling.data.model.User;
import com.travel_world.traveling.databinding.ActivitySignUpBinding;
import com.travel_world.traveling.utils.Intents;
import com.travel_world.traveling.utils.AskPermissions;
import com.travel_world.traveling.utils.UtilsStrings;

import java.util.ArrayList;
import java.util.Arrays;

public class SignUpActivity extends AppCompatActivity {
    private ActivitySignUpBinding signupBinding;
    private ArrayAdapter adapterSpinnerAges;
    private  ArrayList<String> arrayAges;

    private ActivityResultLauncher<Intent> resultCamera = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                signupBinding.buttonPhotoSignup.setImageURI(Intents.getCameraImagenReturn());
            });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBarColor();
        signupBinding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(signupBinding.getRoot());
        arrayAges = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.age_range)));
        setValueSpinnerAges();
        buttonListener();
        inputListener();
    }
    private void buttonListener() {
        signupBinding.buttonPhotoSignup.setOnClickListener(v -> {
            launchCamera();

        });
        signupBinding.buttonPrivacySignup.setOnClickListener(v->
            startActivity(Intents.openPage(getString(R.string.web_developer_google)))
        );
        signupBinding.buttonConfirmSignup.setOnClickListener(v->{
            returnToLoginScreen();
        });
    }

    private void returnToLoginScreen() {
        User u = getUser();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_USER,u);
        setResult(Activity.RESULT_OK,new Intent().putExtras(bundle));
        finish();
    }

    private User getUser() {
        User user = new User();
        user.setName(signupBinding.nameTextSignup.getText().toString());
        user.setLastName(signupBinding.lastNameTextSignup.getText().toString());
        user.setPassword(signupBinding.lastNameTextSignup.getText().toString());
        user.setAgeRange(signupBinding.agesRangeListSignup.getText().toString());
        return user;
    }

    private void launchCamera() {
        AskPermissions.askPermissionCamera(this);
        if(AskPermissions.isPermissionCameraOn(this))
            resultCamera.launch(Intents.openCamera(this));
        else
            Toast.makeText(this, getString(R.string.error_permission_camera), Toast.LENGTH_SHORT).show();
    }

    private void inputListener() {
        signupBinding.nameTextSignup.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!UtilsStrings.rergularExpressions(s.toString(), UserRegex.REGEX_NAME))
                    signupBinding.nameSignup.setError(getString(R.string.error_name_signup));
                else
                    signupBinding.nameSignup.setError(null);
                isAllFineToConfirm();
            }
        });

        signupBinding.lastNameTextSignup.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!UtilsStrings.rergularExpressions(s.toString(), UserRegex.REGEX_LAST_NAME))
                    signupBinding.lastNameSignup.setError(getString(R.string.error_last_name_signup));
                else
                    signupBinding.lastNameSignup.setError(null);
                isAllFineToConfirm();
            }
        });
        signupBinding.agesRangeListSignup.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (arrayAges.indexOf(s.toString()) < arrayAges.indexOf(getResources().getString(R.string.age_range_adult)) && !s.toString().isEmpty())
                {
                    signupBinding.agesRangeSignup.setError(getResources().getText(R.string.error_agesRange_lower_years));
                }
                else
                    signupBinding.agesRangeSignup.setError(null);
            }
        });
    }

    private void isAllFineToConfirm() {
        String name = signupBinding.nameTextSignup.getEditableText().toString();
        String lastName = signupBinding.lastNameTextSignup.getEditableText().toString();
        boolean correctName = signupBinding.nameSignup.getError() == null;
        boolean correctLastName = signupBinding.lastNameSignup.getError() == null;
        if(!name.isEmpty()
                && !lastName.isEmpty()
                && correctName
                && correctLastName)
            signupBinding.buttonConfirmSignup.setEnabled(true);
        else
            signupBinding.buttonConfirmSignup.setEnabled(false);

    }

    private void setBarColor() {
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(
                getResources().getColor(R.color.teal_700,getTheme()))
        );
        getSupportActionBar().setTitle(getString(R.string.sign_up_activty_name));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        getWindow().setStatusBarColor(getResources().getColor(R.color.teal_900,getTheme()));
    }

    private void setValueSpinnerAges() {
        adapterSpinnerAges =  ArrayAdapter.createFromResource(this,R.array.age_range,android.R.layout.simple_spinner_dropdown_item);
        signupBinding.agesRangeListSignup.setAdapter(adapterSpinnerAges);
    }
}