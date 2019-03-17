package com.jelsat.activities;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.v4.content.res.ResourcesCompat;
import android.widget.EditText;
import com.jelsat.R;
import java.util.Date;

class TestActivity$2 implements OnClickListener {
    final /* synthetic */ TestActivity this$0;
    final /* synthetic */ Date val$date;
    final /* synthetic */ EditText val$editText;

    TestActivity$2(TestActivity testActivity, EditText editText, Date date) {
        this.this$0 = testActivity;
        this.val$editText = editText;
        this.val$date = date;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (this.val$editText.getText().toString().trim().isEmpty() != 0) {
            this.val$editText.setError("Please enter price");
            return;
        }
        TestActivity.access$000(this.this$0).updateDate(this.val$date, Integer.parseInt(this.val$editText.getText().toString()));
        dialogInterface.dismiss();
        this.this$0.calendarPickerView.setTitleTypeface(ResourcesCompat.getFont(this.this$0, R.font.sf_ui_display_regular));
    }
}
