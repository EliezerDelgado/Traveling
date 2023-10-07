package com.travel_world.traveling.feature.home.fragments;

import android.annotation.SuppressLint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.travel_world.traveling.R;
import com.travel_world.traveling.databinding.FragmentHomeContentBinding;
import com.travel_world.traveling.domain.User;
import com.travel_world.traveling.feature.home.adapter.HomePagerAdapter;
import com.travel_world.traveling.feature.home.viewmodel.UserHomeViewModel;

public class HomeContentFragment extends Fragment {
    private static final String ARG_USER = "arg_user";
    private FragmentHomeContentBinding binding;
    private User user;
    private HomePagerAdapter pagerAdapter;
    public HomeContentFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeContentBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(!UserHomeViewModel.getInstance().getUser().getValue().equals(new User())) {
            user = UserHomeViewModel.getInstance().getUser().getValue();
            showMensage(user);
        }
        setFragments();
        setContentView();
        createTabLayout();
    }

    private void showMensage(User user)
    {
        Snackbar.make(binding.homeContraintConstraintLayout, getString(R.string.user_name_description)+": "+user.getName()+"  "+getString(R.string.user_password_description)+": "+user.getPassword(),
                BaseTransientBottomBar.LENGTH_SHORT).show();
    }
    private void setFragments() {
        pagerAdapter = new HomePagerAdapter(requireActivity());
        pagerAdapter.addFragment(HomeCameraFragment.class);
        pagerAdapter.addFragment(HomeCarFragment.class);
        pagerAdapter.addFragment(HomeLandscapeFragment.class);
        pagerAdapter.addFragment(HomeFaceFragment.class);
    }
    private void setContentView() {
        binding.homeContentViewpager2.setAdapter(pagerAdapter);
    }

    @SuppressLint("NewApi")
    private void createTabLayout() {
        binding.homeContentTablayout.setTabIndicatorFullWidth(true);
        new TabLayoutMediator(binding.homeContentTablayout, binding.homeContentViewpager2, (tab, position) -> {
           switch (position)
           {
               case 0:
                   tab.setIcon(R.drawable.ic_action_camera);
                   tab.view.setBackgroundColor(getResources().getColor(R.color.blue_light, requireActivity().getTheme()));
                   tab.view.setY(tab.view.getY()-4F);
                   tab.select();
                   break;
               case 1:
                   tab.setIcon(R.drawable.ic_action_car);
                   tab.view.setBackgroundColor(getResources().getColor(R.color.purple_light, requireActivity().getTheme()));
                   tab.view.setY(tab.view.getY()-4F);
                   break;
               case 2:
                   tab.setIcon(R.drawable.ic_action_landscape);
                   tab.view.setBackgroundColor(getResources().getColor(R.color.rose_light, requireActivity().getTheme()));
                   tab.view.setY(tab.view.getY()-4F);
                   break;
               case 3:
                   tab.setIcon(R.drawable.ic_action_face);
                   tab.view.setBackgroundColor(getResources().getColor(R.color.yellow_light, requireActivity().getTheme()));
                   tab.view.setY(tab.view.getY()-4F);
                   break;
           }
        }).attach();
        binding.homeContentTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Drawable icon = tab.getIcon();
                if(icon != null) {
                    icon.setColorFilter(getResources().getColor(R.color.white, requireContext().getTheme()), PorterDuff.Mode.SRC_ATOP);
                    tab.setIcon(icon);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Drawable icon = tab.getIcon();
                if(icon != null) {
                    icon.setColorFilter(getResources().getColor(R.color.black, requireContext().getTheme()), PorterDuff.Mode.SRC_ATOP);
                    tab.setIcon(icon);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Drawable icon = tab.getIcon();
                if(icon != null) {
                    icon.setColorFilter(getResources().getColor(R.color.white, requireContext().getTheme()), PorterDuff.Mode.SRC_ATOP);
                    tab.setIcon(icon);
                }
            }
        });
        binding.homeContentTablayout.selectTab(
                binding.homeContentTablayout.getTabAt(
                        binding.homeContentViewpager2.getCurrentItem()
                )
        );
        binding.homeContentTablayout.setSelectedTabIndicator(binding.homeContentTablayout.getTabSelectedIndicator());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
        pagerAdapter = null;
        user = null;
    }
}