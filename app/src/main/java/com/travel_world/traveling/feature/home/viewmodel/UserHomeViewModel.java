package com.travel_world.traveling.feature.home.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.travel_world.traveling.domain.User;

public class UserHomeViewModel extends ViewModel {

    private MutableLiveData<User> user;
    private static UserHomeViewModel instance;
    static {
        instance = new UserHomeViewModel();


    }
    public static void start() {
        instance = new UserHomeViewModel();
    }
    private UserHomeViewModel()
    {
        user = new MutableLiveData<>();
        user.setValue(new User());
    }
    public static UserHomeViewModel getInstance()
    {
        return instance;
    }

    public MutableLiveData<User> getUser() {
        if(user.getValue() == null)
            user.setValue(new User());
        return user;
    }
    public void destroy()
    {
        user = null;
        instance = null;
    }

}
