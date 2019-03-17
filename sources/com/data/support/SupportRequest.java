package com.data.support;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class SupportRequest implements Serializable {
    @SerializedName("complaint_body")
    private String message;
    @SerializedName("complaint_subject")
    private String userRole;

    public String getUserRole() {
        return this.userRole;
    }

    public void setUserRole(String str) {
        this.userRole = str;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }
}
