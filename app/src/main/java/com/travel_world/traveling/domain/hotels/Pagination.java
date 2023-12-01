
package com.travel_world.traveling.domain.hotels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Pagination {

    @SerializedName("currentPage")
    @Expose
    private Integer currentPage;
    @SerializedName("pageGroup")
    @Expose
    private String pageGroup;
    @SerializedName("nextPageStartIndex")
    @Expose
    private Integer nextPageStartIndex;
    @SerializedName("nextPageNumber")
    @Expose
    private Integer nextPageNumber;
    @SerializedName("nextPageGroup")
    @Expose
    private String nextPageGroup;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public String getPageGroup() {
        return pageGroup;
    }

    public void setPageGroup(String pageGroup) {
        this.pageGroup = pageGroup;
    }

    public Integer getNextPageStartIndex() {
        return nextPageStartIndex;
    }

    public void setNextPageStartIndex(Integer nextPageStartIndex) {
        this.nextPageStartIndex = nextPageStartIndex;
    }

    public Integer getNextPageNumber() {
        return nextPageNumber;
    }

    public void setNextPageNumber(Integer nextPageNumber) {
        this.nextPageNumber = nextPageNumber;
    }

    public String getNextPageGroup() {
        return nextPageGroup;
    }

    public void setNextPageGroup(String nextPageGroup) {
        this.nextPageGroup = nextPageGroup;
    }

}
