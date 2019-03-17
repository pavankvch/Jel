package com.jelsat.activities;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

class AddYourProperty$5 implements OnItemSelectedListener {
    final /* synthetic */ AddYourProperty this$0;

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    AddYourProperty$5(AddYourProperty addYourProperty) {
        this.this$0 = addYourProperty;
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        this.this$0.bedroomPosition = i;
        adapterView = adapterView.getItemAtPosition(i).toString();
        if (i > 0) {
            adapterView = adapterView.split("\\s+");
            this.this$0.selectedBed = adapterView[0];
            if (this.this$0.selectedBed.endsWith("+") != null) {
                this.this$0.selectedBed = this.this$0.selectedBed.replace("+", "");
            }
            if (this.this$0.maxGuestsPosition <= null || this.this$0.bathroomPosition <= null || (this.this$0.singleBedposition <= null && this.this$0.doubleBedPosition <= null)) {
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
