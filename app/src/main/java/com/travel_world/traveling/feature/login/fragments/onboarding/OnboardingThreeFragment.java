package com.travel_world.traveling.feature.login.fragments.onboarding;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.travel_world.traveling.databinding.FragmentOnboardingThreeBinding;
import com.travel_world.traveling.feature.login.interfaces.OnboardingViewPager2;

public class OnboardingThreeFragment extends Fragment {
    private FragmentOnboardingThreeBinding binding;
    private OnboardingViewPager2.LoginIn listener;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getParentFragment() instanceof OnboardingViewPager2.LoginIn)
            listener = (OnboardingViewPager2.LoginIn) getParentFragment();
        else
            throw new ClassCastException(getParentFragment() + " must implement listener");

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOnboardingThreeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonOnboardingTwoLongIn.setOnClickListener(v -> listener.onLoginClicked());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        listener = null;
        binding = null;
    }
}