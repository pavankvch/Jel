package com.jelsat.activities;

import com.jelsat.R;
import com.jelsat.interfaces.OnRangeSeekbarChangeListener;
import java.util.Locale;

class FiltersActivity$1 implements OnRangeSeekbarChangeListener {
    final /* synthetic */ FiltersActivity this$0;

    FiltersActivity$1(FiltersActivity filtersActivity) {
        this.this$0 = filtersActivity;
    }

    public void valueChanged(Number number, Number number2) {
        if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar")) {
            this.this$0.priceTextView.setText(String.format("%s %s - %s %s", new Object[]{number, this.this$0.getString(R.string.saudi_currency), number2, this.this$0.getString(R.string.saudi_currency)}));
            return;
        }
        this.this$0.priceTextView.setText(String.format("%s %s - %s %s", new Object[]{this.this$0.getString(R.string.saudi_currency), number, this.this$0.getString(R.string.saudi_currency), number2}));
    }
}
