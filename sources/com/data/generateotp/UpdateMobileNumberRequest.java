package com.data.generateotp;

import com.google.gson.annotations.SerializedName;

public class UpdateMobileNumberRequest {
    @SerializedName("field_country_code")
    private String fieldCountryCode;
    @SerializedName("old_number")
    private String oldNumber;
    @SerializedName("phone_number")
    private String phoneNumber;
    @SerializedName("uid")
    private String uid;

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String str) {
        this.phoneNumber = str;
    }

    public String getFieldCountryCode() {
        return this.fieldCountryCode;
    }

    public void setFieldCountryCode(String str) {
        this.fieldCountryCode = str;
    }

    public String getOldNumber() {
        return this.oldNumber;
    }

    public void setOldNumber(String str) {
        this.oldNumber = str;
    }
}
