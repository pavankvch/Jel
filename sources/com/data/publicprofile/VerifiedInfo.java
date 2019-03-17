package com.data.publicprofile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifiedInfo {
    @SerializedName("dob_verified")
    @Expose
    private String dobVerified;
    @SerializedName("email_verified")
    @Expose
    private String emailVerified;
    @SerializedName("national_id_verified")
    @Expose
    private String nationalIdVerified;
    @SerializedName("phone_number_verified")
    @Expose
    private int phoneNumberVerified;

    public int getPhoneNumberVerified() {
        return this.phoneNumberVerified;
    }

    public String getEmailVerified() {
        return this.emailVerified;
    }

    public String getNationalIdVerified() {
        return this.nationalIdVerified;
    }

    public String getDobVerified() {
        return this.dobVerified;
    }
}
