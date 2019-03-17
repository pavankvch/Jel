package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class CalendarActivity_ViewBinding implements Unbinder {
    private CalendarActivity target;
    private View view2131361892;
    private View view2131362509;
    private View view2131362799;
    private View view2131362800;

    @UiThread
    public CalendarActivity_ViewBinding(CalendarActivity calendarActivity) {
        this(calendarActivity, calendarActivity.getWindow().getDecorView());
    }

    @UiThread
    public CalendarActivity_ViewBinding(final CalendarActivity calendarActivity, View view) {
        this.target = calendarActivity;
        View findRequiredView = Utils.findRequiredView(view, R.id.upcoming_booking_TV, "field 'upcomingBookingTV' and method 'clickOnUpComing'");
        calendarActivity.upcomingBookingTV = (TextView) Utils.castView(findRequiredView, R.id.upcoming_booking_TV, "field 'upcomingBookingTV'", TextView.class);
        this.view2131362799 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                calendarActivity.clickOnUpComing();
            }
        });
        calendarActivity.moreTV = (ImageView) Utils.findRequiredViewAsType(view, R.id.more_IV, "field 'moreTV'", ImageView.class);
        calendarActivity.inboxTabs = (TabLayout) Utils.findRequiredViewAsType(view, R.id.tabs, "field 'inboxTabs'", TabLayout.class);
        findRequiredView = Utils.findRequiredView(view, R.id.back_arrow_IV, "method 'clickOnBack'");
        this.view2131361892 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                calendarActivity.clickOnBack();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.reset_button, "method 'clickOnResetButton'");
        this.view2131362509 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                calendarActivity.clickOnResetButton();
            }
        });
        view = Utils.findRequiredView(view, R.id.update_button, "method 'clickOnUpdate'");
        this.view2131362800 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                calendarActivity.clickOnUpdate();
            }
        });
    }

    @CallSuper
    public void unbind() {
        CalendarActivity calendarActivity = this.target;
        if (calendarActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        calendarActivity.upcomingBookingTV = null;
        calendarActivity.moreTV = null;
        calendarActivity.inboxTabs = null;
        this.view2131362799.setOnClickListener(null);
        this.view2131362799 = null;
        this.view2131361892.setOnClickListener(null);
        this.view2131361892 = null;
        this.view2131362509.setOnClickListener(null);
        this.view2131362509 = null;
        this.view2131362800.setOnClickListener(null);
        this.view2131362800 = null;
    }
}
