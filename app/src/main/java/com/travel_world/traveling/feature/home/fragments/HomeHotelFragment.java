package com.travel_world.traveling.feature.home.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.travel_world.traveling.databinding.FragmentHomeHotelBinding;
import com.travel_world.traveling.domain.hotels.Hotels;
import com.travel_world.traveling.feature.home.adapter.CardsHotelsAdapter;
import com.travel_world.traveling.io.MyApiAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeHotelFragment extends Fragment {
    private CardsHotelsAdapter adapter;
    private FragmentHomeHotelBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeHotelBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new CardsHotelsAdapter();
        getHotels();
        binding.recyclerCardsHotel.setHasFixedSize(true);
        binding.recyclerCardsHotel.setAdapter(adapter);
    }
    private void getHotels() {
        Call<Hotels> call = MyApiAdapter.getApiService().getHotels();
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Hotels> call, Response<Hotels> response) {
                Hotels hotels = response.body();
                adapter.setHotels(hotels.getResults());
            }

            @Override
            public void onFailure(Call<Hotels> call, Throwable t) {
                binding.recyclerCardsHotel.setVisibility(View.GONE);
                binding.textNoFoundHotels.setVisibility(View.VISIBLE);
            }
        });
    }
}