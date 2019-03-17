package com.data.propertybook;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wallet {
    @SerializedName("main_balance")
    @Expose
    private int mainBalance;
    @SerializedName("referral")
    @Expose
    private int referral;

    public int getMainBalance() {
        return this.mainBalance;
    }

    public int getReferral() {
        return this.referral;
    }
}
