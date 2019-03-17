package com.jelsat.activities;

import android.text.Editable;
import android.text.TextWatcher;
import com.jelsat.R;

class SaveLocation$1 implements TextWatcher {
    final /* synthetic */ SaveLocation this$0;

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    SaveLocation$1(SaveLocation saveLocation) {
        this.this$0 = saveLocation;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.this$0.city_name.getText().toString().trim().length() == null) {
            this.this$0.add_your_property.setEnabled(0);
            this.this$0.add_your_property.setBackgroundResource(R.drawable.button_backgound);
            return;
        }
        this.this$0.add_your_property.setEnabled(1);
        this.this$0.add_your_property.setBackgroundResource(R.drawable.button_save_background);
    }
}
