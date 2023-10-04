package com.travel_world.traveling.feature.login.interfaces;

public interface OnboardingViewPager2 {
    interface FragmentManager extends Next,Skip, LoginIn {

    }
    interface Next {
        void onNextClicked();
    }
    interface Skip {
        void onSkipClicked();
    }
    interface LoginIn
    {
        void onLoginClicked();
    }
}
