package com.data.propertylisting;

import com.data.addproperty.Steps;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Published {
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("currency_code")
    @Expose
    private String currencyCode;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("lat")
    @Expose
    private String latitude;
    @SerializedName("lng")
    @Expose
    private String longitude;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("percentage")
    @Expose
    private int percentage;
    @SerializedName("price")
    @Expose
    private float price;
    @SerializedName("property_id")
    @Expose
    private String propertId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("steps")
    @Expose
    private Steps steps;
    @SerializedName("type")
    @Expose
    private String type;

    public String getPropertId() {
        return this.propertId;
    }

    public void setPropertId(String str) {
        this.propertId = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float f) {
        this.price = f;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public void setCurrencyCode(String str) {
        this.currencyCode = str;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String str) {
        this.latitude = str;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String str) {
        this.longitude = str;
    }

    public Steps getSteps() {
        return this.steps;
    }

    public void setSteps(Steps steps) {
        this.steps = steps;
    }

    public int getPercentage() {
        return this.percentage;
    }

    public void setPercentage(int i) {
        this.percentage = i;
    }
}
