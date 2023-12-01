
package com.travel_world.traveling.domain.hotels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Hotels {

    @SerializedName("totalCount")
    @Expose
    private Integer totalCount;
    @SerializedName("results")
    @Expose
    private List<Result> results;
    @SerializedName("pagination")
    @Expose
    private Pagination pagination;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

}
