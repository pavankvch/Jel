package com.jelsat.activities;

import android.support.v7.widget.RecyclerView.Adapter;
import com.data.propertybook.Card;
import com.jelsat.adapters.CardsAdapter;
import com.jelsat.adapters.RecyclerViewSectionAdapter;

class BookingPaymentActivity$3 extends RecyclerViewSectionAdapter<Card, CardsAdapter> {
    final /* synthetic */ BookingPaymentActivity this$0;

    BookingPaymentActivity$3(BookingPaymentActivity bookingPaymentActivity, Adapter adapter, CardsAdapter cardsAdapter) {
        this.this$0 = bookingPaymentActivity;
        super(adapter, cardsAdapter);
    }

    public int getIndexOfSection(Card card) {
        return (card == null || card.isSavedCard() != null) ? 0 : 1;
    }
}
