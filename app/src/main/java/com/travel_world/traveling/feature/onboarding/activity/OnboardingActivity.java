package com.travel_world.traveling.feature.onboarding.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.travel_world.traveling.databinding.ActivityOnboardingBinding;
import com.travel_world.traveling.feature.login.activity.LoginActivity;
import com.travel_world.traveling.feature.onboarding.adapter.OnboardingPagerAdapter;
import com.travel_world.traveling.feature.onboarding.fragments.OnboardingOneFragment;
import com.travel_world.traveling.feature.onboarding.fragments.OnboardingThreeFragment;
import com.travel_world.traveling.feature.onboarding.fragments.OnboardingTwoFragment;
import com.travel_world.traveling.feature.onboarding.interfaces.OnboardingViewPager2;
import com.travel_world.traveling.utils.Intents;

public class OnboardingActivity extends AppCompatActivity implements OnboardingViewPager2.All {

    private  ActivityOnboardingBinding binding;
    private OnboardingPagerAdapter adapterVP2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnboardingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        createOnboardingViewPager2();
    }


    private void createOnboardingViewPager2() {
        adapterVP2 = new OnboardingPagerAdapter(this);
        adapterVP2.addFragment(new OnboardingOneFragment());
        adapterVP2.addFragment(new OnboardingTwoFragment());
        adapterVP2.addFragment(new OnboardingThreeFragment());
        binding.onboardingViewpager2.setAdapter(adapterVP2);
        binding.onboardingViewpager2.setUserInputEnabled(false);
    }

    @Override
    public void nextFragment()
    {
        int item = binding.onboardingViewpager2.getCurrentItem();
        binding.onboardingViewpager2.setCurrentItem(++item);
    }

    @Override
    public void goLoginActivity() {
        startActivity(Intents.intentActivity(this, LoginActivity.class));
        finish();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
        adapterVP2 = null;
    }
}