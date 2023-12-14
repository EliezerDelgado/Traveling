package com.travel_world.traveling.model;

import com.travel_world.traveling.domain.User;
import com.travel_world.traveling.domain.hotels.Hotels;
import com.travel_world.traveling.io.request.LoginRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MyApiService {
    @POST("login")
    Call<User> login(@Body LoginRequest loginRequest);
    @GET("listHotels")
    Call<Hotels> getHotels();
}
