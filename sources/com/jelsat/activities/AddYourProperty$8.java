package com.jelsat.activities;

import android.text.Editable;
import android.text.TextWatcher;

class AddYourProperty$8 implements TextWatcher {
    final /* synthetic */ AddYourProperty this$0;

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    AddYourProperty$8(AddYourProperty addYourProperty) {
        this.this$0 = addYourProperty;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.this$0.propertyTypeId.equalsIgnoreCase("40") != null) {
            if (!((i == 0 && i3 == 0) || this.this$0.bathroomPosition <= null || this.this$0.noOfPersons.getText().toString().trim().length() == null)) {
                if (Integer.valueOf(this.this$0.noOfPersons.getText().toString()).intValue() > null) {
                    AddYourProperty.access$000(this.this$0);
                    return;
                }
            }
            AddYourProperty.access$100(this.this$0);
            return;
        }
        if (this.this$0.propertyTypeId.equalsIgnoreCase("168") != null) {
            if (!(i == 0 && i3 == 0) && this.this$0.tentsPosition > null && this.this$0.bathroomPosition > null && this.this$0.noOfPersons.getText().toString().trim().length() != null) {
                if (Integer.valueOf(this.this$0.noOfPersons.getText().toString()).intValue() > null) {
                    AddYourProperty.access$000(this.this$0);
                }
            }
            AddYourProperty.access$100(this.this$0);
        }
    }
}
