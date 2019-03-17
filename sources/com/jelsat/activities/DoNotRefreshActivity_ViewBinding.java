package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class DoNotRefreshActivity_ViewBinding implements Unbinder {
    private DoNotRefreshActivity target;
    private View view2131362196;

    @UiThread
    public DoNotRefreshActivity_ViewBinding(DoNotRefreshActivity doNotRefreshActivity) {
        this(doNotRefreshActivity, doNotRefreshActivity.getWindow().getDecorView());
    }

    @UiThread
    public DoNotRefreshActivity_ViewBinding(final DoNotRefreshActivity doNotRefreshActivity, View view) {
        this.target = doNotRefreshActivity;
        doNotRefreshActivity.doNotRefreshLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.dont_refresh_layout, "field 'doNotRefreshLayout'", LinearLayout.class);
        doNotRefreshActivity.bookingsLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.bookings_layout, "field 'bookingsLayout'", LinearLayout.class);
        doNotRefreshActivity.infoTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.info_text, "field 'infoTextView'", TextView.class);
        view = Utils.findRequiredView(view, R.id.goto_bookings_button, "method 'clickOnBookings'");
        this.view2131362196 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                doNotRefreshActivity.clickOnBookings();
            }
        });
    }

    @CallSuper
    public void unbind() {
        DoNotRefreshActivity doNotRefreshActivity = this.target;
        if (doNotRefreshActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        doNotRefreshActivity.doNotRefreshLayout = null;
        doNotRefreshActivity.bookingsLayout = null;
        doNotRefreshActivity.infoTextView = null;
        this.view2131362196.setOnClickListener(null);
        this.view2131362196 = null;
    }
}
