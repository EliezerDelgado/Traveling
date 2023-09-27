package com.travel_world.traveling.data.repository;

import com.travel_world.traveling.R;
import com.travel_world.traveling.domain.Car;
import com.travel_world.traveling.domain.CardCar;

import java.util.ArrayList;

public abstract class RepositoryCardsCars {
    private RepositoryCardsCars() {
        //utility class
    }

    public static ArrayList<CardCar> getListCars()
    {
        ArrayList<CardCar> listCars = new ArrayList<>();
        listCars.add(new CardCar(R.color.peru,R.drawable.classic_car, new Car("Classic Car",34F)));
        listCars.add(new CardCar(R.color.cobalt,R.drawable.sport_cart,new Car("Sport Car",55F)));
        listCars.add(new CardCar(R.color.purple_500,R.drawable.flying_car,new Car("Flying Car",500F)));
        listCars.add(new CardCar(R.color.black,R.drawable.electric_car,new Car("Electric Car",45F)));
        listCars.add(new CardCar(R.color.amulet,R.drawable.motor_home,new Car("Motorhome",23F)));
        listCars.add(new CardCar(R.color.donkey_brown,R.drawable.pick_up_car,new Car("Pickup",10F )));
        listCars.add(new CardCar(R.color.dark_red,R.drawable.airplain,new Car("Airplane",11F )));
        listCars.add(new CardCar(R.color.banana_yellow,R.drawable.bus,new Car("Bus",14F)));
        return listCars;
    }
}
