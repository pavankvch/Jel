package com.data.addproperty;

import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;

public class DecimalFilter implements TextWatcher {
    int count = -1;
    EditText et;

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public DecimalFilter(EditText editText) {
        this.et = editText;
    }

    public void afterTextChanged(Editable editable) {
        if (editable.length() > 0) {
            String obj = this.et.getText().toString();
            this.et.setOnKeyListener(new OnKeyListener() {
                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    if (i == 67) {
                        i = DecimalFilter.this;
                        i.count -= 1;
                        DecimalFilter.this.et.setFilters(new InputFilter[]{new LengthFilter(100)});
                    }
                    return false;
                }
            });
            if (obj.charAt(editable.length() - 1) == '.') {
                this.count = 0;
            }
            if (this.count >= 0) {
                if (this.count == 2) {
                    this.et.setFilters(new InputFilter[]{new LengthFilter(editable.length())});
                }
                this.count += 1;
            }
        }
    }
}
