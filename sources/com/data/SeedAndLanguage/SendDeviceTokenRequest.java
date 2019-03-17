package com.data.SeedAndLanguage;

import com.google.gson.annotations.SerializedName;

public class SendDeviceTokenRequest {
    @SerializedName("device_token")
    private String deviceToken;

    public String getDeviceToken() {
        return this.deviceToken;
    }

    public void setDeviceToken(String str) {
        this.deviceToken = str;
    }
}
