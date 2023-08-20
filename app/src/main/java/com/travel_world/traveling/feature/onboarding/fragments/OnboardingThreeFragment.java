package com.travel_world.traveling.feature.onboarding.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.travel_world.traveling.R;
import com.travel_world.traveling.databinding.FragmentOnboardingThreeBinding;
import com.travel_world.traveling.feature.onboarding.interfaces.OnboardingViewPager2;

public class OnboardingThreeFragment extends Fragment {
    private FragmentOnboardingThreeBinding binding;
    private OnboardingViewPager2.Skip listener;

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnboardingViewPager2.Skip)
            listener = (OnboardingViewPager2.Skip) context;
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
        binding = FragmentOnboardingThreeBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonOnboardingTwoLongIn.setOnClickListener(v-> listener.goLoginActivity());
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        listener = null;
        binding = null;
    }
}