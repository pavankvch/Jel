package com.jelsat.activities;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class BookingPaymentActivity$6 implements OnClickListener {
    final /* synthetic */ BookingPaymentActivity this$0;

    BookingPaymentActivity$6(BookingPaymentActivity bookingPaymentActivity) {
        this.this$0 = bookingPaymentActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        BookingPaymentActivity.access$100(this.this$0).dismissDialog();
        BookingPaymentActivity.access$200(this.this$0);
    }
}
