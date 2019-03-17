package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.widgets.FancyButton;

public class AddingImagesActivity_ViewBinding implements Unbinder {
    private AddingImagesActivity target;
    private View view2131362015;
    private View view2131362166;
    private View view2131362388;
    private View view2131362804;

    @UiThread
    public AddingImagesActivity_ViewBinding(AddingImagesActivity addingImagesActivity) {
        this(addingImagesActivity, addingImagesActivity.getWindow().getDecorView());
    }

    @UiThread
    public AddingImagesActivity_ViewBinding(final AddingImagesActivity addingImagesActivity, View view) {
        this.target = addingImagesActivity;
        View findRequiredView = Utils.findRequiredView(view, R.id.feature_image, "field 'featuredImageIv' and method 'selectImageForUpload'");
        addingImagesActivity.featuredImageIv = (ImageView) Utils.castView(findRequiredView, R.id.feature_image, "field 'featuredImageIv'", ImageView.class);
        this.view2131362166 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addingImagesActivity.selectImageForUpload();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.other_images_layout, "field 'otherImagesLayout' and method 'selectotherImageForUpload'");
        addingImagesActivity.otherImagesLayout = (LinearLayout) Utils.castView(findRequiredView, R.id.other_images_layout, "field 'otherImagesLayout'", LinearLayout.class);
        this.view2131362388 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addingImagesActivity.selectotherImageForUpload();
            }
        });
        addingImagesActivity.horizonatalProgressbar = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.progressbar, "field 'horizonatalProgressbar'", ProgressBar.class);
        addingImagesActivity.imageCount = (TextView) Utils.findRequiredViewAsType(view, R.id.images_count, "field 'imageCount'", TextView.class);
        addingImagesActivity.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        findRequiredView = Utils.findRequiredView(view, R.id.upload_button, "field 'uploadButton' and method 'clickOnUploadButton'");
        addingImagesActivity.uploadButton = (FancyButton) Utils.castView(findRequiredView, R.id.upload_button, "field 'uploadButton'", FancyButton.class);
        this.view2131362804 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addingImagesActivity.clickOnUploadButton(view);
            }
        });
        view = Utils.findRequiredView(view, R.id.closeLayout, "method 'clickOnClose'");
        this.view2131362015 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addingImagesActivity.clickOnClose(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        AddingImagesActivity addingImagesActivity = this.target;
        if (addingImagesActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        addingImagesActivity.featuredImageIv = null;
        addingImagesActivity.otherImagesLayout = null;
        addingImagesActivity.horizonatalProgressbar = null;
        addingImagesActivity.imageCount = null;
        addingImagesActivity.recyclerView = null;
        addingImagesActivity.uploadButton = null;
        this.view2131362166.setOnClickListener(null);
        this.view2131362166 = null;
        this.view2131362388.setOnClickListener(null);
        this.view2131362388 = null;
        this.view2131362804.setOnClickListener(null);
        this.view2131362804 = null;
        this.view2131362015.setOnClickListener(null);
        this.view2131362015 = null;
    }
}
