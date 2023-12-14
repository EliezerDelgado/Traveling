package com.travel_world.traveling.io.request;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("nombre")
    private String username;
    @SerializedName("contraseña")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
