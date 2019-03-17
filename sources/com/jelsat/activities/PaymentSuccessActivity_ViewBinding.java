package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.customclasses.CustomTextView;
import com.jelsat.widgets.FancyButton;
import de.hdodenhof.circleimageview.CircleImageView;

public class PaymentSuccessActivity_ViewBinding implements Unbinder {
    private PaymentSuccessActivity target;
    private View view2131362196;

    @UiThread
    public PaymentSuccessActivity_ViewBinding(PaymentSuccessActivity paymentSuccessActivity) {
        this(paymentSuccessActivity, paymentSuccessActivity.getWindow().getDecorView());
    }

    @UiThread
    public PaymentSuccessActivity_ViewBinding(final PaymentSuccessActivity paymentSuccessActivity, View view) {
        this.target = paymentSuccessActivity;
        paymentSuccessActivity.paymentImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.payment_action_image, "field 'paymentImage'", ImageView.class);
        paymentSuccessActivity.thankyouTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_thankyou, "field 'thankyouTv'", TextView.class);
        paymentSuccessActivity.tvSubmitStatus = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_submitstatus, "field 'tvSubmitStatus'", TextView.class);
        paymentSuccessActivity.propertyImage = (CircleImageView) Utils.findRequiredViewAsType(view, R.id.property_image, "field 'propertyImage'", CircleImageView.class);
        paymentSuccessActivity.propertyNameTv = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.property_name_TV, "field 'propertyNameTv'", CustomTextView.class);
        paymentSuccessActivity.propertyTypeTv = (TextView) Utils.findRequiredViewAsType(view, R.id.property_type_Tv, "field 'propertyTypeTv'", TextView.class);
        paymentSuccessActivity.propertyCheckInDate = (TextView) Utils.findRequiredViewAsType(view, R.id.property_check_in_date, "field 'propertyCheckInDate'", TextView.class);
        paymentSuccessActivity.propertyCheckOutDate = (TextView) Utils.findRequiredViewAsType(view, R.id.property_check_out_date, "field 'propertyCheckOutDate'", TextView.class);
        paymentSuccessActivity.nightsCountTv = (FancyButton) Utils.findRequiredViewAsType(view, R.id.tv_nights_numbers, "field 'nightsCountTv'", FancyButton.class);
        paymentSuccessActivity.guestsCountTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_guests_count, "field 'guestsCountTv'", TextView.class);
        paymentSuccessActivity.confirmationMsgTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_confirmation, "field 'confirmationMsgTv'", TextView.class);
        paymentSuccessActivity.successTransactionLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.success_transaction_layout, "field 'successTransactionLayout'", LinearLayout.class);
        paymentSuccessActivity.paymentSuccessTransactionDateLabel = (TextView) Utils.findRequiredViewAsType(view, R.id.payment_success_transaction_date_label, "field 'paymentSuccessTransactionDateLabel'", TextView.class);
        paymentSuccessActivity.paymentSuccessTransactionNumberLabel = (TextView) Utils.findRequiredViewAsType(view, R.id.payment_success_transaction_number_label, "field 'paymentSuccessTransactionNumberLabel'", TextView.class);
        paymentSuccessActivity.paymentSuccessTransactionAmountLabel = (TextView) Utils.findRequiredViewAsType(view, R.id.payment_success_transaction_amount_label, "field 'paymentSuccessTransactionAmountLabel'", TextView.class);
        paymentSuccessActivity.transactionDate = (TextView) Utils.findRequiredViewAsType(view, R.id.transaction_date, "field 'transactionDate'", TextView.class);
        paymentSuccessActivity.transactionNumber = (TextView) Utils.findRequiredViewAsType(view, R.id.transaction_number, "field 'transactionNumber'", TextView.class);
        paymentSuccessActivity.transactionAmount = (TextView) Utils.findRequiredViewAsType(view, R.id.transaction_amount, "field 'transactionAmount'", TextView.class);
        paymentSuccessActivity.cancellationLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.cancellation_layout, "field 'cancellationLayout'", LinearLayout.class);
        paymentSuccessActivity.paymentCancellationDateLabel = (TextView) Utils.findRequiredViewAsType(view, R.id.payment_cancellation_date_label, "field 'paymentCancellationDateLabel'", TextView.class);
        paymentSuccessActivity.cancelTransactionDate = (TextView) Utils.findRequiredViewAsType(view, R.id.cancel_transaction_date, "field 'cancelTransactionDate'", TextView.class);
        paymentSuccessActivity.infoTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_info, "field 'infoTextView'", TextView.class);
        paymentSuccessActivity.bookingIdLabel = (TextView) Utils.findRequiredViewAsType(view, R.id.booking_id_label, "field 'bookingIdLabel'", TextView.class);
        paymentSuccessActivity.bookingId = (TextView) Utils.findRequiredViewAsType(view, R.id.booking_id, "field 'bookingId'", TextView.class);
        view = Utils.findRequiredView(view, R.id.goto_bookings_button, "method 'clickOnGoToBookings'");
        this.view2131362196 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                paymentSuccessActivity.clickOnGoToBookings();
            }
        });
    }

    @CallSuper
    public void unbind() {
        PaymentSuccessActivity paymentSuccessActivity = this.target;
        if (paymentSuccessActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        paymentSuccessActivity.paymentImage = null;
        paymentSuccessActivity.thankyouTv = null;
        paymentSuccessActivity.tvSubmitStatus = null;
        paymentSuccessActivity.propertyImage = null;
        paymentSuccessActivity.propertyNameTv = null;
        paymentSuccessActivity.propertyTypeTv = null;
        paymentSuccessActivity.propertyCheckInDate = null;
        paymentSuccessActivity.propertyCheckOutDate = null;
        paymentSuccessActivity.nightsCountTv = null;
        paymentSuccessActivity.guestsCountTv = null;
        paymentSuccessActivity.confirmationMsgTv = null;
        paymentSuccessActivity.successTransactionLayout = null;
        paymentSuccessActivity.paymentSuccessTransactionDateLabel = null;
        paymentSuccessActivity.paymentSuccessTransactionNumberLabel = null;
        paymentSuccessActivity.paymentSuccessTransactionAmountLabel = null;
        paymentSuccessActivity.transactionDate = null;
        paymentSuccessActivity.transactionNumber = null;
        paymentSuccessActivity.transactionAmount = null;
        paymentSuccessActivity.cancellationLayout = null;
        paymentSuccessActivity.paymentCancellationDateLabel = null;
        paymentSuccessActivity.cancelTransactionDate = null;
        paymentSuccessActivity.infoTextView = null;
        paymentSuccessActivity.bookingIdLabel = null;
        paymentSuccessActivity.bookingId = null;
        this.view2131362196.setOnClickListener(null);
        this.view2131362196 = null;
    }
}
