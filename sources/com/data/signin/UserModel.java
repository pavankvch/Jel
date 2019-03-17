package com.data.signin;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserModel implements Parcelable {
    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        public final UserModel createFromParcel(Parcel parcel) {
            return new UserModel(parcel);
        }

        public final UserModel[] newArray(int i) {
            return new UserModel[i];
        }
    };
    @SerializedName("bio")
    @Expose
    private String bio;
    @SerializedName("field_country_code")
    @Expose
    private String countryCode;
    @SerializedName("field_date_of_birth")
    @Expose
    private String dateOfBirth;
    @SerializedName("field_email_verified")
    @Expose
    private String emailVerified;
    @SerializedName("field_dob_verified")
    @Expose
    private String fieldDobVerified;
    @SerializedName("field_national_id_verified")
    @Expose
    private String fieldNationalIdVerified;
    @SerializedName("field_full_name")
    @Expose
    private String fullName;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("guest_image")
    @Expose
    private String guestImage;
    @SerializedName("host")
    @Expose
    private String host;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("mail")
    @Expose
    private String mail;
    @SerializedName("field_national_id")
    @Expose
    private String nationalId;
    @SerializedName("nationality")
    @Expose
    private String nationality;
    @SerializedName("field_otp_validated")
    @Expose
    private String otpValidated;
    @SerializedName("field_phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("field_phone_number_verified")
    @Expose
    private String phoneNumberVerified;
    @SerializedName("referral_code")
    @Expose
    private String referralCode;
    @SerializedName("spoken_languages")
    @Expose
    private String spokenLanguages;
    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("user_address")
    @Expose
    private String userAddress;

    public int describeContents() {
        return 0;
    }

    protected UserModel(Parcel parcel) {
        this.uid = (String) parcel.readValue(String.class.getClassLoader());
        this.mail = (String) parcel.readValue(String.class.getClassLoader());
        this.language = (String) parcel.readValue(String.class.getClassLoader());
        this.otpValidated = (String) parcel.readValue(String.class.getClassLoader());
        this.fullName = (String) parcel.readValue(String.class.getClassLoader());
        this.phoneNumber = (String) parcel.readValue(String.class.getClassLoader());
        this.dateOfBirth = (String) parcel.readValue(String.class.getClassLoader());
        this.phoneNumberVerified = (String) parcel.readValue(String.class.getClassLoader());
        this.emailVerified = (String) parcel.readValue(String.class.getClassLoader());
        this.fieldNationalIdVerified = (String) parcel.readValue(String.class.getClassLoader());
        this.fieldDobVerified = (String) parcel.readValue(String.class.getClassLoader());
        this.nationalId = (String) parcel.readValue(String.class.getClassLoader());
        this.nationality = (String) parcel.readValue(String.class.getClassLoader());
        this.gender = (String) parcel.readValue(String.class.getClassLoader());
        this.spokenLanguages = (String) parcel.readValue(String.class.getClassLoader());
        this.userAddress = (String) parcel.readValue(String.class.getClassLoader());
        this.bio = (String) parcel.readValue(String.class.getClassLoader());
        this.guestImage = (String) parcel.readValue(String.class.getClassLoader());
        this.host = (String) parcel.readValue(String.class.getClassLoader());
        this.referralCode = (String) parcel.readValue(String.class.getClassLoader());
        this.countryCode = (String) parcel.readValue(String.class.getClassLoader());
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String str) {
        this.mail = str;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public String getOtpValidated() {
        return this.otpValidated;
    }

    public void setOtpValidated(String str) {
        this.otpValidated = str;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String str) {
        this.phoneNumber = str;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String str) {
        this.fullName = str;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(String str) {
        this.dateOfBirth = str;
    }

    public String getNationalId() {
        return this.nationalId;
    }

    public void setNationalId(String str) {
        this.nationalId = str;
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

    public String getReferralCode() {
        return this.referralCode;
    }

    public void setReferralCode(String str) {
        this.referralCode = str;
    }

    public String getBio() {
        return this.bio;
    }

    public void setBio(String str) {
        this.bio = str;
    }

    public String getGuestImage() {
        return this.guestImage;
    }

    public void setGuestImage(String str) {
        this.guestImage = str;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String str) {
        this.host = str;
    }

    public String getEmailVerified() {
        return this.emailVerified;
    }

    public void setEmailVerified(String str) {
        this.emailVerified = str;
    }

    public String getPhoneNumberVerified() {
        return this.phoneNumberVerified;
    }

    public void setPhoneNumberVerified(String str) {
        this.phoneNumberVerified = str;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String str) {
        this.countryCode = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.uid);
        parcel.writeValue(this.mail);
        parcel.writeValue(this.language);
        parcel.writeValue(this.otpValidated);
        parcel.writeValue(this.phoneNumber);
        parcel.writeValue(this.fullName);
        parcel.writeValue(this.dateOfBirth);
        parcel.writeValue(this.phoneNumberVerified);
        parcel.writeValue(this.emailVerified);
        parcel.writeValue(this.fieldNationalIdVerified);
        parcel.writeValue(this.fieldDobVerified);
        parcel.writeValue(this.nationalId);
        parcel.writeValue(this.nationality);
        parcel.writeValue(this.gender);
        parcel.writeValue(this.spokenLanguages);
        parcel.writeValue(this.userAddress);
        parcel.writeValue(this.referralCode);
        parcel.writeValue(this.bio);
        parcel.writeValue(this.guestImage);
        parcel.writeValue(this.host);
        parcel.writeValue(this.countryCode);
    }

    public String getFieldDobVerified() {
        return this.fieldDobVerified;
    }

    public void setFieldDobVerified(String str) {
        this.fieldDobVerified = str;
    }

    public String getFieldNationalIdVerified() {
        return this.fieldNationalIdVerified;
    }

    public void setFieldNationalIdVerified(String str) {
        this.fieldNationalIdVerified = str;
    }
}
