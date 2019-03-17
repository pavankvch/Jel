package com.data.profilefeedback;

import com.google.gson.annotations.SerializedName;

public class SubmitGuestFeedbackRequest {
    @SerializedName("order_id")
    private String bookingid;
    private String comments;
    private String rating;
    @SerializedName("user_id")
    private String userId;

    public String getRating() {
        return this.rating;
    }

    public void setRating(String str) {
        this.rating = str;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String str) {
        this.comments = str;
    }

    public String getBookingid() {
        return this.bookingid;
    }

    public void setBookingid(String str) {
        this.bookingid = str;
    }
}
