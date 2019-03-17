package com.data.amenitiesandhouserules;

import com.google.gson.annotations.SerializedName;

public class ImageUpload {
    @SerializedName("response")
    private String Response;
    @SerializedName("extension")
    private String extension;
    @SerializedName("imagedata")
    private String imagedata;
    @SerializedName("properrty_id")
    private String properrty_id;
    @SerializedName("title")
    private String title;
    @SerializedName("type")
    private String type;

    public String getResponse() {
        return this.Response;
    }
}
