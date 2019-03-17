package com.data.publicprofile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReviewsResponse {
    private String hostName;
    private int rating;
    private String reviewDate;
    private String reviewTxt;
    @SerializedName("reviewer_image")
    @Expose
    private String reviewerImage;

    public String getHostName() {
        return this.hostName;
    }

    public void setHostName(String str) {
        this.hostName = str;
    }

    public String getReviewDate() {
        return this.reviewDate;
    }

    public void setReviewDate(String str) {
        this.reviewDate = str;
    }

    public String getReviewerImage() {
        return this.reviewerImage;
    }

    public void setReviewerImage(String str) {
        this.reviewerImage = str;
    }

    public String getReviewTxt() {
        return this.reviewTxt;
    }

    public void setReviewTxt(String str) {
        this.reviewTxt = str;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int i) {
        this.rating = i;
    }
}
