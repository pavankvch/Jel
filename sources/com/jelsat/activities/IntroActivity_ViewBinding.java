package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.customclasses.viewpagerindicator.InkPageIndicator;

public class IntroActivity_ViewBinding implements Unbinder {
    private IntroActivity target;
    private View view2131361936;
    private View view2131361942;
    private View view2131362202;
    private View view2131362267;

    @UiThread
    public IntroActivity_ViewBinding(IntroActivity introActivity) {
        this(introActivity, introActivity.getWindow().getDecorView());
    }

    @UiThread
    public IntroActivity_ViewBinding(final IntroActivity introActivity, View view) {
        this.target = introActivity;
        introActivity.viewPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.view_pager, "field 'viewPager'", ViewPager.class);
        introActivity.circlePageIndicator = (InkPageIndicator) Utils.findRequiredViewAsType(view, R.id.circle_indicator, "field 'circlePageIndicator'", InkPageIndicator.class);
        introActivity.lastlayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lastlayout, "field 'lastlayout'", LinearLayout.class);
        introActivity.fancyButtonsLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.fancy_buttons_layout, "field 'fancyButtonsLayout'", LinearLayout.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.btn_skip, "method 'skipButton'");
        this.view2131361942 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                introActivity.skipButton();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.guest_user, "method 'SignIn'");
        this.view2131362202 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                introActivity.SignIn();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.joinNow, "method 'joinNow'");
        this.view2131362267 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                introActivity.joinNow();
            }
        });
        view = Utils.findRequiredView(view, R.id.btn_next, "method 'btnNext'");
        this.view2131361936 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                introActivity.btnNext();
            }
        });
    }

    @CallSuper
    public void unbind() {
        IntroActivity introActivity = this.target;
        if (introActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        introActivity.viewPager = null;
        introActivity.circlePageIndicator = null;
        introActivity.lastlayout = null;
        introActivity.fancyButtonsLayout = null;
        this.view2131361942.setOnClickListener(null);
        this.view2131361942 = null;
        this.view2131362202.setOnClickListener(null);
        this.view2131362202 = null;
        this.view2131362267.setOnClickListener(null);
        this.view2131362267 = null;
        this.view2131361936.setOnClickListener(null);
        this.view2131361936 = null;
    }
}
