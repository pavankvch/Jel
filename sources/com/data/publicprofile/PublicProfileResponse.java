package com.data.publicprofile;

import com.data.propertydetail.Feed;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PublicProfileResponse {
    @SerializedName("average_rating")
    @Expose
    private int averageRating;
    @SerializedName("guest_desc")
    @Expose
    private String description;
    @SerializedName("feeds")
    @Expose
    private List<Feed> feeds;
    @SerializedName("guest_name")
    @Expose
    private String name;
    @SerializedName("guest_image")
    @Expose
    private String profileImage;
    @SerializedName("reviewed")
    @Expose
    private int reviewed;
    @SerializedName("since")
    @Expose
    private String since;
    @SerializedName("spoken_languages")
    @Expose
    private String spokenLanguages;
    @SerializedName("verified")
    @Expose
    private VerifiedInfo verifiedInfo;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getProfileImage() {
        return this.profileImage;
    }

    public void setProfileImage(String str) {
        this.profileImage = str;
    }

    public String getSince() {
        return this.since;
    }

    public void setSince(String str) {
        this.since = str;
    }

    public String getSpokenLanguages() {
        return this.spokenLanguages;
    }

    public void setSpokenLanguages(String str) {
        this.spokenLanguages = str;
    }

    public int getAverageRating() {
        return this.averageRating;
    }

    public void setAverageRating(int i) {
        this.averageRating = i;
    }

    public int getReviewed() {
        return this.reviewed;
    }

    public void setReviewed(int i) {
        this.reviewed = i;
    }

    public List<Feed> getFeeds() {
        return this.feeds;
    }

    public void setFeeds(List<Feed> list) {
        this.feeds = list;
    }

    public VerifiedInfo getVerifiedInfo() {
        return this.verifiedInfo;
    }

    public void setVerifiedInfo(VerifiedInfo verifiedInfo) {
        this.verifiedInfo = verifiedInfo;
    }
}
