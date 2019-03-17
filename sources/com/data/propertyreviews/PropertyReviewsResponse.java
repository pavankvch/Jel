package com.data.propertyreviews;

import com.data.propertydetail.Feed;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PropertyReviewsResponse {
    @SerializedName("feeds")
    @Expose
    private List<Feed> review;

    public List<Feed> getReview() {
        return this.review;
    }

    public void setReview(List<Feed> list) {
        this.review = list;
    }
}
