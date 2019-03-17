package com.jelsat.fragments;

import android.app.Dialog;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.data.propertycostcalendar.UpdateCalendarRequest;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseDialogFragment;
import com.jelsat.utils.Utils;
import com.jelsat.widgets.FancyButton;
import org.greenrobot.eventbus.EventBus;

public class UpdatePriceDialogFragment extends BaseDialogFragment {
    private static final String PROPERTY_ID = "property_id";
    private static final String SELECTED_DATE = "selected_date";
    private int propertyId;
    private String selectedDate;
    @BindView(2131362577)
    TextView selectedDateTv;
    @BindView(2131362800)
    FancyButton updateButton;
    @BindView(2131362801)
    EditText updatePriceEditText;
    @BindView(2131362802)
    TextInputLayout updatePriceTextInputLayout;

    public int getDialogFragmentLayoutId() {
        return R.layout.price_update_dialog;
    }

    @OnClick({2131361961})
    public void clickOnCancelButton() {
        Utils.hideKeyboard(getActivity());
        dismiss();
    }

    @OnClick({2131362800})
    public void clickOnUpdateButton() {
        if (!fieldValidation()) {
            return;
        }
        if (isNetworkConnected()) {
            Utils.hideKeyboard(getActivity());
            UpdateCalendarRequest updateCalendarRequest = new UpdateCalendarRequest();
            updateCalendarRequest.setEndDate(this.selectedDate);
            updateCalendarRequest.setPropertyId(this.propertyId);
            updateCalendarRequest.setStartDate(this.selectedDate);
            updateCalendarRequest.setWeeklyDay("");
            updateCalendarRequest.setPrice(Float.parseFloat(this.updatePriceEditText.getText().toString().trim()));
            updateCalendarRequest.setType("pricing");
            EventBus.getDefault().post(updateCalendarRequest);
            dismiss();
            return;
        }
        showToast(getString(R.string.error_message_network));
    }

    public static UpdatePriceDialogFragment newInstance(String str, int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("property_id", i);
        bundle.putString(SELECTED_DATE, str);
        str = new UpdatePriceDialogFragment();
        str.setArguments(bundle);
        return str;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        bundle = super.onCreateDialog(bundle);
        if (bundle.getWindow() != null) {
            bundle.getWindow().requestFeature(1);
        }
        return bundle;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        layoutInflater = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.updatePriceTextInputLayout.setHintEnabled(null);
        viewGroup = new Rect();
        getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(viewGroup);
        if (layoutInflater != null) {
            layoutInflater.setMinimumWidth((int) (((float) viewGroup.width()) * 0.8f));
            layoutInflater.setMinimumHeight((int) (((float) viewGroup.height()) * 1053609165));
        }
        return layoutInflater;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getArguments() != null) {
            this.propertyId = getArguments().getInt("property_id", 0);
            this.selectedDate = getArguments().getString(SELECTED_DATE);
        }
        this.selectedDateTv.setText(Utils.inboxDateFormatter(this.selectedDate));
    }

    private boolean fieldValidation() {
        this.updatePriceTextInputLayout.setErrorEnabled(false);
        String trim = this.updatePriceEditText.getText().toString().trim();
        if (!TextUtils.isEmpty(trim)) {
            if (!trim.equalsIgnoreCase(".")) {
                if (Float.parseFloat(trim) < 1.0f) {
                    this.updatePriceTextInputLayout.setError(getString(R.string.atleast_price_label));
                    return false;
                } else if (Float.parseFloat(trim) <= 9999.0f) {
                    return true;
                } else {
                    this.updatePriceTextInputLayout.setError(getString(R.string.price_must_lessthan_tenthousand));
                    return false;
                }
            }
        }
        this.updatePriceTextInputLayout.setError(getString(R.string.enter_price_label));
        return false;
    }
}
