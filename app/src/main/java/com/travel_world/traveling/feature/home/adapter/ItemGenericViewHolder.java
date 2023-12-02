package com.travel_world.traveling.feature.home.adapter;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.travel_world.traveling.BR;

public class ItemGenericViewHolder<T> extends RecyclerView.ViewHolder {
    protected final ViewDataBinding binding;
    public ItemGenericViewHolder(@NonNull ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(T item) {
        binding.setVariable(BR.item,item);
        binding.executePendingBindings();
        onBindMethodCalled(item);
    }

    protected void onBindMethodCalled(T item)
    {
        //mas funcionalidades
    }
}
