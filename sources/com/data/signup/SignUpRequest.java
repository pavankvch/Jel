package com.data.signup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpRequest {
    @SerializedName("field_country_code")
    @Expose
    private String countryCode;
    private int countryFlag;
    @SerializedName("field_device_type")
    private String fieldDeviceType;
    @SerializedName("field_full_name")
    private String fieldFullName;
    @SerializedName("field_phone_number")
    private String fieldPhoneNumber;
    @SerializedName("field_referral_code")
    private String fieldReferralCode;
    private String identifier;
    private boolean isFromSocial;
    private String mail;
    private String pass;
    private String provider;
    private String userId;

    public String getMail() {
        return this.mail;
    }

    public void setMail(String str) {
        this.mail = str;
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String str) {
        this.pass = str;
    }

    public String getFieldPhoneNumber() {
        return this.fieldPhoneNumber;
    }

    public void setFieldPhoneNumber(String str) {
        this.fieldPhoneNumber = str;
    }

    public String getFieldFullName() {
        return this.fieldFullName;
    }

    public void setFieldFullName(String str) {
        this.fieldFullName = str;
    }

    public String getFieldReferralCode() {
        return this.fieldReferralCode;
    }

    public void setFieldReferralCode(String str) {
        this.fieldReferralCode = str;
    }

    public String getFieldDeviceType() {
        return this.fieldDeviceType;
    }

    public void setFieldDeviceType(String str) {
        this.fieldDeviceType = str;
    }

    public String getProvider() {
        return this.provider;
    }

    public void setProvider(String str) {
        this.provider = str;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String str) {
        this.identifier = str;
    }

    public boolean isFromSocial() {
        return this.isFromSocial;
    }

    public void setFromSocial(boolean z) {
        this.isFromSocial = z;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String str) {
        this.countryCode = str;
    }

    public int getCountryFlag() {
        return this.countryFlag;
    }

    public void setCountryFlag(int i) {
        this.countryFlag = i;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }
}
