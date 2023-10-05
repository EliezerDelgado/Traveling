package com.travel_world.traveling.feature.login.fragments.onboarding;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentOnAttachListener;

import com.travel_world.traveling.databinding.FragmentOnboardingOneBinding;
import com.travel_world.traveling.feature.login.interfaces.OnboardingViewPager2;

public class OnboardingOneFragment extends Fragment {

    private FragmentOnboardingOneBinding binding;
    private OnboardingViewPager2.Next listener;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getParentFragment() instanceof OnboardingViewPager2.Next)
        {
            listener = (OnboardingViewPager2.Next) getParentFragment();
        }
        else
             throw  new ClassCastException(getParentFragment() + " must implement listener");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOnboardingOneBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonOnboardingOneNext.setOnClickListener(v->listener.onNextClicked());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        listener = null;
        binding = null;
    }
}