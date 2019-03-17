package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jelsat.R;

public class IntroFragment_ViewBinding implements Unbinder {
    private IntroFragment target;

    @UiThread
    public IntroFragment_ViewBinding(IntroFragment introFragment, View view) {
        this.target = introFragment;
        introFragment.screenImg = (ImageView) Utils.findRequiredViewAsType(view, R.id.screen_img, "field 'screenImg'", ImageView.class);
        introFragment.screenTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.screen_title, "field 'screenTitle'", TextView.class);
        introFragment.screenDescriptionTv = (TextView) Utils.findRequiredViewAsType(view, R.id.screen_description_tv, "field 'screenDescriptionTv'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        IntroFragment introFragment = this.target;
        if (introFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        introFragment.screenImg = null;
        introFragment.screenTitle = null;
        introFragment.screenDescriptionTv = null;
    }
}
