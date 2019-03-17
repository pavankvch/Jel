package com.jelsat.activities;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.jelsat.R;

class EditProfileActivity$2 implements OnTouchListener {
    final /* synthetic */ EditProfileActivity this$0;

    EditProfileActivity$2(EditProfileActivity editProfileActivity) {
        this.this$0 = editProfileActivity;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (view.getId() == R.id.et_aboutme) {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            if ((motionEvent.getAction() & 255) == 1) {
                view.getParent().requestDisallowInterceptTouchEvent(false);
            }
        }
        return false;
    }
}
