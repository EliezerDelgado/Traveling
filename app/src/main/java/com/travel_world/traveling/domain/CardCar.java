package com.travel_world.traveling.domain;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;

public class CardCar {
    private @ColorRes int colorRes;
    private @DrawableRes int imgRes;
    private Car car;
    private boolean star;

    public CardCar() {
    }

    public CardCar(int colorRes, int imgRes,Car car) {
        this.colorRes = colorRes;
        this.imgRes = imgRes;
        this.car = car;
    }

    public @ColorRes  int getColorRes() {
        return colorRes;
    }

    public void setColorRes(@ColorRes int colorRes) {
        this.colorRes = colorRes;
    }

    public @DrawableRes int getImgRes() {
        return imgRes;
    }

    public void setImgRes(@DrawableRes int imgRes) {
        this.imgRes = imgRes;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public boolean isStar() {
        return star;
    }

    public void setStar(boolean star) {
        this.star = star;
    }
}
