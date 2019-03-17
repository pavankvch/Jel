package com.data.generateotp;

import com.google.gson.annotations.SerializedName;

public class GenerateOTPRequest {
    private String email;
    @SerializedName("phone_number")
    private String phoneNumber;

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String str) {
        this.phoneNumber = str;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }
}
