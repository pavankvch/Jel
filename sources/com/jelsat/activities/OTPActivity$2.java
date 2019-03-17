package com.jelsat.activities;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

class OTPActivity$2 implements OnEditorActionListener {
    final /* synthetic */ OTPActivity this$0;

    OTPActivity$2(OTPActivity oTPActivity) {
        this.this$0 = oTPActivity;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 6) {
            return null;
        }
        this.this$0.otpEditText.clearFocus();
        OTPActivity.access$100(this.this$0, OTPActivity.access$000(this.this$0));
        return true;
    }
}
