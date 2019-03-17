package com.data.propertybook;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PropertyBookResponse {
    @SerializedName("amount")
    @Expose
    private int amount;
    @SerializedName("booking_type")
    @Expose
    private String bookingType;
    @SerializedName("saved_cards")
    @Expose
    private List<Card> cards = null;
    @SerializedName("mada_enabled")
    @Expose
    private String madaEnabled;
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("wallet")
    @Expose
    private Wallet wallet;

    public String getOrderId() {
        return this.orderId;
    }

    public int getAmount() {
        return this.amount;
    }

    public Wallet getWallet() {
        return this.wallet;
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public String getBookingType() {
        return this.bookingType;
    }

    public String getMadaEnabled() {
        return this.madaEnabled;
    }

    public void setMadaEnabled(String str) {
        this.madaEnabled = str;
    }
}
