
package com.travel_world.traveling.domain.hotels;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class RatePlan {

    @SerializedName("price")
    @Expose
    private Price price;
    @SerializedName("features")
    @Expose
    private Features features;

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Features getFeatures() {
        return features;
    }

    public void setFeatures(Features features) {
        this.features = features;
    }

}
