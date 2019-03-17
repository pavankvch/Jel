package com.data.searchproperty;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SearchPropertyRequest {
    @SerializedName("amenities")
    @Expose
    private List<String> amenities = null;
    @SerializedName("booking_type")
    @Expose
    private String bookingType;
    @SerializedName("cancel_policy")
    @Expose
    private List<String> cancelPolicy = null;
    @SerializedName("check_in")
    @Expose
    private String checkIn;
    @SerializedName("check_out")
    @Expose
    private String checkOut;
    @SerializedName("double_beds")
    @Expose
    private String doubleBeds;
    @SerializedName("house_rules")
    @Expose
    private List<String> houseRules = null;
    @SerializedName("nearby")
    @Expose
    private boolean isNearBy;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lng")
    @Expose
    private String lng;
    @SerializedName("no_of_guests")
    @Expose
    private String noOfGuests;
    @SerializedName("price_from")
    @Expose
    private String priceFrom;
    @SerializedName("price_to")
    @Expose
    private String priceTo;
    @SerializedName("property_type")
    @Expose
    private List<String> propertyType = null;
    @SerializedName("rooms")
    @Expose
    private String rooms;
    @SerializedName("save")
    @Expose
    private String saved;
    @SerializedName("beds")
    @Expose
    private String singleBeds;
    @SerializedName("sort")
    @Expose
    private String sort;

    public boolean isNearBy() {
        return this.isNearBy;
    }

    public void setNearBy(boolean z) {
        this.isNearBy = z;
    }

    public String getLat() {
        return this.lat;
    }

    public void setLat(String str) {
        this.lat = str;
    }

    public String getLng() {
        return this.lng;
    }

    public void setLng(String str) {
        this.lng = str;
    }

    public String getCheckIn() {
        return this.checkIn;
    }

    public void setCheckIn(String str) {
        this.checkIn = str;
    }

    public String getCheckOut() {
        return this.checkOut;
    }

    public void setCheckOut(String str) {
        this.checkOut = str;
    }

    public String getNoOfGuests() {
        return this.noOfGuests;
    }

    public void setNoOfGuests(String str) {
        this.noOfGuests = str;
    }

    public List<String> getPropertyType() {
        return this.propertyType;
    }

    public void setPropertyType(List<String> list) {
        this.propertyType = list;
    }

    public String getBookingType() {
        return this.bookingType;
    }

    public void setBookingType(String str) {
        this.bookingType = str;
    }

    public String getPriceFrom() {
        return this.priceFrom;
    }

    public void setPriceFrom(String str) {
        this.priceFrom = str;
    }

    public String getPriceTo() {
        return this.priceTo;
    }

    public void setPriceTo(String str) {
        this.priceTo = str;
    }

    public String getRooms() {
        return this.rooms;
    }

    public void setRooms(String str) {
        this.rooms = str;
    }

    public String getSingleBeds() {
        return this.singleBeds;
    }

    public void setSingleBeds(String str) {
        this.singleBeds = str;
    }

    public String getDoubleBeds() {
        return this.doubleBeds;
    }

    public void setDoubleBeds(String str) {
        this.doubleBeds = str;
    }

    public List<String> getAmenities() {
        return this.amenities;
    }

    public void setAmenities(List<String> list) {
        this.amenities = list;
    }

    public List<String> getHouseRules() {
        return this.houseRules;
    }

    public void setHouseRules(List<String> list) {
        this.houseRules = list;
    }

    public List<String> getCancelPolicy() {
        return this.cancelPolicy;
    }

    public void setCancelPolicy(List<String> list) {
        this.cancelPolicy = list;
    }

    public String getSaved() {
        return this.saved;
    }

    public void setSaved(String str) {
        this.saved = str;
    }

    public String getSort() {
        return this.sort;
    }

    public void setSort(String str) {
        this.sort = str;
    }
}
