package com.travel_world.traveling.feature.home.adapter;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;

import com.travel_world.traveling.domain.hotels.Result;
import com.travel_world.traveling.utils.UtilsPictures;

public class ItemHotelsViewHolder extends ItemGenericViewHolder<Result>{
    public ItemHotelsViewHolder(@NonNull ViewDataBinding binding) {
        super(binding);
    }

    @Override
    protected void onBindMethodCalled(Result item) {
        super.onBindMethodCalled(item);
        binding.setVariable(BR.img, null);
        new Thread(() ->
        {
            Drawable d = UtilsPictures.loadImageFromWebOperations(item.getOptimizedThumbUrls().getSrpDesktop());
            binding.setVariable(BR.img, d);
        }).start();
    }
}
