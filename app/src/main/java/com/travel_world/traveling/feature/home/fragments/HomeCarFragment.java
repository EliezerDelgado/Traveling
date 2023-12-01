package com.travel_world.traveling.feature.home.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.travel_world.traveling.data.repository.RepositoryCardsCars;
import com.travel_world.traveling.databinding.FragmentHomeCarBinding;
import com.travel_world.traveling.domain.CardCar;
import com.travel_world.traveling.feature.home.adapter.CardsCarsAdapter;

public class HomeCarFragment extends Fragment implements CardsCarsAdapter.OnCardCarClickListener {

    private FragmentHomeCarBinding binding;
    private CardsCarsAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeCarBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new CardsCarsAdapter(RepositoryCardsCars.getListCars(),this);
        binding.recyclerCardsCar.setHasFixedSize(true);
        binding.recyclerCardsCar.setAdapter(adapter);
    }


    @Override
    public void info(CardCar cardCar) {
        Toast.makeText(requireContext(), cardCar.getCar().getName(), Toast.LENGTH_SHORT).show();
    }
}