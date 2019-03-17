package com.jelsat.activities;

import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

class AddYourProperty$2 implements OnItemSelectedListener {
    final /* synthetic */ AddYourProperty this$0;

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    AddYourProperty$2(AddYourProperty addYourProperty) {
        this.this$0 = addYourProperty;
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        this.this$0.tentsPosition = i;
        adapterView = adapterView.getItemAtPosition(i).toString();
        if (i > 0) {
            adapterView = adapterView.split("\\s+");
            this.this$0.selectedTent = adapterView[0];
            if (this.this$0.selectedTent.endsWith("+") != null) {
                this.this$0.selectedTent = this.this$0.selectedTent.replace("+", "");
            }
            if (this.this$0.bathroomPosition <= null || this.this$0.noOfPersons.getText().toString().equals("") != null || (TextUtils.isEmpty(this.this$0.noOfPersons.getText().toString()) != null && Integer.valueOf(this.this$0.noOfPersons.getText().toString()).intValue() <= null)) {
                AddYourProperty.access$100(this.this$0);
                return;
            } else {
                AddYourProperty.access$000(this.this$0);
                return;
            }
        }
        AddYourProperty.access$100(this.this$0);
    }
}
