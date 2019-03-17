package com.data.viewbill;

import com.data.amenitiesandhouserules.HouseSafety;
import com.data.amenitiesandhouserules.Houserule;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Propertydata {
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("cancell_description")
    @Expose
    private String cancelDescription;
    @SerializedName("cancellation_policy")
    @Expose
    private String cancellationPolicy;
    @SerializedName("currency_code")
    @Expose
    private String currencyCode;
    @SerializedName("custom_house_rule")
    @Expose
    private String customHouseRule;
    @SerializedName("favourite")
    @Expose
    private int favourite;
    @SerializedName("house_rules")
    @Expose
    private List<Houserule> houseRules = null;
    @SerializedName("house_safety")
    @Expose
    private List<HouseSafety> houseSafety = null;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private float price;
    @SerializedName("property_id")
    @Expose
    private String propertyId;
    @SerializedName("type")
    @Expose
    private String type;

    public String getPropertyId() {
        return this.propertyId;
    }

    public void setPropertyId(String str) {
        this.propertyId = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
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

    public int getFavourite() {
        return this.favourite;
    }

    public void setFavourite(int i) {
        this.favourite = i;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public List<Houserule> getHouseRules() {
        return this.houseRules;
    }

    public void setHouseRules(List<Houserule> list) {
        this.houseRules = list;
    }

    public List<HouseSafety> getHouseSafety() {
        return this.houseSafety;
    }

    public void setHouseSafety(List<HouseSafety> list) {
        this.houseSafety = list;
    }

    public String getCancellationPolicy() {
        return this.cancellationPolicy;
    }

    public void setCancellationPolicy(String str) {
        this.cancellationPolicy = str;
    }

    public String getCancelDescription() {
        return this.cancelDescription;
    }

    public void setCancelDescription(String str) {
        this.cancelDescription = str;
    }

    public String getCustomHouseRule() {
        return this.customHouseRule;
    }

    public void setCustomHouseRule(String str) {
        this.customHouseRule = str;
    }
}
