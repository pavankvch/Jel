package com.data.paymenthistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionData {
    @SerializedName("amount")
    @Expose
    private float amount;
    @SerializedName("transaction_id")
    @Expose
    private String transactionId;
    @SerializedName("title")
    @Expose
    private String transactionName;
    @SerializedName("type")
    @Expose
    private String transactionType;

    public String getTransactionType() {
        return this.transactionType;
    }

    public void setTransactionType(String str) {
        this.transactionType = str;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(String str) {
        this.transactionId = str;
    }

    public float getAmount() {
        return this.amount;
    }

    public void setAmount(float f) {
        this.amount = f;
    }

    public String getTransactionName() {
        return this.transactionName;
    }

    public void setTransactionName(String str) {
        this.transactionName = str;
    }
}
