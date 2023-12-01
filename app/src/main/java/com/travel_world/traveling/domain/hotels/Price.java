
package com.travel_world.traveling.domain.hotels;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Price {

    @SerializedName("current")
    @Expose
    private String current;
    @SerializedName("exactCurrent")
    @Expose
    private Double exactCurrent;
    @SerializedName("old")
    @Expose
    private String old;

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public Double getExactCurrent() {
        return exactCurrent;
    }

    public void setExactCurrent(Double exactCurrent) {
        this.exactCurrent = exactCurrent;
    }

    public String getOld() {
        return old;
    }

    public void setOld(String old) {
        this.old = old;
    }

}
