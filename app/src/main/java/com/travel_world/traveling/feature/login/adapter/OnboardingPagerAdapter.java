package com.travel_world.traveling.feature.login.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class OnboardingPagerAdapter extends FragmentStateAdapter {

    private final ArrayList<Class<? extends Fragment>> listFragmentClass = new ArrayList<>();

    public OnboardingPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }


    public void addFragment(Class<? extends Fragment> fragment)
    {
        listFragmentClass.add(fragment);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        try {
            return (Fragment) listFragmentClass.get(position).newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getItemCount() {
        return listFragmentClass.size();
    }
}
