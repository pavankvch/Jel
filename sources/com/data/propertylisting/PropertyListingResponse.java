package com.data.propertylisting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PropertyListingResponse {
    @SerializedName("incomplete")
    @Expose
    private List<Published> incompleteList;
    @SerializedName("published")
    @Expose
    private List<Published> publishedList;
    @SerializedName("review")
    @Expose
    private List<Published> reviewList;
    @SerializedName("unpublished")
    @Expose
    private List<Published> unpublishedList;

    public List<Published> getIncompleteList() {
        return this.incompleteList;
    }

    public List<Published> getUnpublishedList() {
        return this.unpublishedList;
    }

    public List<Published> getPublishedList() {
        return this.publishedList;
    }

    public List<Published> getReviewList() {
        return this.reviewList;
    }
}
