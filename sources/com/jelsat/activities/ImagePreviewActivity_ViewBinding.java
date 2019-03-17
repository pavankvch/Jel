package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class ImagePreviewActivity_ViewBinding implements Unbinder {
    private ImagePreviewActivity target;
    private View view2131361849;
    private View view2131361894;
    private View view2131362095;

    @UiThread
    public ImagePreviewActivity_ViewBinding(ImagePreviewActivity imagePreviewActivity) {
        this(imagePreviewActivity, imagePreviewActivity.getWindow().getDecorView());
    }

    @UiThread
    public ImagePreviewActivity_ViewBinding(final ImagePreviewActivity imagePreviewActivity, View view) {
        this.target = imagePreviewActivity;
        imagePreviewActivity.otherImageIv = (ImageView) Utils.findRequiredViewAsType(view, R.id.other_imageview, "field 'otherImageIv'", ImageView.class);
        imagePreviewActivity.otherImageTitleEt = (EditText) Utils.findRequiredViewAsType(view, R.id.other_title_dialog, "field 'otherImageTitleEt'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.add_item, "field 'add_item' and method 'clickOnAdd'");
        imagePreviewActivity.add_item = (TextView) Utils.castView(findRequiredView, R.id.add_item, "field 'add_item'", TextView.class);
        this.view2131361849 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                imagePreviewActivity.clickOnAdd();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.dialog_cancel, "method 'finishScreen'");
        this.view2131362095 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                imagePreviewActivity.finishScreen();
            }
        });
        view = Utils.findRequiredView(view, R.id.back_dialog, "method 'backfinishScreen'");
        this.view2131361894 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                imagePreviewActivity.backfinishScreen();
            }
        });
    }

    @CallSuper
    public void unbind() {
        ImagePreviewActivity imagePreviewActivity = this.target;
        if (imagePreviewActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        imagePreviewActivity.otherImageIv = null;
        imagePreviewActivity.otherImageTitleEt = null;
        imagePreviewActivity.add_item = null;
        this.view2131361849.setOnClickListener(null);
        this.view2131361849 = null;
        this.view2131362095.setOnClickListener(null);
        this.view2131362095 = null;
        this.view2131361894.setOnClickListener(null);
        this.view2131361894 = null;
    }
}
