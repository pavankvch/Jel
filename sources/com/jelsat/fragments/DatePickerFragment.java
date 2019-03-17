package com.jelsat.fragments;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.DatePicker;
import com.jelsat.R;
import com.jelsat.events.DOBSelectedEvent;
import java.util.Calendar;
import org.greenrobot.eventbus.EventBus;

public class DatePickerFragment extends DialogFragment implements OnDateSetListener {
    Calendar calendar;

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        if (getArguments() == null || getArguments().getSerializable("calendar") == null) {
            this.calendar = Calendar.getInstance();
        } else {
            this.calendar = (Calendar) getArguments().getSerializable("calendar");
        }
        if (this.calendar == null) {
            throw new RuntimeException("calendar instance must not be null");
        }
        Bundle datePickerDialog = new DatePickerDialog(getActivity(), this, this.calendar.get(1), this.calendar.get(2), this.calendar.get(5));
        datePickerDialog.getDatePicker().setMaxDate(Calendar.getInstance().getTimeInMillis() - 568024668000L);
        datePickerDialog.setButton(-1, getString(R.string.ok), datePickerDialog);
        datePickerDialog.setButton(-2, getString(R.string.cancellation_policy_txt), datePickerDialog);
        return datePickerDialog;
    }

    public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
        datePicker = Calendar.getInstance();
        datePicker.set(i, i2, i3);
        Log.d("zzz", datePicker.getTime().toString());
        EventBus.getDefault().post(new DOBSelectedEvent(datePicker));
    }
}
