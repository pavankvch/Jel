package com.jelsat.activities;

import android.text.Editable;
import android.text.TextWatcher;

class OTPActivity$1 implements TextWatcher {
    final /* synthetic */ OTPActivity this$0;

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    OTPActivity$1(OTPActivity oTPActivity) {
        this.this$0 = oTPActivity;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (charSequence.toString().length() == 4) {
            this.this$0.verifyOTPButton.setFocusable(true);
            this.this$0.verifyOTPButton.setClickable(true);
            this.this$0.verifyOTPButton.setEnabled(true);
            return;
        }
        this.this$0.verifyOTPButton.setFocusable(false);
        this.this$0.verifyOTPButton.setClickable(false);
        this.this$0.verifyOTPButton.setEnabled(false);
    }
}
