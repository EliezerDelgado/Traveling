package com.travel_world.traveling.feature.login.activity;

import static com.travel_world.traveling.data.constants.Keys.CHANNEL_NOTIFICATION;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.travel_world.traveling.R;
import com.travel_world.traveling.databinding.ActivityLoginBinding;
import com.travel_world.traveling.feature.login.interfaces.OnListenerLogin;
import com.travel_world.traveling.feature.login.interfaces.OnListenerRegister;

public class LoginActivity extends AppCompatActivity implements OnListenerLogin, OnListenerRegister {

    private ActivityLoginBinding binding;
    private CoordinatorLayout.LayoutParams paramsConstraint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        createNotificationChannel();
        paramsConstraint = (CoordinatorLayout.LayoutParams) binding.contraintLoginActivity.getLayoutParams();
        setContentView(binding.getRoot());
    }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is not in the Support Library.
        CharSequence name = getString(R.string.channel_name);
        String description = getString(R.string.channel_description);
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_NOTIFICATION, name, importance);
        channel.setDescription(description);
        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this.
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }
    @Override
    public void ocultToolbar() {
        binding.barLayoutLogin.setVisibility(View.GONE);
        paramsConstraint.setBehavior(null);
        binding.contraintLoginActivity.requestLayout();
    }

    @Override
    public void showToolbar() {
        binding.barLayoutLogin.setVisibility(View.VISIBLE);
        paramsConstraint.setBehavior(new AppBarLayout.ScrollingViewBehavior());
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}