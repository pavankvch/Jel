package com.data.viewbill;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Addon {
    @SerializedName("amount")
    @Expose
    private float amount;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public float getAmount() {
        return this.amount;
    }

    public void setAmount(float f) {
        this.amount = f;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }
}
