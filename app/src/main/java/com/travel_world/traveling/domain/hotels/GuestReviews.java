
package com.travel_world.traveling.domain.hotels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class GuestReviews {

    @SerializedName("unformattedRating")
    @Expose
    private Double unformattedRating;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("scale")
    @Expose
    private Integer scale;
    @SerializedName("badge")
    @Expose
    private String badge;
    @SerializedName("badgeText")
    @Expose
    private String badgeText;

    public Double getUnformattedRating() {
        return unformattedRating;
    }

    public void setUnformattedRating(Double unformattedRating) {
        this.unformattedRating = unformattedRating;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getBadgeText() {
        return badgeText;
    }

    public void setBadgeText(String badgeText) {
        this.badgeText = badgeText;
    }

}
