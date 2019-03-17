package com.data.paymenthistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PaymentHistoryResponse {
    @SerializedName("all")
    @Expose
    private List<PaymentHistory> allPaymentsList;
    @SerializedName("in")
    @Expose
    private List<PaymentHistory> creditedPaymentsList;
    @SerializedName("out")
    @Expose
    private List<PaymentHistory> debitedPaymentsList;

    public List<PaymentHistory> getAllPaymentsList() {
        return this.allPaymentsList;
    }

    public void setAllPaymentsList(List<PaymentHistory> list) {
        this.allPaymentsList = list;
    }

    public List<PaymentHistory> getCreditedPaymentsList() {
        return this.creditedPaymentsList;
    }

    public void setCreditedPaymentsList(List<PaymentHistory> list) {
        this.creditedPaymentsList = list;
    }

    public List<PaymentHistory> getDebitedPaymentsList() {
        return this.debitedPaymentsList;
    }

    public void setDebitedPaymentsList(List<PaymentHistory> list) {
        this.debitedPaymentsList = list;
    }
}
