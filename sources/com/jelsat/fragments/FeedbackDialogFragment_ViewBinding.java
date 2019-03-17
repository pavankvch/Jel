package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class FeedbackDialogFragment_ViewBinding implements Unbinder {
    private FeedbackDialogFragment target;
    private View view2131361943;
    private View view2131362246;

    @UiThread
    public FeedbackDialogFragment_ViewBinding(final FeedbackDialogFragment feedbackDialogFragment, View view) {
        this.target = feedbackDialogFragment;
        feedbackDialogFragment.starImgView = (ImageView) Utils.findRequiredViewAsType(view, R.id.img_star, "field 'starImgView'", ImageView.class);
        feedbackDialogFragment.headingTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_heading, "field 'headingTv'", TextView.class);
        feedbackDialogFragment.textTitleLabel = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_text_title, "field 'textTitleLabel'", TextView.class);
        feedbackDialogFragment.feedbackTimeTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_feedback_time, "field 'feedbackTimeTv'", TextView.class);
        feedbackDialogFragment.messageEt = (EditText) Utils.findRequiredViewAsType(view, R.id.et_message, "field 'messageEt'", EditText.class);
        feedbackDialogFragment.ratingBar = (RatingBar) Utils.findRequiredViewAsType(view, R.id.ratingBar, "field 'ratingBar'", RatingBar.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.img_close, "method 'clickOnClose'");
        this.view2131362246 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                feedbackDialogFragment.clickOnClose();
            }
        });
        view = Utils.findRequiredView(view, R.id.btn_submit, "method 'clickOnSubmit'");
        this.view2131361943 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                feedbackDialogFragment.clickOnSubmit();
            }
        });
    }

    @CallSuper
    public void unbind() {
        FeedbackDialogFragment feedbackDialogFragment = this.target;
        if (feedbackDialogFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        feedbackDialogFragment.starImgView = null;
        feedbackDialogFragment.headingTv = null;
        feedbackDialogFragment.textTitleLabel = null;
        feedbackDialogFragment.feedbackTimeTv = null;
        feedbackDialogFragment.messageEt = null;
        feedbackDialogFragment.ratingBar = null;
        this.view2131362246.setOnClickListener(null);
        this.view2131362246 = null;
        this.view2131361943.setOnClickListener(null);
        this.view2131361943 = null;
    }
}
