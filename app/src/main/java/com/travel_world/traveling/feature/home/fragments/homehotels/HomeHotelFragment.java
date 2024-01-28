package com.travel_world.traveling.feature.home.fragments.homehotels;

import static com.travel_world.traveling.data.constants.Keys.KEY_HOTEL;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.travel_world.traveling.R;
import com.travel_world.traveling.databinding.FragmentHomeHotelBinding;
import com.travel_world.traveling.domain.hotels.Hotels;
import com.travel_world.traveling.domain.hotels.Result;
import com.travel_world.traveling.feature.home.adapter.CardsHotelsAdapter;
import com.travel_world.traveling.io.MyApiAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeHotelFragment extends Fragment implements CardsHotelsAdapter.MyotelsAdapterListener {
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
        adapter = new CardsHotelsAdapter(this);
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

    @Override
    public void onCick(Result hotel) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_HOTEL,hotel);
        NavHostFragment.findNavController(this).navigate(R.id.action_homeContentFragment_to_mapHotelFragment,bundle);
    }
}