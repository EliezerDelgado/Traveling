package com.travel_world.traveling.ui;

import static com.travel_world.traveling.data.constants.Keys.KEY_USER;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.travel_world.traveling.R;
import com.travel_world.traveling.data.model.User;
import com.travel_world.traveling.databinding.ActivityHomeBinding;
import com.travel_world.traveling.utils.Intents;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding homeBinding;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(homeBinding.getRoot());
        getIntentExtras();
        toolbarListener();
    }
    private void getIntentExtras() {
        if(getIntent().getExtras() != null){
            Bundle bundle = getIntent().getExtras();
            user = bundle.getParcelable(KEY_USER);
            showMensage(user);
        }
    }
    private void toolbarListener() {
        homeBinding.homeToolbar.setOnMenuItemClickListener(item ->{
            if(item.getItemId() == R.id.menu_castle)
            {
                startActivity(Intents.openPage(getResources().getString(R.string.web_eurodisney)));
            } else if (item.getItemId() == R.id.menu_car) {
                startActivity(Intents.intentActivity(this, LilaActivity.class));
            }
            return true;
        });
    }

    private void showMensage(User user)
    {
        Snackbar.make(homeBinding.coordinatorlayoutHome, getString(R.string.user_name_description)+": "+user.getName()+"  "+getString(R.string.user_password_description)+": "+user.getPassword(),
                BaseTransientBottomBar.LENGTH_SHORT).show();
    }
}