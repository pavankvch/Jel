package com.jelsat.activities;

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

public class HostbankDetailsActivity_ViewBinding implements Unbinder {
    private HostbankDetailsActivity target;
    private View view2131361892;
    private View view2131361932;
    private View view2131362138;
    private View view2131362710;

    @UiThread
    public HostbankDetailsActivity_ViewBinding(HostbankDetailsActivity hostbankDetailsActivity) {
        this(hostbankDetailsActivity, hostbankDetailsActivity.getWindow().getDecorView());
    }

    @UiThread
    public HostbankDetailsActivity_ViewBinding(final HostbankDetailsActivity hostbankDetailsActivity, View view) {
        this.target = hostbankDetailsActivity;
        hostbankDetailsActivity.headingTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_heading, "field 'headingTv'", TextView.class);
        hostbankDetailsActivity.countryError = (TextView) Utils.findRequiredViewAsType(view, R.id.country_error, "field 'countryError'", TextView.class);
        hostbankDetailsActivity.bankNameError = (TextView) Utils.findRequiredViewAsType(view, R.id.bank_name_error, "field 'bankNameError'", TextView.class);
        hostbankDetailsActivity.holderNameError = (TextView) Utils.findRequiredViewAsType(view, R.id.holder_name_error, "field 'holderNameError'", TextView.class);
        hostbankDetailsActivity.ibanError = (TextView) Utils.findRequiredViewAsType(view, R.id.iban_error, "field 'ibanError'", TextView.class);
        hostbankDetailsActivity.ibanEtCopy = (EditText) Utils.findRequiredViewAsType(view, R.id.et_iban_copy, "field 'ibanEtCopy'", EditText.class);
        hostbankDetailsActivity.ibanErrorCopy = (TextView) Utils.findRequiredViewAsType(view, R.id.iban_error_copy, "field 'ibanErrorCopy'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.et_country, "field 'countryEditText' and method 'clickOnCountry'");
        hostbankDetailsActivity.countryEditText = (EditText) Utils.castView(findRequiredView, R.id.et_country, "field 'countryEditText'", EditText.class);
        this.view2131362138 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                hostbankDetailsActivity.clickOnCountry();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.tv_close, "field 'tvEdit' and method 'clickEdit'");
        hostbankDetailsActivity.tvEdit = (TextView) Utils.castView(findRequiredView, R.id.tv_close, "field 'tvEdit'", TextView.class);
        this.view2131362710 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                hostbankDetailsActivity.clickEdit();
            }
        });
        hostbankDetailsActivity.bankNameEdittext = (EditText) Utils.findRequiredViewAsType(view, R.id.et_bank, "field 'bankNameEdittext'", EditText.class);
        hostbankDetailsActivity.bankHolderNameEt = (EditText) Utils.findRequiredViewAsType(view, R.id.et_bank_holder_name, "field 'bankHolderNameEt'", EditText.class);
        hostbankDetailsActivity.ibanEt = (EditText) Utils.findRequiredViewAsType(view, R.id.et_iban, "field 'ibanEt'", EditText.class);
        findRequiredView = Utils.findRequiredView(view, R.id.btn_add, "field 'addOrUpdateButton' and method 'addBankDetails'");
        hostbankDetailsActivity.addOrUpdateButton = (FancyButton) Utils.castView(findRequiredView, R.id.btn_add, "field 'addOrUpdateButton'", FancyButton.class);
        this.view2131361932 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                hostbankDetailsActivity.addBankDetails();
            }
        });
        hostbankDetailsActivity.textilIbanCopy = (TextInputLayout) Utils.findRequiredViewAsType(view, R.id.textil_iban_copy, "field 'textilIbanCopy'", TextInputLayout.class);
        view = Utils.findRequiredView(view, R.id.back_arrow_IV, "method 'clickOnBack'");
        this.view2131361892 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                hostbankDetailsActivity.clickOnBack();
            }
        });
    }

    @CallSuper
    public void unbind() {
        HostbankDetailsActivity hostbankDetailsActivity = this.target;
        if (hostbankDetailsActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        hostbankDetailsActivity.headingTv = null;
        hostbankDetailsActivity.countryError = null;
        hostbankDetailsActivity.bankNameError = null;
        hostbankDetailsActivity.holderNameError = null;
        hostbankDetailsActivity.ibanError = null;
        hostbankDetailsActivity.ibanEtCopy = null;
        hostbankDetailsActivity.ibanErrorCopy = null;
        hostbankDetailsActivity.countryEditText = null;
        hostbankDetailsActivity.tvEdit = null;
        hostbankDetailsActivity.bankNameEdittext = null;
        hostbankDetailsActivity.bankHolderNameEt = null;
        hostbankDetailsActivity.ibanEt = null;
        hostbankDetailsActivity.addOrUpdateButton = null;
        hostbankDetailsActivity.textilIbanCopy = null;
        this.view2131362138.setOnClickListener(null);
        this.view2131362138 = null;
        this.view2131362710.setOnClickListener(null);
        this.view2131362710 = null;
        this.view2131361932.setOnClickListener(null);
        this.view2131361932 = null;
        this.view2131361892.setOnClickListener(null);
        this.view2131361892 = null;
    }
}
