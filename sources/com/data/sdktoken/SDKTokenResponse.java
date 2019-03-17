package com.data.sdktoken;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SDKTokenResponse {
    @SerializedName("token")
    @Expose
    private String token;

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }
}
