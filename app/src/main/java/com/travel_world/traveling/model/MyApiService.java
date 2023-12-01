package com.travel_world.traveling.model;

import com.travel_world.traveling.domain.User;
import com.travel_world.traveling.domain.hotels.Hotels;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MyApiService {
    @FormUrlEncoded
    @POST("login")
    Call<User> getUser(@Field("nombre") String name, @Field("contraseña") String password );
    @GET("listHotels")
    Call<Hotels> getHotels();
}
