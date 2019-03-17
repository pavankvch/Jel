package com.data.signin;

import com.google.gson.annotations.SerializedName;

public class SaveFcmTokenRequest {
    @SerializedName("token")
    private String token;
    @SerializedName("type")
    private String type;

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }
}
