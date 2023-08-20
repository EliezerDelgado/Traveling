package com.travel_world.traveling.feature.onboarding.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.travel_world.traveling.databinding.FragmentOnboardingOneBinding;
import com.travel_world.traveling.feature.onboarding.interfaces.OnboardingViewPager2;

public class OnboardingOneFragment extends Fragment {

    private FragmentOnboardingOneBinding binding;
    private OnboardingViewPager2.Next listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnboardingViewPager2.Next)
            listener = (OnboardingViewPager2.Next) context;
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
        binding = FragmentOnboardingOneBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonOnboardingOneNext.setOnClickListener(v->listener.nextFragment());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        listener = null;
        binding = null;
    }
}