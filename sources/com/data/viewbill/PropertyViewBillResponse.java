package com.data.viewbill;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PropertyViewBillResponse {
    @SerializedName("addon")
    @Expose
    private List<Addon> addon = null;
    @SerializedName("check_in")
    @Expose
    private String checkIn;
    @SerializedName("check_out")
    @Expose
    private String checkOut;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("end_day")
    @Expose
    private String endDay;
    @SerializedName("guests")
    @Expose
    private String guests;
    @SerializedName("nights")
    @Expose
    private int nights;
    @SerializedName("per_night")
    @Expose
    private float perNightPrice;
    @SerializedName("price")
    @Expose
    private float price;
    @SerializedName("propertydata")
    @Expose
    private Propertydata propertydata;
    @SerializedName("servicefee")
    @Expose
    private List<Addon> servicefee = null;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("start_day")
    @Expose
    private String startDay;
    @SerializedName("tax")
    @Expose
    private float tax;
    @SerializedName("total")
    @Expose
    private float total;

    public float getTotal() {
        return this.total;
    }

    public void setTotal(float f) {
        this.total = f;
    }

    public Propertydata getPropertydata() {
        return this.propertydata;
    }

    public void setPropertydata(Propertydata propertydata) {
        this.propertydata = propertydata;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float f) {
        this.price = f;
    }

    public List<Addon> getServicefee() {
        return this.servicefee;
    }

    public void setServicefee(List<Addon> list) {
        this.servicefee = list;
    }

    public List<Addon> getAddon() {
        return this.addon;
    }

    public void setAddon(List<Addon> list) {
        this.addon = list;
    }

    public float getTax() {
        return this.tax;
    }

    public void setTax(float f) {
        this.tax = f;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String str) {
        this.endDate = str;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String str) {
        this.startDate = str;
    }

    public String getGuests() {
        return this.guests;
    }

    public void setGuests(String str) {
        this.guests = str;
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

    public int getNights() {
        return this.nights;
    }

    public void setNights(int i) {
        this.nights = i;
    }

    public float getPerNightPrice() {
        return this.perNightPrice;
    }

    public void setPerNightPrice(float f) {
        this.perNightPrice = f;
    }

    public String getStartDay() {
        return this.startDay;
    }

    public void setStartDay(String str) {
        this.startDay = str;
    }

    public String getEndDay() {
        return this.endDay;
    }

    public void setEndDay(String str) {
        this.endDay = str;
    }
}
