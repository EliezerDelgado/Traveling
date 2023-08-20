package com.travel_world.traveling.feature.onboarding.interfaces;

public interface OnboardingViewPager2 {
    interface All extends Next,Skip{

    }
    interface Next {
        void nextFragment();
    }
    interface Skip {
        void goLoginActivity();
    }
}
