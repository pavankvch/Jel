package com.jelsat.activities;

import android.text.Editable;
import android.text.TextWatcher;
import com.jelsat.R;

class EditProfileActivity$1 implements TextWatcher {
    final /* synthetic */ EditProfileActivity this$0;

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    EditProfileActivity$1(EditProfileActivity editProfileActivity) {
        this.this$0 = editProfileActivity;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (charSequence.toString().length() > null) {
            this.this$0.updateProfileBtn.setClickable(1);
            this.this$0.updateProfileBtn.setBackgroundColor(this.this$0.getResources().getColor(R.color.app_background));
            return;
        }
        this.this$0.updateProfileBtn.setClickable(0);
        this.this$0.updateProfileBtn.setBackgroundColor(this.this$0.getResources().getColor(R.color.grey));
    }
}
