package com.jelsat.activities;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import com.facebook.appevents.AppEventsConstants;
import com.jelsat.R;

class MinimumNightsToBook$2 implements OnItemSelectedListener {
    final /* synthetic */ MinimumNightsToBook this$0;

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    MinimumNightsToBook$2(MinimumNightsToBook minimumNightsToBook) {
        this.this$0 = minimumNightsToBook;
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        this.this$0.itemAtPosition = i;
        if (this.this$0.itemAtPosition != null) {
            if (this.this$0.itemAtPosition == 2) {
                this.this$0.bookingTypeString = AppEventsConstants.EVENT_PARAM_VALUE_NO;
            } else {
                this.this$0.bookingTypeString = AppEventsConstants.EVENT_PARAM_VALUE_YES;
            }
            if (this.this$0.cancelationPolicesEdt.getText().toString().isEmpty() == null) {
                this.this$0.saveAndExit.setVisibility(0);
                this.this$0.reviewProperty.setEnabled(true);
                this.this$0.reviewProperty.setBackgroundResource(R.drawable.button_selected_background);
                return;
            }
            this.this$0.reviewProperty.setEnabled(false);
            this.this$0.saveAndExit.setVisibility(8);
            this.this$0.reviewProperty.setBackgroundResource(R.drawable.button_backgound);
            return;
        }
        this.this$0.reviewProperty.setEnabled(false);
        this.this$0.saveAndExit.setVisibility(8);
        this.this$0.reviewProperty.setBackgroundResource(R.drawable.button_backgound);
    }
}
