package com.data.profile;

import com.google.gson.annotations.SerializedName;

public class GetGuestProfileRequest {
    @SerializedName("guest_id")
    private String guestId;
    @SerializedName("need_review")
    private int showReview;

    public String getGuestId() {
        return this.guestId;
    }

    public void setGuestId(String str) {
        this.guestId = str;
    }

    public int getShowReview() {
        return this.showReview;
    }

    public void setShowReview(int i) {
        this.showReview = i;
    }
}
