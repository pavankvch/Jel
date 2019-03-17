package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class ShowLocationActivity_ViewBinding implements Unbinder {
    private ShowLocationActivity target;
    private View view2131361893;
    private View view2131362014;

    @UiThread
    public ShowLocationActivity_ViewBinding(ShowLocationActivity showLocationActivity) {
        this(showLocationActivity, showLocationActivity.getWindow().getDecorView());
    }

    @UiThread
    public ShowLocationActivity_ViewBinding(final ShowLocationActivity showLocationActivity, View view) {
        this.target = showLocationActivity;
        View findRequiredView = Utils.findRequiredView(view, R.id.back_arrow_property, "field 'backArrowProperty' and method 'backArrow'");
        showLocationActivity.backArrowProperty = (ImageView) Utils.castView(findRequiredView, R.id.back_arrow_property, "field 'backArrowProperty'", ImageView.class);
        this.view2131361893 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                showLocationActivity.backArrow();
            }
        });
        view = Utils.findRequiredView(view, R.id.close, "field 'close' and method 'close'");
        showLocationActivity.close = (TextView) Utils.castView(view, R.id.close, "field 'close'", TextView.class);
        this.view2131362014 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                showLocationActivity.close();
            }
        });
    }

    @CallSuper
    public void unbind() {
        ShowLocationActivity showLocationActivity = this.target;
        if (showLocationActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        showLocationActivity.backArrowProperty = null;
        showLocationActivity.close = null;
        this.view2131361893.setOnClickListener(null);
        this.view2131361893 = null;
        this.view2131362014.setOnClickListener(null);
        this.view2131362014 = null;
    }
}
