package com.data.changepassword;

import com.google.gson.annotations.SerializedName;

public class ChangePasswordRequest {
    @SerializedName("curent_password")
    private String curentPassword;
    @SerializedName("new_password")
    private String newPassword;

    public void setCurentPassword(String str) {
        this.curentPassword = str;
    }

    public void setNewPassword(String str) {
        this.newPassword = str;
    }
}
