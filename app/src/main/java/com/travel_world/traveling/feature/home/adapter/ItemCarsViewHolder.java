package com.travel_world.traveling.feature.home.adapter;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.travel_world.traveling.R;
import com.travel_world.traveling.databinding.ItemListCarBinding;
import com.travel_world.traveling.domain.CardCar;

public class ItemCarsViewHolder extends ViewHolder {
    private final ItemListCarBinding binding;
    public ItemCarsViewHolder(@NonNull ItemListCarBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
    @SuppressLint({"ResourceAsColor", "UseCompatLoadingForDrawables"})
    public void bind(CardCar cardCar, CardsCarsAdapter.OnCardCarClickListener listener)
    {
        binding.itemListcarBackground.setBackgroundColor(ContextCompat.getColor(itemView.getContext(),cardCar.getColorRes()));
        binding.itemListcarImg.setImageResource(cardCar.getImgRes());
        binding.itemListcarName.setText(cardCar.getCar().getName());
        binding.itemListcarPrice.setText(itemView.getResources().getString(R.string.home_car_price_text,cardCar.getCar().getPrice()));
        itemView.setOnClickListener(view ->
            listener.info(cardCar)
        );
        binding.itemListcarFavourite.setOnClickListener(view -> {
            cardCar.setStar(!cardCar.isStar());
            view.setBackground(itemView.getResources().getDrawable(cardCar.isStar()?R.drawable.ic_star_on:R.drawable.ic_star,itemView.getContext().getTheme()));
        });
    }
}
