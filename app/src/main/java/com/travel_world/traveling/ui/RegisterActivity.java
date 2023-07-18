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
import com.travel_world.traveling.databinding.ActivityRegisterBinding;
import com.travel_world.traveling.utils.Intents;
import com.travel_world.traveling.utils.AskPermissions;
import com.travel_world.traveling.utils.UtilsStrings;

import java.util.ArrayList;
import java.util.Arrays;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding registerBinding;
    private ArrayAdapter adapterSpinnerAges;
    private  ArrayList<String> arrayAges;

    private ActivityResultLauncher<Intent> resultCamera = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                registerBinding.buttonPhotoRegister.setImageURI(Intents.getCameraImagenReturn());
            });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(registerBinding.getRoot());
        arrayAges = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.age_range)));
        setValueSpinnerAges();
        buttonListener();
        inputListener();
    }
    private void buttonListener() {
        registerBinding.buttonPhotoRegister.setOnClickListener(v -> {
            launchCamera();

        });
        registerBinding.buttonPrivacyRegister.setOnClickListener(v->
                startActivity(Intents.openPage(getString(R.string.web_developer_google)))
        );
        registerBinding.buttonConfirmRegister.setOnClickListener(v->{
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
        user.setName(registerBinding.nameTextRegister.getText().toString());
        user.setLastName(registerBinding.lastNameTextRegister.getText().toString());
        user.setPassword(registerBinding.lastNameTextRegister.getText().toString());
        user.setAgeRange(registerBinding.agesRangeListRegister.getText().toString());
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
        registerBinding.nameTextRegister.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!UtilsStrings.rergularExpressions(s.toString(), UserRegex.REGEX_NAME))
                    registerBinding.nameRegister.setError(getString(R.string.error_name_register));
                else
                    registerBinding.nameRegister.setError(null);
                isAllFineToConfirm();
            }
        });

        registerBinding.lastNameTextRegister.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!UtilsStrings.rergularExpressions(s.toString(), UserRegex.REGEX_LAST_NAME))
                    registerBinding.lastNameRegister.setError(getString(R.string.error_last_name_register));
                else
                    registerBinding.lastNameRegister.setError(null);
                isAllFineToConfirm();
            }
        });
        registerBinding.agesRangeListRegister.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (arrayAges.indexOf(s.toString()) < arrayAges.indexOf(getResources().getString(R.string.age_range_adult)) && !s.toString().isEmpty())
                    registerBinding.agesRangeRegister.setError(getResources().getText(R.string.error_agesRange_lower_years));

                else
                    registerBinding.agesRangeRegister.setError(null);
                isAllFineToConfirm();
            }
        });
    }

    private void isAllFineToConfirm() {
        boolean correctName = registerBinding.nameRegister.getError() == null
                && !registerBinding.nameTextRegister.getText().toString().isEmpty();

        boolean correctLastName = registerBinding.lastNameRegister.getError() == null
                && !registerBinding.lastNameTextRegister.getText().toString().isEmpty();

        boolean correctAgeRange = registerBinding.agesRangeRegister.getError() == null
                && !registerBinding.agesRangeListRegister.getText().toString().isEmpty();

        if(correctName && correctLastName    && correctAgeRange)
            registerBinding.buttonConfirmRegister.setEnabled(true);
        else
            registerBinding.buttonConfirmRegister.setEnabled(false);

    }
    private void setValueSpinnerAges() {
        adapterSpinnerAges =  ArrayAdapter.createFromResource(this,R.array.age_range,android.R.layout.simple_spinner_dropdown_item);
        registerBinding.agesRangeListRegister.setAdapter(adapterSpinnerAges);
    }
}