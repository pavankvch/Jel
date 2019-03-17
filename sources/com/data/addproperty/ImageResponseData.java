package com.data.addproperty;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageResponseData {
    @SerializedName("featured")
    @Expose
    private String featured;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("url")
    @Expose
    private String url;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getFeatured() {
        return this.featured;
    }

    public void setFeatured(String str) {
        this.featured = str;
    }
}
