package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.widgets.FancyButton;

public class DateGuestPropertySelectionDialog_ViewBinding implements Unbinder {
    private DateGuestPropertySelectionDialog target;
    private View view2131361892;
    private View view2131361961;
    private View view2131362533;

    @UiThread
    public DateGuestPropertySelectionDialog_ViewBinding(final DateGuestPropertySelectionDialog dateGuestPropertySelectionDialog, View view) {
        this.target = dateGuestPropertySelectionDialog;
        dateGuestPropertySelectionDialog.locationText = (TextView) Utils.findRequiredViewAsType(view, R.id.location_text, "field 'locationText'", TextView.class);
        dateGuestPropertySelectionDialog.saveButtonLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.save_button_layout, "field 'saveButtonLayout'", LinearLayout.class);
        dateGuestPropertySelectionDialog.tabLayout = (TabLayout) Utils.findRequiredViewAsType(view, R.id.tabLayout, "field 'tabLayout'", TabLayout.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.cancel_button, "field 'cancelButton' and method 'clickOnCancel'");
        dateGuestPropertySelectionDialog.cancelButton = (FancyButton) Utils.castView(findRequiredView, R.id.cancel_button, "field 'cancelButton'", FancyButton.class);
        this.view2131361961 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                dateGuestPropertySelectionDialog.clickOnCancel();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.save_button, "field 'saveButton' and method 'clickOnSave'");
        dateGuestPropertySelectionDialog.saveButton = (FancyButton) Utils.castView(findRequiredView, R.id.save_button, "field 'saveButton'", FancyButton.class);
        this.view2131362533 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                dateGuestPropertySelectionDialog.clickOnSave();
            }
        });
        dateGuestPropertySelectionDialog.viewPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.viewPager, "field 'viewPager'", ViewPager.class);
        view = Utils.findRequiredView(view, R.id.back_arrow_IV, "method 'clickOnBack'");
        this.view2131361892 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                dateGuestPropertySelectionDialog.clickOnBack(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        DateGuestPropertySelectionDialog dateGuestPropertySelectionDialog = this.target;
        if (dateGuestPropertySelectionDialog == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        dateGuestPropertySelectionDialog.locationText = null;
        dateGuestPropertySelectionDialog.saveButtonLayout = null;
        dateGuestPropertySelectionDialog.tabLayout = null;
        dateGuestPropertySelectionDialog.cancelButton = null;
        dateGuestPropertySelectionDialog.saveButton = null;
        dateGuestPropertySelectionDialog.viewPager = null;
        this.view2131361961.setOnClickListener(null);
        this.view2131361961 = null;
        this.view2131362533.setOnClickListener(null);
        this.view2131362533 = null;
        this.view2131361892.setOnClickListener(null);
        this.view2131361892 = null;
    }
}
