
package com.travel_world.traveling.domain.hotels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Features {

    @SerializedName("paymentPreference")
    @Expose
    private Boolean paymentPreference;
    @SerializedName("noCCRequired")
    @Expose
    private Boolean noCCRequired;

    public Boolean getPaymentPreference() {
        return paymentPreference;
    }

    public void setPaymentPreference(Boolean paymentPreference) {
        this.paymentPreference = paymentPreference;
    }

    public Boolean getNoCCRequired() {
        return noCCRequired;
    }

    public void setNoCCRequired(Boolean noCCRequired) {
        this.noCCRequired = noCCRequired;
    }

}
