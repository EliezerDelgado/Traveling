package com.travel_world.traveling.feature.home.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class HomePagerAdapter extends FragmentStateAdapter {
    private final ArrayList<Class<? extends Fragment>> listFragmentClass = new ArrayList<>();
    public HomePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    public void addFragment(Class<? extends Fragment> fragmentClass)
    {
        listFragmentClass.add(fragmentClass);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        try {
            return (Fragment) listFragmentClass.get(position).newInstance();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getItemCount() {
        return listFragmentClass.size();
    }
}
