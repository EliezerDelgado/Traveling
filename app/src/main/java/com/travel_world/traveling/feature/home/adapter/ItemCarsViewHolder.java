package com.travel_world.traveling.feature.home.adapter;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.travel_world.traveling.BR;
import com.travel_world.traveling.R;
import com.travel_world.traveling.databinding.ItemListCarBinding;
import com.travel_world.traveling.domain.CardCar;

public class ItemCarsViewHolder extends ViewHolder {
    private ItemListCarBinding binding;
    public ItemCarsViewHolder(@NonNull ItemListCarBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
    @SuppressLint("ResourceAsColor")
    public void bind(CardCar cardCar, CardsCarsAdapter.OnClickListener listener)
    {
        binding.itemListcarBackground.setBackgroundColor(ContextCompat.getColor(itemView.getContext(),cardCar.getColorRes()));
        binding.itemListcarImg.setImageResource(cardCar.getImgRes());
        binding.itemListcarName.setText(cardCar.getCar().getName());
        binding.itemListcarPrice.setText(itemView.getResources().getString(R.string.home_car_price_text,cardCar.getCar().getPrice()));
        itemView.setOnClickListener(view -> {
            listener.info(cardCar);
        });
    }
}
