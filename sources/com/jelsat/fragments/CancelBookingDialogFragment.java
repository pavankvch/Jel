package com.jelsat.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.framework.ErrorCodes;
import com.businesslogic.propertydetail.IPropertyDetailView;
import com.businesslogic.propertydetail.PropertyDetailPresenter;
import com.data.bookings.BookingCancelRequest;
import com.data.bookings.BookingCancelResponse;
import com.data.propertydetail.PropertyDetailResponse;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.jelsat.R;
import com.jelsat.activities.PaymentSuccessActivity;
import com.jelsat.baseuiframework.BaseDialogFragment;
import com.jelsat.constants.StringConstants;
import com.jelsat.events.CancelBookingEvent;
import com.jelsat.widgets.FancyButton;
import org.greenrobot.eventbus.EventBus;

public class CancelBookingDialogFragment extends BaseDialogFragment implements IPropertyDetailView {
    private String bookingId;
    private String bookingStatusCode;
    @BindView(2131361933)
    FancyButton btnConfirm;
    @BindView(2131361967)
    TextView cancelationPolicyDesc;
    private boolean isHost;
    private PropertyDetailPresenter propertyDetailPresenter = new PropertyDetailPresenter(this, RetrofitClient.getAPIService());
    @BindView(2131362147)
    EditText reasonEt;

    public int getDialogFragmentLayoutId() {
        return R.layout.cancel_booking_dialogue;
    }

    public void onSuccess(PropertyDetailResponse propertyDetailResponse) {
    }

    public void showSwipeToRefresh(boolean z) {
    }

    public static CancelBookingDialogFragment newInstance(String str, String str2, boolean z) {
        CancelBookingDialogFragment cancelBookingDialogFragment = new CancelBookingDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(StringConstants.BOOKING_ID, str);
        bundle.putString(StringConstants.BOOKING_STATUS_CODE, str2);
        bundle.putBoolean(StringConstants.CANCEL_BY_HOST, z);
        cancelBookingDialogFragment.setArguments(bundle);
        return cancelBookingDialogFragment;
    }

    @OnClick({2131361933})
    public void clickOnSubmit() {
        BookingCancelRequest bookingCancelRequest;
        if (this.reasonEt.getText().toString().trim().length() > 4) {
            bookingCancelRequest = new BookingCancelRequest();
            bookingCancelRequest.setOrderId(this.bookingId);
            bookingCancelRequest.setReason(this.reasonEt.getText().toString());
            if (this.isHost) {
                this.propertyDetailPresenter.cancelBookingByHost(getString(R.string.please_wait), bookingCancelRequest);
                return;
            } else {
                this.propertyDetailPresenter.cancelBookingByGuest(getString(R.string.please_wait), bookingCancelRequest);
                return;
            }
        }
        if (this.bookingStatusCode != null && this.bookingStatusCode.equalsIgnoreCase(StringConstants.BOOKING_WAITING)) {
            bookingCancelRequest = new BookingCancelRequest();
            bookingCancelRequest.setOrderId(this.bookingId);
            if (!this.isHost) {
                this.propertyDetailPresenter.cancelBookingByGuest(getString(R.string.please_wait), bookingCancelRequest);
            }
        }
    }

    @OnClick({2131361939})
    public void clickOnReject() {
        dismiss();
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
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (getArguments() != null) {
            this.isHost = getArguments().getBoolean(StringConstants.CANCEL_BY_HOST);
            this.bookingId = getArguments().getString(StringConstants.BOOKING_ID);
            this.bookingStatusCode = getArguments().getString(StringConstants.BOOKING_STATUS_CODE);
        }
        if (this.bookingStatusCode == null || this.bookingStatusCode.equalsIgnoreCase(StringConstants.BOOKING_WAITING) == null) {
            this.reasonEt.setImeOptions(6);
            this.reasonEt.setRawInputType(1);
            this.reasonEt.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable editable) {
                }

                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    if (charSequence.toString().trim().length() > 4) {
                        CancelBookingDialogFragment.this.btnConfirm.setClickable(1);
                        CancelBookingDialogFragment.this.btnConfirm.setBackgroundColor(CancelBookingDialogFragment.this.applyColor(R.color.property_instant_color));
                        return;
                    }
                    CancelBookingDialogFragment.this.btnConfirm.setClickable(0);
                    CancelBookingDialogFragment.this.btnConfirm.setBackgroundColor(CancelBookingDialogFragment.this.applyColor(R.color.grey));
                }
            });
        } else {
            this.cancelationPolicyDesc.setVisibility(8);
            this.reasonEt.setVisibility(8);
            this.btnConfirm.setClickable(true);
            this.btnConfirm.setBackgroundColor(applyColor(R.color.property_instant_color));
        }
        return layoutInflater;
    }

    public void onDetach() {
        if (this.propertyDetailPresenter != null) {
            this.propertyDetailPresenter.unSubscribeDisposables();
        }
        super.onDetach();
    }

    public void onSuccess(BookingCancelResponse bookingCancelResponse) {
        if (bookingCancelResponse != null) {
            if (bookingCancelResponse.isStatus()) {
                showToast(getString(R.string.booking_cancelled));
                EventBus.getDefault().postSticky(new CancelBookingEvent(bookingCancelResponse.getBookingCancelData(), this.isHost ^ 1));
                startActivity(new Intent(getActivity(), PaymentSuccessActivity.class));
            } else {
                showToast(getString(R.string.failed_to_cancel_booking));
            }
        }
        dismiss();
    }

    public void onError(APIError aPIError, int i) {
        dismiss();
        if (i != ErrorCodes.API_ERROR) {
            if (i == ErrorCodes.INTERNAL_SERVER_ERROR) {
                showToast(getString(R.string.internal_server_error));
            } else if (i == ErrorCodes.SOCKET_TIME_OUT_ERROR_CODE) {
                showToast(getString(R.string.socket_time_out_error));
            } else if (i == 511) {
                showToast(getString(R.string.network_error));
            }
        } else if (aPIError != null) {
            showToast(aPIError.getSeken_errors());
        }
    }
}
