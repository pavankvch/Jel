package com.jelsat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.bankdetails.BankDetailsPresenter;
import com.businesslogic.bankdetails.IBankDetailsView;
import com.data.bankdetails.AddBankDetailsRequest;
import com.data.bankdetails.BankDetailsResponse;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import com.jelsat.widgets.FancyButton;

public class HostbankDetailsActivity extends BaseAppCompactActivity implements IBankDetailsView {
    @BindView(2131361932)
    public FancyButton addOrUpdateButton;
    private BankDetailsPresenter bankDetailsPresenter = new BankDetailsPresenter(this, RetrofitClient.getAPIService());
    @BindView(2131362137)
    public EditText bankHolderNameEt;
    @BindView(2131362136)
    public EditText bankNameEdittext;
    @BindView(2131361902)
    public TextView bankNameError;
    boolean cheakClick;
    boolean checkRemove = false;
    @BindView(2131362138)
    public EditText countryEditText;
    @BindView(2131362040)
    public TextView countryError;
    @BindView(2131362733)
    public TextView headingTv;
    @BindView(2131362213)
    public TextView holderNameError;
    @BindView(2131362229)
    public TextView ibanError;
    @BindView(2131362230)
    public TextView ibanErrorCopy;
    @BindView(2131362140)
    public EditText ibanEt;
    @BindView(2131362141)
    public EditText ibanEtCopy;
    private AddBankDetailsRequest request;
    @BindView(2131362661)
    public TextInputLayout textilIbanCopy;
    @BindView(2131362710)
    public TextView tvEdit;

    public int getActivityLayout() {
        return R.layout.host_bank_details_add;
    }

    @OnClick({2131362138})
    public void clickOnCountry() {
        Intent intent = new Intent(this, CountryPickerActivity.class);
        intent.putExtra(StringConstants.SHOW_COUNTRY_CODE, false);
        startActivityForResult(intent, 101);
    }

    @OnClick({2131361932})
    public void addBankDetails() {
        if (fieldValidation()) {
            this.cheakClick = true;
            this.request = new AddBankDetailsRequest();
            this.request.setBankName(this.bankNameEdittext.getText().toString().trim());
            this.request.setCountry(this.countryEditText.getText().toString().trim());
            this.request.setIbanNumber(this.ibanEt.getText().toString().trim());
            this.request.setBankHolderName(this.bankHolderNameEt.getText().toString().trim());
            this.bankDetailsPresenter.addBankDetails(getString(R.string.please_wait), this.request);
        }
    }

    @OnClick({2131361892})
    public void clickOnBack() {
        finish();
    }

    @OnClick({2131362710})
    public void clickEdit() {
        if (this.tvEdit.getText().equals(getString(R.string.edit))) {
            this.countryEditText.setEnabled(true);
            this.bankNameEdittext.setEnabled(true);
            this.bankHolderNameEt.setEnabled(true);
            this.ibanEt.setEnabled(true);
            this.addOrUpdateButton.setClickable(true);
            this.addOrUpdateButton.setBackgroundColor(getResources().getColor(R.color.app_background));
            this.textilIbanCopy.setVisibility(0);
            this.tvEdit.setText(R.string.remove_coupoun);
            return;
        }
        this.checkRemove = true;
        this.request = new AddBankDetailsRequest();
        this.request.setBankName("");
        this.request.setCountry("");
        this.request.setIbanNumber("");
        this.request.setBankHolderName("");
        this.bankDetailsPresenter.addBankDetails(getString(R.string.please_wait), this.request);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.headingTv.setText(getResources().getString(R.string.bankdetails_label));
        this.bankDetailsPresenter.getBankDetails(getString(R.string.please_wait));
    }

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void onSuccess(BankDetailsResponse bankDetailsResponse, boolean z) {
        setData(bankDetailsResponse);
        if (z) {
            showToast(getString(R.string.bank_details_updated));
        }
        if (this.cheakClick) {
            finish();
        }
        if (this.checkRemove) {
            showToast(getString(R.string.bank_details_updated));
        }
    }

