package com.jelsat.activities;

import android.text.Editable;
import android.text.TextWatcher;
import com.jelsat.R;

class MinimumNightsToBook$5 implements TextWatcher {
    final /* synthetic */ MinimumNightsToBook this$0;

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    MinimumNightsToBook$5(MinimumNightsToBook minimumNightsToBook) {
        this.this$0 = minimumNightsToBook;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (!(i == 0 || i3 == 0 || this.this$0.itemAtPosition == 0)) {
            if (this.this$0.cancelationPolicesEdt.getText().toString().isEmpty() == 0) {
                this.this$0.saveAndExit.setVisibility(0);
                this.this$0.reviewProperty.setEnabled(1);
                this.this$0.reviewProperty.setBackgroundResource(R.drawable.button_selected_background);
                return;
            }
        }
        this.this$0.reviewProperty.setEnabled(false);
        this.this$0.saveAndExit.setVisibility(8);
        this.this$0.reviewProperty.setBackgroundResource(R.drawable.button_backgound);
    }

    public void afterTextChanged(Editable editable) {
        if (this.this$0.itemAtPosition != null) {
            if (this.this$0.cancelationPolicesEdt.getText().toString().isEmpty() == null) {
                this.this$0.saveAndExit.setVisibility(0);
                this.this$0.reviewProperty.setEnabled(true);
                this.this$0.reviewProperty.setBackgroundResource(R.drawable.button_selected_background);
                return;
            }
        }
        this.this$0.reviewProperty.setEnabled(false);
        this.this$0.saveAndExit.setVisibility(8);
        this.this$0.reviewProperty.setBackgroundResource(R.drawable.button_backgound);
    }
}
