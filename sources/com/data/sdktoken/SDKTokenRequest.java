package com.data.sdktoken;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SDKTokenRequest {
    @SerializedName("device_id")
    @Expose
    private String deviceId;

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }
}
