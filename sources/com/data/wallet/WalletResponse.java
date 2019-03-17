package com.data.wallet;

import com.data.propertybook.Card;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class WalletResponse {
    private List<Card> cards;
    @SerializedName("offer_money")
    private float offerMoney;
    @SerializedName("refund_money")
    private float refundMoney;
    private float total;

    public float getRefundMoney() {
        return this.refundMoney;
    }

    public void setRefundMoney(float f) {
        this.refundMoney = f;
    }

    public float getOfferMoney() {
        return this.offerMoney;
    }

    public void setOfferMoney(float f) {
        this.offerMoney = f;
    }

    public float getTotal() {
        return this.total;
    }

    public void setTotal(float f) {
        this.total = f;
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public void setCards(List<Card> list) {
        this.cards = list;
    }
}
