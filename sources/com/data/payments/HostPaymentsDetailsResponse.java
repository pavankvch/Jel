package com.data.payments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class HostPaymentsDetailsResponse {
    @SerializedName("all")
    @Expose
    private List<PaymentsDetailsData> all;
    @SerializedName("recieved")
    @Expose
    private List<PaymentsDetailsData> recieved;
    @SerializedName("upcomming")
    @Expose
    private List<PaymentsDetailsData> upcoming;

    public List<PaymentsDetailsData> getAll() {
        return this.all;
    }

    public void setAll(List<PaymentsDetailsData> list) {
        this.all = list;
    }

    public List<PaymentsDetailsData> getRecieved() {
        return this.recieved;
    }

    public void setRecieved(List<PaymentsDetailsData> list) {
        this.recieved = list;
    }

    public List<PaymentsDetailsData> getUpcoming() {
        return this.upcoming;
    }

    public void setUpcoming(List<PaymentsDetailsData> list) {
        this.upcoming = list;
    }
}
