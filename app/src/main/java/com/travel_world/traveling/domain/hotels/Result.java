
package com.travel_world.traveling.domain.hotels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Result implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("starRating")
    @Expose
    private Integer starRating;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("guestReviews")
    @Expose
    private GuestReviews guestReviews;
    @SerializedName("ratePlan")
    @Expose
    private RatePlan ratePlan;
    @SerializedName("neighbourhood")
    @Expose
    private String neighbourhood;
    @SerializedName("coordinate")
    @Expose
    private Coordinate coordinate;
    @SerializedName("providerType")
    @Expose
    private String providerType;
    @SerializedName("supplierHotelId")
    @Expose
    private Integer supplierHotelId;
    @SerializedName("isAlternative")
    @Expose
    private Boolean isAlternative;
    @SerializedName("optimizedThumbUrls")
    @Expose
    private OptimizedThumbUrls optimizedThumbUrls;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStarRating() {
        return starRating;
    }

    public void setStarRating(Integer starRating) {
        this.starRating = starRating;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public GuestReviews getGuestReviews() {
        return guestReviews;
    }

    public void setGuestReviews(GuestReviews guestReviews) {
        this.guestReviews = guestReviews;
    }

    public RatePlan getRatePlan() {
        return ratePlan;
    }

    public void setRatePlan(RatePlan ratePlan) {
        this.ratePlan = ratePlan;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public String getProviderType() {
        return providerType;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType;
    }

    public Integer getSupplierHotelId() {
        return supplierHotelId;
    }

    public void setSupplierHotelId(Integer supplierHotelId) {
        this.supplierHotelId = supplierHotelId;
    }

    public Boolean getIsAlternative() {
        return isAlternative;
    }

    public void setIsAlternative(Boolean isAlternative) {
        this.isAlternative = isAlternative;
    }

    public OptimizedThumbUrls getOptimizedThumbUrls() {
        return optimizedThumbUrls;
    }

    public void setOptimizedThumbUrls(OptimizedThumbUrls optimizedThumbUrls) {
        this.optimizedThumbUrls = optimizedThumbUrls;
    }

}
