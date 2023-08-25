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
import com.google.android.material.tabs.TabLayoutMediator;
import com.travel_world.traveling.R;
import com.travel_world.traveling.databinding.FragmentHomeContentBinding;
import com.travel_world.traveling.domain.User;
import com.travel_world.traveling.feature.home.adapter.HomePagerAdapter;

public class HomeContentFragment extends Fragment {
    private static final String ARG_USER = "arg_user";
    private FragmentHomeContentBinding binding;
    private User user;
    private HomePagerAdapter pagerAdapter;

    public HomeContentFragment() {
        // Required empty public constructor
    }
    public static HomeContentFragment newInstance(User paramuser) {
        HomeContentFragment fragment = new HomeContentFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_USER, paramuser);
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
        binding = FragmentHomeContentBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getArguments() !=null)
            showMensage(user);
        setFragments();
        setContentView();
        //createTabLayout();
    }

    private void showMensage(User user)
    {
        Snackbar.make(binding.homeContraintConstraintLayout, getString(R.string.user_name_description)+": "+user.getName()+"  "+getString(R.string.user_password_description)+": "+user.getPassword(),
                BaseTransientBottomBar.LENGTH_SHORT).show();
    }
    private void setFragments() {
        pagerAdapter = new HomePagerAdapter(requireActivity());
        pagerAdapter.addFragment(HomeCameraFragment.class);
        pagerAdapter.addFragment(HomeFavoriteFragment.class);
        pagerAdapter.addFragment(HomeLandscapeFragment.class);
        pagerAdapter.addFragment(HomeFaceFragment.class);
    }
    private void setContentView() {
        binding.homeContentViewpager2.setAdapter(pagerAdapter);
    }

    private void createTabLayout() {
        new TabLayoutMediator(binding.homeContentTablayout, binding.homeContentViewpager2, (tab, position) -> {
           switch (position)
           {
               case 0:
                   tab.setIcon(R.drawable.ic_action_camera);
                   break;
               case 1:
                   tab.setIcon(R.drawable.ic_action_favorite);
                   break;
               case 2:
                   tab.setIcon(R.drawable.ic_action_landscape);
                   break;
               case 3:
                   tab.setIcon(R.drawable.ic_action_face);
                   break;
           }
        }).attach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
        user = null;
    }
}