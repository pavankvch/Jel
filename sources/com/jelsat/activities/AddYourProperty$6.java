package com.jelsat.activities;

import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

class AddYourProperty$6 implements OnItemSelectedListener {
    final /* synthetic */ AddYourProperty this$0;

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    AddYourProperty$6(AddYourProperty addYourProperty) {
        this.this$0 = addYourProperty;
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        this.this$0.bathroomPosition = i;
        adapterView = adapterView.getItemAtPosition(i).toString();
        if (this.this$0.propertyTypeId.equalsIgnoreCase("40") != null) {
            if (i > 0) {
                adapterView = adapterView.split("\\s+");
                this.this$0.selectedBathroom = adapterView[0];
                if (this.this$0.noOfPersons.getText().toString().equals("") != null || (TextUtils.isEmpty(this.this$0.noOfPersons.getText().toString()) != null && Integer.valueOf(this.this$0.noOfPersons.getText().toString()).intValue() <= null)) {
                    AddYourProperty.access$100(this.this$0);
                    return;
                } else {
                    AddYourProperty.access$000(this.this$0);
                    return;
                }
            }
            AddYourProperty.access$100(this.this$0);
        } else if (this.this$0.propertyTypeId.equalsIgnoreCase("168") != null) {
            if (i > 0) {
                adapterView = adapterView.split("\\s+");
                this.this$0.selectedBathroom = adapterView[0];
                if (this.this$0.tentsPosition <= null || TextUtils.isEmpty(this.this$0.noOfPersons.getText().toString()) != null || Integer.valueOf(this.this$0.noOfPersons.getText().toString()).intValue() <= null) {
                    AddYourProperty.access$100(this.this$0);
                    return;
                } else {
                    AddYourProperty.access$000(this.this$0);
                    return;
                }
            }
            AddYourProperty.access$100(this.this$0);
        } else if (i > 0) {
            adapterView = adapterView.split("\\s+");
            this.this$0.selectedBathroom = adapterView[0];
            if (this.this$0.bedroomPosition <= null || this.this$0.maxGuestsPosition <= null || (this.this$0.singleBedposition <= null && this.this$0.doubleBedPosition <= null)) {
                AddYourProperty.access$100(this.this$0);
            } else {
                AddYourProperty.access$000(this.this$0);
            }
        } else {
            AddYourProperty.access$100(this.this$0);
        }
    }
}
