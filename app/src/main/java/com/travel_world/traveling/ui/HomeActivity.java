package com.travel_world.traveling.ui;

import static com.travel_world.traveling.data.constants.Keys.KEY_USER;
import static com.travel_world.traveling.data.constants.Tags.TAG_GET_EXTRAS_SUCCESS;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
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
        setBar();
        homeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(homeBinding.getRoot());
        getIntentExtras();
    }
    private void getIntentExtras() {
        if(getIntent().getExtras() != null){
            Bundle bundle = getIntent().getExtras();
            user = bundle.getParcelable(KEY_USER);
            Log.d(TAG_GET_EXTRAS_SUCCESS,user.getName());
        }
    }
    private void setBar() {
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(
                getResources().getColor(R.color.teal_700,getTheme()))
        );
        getSupportActionBar().setTitle(getString(R.string.activity_home_name));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_dehaze);
        getWindow().setStatusBarColor(getResources().getColor(R.color.teal_900,getTheme()));
    }

}