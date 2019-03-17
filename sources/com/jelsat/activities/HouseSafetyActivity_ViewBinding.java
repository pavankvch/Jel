package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class HouseSafetyActivity_ViewBinding implements Unbinder {
    private HouseSafetyActivity target;
    private View view2131361893;
    private View view2131362435;
    private View view2131362531;

    @UiThread
    public HouseSafetyActivity_ViewBinding(HouseSafetyActivity houseSafetyActivity) {
        this(houseSafetyActivity, houseSafetyActivity.getWindow().getDecorView());
    }

    @UiThread
    public HouseSafetyActivity_ViewBinding(final HouseSafetyActivity houseSafetyActivity, View view) {
        this.target = houseSafetyActivity;
        houseSafetyActivity.hoseSafetyRecyclerview = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.housesafety_recyclerView, "field 'hoseSafetyRecyclerview'", RecyclerView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.back_arrow_property, "field 'backArrowProperty' and method 'backbutton'");
        houseSafetyActivity.backArrowProperty = (ImageView) Utils.castView(findRequiredView, R.id.back_arrow_property, "field 'backArrowProperty'", ImageView.class);
        this.view2131361893 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                houseSafetyActivity.backbutton();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.saveAndExit, "field 'saveAndExit' and method 'saveAndExit'");
        houseSafetyActivity.saveAndExit = (TextView) Utils.castView(findRequiredView, R.id.saveAndExit, "field 'saveAndExit'", TextView.class);
        this.view2131362531 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                houseSafetyActivity.saveAndExit();
            }
        });
        view = Utils.findRequiredView(view, R.id.products_list_next, "field 'hosueSafetyNextButton' and method 'houseSafety'");
        houseSafetyActivity.hosueSafetyNextButton = (Button) Utils.castView(view, R.id.products_list_next, "field 'hosueSafetyNextButton'", Button.class);
        this.view2131362435 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                houseSafetyActivity.houseSafety();
            }
        });
    }

    @CallSuper
    public void unbind() {
        HouseSafetyActivity houseSafetyActivity = this.target;
        if (houseSafetyActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        houseSafetyActivity.hoseSafetyRecyclerview = null;
        houseSafetyActivity.backArrowProperty = null;
        houseSafetyActivity.saveAndExit = null;
        houseSafetyActivity.hosueSafetyNextButton = null;
        this.view2131361893.setOnClickListener(null);
        this.view2131361893 = null;
        this.view2131362531.setOnClickListener(null);
        this.view2131362531 = null;
        this.view2131362435.setOnClickListener(null);
        this.view2131362435 = null;
    }
}
