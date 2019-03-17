package com.data.payments;

import com.data.propertydetail.Review;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HostProperty {
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("currency_code")
    @Expose
    private String currencyCode;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("price")
    @Expose
    private float price;
    @SerializedName("property_id")
    @Expose
    private String propertyId;
    @SerializedName("name")
    @Expose
    private String propertyName;
    @SerializedName("published_on")
    @Expose
    private String published_on;
    @SerializedName("reviews")
    @Expose
    private Review review;
    @SerializedName("type")
    @Expose
    private String type;

    public String getPropertyName() {
        return this.propertyName;
    }

    public void setPropertyName(String str) {
        this.propertyName = str;
    }

    public Review getReview() {
        return this.review;
    }

    public void setReview(Review review) {
        this.review = review;
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

    public String getPublished_on() {
        return this.published_on;
    }

    public void setPublished_on(String str) {
        this.published_on = str;
    }

    public String getPropertyId() {
        return this.propertyId;
    }

    public void setPropertyId(String str) {
        this.propertyId = str;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public void setCurrencyCode(String str) {
        this.currencyCode = str;
    }

    public String toString() {
        return this.propertyName;
    }
}
