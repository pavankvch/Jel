package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.widgets.FancyButton;

public class UpdatePriceDialogFragment_ViewBinding implements Unbinder {
    private UpdatePriceDialogFragment target;
    private View view2131361961;
    private View view2131362800;

    @UiThread
    public UpdatePriceDialogFragment_ViewBinding(final UpdatePriceDialogFragment updatePriceDialogFragment, View view) {
        this.target = updatePriceDialogFragment;
        updatePriceDialogFragment.updatePriceEditText = (EditText) Utils.findRequiredViewAsType(view, R.id.updated_price, "field 'updatePriceEditText'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.update_button, "field 'updateButton' and method 'clickOnUpdateButton'");
        updatePriceDialogFragment.updateButton = (FancyButton) Utils.castView(findRequiredView, R.id.update_button, "field 'updateButton'", FancyButton.class);
        this.view2131362800 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                updatePriceDialogFragment.clickOnUpdateButton();
            }
        });
        updatePriceDialogFragment.selectedDateTv = (TextView) Utils.findRequiredViewAsType(view, R.id.selected_date_TV, "field 'selectedDateTv'", TextView.class);
        updatePriceDialogFragment.updatePriceTextInputLayout = (TextInputLayout) Utils.findRequiredViewAsType(view, R.id.updated_price_text_inputLayout, "field 'updatePriceTextInputLayout'", TextInputLayout.class);
        view = Utils.findRequiredView(view, R.id.cancel_button, "method 'clickOnCancelButton'");
        this.view2131361961 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                updatePriceDialogFragment.clickOnCancelButton();
            }
        });
    }

    @CallSuper
    public void unbind() {
        UpdatePriceDialogFragment updatePriceDialogFragment = this.target;
        if (updatePriceDialogFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        updatePriceDialogFragment.updatePriceEditText = null;
        updatePriceDialogFragment.updateButton = null;
        updatePriceDialogFragment.selectedDateTv = null;
        updatePriceDialogFragment.updatePriceTextInputLayout = null;
        this.view2131362800.setOnClickListener(null);
        this.view2131362800 = null;
        this.view2131361961.setOnClickListener(null);
        this.view2131361961 = null;
    }
}
