package com.data.propertyfeedback;

import com.google.gson.annotations.SerializedName;

public class SubmitPropertyFeedbackRequest {
    @SerializedName("order_id")
    private String bookingID;
    private String comments;
    @SerializedName("property_id")
    private String propertyId;
    private String rating;

    public String getRating() {
        return this.rating;
    }

    public void setRating(String str) {
        this.rating = str;
    }

    public String getPropertyId() {
        return this.propertyId;
    }

    public void setPropertyId(String str) {
        this.propertyId = str;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String str) {
        this.comments = str;
    }

    public String getBookingID() {
        return this.bookingID;
    }

    public void setBookingID(String str) {
        this.bookingID = str;
    }
}
