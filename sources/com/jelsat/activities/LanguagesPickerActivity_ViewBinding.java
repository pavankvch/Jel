package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class LanguagesPickerActivity_ViewBinding implements Unbinder {
    private LanguagesPickerActivity target;
    private View view2131361943;
    private View view2131361959;

    @UiThread
    public LanguagesPickerActivity_ViewBinding(LanguagesPickerActivity languagesPickerActivity) {
        this(languagesPickerActivity, languagesPickerActivity.getWindow().getDecorView());
    }

    @UiThread
    public LanguagesPickerActivity_ViewBinding(final LanguagesPickerActivity languagesPickerActivity, View view) {
        this.target = languagesPickerActivity;
        languagesPickerActivity.lanuguagesRecyclerview = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.lanuguages_recyclerview, "field 'lanuguagesRecyclerview'", RecyclerView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.cancel_TV, "method 'clickOnCancel'");
        this.view2131361959 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                languagesPickerActivity.clickOnCancel(view);
            }
        });
        view = Utils.findRequiredView(view, R.id.btn_submit, "method 'clickOnSubmit'");
        this.view2131361943 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                languagesPickerActivity.clickOnSubmit(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        LanguagesPickerActivity languagesPickerActivity = this.target;
        if (languagesPickerActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        languagesPickerActivity.lanuguagesRecyclerview = null;
        this.view2131361959.setOnClickListener(null);
        this.view2131361959 = null;
        this.view2131361943.setOnClickListener(null);
        this.view2131361943 = null;
    }
}
