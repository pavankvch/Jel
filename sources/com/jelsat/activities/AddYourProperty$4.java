package com.jelsat.activities;

import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import com.facebook.appevents.AppEventsConstants;

class AddYourProperty$4 implements OnItemSelectedListener {
    final /* synthetic */ AddYourProperty this$0;

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    AddYourProperty$4(AddYourProperty addYourProperty) {
        this.this$0 = addYourProperty;
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        this.this$0.singleBedposition = i;
        adapterView = adapterView.getItemAtPosition(i).toString();
        if (this.this$0.propertyTypeId.equalsIgnoreCase("40") == null && this.this$0.propertyTypeId.equalsIgnoreCase("168") == null) {
            if (i > 0) {
                adapterView = adapterView.split("\\s+");
                this.this$0.selectedSinglebed = adapterView[0];
                if (this.this$0.bedroomPosition <= null || this.this$0.maxGuestsPosition <= null || this.this$0.bathroomPosition <= null) {
                    AddYourProperty.access$100(this.this$0);
                } else {
                    AddYourProperty.access$000(this.this$0);
                }
                if (this.this$0.selectedSinglebed.endsWith("+") != null) {
                    this.this$0.selectedSinglebed = this.this$0.selectedSinglebed.replace("+", "");
                }
                return;
            }
            this.this$0.selectedSinglebed = AppEventsConstants.EVENT_PARAM_VALUE_NO;
            if (this.this$0.doubleBedPosition <= null || this.this$0.bedroomPosition <= null || this.this$0.maxGuestsPosition <= null || this.this$0.bathroomPosition <= null) {
                AddYourProperty.access$100(this.this$0);
            } else {
                AddYourProperty.access$000(this.this$0);
            }
        } else if (this.this$0.propertyTypeId.equalsIgnoreCase("168") == null) {
            this.this$0.selectedSinglebed = AppEventsConstants.EVENT_PARAM_VALUE_NO;
            if (i > 0) {
                adapterView = adapterView.split("\\s+");
                this.this$0.selectedSinglebed = adapterView[0];
                if (!(this.this$0.selectedSinglebed == null || this.this$0.selectedSinglebed.endsWith("+") == null)) {
                    this.this$0.selectedSinglebed = this.this$0.selectedSinglebed.replace("+", "");
                }
            }
        } else if (i > 0) {
            adapterView = adapterView.split("\\s+");
            this.this$0.selectedSinglebed = adapterView[0];
            if (this.this$0.tentsPosition <= null || this.this$0.bathroomPosition <= null || this.this$0.noOfPersons.getText().toString().equals("") != null || (TextUtils.isEmpty(this.this$0.noOfPersons.getText().toString()) != null && Integer.valueOf(this.this$0.noOfPersons.getText().toString()).intValue() <= null)) {
                AddYourProperty.access$100(this.this$0);
            } else {
                AddYourProperty.access$000(this.this$0);
            }
        } else {
            this.this$0.selectedSinglebed = AppEventsConstants.EVENT_PARAM_VALUE_NO;
            if (this.this$0.tentsPosition <= null || this.this$0.bathroomPosition <= null || this.this$0.noOfPersons.getText().toString().equals("") != null || (TextUtils.isEmpty(this.this$0.noOfPersons.getText().toString()) != null && Integer.valueOf(this.this$0.noOfPersons.getText().toString()).intValue() <= null)) {
                AddYourProperty.access$100(this.this$0);
            } else {
                AddYourProperty.access$000(this.this$0);
            }
        }
    }
}
