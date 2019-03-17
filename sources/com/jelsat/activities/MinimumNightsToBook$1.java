package com.jelsat.activities;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

class MinimumNightsToBook$1 implements OnItemSelectedListener {
    final /* synthetic */ MinimumNightsToBook this$0;

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    MinimumNightsToBook$1(MinimumNightsToBook minimumNightsToBook) {
        this.this$0 = minimumNightsToBook;
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        adapterView = adapterView.getItemAtPosition(i).toString().split("\\s+");
        this.this$0.selectedNights = adapterView[0];
    }
}
