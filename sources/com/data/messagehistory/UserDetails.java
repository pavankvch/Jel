package com.data.messagehistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDetails {
    @SerializedName("guest_mobile_no")
    @Expose
    private String guestMobileNo;
    @SerializedName("host_mobile_no")
    @Expose
    private String hostMobileNo;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("owner_image")
    @Expose
    private String ownerImage;
    @SerializedName("user_id")
    @Expose
    private String userId;

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String getOwnerImage() {
        return this.ownerImage;
    }

    public void setOwnerImage(String str) {
        this.ownerImage = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getGuestMobileNo() {
        return this.guestMobileNo;
    }

    public void setGuestMobileNo(String str) {
        this.guestMobileNo = str;
    }

    public String getHostMobileNo() {
        return this.hostMobileNo;
    }

    public void setHostMobileNo(String str) {
        this.hostMobileNo = str;
    }
}
