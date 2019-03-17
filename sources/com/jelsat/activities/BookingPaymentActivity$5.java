package com.jelsat.activities;

import android.text.Editable;
import android.text.TextWatcher;
import com.jelsat.R;

class BookingPaymentActivity$5 implements TextWatcher {
    final /* synthetic */ BookingPaymentActivity this$0;

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    BookingPaymentActivity$5(BookingPaymentActivity bookingPaymentActivity) {
        this.this$0 = bookingPaymentActivity;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.this$0.couponEditText.getText().toString().trim().length() > null) {
            this.this$0.applyTV.setTextColor(this.this$0.getResources().getColor(R.color.accept_color));
        } else {
            this.this$0.applyTV.setTextColor(this.this$0.getResources().getColor(R.color.bookings_propertyname_color));
        }
    }
}
