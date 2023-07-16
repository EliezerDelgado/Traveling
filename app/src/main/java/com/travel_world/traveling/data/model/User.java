package com.travel_world.traveling.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class User implements Parcelable {
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

    protected User(Parcel in) {
        name = in.readString();
        lastName = in.readString();
        password = in.readString();
        ageRange = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.lastName);
        dest.writeString(this.password);
        dest.writeString(this.ageRange);
    }
}
