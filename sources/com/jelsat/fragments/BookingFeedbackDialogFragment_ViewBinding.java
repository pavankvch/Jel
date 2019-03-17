package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class BookingFeedbackDialogFragment_ViewBinding implements Unbinder {
    private BookingFeedbackDialogFragment target;
    private View view2131361943;
    private View view2131362246;

    @UiThread
    public BookingFeedbackDialogFragment_ViewBinding(final BookingFeedbackDialogFragment bookingFeedbackDialogFragment, View view) {
        this.target = bookingFeedbackDialogFragment;
        bookingFeedbackDialogFragment.headingTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_heading, "field 'headingTv'", TextView.class);
        bookingFeedbackDialogFragment.messageEt = (EditText) Utils.findRequiredViewAsType(view, R.id.et_message, "field 'messageEt'", EditText.class);
        bookingFeedbackDialogFragment.textTitleTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_text_title, "field 'textTitleTv'", TextView.class);
        bookingFeedbackDialogFragment.ratingBar = (RatingBar) Utils.findRequiredViewAsType(view, R.id.ratingBar, "field 'ratingBar'", RatingBar.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.img_close, "method 'clickOnClose'");
        this.view2131362246 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                bookingFeedbackDialogFragment.clickOnClose();
            }
        });
        view = Utils.findRequiredView(view, R.id.btn_submit, "method 'clickOnSubmit'");
        this.view2131361943 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                bookingFeedbackDialogFragment.clickOnSubmit();
            }
        });
    }

    @CallSuper
    public void unbind() {
        BookingFeedbackDialogFragment bookingFeedbackDialogFragment = this.target;
        if (bookingFeedbackDialogFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        bookingFeedbackDialogFragment.headingTv = null;
        bookingFeedbackDialogFragment.messageEt = null;
        bookingFeedbackDialogFragment.textTitleTv = null;
        bookingFeedbackDialogFragment.ratingBar = null;
        this.view2131362246.setOnClickListener(null);
        this.view2131362246 = null;
        this.view2131361943.setOnClickListener(null);
        this.view2131361943 = null;
    }
}
