package com.travel_world.traveling.feature.home.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.travel_world.traveling.R;
import com.travel_world.traveling.domain.User;
import com.travel_world.traveling.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private static final String ARG_USER = "arg_user";
    private FragmentHomeBinding binding;
    private User user;

    public HomeFragment() {
    }
    public static HomeFragment newInstance(User user) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = getArguments().getParcelable(ARG_USER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getArguments() !=null)
            showMensage(user);
    }

    private void showMensage(User user)
    {
        Snackbar.make(binding.coordinatorlayoutHome, getString(R.string.user_name_description)+": "+user.getName()+"  "+getString(R.string.user_password_description)+": "+user.getPassword(),
                BaseTransientBottomBar.LENGTH_SHORT).show();
    }
}