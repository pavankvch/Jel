package com.data.propertybook;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Card {
    private int cardDrawable;
    @SerializedName("card_no")
    @Expose
    private String cardNo;
    @SerializedName("card_type")
    @Expose
    private String cardType;
    private boolean isChecked;
    private boolean isClickable;
    private boolean isSavedCard;
    @SerializedName("token")
    @Expose
    private String token;

    public String getCardNo() {
        return this.cardNo;
    }

    public void setCardNo(String str) {
        this.cardNo = str;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public boolean getChecked() {
        return this.isChecked;
    }

    public void setChecked(boolean z) {
        this.isChecked = z;
    }

    public boolean isSavedCard() {
        return this.isSavedCard;
    }

    public void setSavedCard(boolean z) {
        this.isSavedCard = z;
    }

    public int getCardDrawable() {
        return this.cardDrawable;
    }

    public void setCardDrawable(int i) {
        this.cardDrawable = i;
    }

    public boolean isClickable() {
        return this.isClickable;
    }

    public void setClickable(boolean z) {
        this.isClickable = z;
    }

    public String getCardType() {
        return this.cardType;
    }

    public void setCardType(String str) {
        this.cardType = str;
    }
}
