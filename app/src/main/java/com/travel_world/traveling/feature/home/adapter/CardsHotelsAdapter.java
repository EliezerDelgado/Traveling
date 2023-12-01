package com.travel_world.traveling.feature.home.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.travel_world.traveling.BR;
import com.travel_world.traveling.databinding.ItemListHotelBinding;
import com.travel_world.traveling.domain.hotels.Result;
import com.travel_world.traveling.utils.UtilsPictures;

import java.util.ArrayList;
import java.util.List;

public class CardsHotelsAdapter extends RecyclerView.Adapter<ItemGenericViewHolder> {
    private List<Result> items;
    public CardsHotelsAdapter() {
        items = new ArrayList<>();
    }
    public CardsHotelsAdapter(List<Result> items) {
        this.items = items;
    }
    @SuppressLint("NotifyDataSetChanged")
    public void setHotels(List<Result> items)
    {
        this.items = items;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ItemGenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @NonNull ItemListHotelBinding binding = ItemListHotelBinding.inflate(LayoutInflater.from(parent.getContext()));
        binding.setVariable(BR.util, new UtilsPictures());
        return new ItemGenericViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemGenericViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
