package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.customclasses.viewpagerindicator.InkPageIndicator;

public class BecomeHostIntroDialogFragment_ViewBinding implements Unbinder {
    private BecomeHostIntroDialogFragment target;
    private View view2131362016;

    @UiThread
    public BecomeHostIntroDialogFragment_ViewBinding(final BecomeHostIntroDialogFragment becomeHostIntroDialogFragment, View view) {
        this.target = becomeHostIntroDialogFragment;
        becomeHostIntroDialogFragment.viewPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.view_pager, "field 'viewPager'", ViewPager.class);
        becomeHostIntroDialogFragment.circlePageIndicator = (InkPageIndicator) Utils.findRequiredViewAsType(view, R.id.circle_indicator, "field 'circlePageIndicator'", InkPageIndicator.class);
        view = Utils.findRequiredView(view, R.id.close_TV, "method 'clickOnClose'");
        this.view2131362016 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                becomeHostIntroDialogFragment.clickOnClose();
            }
        });
    }

    @CallSuper
    public void unbind() {
        BecomeHostIntroDialogFragment becomeHostIntroDialogFragment = this.target;
        if (becomeHostIntroDialogFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        becomeHostIntroDialogFragment.viewPager = null;
        becomeHostIntroDialogFragment.circlePageIndicator = null;
        this.view2131362016.setOnClickListener(null);
        this.view2131362016 = null;
    }
}
