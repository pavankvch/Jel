package com.data.paymenthistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PaymentHistory {
    @SerializedName("data")
    @Expose
    private List<TransactionData> data;
    @SerializedName("month")
    @Expose
    private String month;

    public String getMonth() {
        return this.month;
    }

    public void setMonth(String str) {
        this.month = str;
    }

    public List<TransactionData> getData() {
        return this.data;
    }

    public void setData(List<TransactionData> list) {
        this.data = list;
    }
}
