package com.travel_world.traveling.ui;

import static com.travel_world.traveling.data.constants.Keys.KEY_USER;
import static com.travel_world.traveling.data.constants.Tags.TAG_GET_EXTRAS_SUCCESS;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.travel_world.traveling.R;
import com.travel_world.traveling.data.model.User;
import com.travel_world.traveling.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding homeBinding;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(homeBinding.getRoot());
        setActionBarOff();
        getIntentExtras();
    }
    private void getIntentExtras() {
        if(getIntent().getExtras() != null){
            Bundle bundle = getIntent().getExtras();
            user = bundle.getParcelable(KEY_USER);
            Log.d(TAG_GET_EXTRAS_SUCCESS,user.getName());
        }
    }
    private void setActionBarOff() {
        getSupportActionBar().hide();
    }
}