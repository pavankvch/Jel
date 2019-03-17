package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jelsat.R;

public class BecomeHostIntroFragment_ViewBinding implements Unbinder {
    private BecomeHostIntroFragment target;

    @UiThread
    public BecomeHostIntroFragment_ViewBinding(BecomeHostIntroFragment becomeHostIntroFragment, View view) {
        this.target = becomeHostIntroFragment;
        becomeHostIntroFragment.screenImg = (ImageView) Utils.findRequiredViewAsType(view, R.id.screen_img, "field 'screenImg'", ImageView.class);
        becomeHostIntroFragment.screenTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.screen_title, "field 'screenTitle'", TextView.class);
        becomeHostIntroFragment.screenDescriptionTv = (TextView) Utils.findRequiredViewAsType(view, R.id.screen_description_tv, "field 'screenDescriptionTv'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        BecomeHostIntroFragment becomeHostIntroFragment = this.target;
        if (becomeHostIntroFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        becomeHostIntroFragment.screenImg = null;
        becomeHostIntroFragment.screenTitle = null;
        becomeHostIntroFragment.screenDescriptionTv = null;
    }
}
