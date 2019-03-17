package com.jelsat.activities;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

class AddYourProperty$1 implements OnItemSelectedListener {
    final /* synthetic */ AddYourProperty this$0;

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    AddYourProperty$1(AddYourProperty addYourProperty) {
        this.this$0 = addYourProperty;
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        adapterView = adapterView.getItemAtPosition(i).toString();
        if (i > 0) {
            adapterView = adapterView.split("\\s+");
            this.this$0.selectedRoom = adapterView[0];
            if (this.this$0.selectedRoom.endsWith("+") != null) {
                this.this$0.selectedRoom = this.this$0.selectedRoom.replace("+", "");
            }
        }
    }
}
