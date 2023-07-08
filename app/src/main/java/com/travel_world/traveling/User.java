package com.travel_world.traveling;

public class User {
    private String name;
    private String lastName;
    private String password;

    private String ageRange;

    public User() {
        name = "";
        lastName = "";
        password = "";
    }
    public User(String name, String lastName, String password, String ageRange) {
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.ageRange = ageRange;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }
}
