package com.jelsat.activities;

import android.util.Log;
import com.jelsat.interfaces.OnRangeSeekbarFinalValueListener;

class FiltersActivity$2 implements OnRangeSeekbarFinalValueListener {
    final /* synthetic */ FiltersActivity this$0;

    FiltersActivity$2(FiltersActivity filtersActivity) {
        this.this$0 = filtersActivity;
    }

    public void finalValue(Number number, Number number2) {
        Log.d("xxx", String.format("%s -  %s", new Object[]{number, number2}));
        FiltersActivity.access$002(this.this$0, number.intValue());
        FiltersActivity.access$102(this.this$0, number2.intValue());
    }
}
