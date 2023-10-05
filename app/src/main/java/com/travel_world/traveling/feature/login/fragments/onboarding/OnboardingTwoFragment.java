package com.travel_world.traveling.feature.login.fragments.onboarding;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.travel_world.traveling.databinding.FragmentOnboardingTwoBinding;
import com.travel_world.traveling.feature.login.interfaces.OnboardingViewPager2;

public class OnboardingTwoFragment extends Fragment {
    private FragmentOnboardingTwoBinding binding;
    private OnboardingViewPager2.FragmentManager listener;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getParentFragment() instanceof OnboardingViewPager2.FragmentManager)
            listener = (OnboardingViewPager2.FragmentManager) getParentFragment();
        else
            throw new ClassCastException(getParentFragment() + " must implement listener");

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOnboardingTwoBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonOnboardingTwoNext.setOnClickListener(v -> listener.onNextClicked());
        binding.buttonOnboardingTwoSkip.setOnClickListener(v -> listener.onSkipClicked());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        listener = null;
        binding = null;
    }
}