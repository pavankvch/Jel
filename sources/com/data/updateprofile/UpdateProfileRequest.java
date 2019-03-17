package com.data.updateprofile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateProfileRequest {
    @SerializedName("bio")
    @Expose
    private String about;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("field_full_name")
    @Expose
    private String fieldFullName;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("mail")
    @Expose
    private String mail;
    @SerializedName("nationality")
    @Expose
    private String nationality;
    @SerializedName("spoken_languages")
    @Expose
    private String spokenLanguages;
    @SerializedName("user_address")
    @Expose
    private String userAddress;

    public String getDob() {
        return this.dob;
    }

    public void setDob(String str) {
        this.dob = str;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String str) {
        this.gender = str;
    }

    public String getNationality() {
        return this.nationality;
    }

    public void setNationality(String str) {
        this.nationality = str;
    }

    public String getSpokenLanguages() {
        return this.spokenLanguages;
    }

    public void setSpokenLanguages(String str) {
        this.spokenLanguages = str;
    }

    public String getUserAddress() {
        return this.userAddress;
    }

    public void setUserAddress(String str) {
        this.userAddress = str;
    }

    public String getAbout() {
        return this.about;
    }

    public void setAbout(String str) {
        this.about = str;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String str) {
        this.mail = str;
    }

    public String getFieldFullName() {
        return this.fieldFullName;
    }

    public void setFieldFullName(String str) {
        this.fieldFullName = str;
    }
}
