package com.travel_world.traveling.feature.login.fragments.onboarding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.travel_world.traveling.R;
import com.travel_world.traveling.databinding.FragmentOnboardingBinding;
import com.travel_world.traveling.feature.login.adapter.OnboardingPagerAdapter;
import com.travel_world.traveling.feature.login.interfaces.OnboardingViewPager2;

public class OnboardingFragment extends Fragment implements OnboardingViewPager2.FragmentManager {

    private FragmentOnboardingBinding binding;
    private OnboardingPagerAdapter adapterVP2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOnboardingBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createOnboardingViewPager2();
    }

    private void createOnboardingViewPager2() {
        adapterVP2 = new OnboardingPagerAdapter(getParentFragmentManager(), getLifecycle());
        adapterVP2.addFragment(OnboardingOneFragment.class);
        adapterVP2.addFragment(OnboardingTwoFragment.class);
        adapterVP2.addFragment(OnboardingThreeFragment.class);
        binding.onboardingViewpager2.setAdapter(adapterVP2);
        binding.onboardingViewpager2.setUserInputEnabled(false);
    }

    @Override
    public void onNextClicked() {
        int item = binding.onboardingViewpager2.getCurrentItem();
        binding.onboardingViewpager2.setCurrentItem(++item, true);
    }

    @Override
    public void onSkipClicked() {
        navigateToLoginFragment();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
        adapterVP2 = null;
    }

    @Override
    public void onLoginClicked() {
        navigateToLoginFragment();
    }

    /*
        private void navigateToLoginActivity() {
            startActivity(Intents.intentActivity(this, LoginActivity.class));
            finish();
        }
     */
    private void navigateToLoginFragment() {
        NavHostFragment.findNavController(this).navigate(R.id.action_onboard_fragment_to_loginFragment);
    }
}