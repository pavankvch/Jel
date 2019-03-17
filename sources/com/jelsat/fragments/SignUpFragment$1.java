package com.jelsat.fragments;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import com.jelsat.R;
import com.jelsat.utils.Utils;

class SignUpFragment$1 implements OnFocusChangeListener {
    final /* synthetic */ SignUpFragment this$0;

    SignUpFragment$1(SignUpFragment signUpFragment) {
        this.this$0 = signUpFragment;
    }

    public void onFocusChange(View view, boolean z) {
        if (this.this$0.mobileLineView != null) {
            if (z) {
                if (SignUpFragment.access$000(this.this$0)) {
                    this.this$0.mobileLineView.setBackgroundColor(Utils.applyColor(this.this$0.requireActivity(), R.color.edit_text_error_text_color));
                } else {
                    this.this$0.mobileLineView.setBackgroundColor(Utils.applyColor(this.this$0.requireActivity(), R.color.editext_line_color));
                }
            } else if (SignUpFragment.access$000(this.this$0)) {
                this.this$0.mobileLineView.setBackgroundColor(Utils.applyColor(this.this$0.requireActivity(), R.color.edit_text_error_background_line_color));
            } else {
                this.this$0.mobileLineView.setBackgroundColor(Utils.applyColor(this.this$0.requireActivity(), R.color.editext_line_color));
            }
        }
    }
}
