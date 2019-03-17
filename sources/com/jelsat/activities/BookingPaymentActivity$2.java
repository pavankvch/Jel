package com.jelsat.activities;

import android.support.v4.content.ContextCompat;
import com.jelsat.R;
import com.jelsat.adapters.SectionNameAdapter;

class BookingPaymentActivity$2 extends SectionNameAdapter {
    final /* synthetic */ BookingPaymentActivity this$0;

    BookingPaymentActivity$2(BookingPaymentActivity bookingPaymentActivity) {
        this.this$0 = bookingPaymentActivity;
    }

    public String getFirstSectionName() {
        return this.this$0.getString(R.string.saved_cards);
    }

    public String getSecondSectionName() {
        return this.this$0.getString(R.string.choose_payment);
    }

    public int getBackgroundColor() {
        return ContextCompat.getColor(this.this$0, R.color.transparent);
    }

    public int getSectionTextColor() {
        return ContextCompat.getColor(this.this$0, R.color.white);
    }
}
