package com.data.socialsignup;

public class SocialSignUpResponse {
    private String field_device_type;
    private String field_full_name;
    private String field_phone_number;
    private String field_referral_code;
    private String firstName;
    private String identifier;
    private String mail;
    private String profileURL;
    private String provider;
    private String uid;

    public String getField_device_type() {
        return this.field_device_type;
    }

    public void setField_device_type(String str) {
        this.field_device_type = str;
    }

    public String getField_full_name() {
        return this.field_full_name;
    }

    public void setField_full_name(String str) {
        this.field_full_name = str;
    }

    public String getField_phone_number() {
        return this.field_phone_number;
    }

    public void setField_phone_number(String str) {
        this.field_phone_number = str;
    }

    public String getField_referral_code() {
        return this.field_referral_code;
    }

    public void setField_referral_code(String str) {
        this.field_referral_code = str;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String str) {
        this.identifier = str;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String str) {
        this.mail = str;
    }

    public String getProfileURL() {
        return this.profileURL;
    }

    public void setProfileURL(String str) {
        this.profileURL = str;
    }

    public String getProvider() {
        return this.provider;
    }

    public void setProvider(String str) {
        this.provider = str;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String str) {
        this.firstName = str;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }
}
