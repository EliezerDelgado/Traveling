package com.travel_world.traveling.feature.onboarding.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.travel_world.traveling.databinding.FragmentOnboardingTwoBinding;
import com.travel_world.traveling.feature.onboarding.interfaces.OnboardingViewPager2;

public class OnboardingTwoFragment extends Fragment {
    private FragmentOnboardingTwoBinding binding;
    private OnboardingViewPager2.FragmentManager listener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnboardingViewPager2.FragmentManager)
            listener = (OnboardingViewPager2.FragmentManager) context;
        else
            throw  new ClassCastException(context + " must implement listener");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOnboardingTwoBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonOnboardingTwoNext.setOnClickListener(v-> listener.onNextClicked());
        binding.buttonOnboardingTwoSkip.setOnClickListener(v-> listener.onSkipClicked());
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        listener = null;
        binding = null;
    }
}