package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.widgets.FancyButton;
import com.squareup.timessquare.CalendarPickerView;

public class TestActivity_ViewBinding implements Unbinder {
    private TestActivity target;
    private View view2131361892;
    private View view2131362517;

    @UiThread
    public TestActivity_ViewBinding(TestActivity testActivity) {
        this(testActivity, testActivity.getWindow().getDecorView());
    }

    @UiThread
    public TestActivity_ViewBinding(final TestActivity testActivity, View view) {
        this.target = testActivity;
        testActivity.calendarPickerView = (CalendarPickerView) Utils.findRequiredViewAsType(view, R.id.calendarView, "field 'calendarPickerView'", CalendarPickerView.class);
        testActivity.clear = (TextView) Utils.findRequiredViewAsType(view, R.id.clear_TV, "field 'clear'", TextView.class);
        testActivity.noResultTV = (TextView) Utils.findRequiredViewAsType(view, R.id.no_result_textView, "field 'noResultTV'", TextView.class);
        testActivity.noResultLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.no_result_layout, "field 'noResultLayout'", LinearLayout.class);
        testActivity.noResultImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.no_result_image, "field 'noResultImage'", ImageView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.retry_button, "field 'retryButton' and method 'clickOnRetryButton'");
        testActivity.retryButton = (FancyButton) Utils.castView(findRequiredView, R.id.retry_button, "field 'retryButton'", FancyButton.class);
        this.view2131362517 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                testActivity.clickOnRetryButton();
            }
        });
        view = Utils.findRequiredView(view, R.id.back_arrow_IV, "method 'clickOnBack'");
        this.view2131361892 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                testActivity.clickOnBack();
            }
        });
    }

    @CallSuper
    public void unbind() {
        TestActivity testActivity = this.target;
        if (testActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        testActivity.calendarPickerView = null;
        testActivity.clear = null;
        testActivity.noResultTV = null;
        testActivity.noResultLayout = null;
        testActivity.noResultImage = null;
        testActivity.retryButton = null;
        this.view2131362517.setOnClickListener(null);
        this.view2131362517 = null;
        this.view2131361892.setOnClickListener(null);
        this.view2131361892 = null;
    }
}
