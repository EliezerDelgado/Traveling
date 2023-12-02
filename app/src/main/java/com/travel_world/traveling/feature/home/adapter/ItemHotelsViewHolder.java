package com.travel_world.traveling.feature.home.adapter;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;

import com.travel_world.traveling.domain.hotels.Result;
import com.travel_world.traveling.utils.UtilsPictures;

public class ItemHotelsViewHolder extends ItemGenericViewHolder<Result> {
    public ItemHotelsViewHolder(@NonNull ViewDataBinding binding) {
        super(binding);
    }

    @Override
    protected void onBindMethodCalled(Result item) {
        super.onBindMethodCalled(item);
        binding.setVariable(BR.ioPB, true);
        new Thread(() -> {
            binding.setVariable(BR.img,
                    UtilsPictures.loadImageFromWebOperations(item.getOptimizedThumbUrls().getSrpDesktop()));
            binding.setVariable(BR.ioPB, false);
        }).start();
    }
}
