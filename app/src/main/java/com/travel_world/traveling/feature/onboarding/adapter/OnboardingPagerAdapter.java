package com.travel_world.traveling.feature.onboarding.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class OnboardingPagerAdapter extends FragmentStateAdapter {

    private ArrayList<Class> list = new ArrayList<>();

    public OnboardingPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    public void addFragment(Fragment fragment)
    {
        list.add(fragment.getClass());
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        try {
            return (Fragment) list.get(position).newInstance();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
