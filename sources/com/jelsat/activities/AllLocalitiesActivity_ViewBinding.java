package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Spinner;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class AllLocalitiesActivity_ViewBinding implements Unbinder {
    private AllLocalitiesActivity target;
    private View view2131361892;

    @UiThread
    public AllLocalitiesActivity_ViewBinding(AllLocalitiesActivity allLocalitiesActivity) {
        this(allLocalitiesActivity, allLocalitiesActivity.getWindow().getDecorView());
    }

    @UiThread
    public AllLocalitiesActivity_ViewBinding(final AllLocalitiesActivity allLocalitiesActivity, View view) {
        this.target = allLocalitiesActivity;
        allLocalitiesActivity.spinner_nav = (Spinner) Utils.findRequiredViewAsType(view, R.id.spinner_nav, "field 'spinner_nav'", Spinner.class);
        allLocalitiesActivity.allLocalitiesRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.all_localities_recyclerview, "field 'allLocalitiesRecyclerView'", RecyclerView.class);
        view = Utils.findRequiredView(view, R.id.back_arrow_IV, "method 'clickOnBackArrow'");
        this.view2131361892 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                allLocalitiesActivity.clickOnBackArrow();
            }
        });
    }

    @CallSuper
    public void unbind() {
        AllLocalitiesActivity allLocalitiesActivity = this.target;
        if (allLocalitiesActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        allLocalitiesActivity.spinner_nav = null;
        allLocalitiesActivity.allLocalitiesRecyclerView = null;
        this.view2131361892.setOnClickListener(null);
        this.view2131361892 = null;
    }
}
