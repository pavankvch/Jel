package com.jelsat.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.propertyfeedback.FeedbackPresenter;
import com.businesslogic.propertyfeedback.IFeedbackView;
import com.data.propertyfeedback.SubmitPropertyFeedbackRequest;
import com.data.propertyreviews.PropertyReviewsResponse;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIConstants;
import com.data.utils.APIError;
import com.jelsat.R;
import com.jelsat.adapters.GuestBookingsAdapter;
import com.jelsat.baseuiframework.BaseDialogFragment;
import com.jelsat.constants.StringConstants;

public class FeedbackDialogFragment extends BaseDialogFragment implements IFeedbackView {
    String bookingID;
    private FeedbackPresenter feedbackPresenter = new FeedbackPresenter(this, RetrofitClient.getAPIService());
    @BindView(2131362728)
    TextView feedbackTimeTv;
    public GuestBookingsAdapter guestBookingsAdapter = null;
    @BindView(2131362733)
    TextView headingTv;
    boolean isFeedback;
    @BindView(2131362143)
    EditText messageEt;
    int position;
    String propertyId;
    @BindView(2131362494)
    RatingBar ratingBar;
    @BindView(2131362251)
    ImageView starImgView;
    @BindView(2131362776)
    TextView textTitleLabel;

    public int getDialogFragmentLayoutId() {
        return R.layout.bookings_dialogue_feedback;
    }

    public void onReviewsGet(PropertyReviewsResponse propertyReviewsResponse) {
    }

    public void showSwipeToRefresh(boolean z) {
    }

    @OnClick({2131362246})
    public void clickOnClose() {
        getDialog().dismiss();
    }

    public FeedbackDialogFragment newInstance(String str, boolean z, String str2, int i, GuestBookingsAdapter guestBookingsAdapter) {
        FeedbackDialogFragment feedbackDialogFragment = new FeedbackDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(StringConstants.PROPERTY_ID, str);
        bundle.putBoolean(APIConstants.SUBMIT_FEEDBACK, z);
        bundle.putString(StringConstants.BOOKING_ID, str2);
        feedbackDialogFragment.setArguments(bundle);
        z = new StringBuilder("guestBookingAdapter value : ");
        z.append(guestBookingsAdapter);
        z.append(" pos : ");
        z.append(i);
        Log.e("FeedbackDialaugeFrag", z.toString());
        this.guestBookingsAdapter = guestBookingsAdapter;
        this.position = i;
        return feedbackDialogFragment;
    }

    @OnClick({2131361943})
    public void clickOnSubmit() {
        if (this.messageEt.getText().toString().trim().length() <= 0 || this.ratingBar.getRating() <= 0.0f) {
            showToast(getString(R.string.feedback_message));
            return;
        }
        SubmitPropertyFeedbackRequest submitPropertyFeedbackRequest = new SubmitPropertyFeedbackRequest();
        submitPropertyFeedbackRequest.setComments(this.messageEt.getText().toString().trim());
        submitPropertyFeedbackRequest.setPropertyId(this.propertyId);
        submitPropertyFeedbackRequest.setBookingID(this.bookingID);
        this.guestBookingsAdapter.refreshListItem(this.position, false);
        if (this.isFeedback) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.ratingBar.getRating());
            submitPropertyFeedbackRequest.setRating(stringBuilder.toString());
            this.feedbackPresenter.submitFeedback(getString(R.string.please_wait), submitPropertyFeedbackRequest);
            return;
        }
        this.feedbackPresenter.submitReport(getString(R.string.please_wait), submitPropertyFeedbackRequest);
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
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.messageEt.setImeOptions(6);
        this.messageEt.setRawInputType(1);
        viewGroup = getArguments();
        this.propertyId = viewGroup.getString(StringConstants.PROPERTY_ID, "");
        this.bookingID = viewGroup.getString(StringConstants.BOOKING_ID, "");
        this.isFeedback = viewGroup.getBoolean(APIConstants.SUBMIT_FEEDBACK, false);
        if (this.isFeedback == null) {
            this.starImgView.setVisibility(8);
            this.headingTv.setVisibility(8);
            this.ratingBar.setVisibility(8);
            this.ratingBar.setRating(1.0f);
            this.feedbackTimeTv.setVisibility(8);
            this.textTitleLabel.setText(getString(R.string.report_this_product));
            this.textTitleLabel.setGravity(3);
            this.textTitleLabel.setTextSize(20.0f);
            this.messageEt.setHint(getString(R.string.enter_message_here));
        }
        return layoutInflater;
    }

    public void onFeedbackSubmitSuccess(boolean z) {
        getDialog().dismiss();
        if (z) {
            Log.e("FeedbackDialogFragment", "OnFeedbackSubmitSuccess() If Condition executed");
            showToast(getString(true));
            Log.e("FeedbackDialougeFrag", "guestbookingadapter is not empty");
            return;
        }
        showToast(getString(true));
    }

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void onDetach() {
        if (this.feedbackPresenter != null) {
            this.feedbackPresenter.unSubscribeDisposables();
        }
        super.onDetach();
    }
}
