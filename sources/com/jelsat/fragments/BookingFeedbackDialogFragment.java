package com.jelsat.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.guestprofilefeedback.IProfileFeedbackView;
import com.businesslogic.guestprofilefeedback.ProfileFeedbackPresenter;
import com.data.profilefeedback.SubmitGuestFeedbackRequest;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.jelsat.R;
import com.jelsat.adapters.HostBookingsAdapter;
import com.jelsat.baseuiframework.BaseDialogFragment;

public class BookingFeedbackDialogFragment extends BaseDialogFragment implements IProfileFeedbackView {
    String bookingId;
    @BindView(2131362733)
    TextView headingTv;
    @BindView(2131362143)
    EditText messageEt;
    private ProfileFeedbackPresenter profileFeedbackPresenter = new ProfileFeedbackPresenter(this, RetrofitClient.getAPIService());
    @BindView(2131362494)
    RatingBar ratingBar;
    SubmitGuestFeedbackRequest request;
    @BindView(2131362776)
    TextView textTitleTv;
    String userId;

    public int getDialogFragmentLayoutId() {
        return R.layout.bookings_dialogue_feedback;
    }

    public void showSwipeToRefresh(boolean z) {
    }

    @OnClick({2131362246})
    public void clickOnClose() {
        getDialog().dismiss();
    }

    @OnClick({2131361943})
    public void clickOnSubmit() {
        if (this.messageEt.getText().toString().trim().length() <= 0 || this.ratingBar.getRating() <= 0.0f) {
            showToast(getString(R.string.feedback_message));
            return;
        }
        Bundle arguments = getArguments();
        this.userId = arguments.getString("userId");
        this.bookingId = arguments.getString("bookingID");
        this.request = new SubmitGuestFeedbackRequest();
        this.request.setComments(this.messageEt.getText().toString().trim());
        SubmitGuestFeedbackRequest submitGuestFeedbackRequest = this.request;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.ratingBar.getRating());
        submitGuestFeedbackRequest.setRating(stringBuilder.toString());
        this.request.setUserId(this.userId);
        this.request.setBookingid(this.bookingId);
        this.profileFeedbackPresenter.submitFeedback(getString(R.string.please_wait), this.request);
    }

    public BookingFeedbackDialogFragment newInstance(String str, String str2) {
        BookingFeedbackDialogFragment bookingFeedbackDialogFragment = new BookingFeedbackDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("userId", str);
        bundle.putString("bookingID", str2);
        bookingFeedbackDialogFragment.setArguments(bundle);
        return bookingFeedbackDialogFragment;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        bundle = super.onCreateDialog(bundle);
        bundle.getWindow().requestFeature(1);
        return bundle;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.headingTv.setText(getResources().getString(R.string.host_booking_dialogue_heading));
        this.textTitleTv.setText(getResources().getString(R.string.host_booking_dialogue_write_about_guest));
        this.textTitleTv.setGravity(17);
        this.messageEt.setHint(getResources().getString(R.string.host_booking_dialogue_message_hint));
        return layoutInflater;
    }

    public void onFeedbackSubmitSuccess() {
        getDialog().dismiss();
        showToast(getString(R.string.feedback_submitted));
        HostBookingsAdapter.feedbackSubmitted = false;
    }

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void showToast(String str) {
        Toast.makeText(getActivity(), str, 0).show();
    }
}
