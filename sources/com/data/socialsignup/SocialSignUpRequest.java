package com.data.socialsignup;

import com.google.gson.annotations.SerializedName;

public class SocialSignUpRequest {
    private String birthday;
    @SerializedName("field_country_code")
    private String countryCode;
    @SerializedName("field_device_type")
    private String deviceType;
    @SerializedName("field_full_name")
    private String fullName;
    private String gender;
    private String identifier;
    private String mail;
    @SerializedName("field_phone_number")
    private String phoneNumber;
    private String profileURL;
    private String provider;
    @SerializedName("field_referral_code")
    private String referralCode;

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

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String str) {
        this.phoneNumber = str;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String str) {
        this.mail = str;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String str) {
        this.fullName = str;
    }

    public String getReferralCode() {
        return this.referralCode;
    }

    public void setReferralCode(String str) {
        this.referralCode = str;
    }

    public String getProfileURL() {
        return this.profileURL;
    }

    public void setProfileURL(String str) {
        this.profileURL = str;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String str) {
        this.gender = str;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String str) {
        this.birthday = str;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String str) {
        this.countryCode = str;
    }
}
