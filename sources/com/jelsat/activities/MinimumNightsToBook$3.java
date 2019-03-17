package com.jelsat.activities;

import android.app.TimePickerDialog.OnTimeSetListener;
import android.widget.EditText;
import android.widget.TimePicker;
import com.facebook.appevents.AppEventsConstants;

class MinimumNightsToBook$3 implements OnTimeSetListener {
    final /* synthetic */ MinimumNightsToBook this$0;

    MinimumNightsToBook$3(MinimumNightsToBook minimumNightsToBook) {
        this.this$0 = minimumNightsToBook;
    }

    public void onTimeSet(TimePicker timePicker, int i, int i2) {
        if (i == 12) {
            timePicker = "PM";
        } else if (i > 12) {
            i -= 12;
            timePicker = "PM";
        } else {
            if (i == 0) {
                i += 12;
            }
            timePicker = "AM";
        }
        if (i2 < 10) {
            StringBuilder stringBuilder = new StringBuilder(AppEventsConstants.EVENT_PARAM_VALUE_NO);
            stringBuilder.append(i2);
            i2 = stringBuilder.toString();
        } else {
            i2 = String.valueOf(i2);
        }
        EditText editText = this.this$0.checkInTimeEdt;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(i);
        stringBuilder2.append(":");
        stringBuilder2.append(i2);
        stringBuilder2.append(" ");
        stringBuilder2.append(timePicker);
        editText.setText(stringBuilder2.toString());
    }
}
