package com.data.SeedAndLanguage;

import com.google.gson.annotations.SerializedName;

public class ChangeLanguageResponse {
    @SerializedName("status")
    private boolean status;

    public boolean isStatus() {
        return this.status;
    }

    public void setStatus(boolean z) {
        this.status = z;
    }
}
