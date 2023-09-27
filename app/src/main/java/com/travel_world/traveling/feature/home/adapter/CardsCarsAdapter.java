package com.travel_world.traveling.feature.home.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.travel_world.traveling.databinding.ItemListCarBinding;
import com.travel_world.traveling.domain.CardCar;

import java.util.ArrayList;

public class CardsCarsAdapter extends RecyclerView.Adapter<ItemCarsViewHolder> {
    private ArrayList<CardCar> items;
    private OnCardCarClickListener listener;

    public interface OnCardCarClickListener {
        void info(CardCar cardCar);
    }

    public CardsCarsAdapter(ArrayList<CardCar> items, OnCardCarClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemCarsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @NonNull ItemListCarBinding binding = ItemListCarBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ItemCarsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemCarsViewHolder holder, int position) {
        holder.bind(items.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