    private void setData(BankDetailsResponse bankDetailsResponse) {
        this.countryEditText.setText(bankDetailsResponse.getCountry());
        this.bankNameEdittext.setText(bankDetailsResponse.getBankName());
        this.bankNameEdittext.setSelection(this.bankNameEdittext.getText().length());
        this.bankHolderNameEt.setText(bankDetailsResponse.getBankHolderName());
        this.bankHolderNameEt.setSelection(this.bankHolderNameEt.getText().length());
        this.ibanEt.setText(bankDetailsResponse.getIbanNumber());
        this.ibanEtCopy.setText(bankDetailsResponse.getIbanNumber());
        this.ibanEt.setSelection(this.ibanEt.getText().length());
        if (this.countryEditText.getText().toString().trim().equalsIgnoreCase("") == null || this.bankNameEdittext.getText().toString().trim().equalsIgnoreCase("") == null || this.bankHolderNameEt.getText().toString().trim().equalsIgnoreCase("") == null || this.ibanEt.getText().toString().trim().equalsIgnoreCase("") == null) {
            this.countryEditText.setEnabled(false);
            this.bankNameEdittext.setEnabled(false);
            this.bankHolderNameEt.setEnabled(false);
            this.ibanEt.setEnabled(false);
            this.textilIbanCopy.setVisibility(8);
            this.tvEdit.setText(R.string.edit);
            this.tvEdit.setVisibility(0);
            this.addOrUpdateButton.setBackgroundColor(getResources().getColor(R.color.textview_background_color));
            this.addOrUpdateButton.setClickable(false);
            this.addOrUpdateButton.setText(getString(R.string.bank_details_button_update));
            return;
        }
        this.addOrUpdateButton.setText(getString(R.string.bank_details_button_add));
        this.tvEdit.setVisibility(8);
    }

    private boolean fieldValidation() {
        hideKeyboard();
        this.countryEditText.setError(null);
        this.countryError.setVisibility(4);
        this.bankNameError.setError(null);
        this.bankNameError.setVisibility(4);
        this.holderNameError.setError(null);
        this.holderNameError.setVisibility(4);
        this.ibanError.setError(null);
        this.ibanError.setVisibility(4);
        String trim = this.countryEditText.getText().toString().trim();
        String trim2 = this.bankNameEdittext.getText().toString().trim();
        String trim3 = this.bankHolderNameEt.getText().toString().trim();
        String trim4 = this.ibanEt.getText().toString().trim();
        String trim5 = this.ibanEtCopy.getText().toString().trim();
        if (trim.length() <= 0) {
            this.countryError.setText(getString(R.string.home_field_not_empty_label));
            this.countryError.setAlpha(1.0f);
            this.countryError.setVisibility(0);
            return false;
        } else if (trim2.length() <= 0) {
            this.bankNameError.setText(getString(R.string.home_field_not_empty_label));
            this.bankNameError.setAlpha(1.0f);
            this.bankNameError.setVisibility(0);
            return false;
        } else if (trim3.length() <= 0) {
            this.holderNameError.setText(getString(R.string.home_field_not_empty_label));
            this.holderNameError.setAlpha(1.0f);
            this.holderNameError.setVisibility(0);
            return false;
        } else if (trim4.length() <= 0) {
            this.ibanError.setText(getString(R.string.home_field_not_empty_label));
            this.ibanError.setAlpha(1.0f);
            this.ibanError.setVisibility(0);
            return false;
        } else if (trim5.length() <= 0) {
            this.ibanErrorCopy.setText(getString(R.string.confirm_iban_number));
            this.ibanErrorCopy.setAlpha(1.0f);
            this.ibanErrorCopy.setVisibility(0);
            return false;
        } else if (trim4.equalsIgnoreCase(trim5)) {
            return true;
        } else {
            this.ibanErrorCopy.setText(getString(R.string.iban_not_matched));
            this.ibanErrorCopy.setAlpha(1.0f);
            this.ibanErrorCopy.setVisibility(0);
            return false;
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == 101) {
            i = intent.getExtras();
            if (i != 0) {
                this.countryEditText.setText(i.getString(StringConstants.COUNTRY_NAME));
            }
        }
    }

    public void onDetachedFromWindow() {
        if (this.bankDetailsPresenter != null) {
            this.bankDetailsPresenter.unSubscribeDisposables();
        }
        super.onDetachedFromWindow();
    }
}
