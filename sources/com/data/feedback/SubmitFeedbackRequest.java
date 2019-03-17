package com.data.feedback;

import com.google.gson.annotations.SerializedName;

public class SubmitFeedbackRequest {
    @SerializedName("comments")
    private String comments;
    @SerializedName("id")
    private String id;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String str) {
        this.comments = str;
    }
}
