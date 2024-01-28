package com.travel_world.traveling.feature.home.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.travel_world.traveling.databinding.ItemListHotelBinding;
import com.travel_world.traveling.domain.hotels.Result;

import java.util.ArrayList;
import java.util.List;

public class CardsHotelsAdapter extends RecyclerView.Adapter<ItemHotelsViewHolder> {
    public interface MyotelsAdapterListener {
        void onCick(Result hotel);
    }
    private List<Result> items;
    private MyotelsAdapterListener listener;
    public CardsHotelsAdapter(MyotelsAdapterListener listener) {
        this.listener = listener;
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
    public ItemHotelsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @NonNull ItemListHotelBinding binding = ItemListHotelBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ItemHotelsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHotelsViewHolder holder, int position) {
        holder.bind(items.get(position));
        holder.itemView.setOnClickListener(v -> {
            listener.onCick(items.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
