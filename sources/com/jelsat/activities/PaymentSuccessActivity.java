package com.jelsat.activities;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.data.propertypayment.PropertyPaymentResponse;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import com.jelsat.customclasses.CustomTextView;
import com.jelsat.events.CancelBookingEvent;
import com.jelsat.utils.GlideApp;
import com.jelsat.utils.Utils;
import com.jelsat.widgets.FancyButton;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.Locale;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class PaymentSuccessActivity extends BaseAppCompactActivity {
    @BindView(2131361915)
    TextView bookingId;
    @BindView(2131361916)
    TextView bookingIdLabel;
    @BindView(2131361964)
    TextView cancelTransactionDate;
    @BindView(2131361968)
    LinearLayout cancellationLayout;
    @BindView(2131362712)
    TextView confirmationMsgTv;
    @BindView(2131362732)
    TextView guestsCountTv;
    @BindView(2131362735)
    TextView infoTextView;
    private boolean isFromGuest;
    @BindView(2131362747)
    FancyButton nightsCountTv;
    @BindView(2131362410)
    TextView paymentCancellationDateLabel;
    @BindView(2131362408)
    ImageView paymentImage;
    @BindView(2131362411)
    TextView paymentSuccessTransactionAmountLabel;
    @BindView(2131362412)
    TextView paymentSuccessTransactionDateLabel;
    @BindView(2131362413)
    TextView paymentSuccessTransactionNumberLabel;
    @BindView(2131362455)
    TextView propertyCheckInDate;
    @BindView(2131362456)
    TextView propertyCheckOutDate;
    @BindView(2131362468)
    CircleImageView propertyImage;
    @BindView(2131362477)
    CustomTextView propertyNameTv;
    @BindView(2131362483)
    TextView propertyTypeTv;
    String propertyaddress = "";
    String propertytype = "";
    @BindView(2131362635)
    LinearLayout successTransactionLayout;
    @BindView(2131362777)
    TextView thankyouTv;
    @BindView(2131362689)
    TextView transactionAmount;
    @BindView(2131362690)
    TextView transactionDate;
    @BindView(2131362691)
    TextView transactionNumber;
    @BindView(2131362774)
    TextView tvSubmitStatus;

    public int getActivityLayout() {
        return R.layout.activity_payment_success;
    }

    @OnClick({2131362196})
    public void clickOnGoToBookings() {
        Intent intent = new Intent(this, DashBoardActivity.class);
        intent.setFlags(268468224);
        intent.putExtra(StringConstants.FROM_PAYMENT_SUCCESS, true);
        intent.putExtra(StringConstants.FROM_GUEST, this.isFromGuest);
        startActivity(intent);
        finish();
    }

    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getPaymentSuccessDetails(CancelBookingEvent cancelBookingEvent) {
        if (cancelBookingEvent != null) {
            PropertyPaymentResponse response = cancelBookingEvent.getResponse();
            if (response.getTransactionId() == null) {
                this.isFromGuest = cancelBookingEvent.isFromGuest();
                this.paymentImage.setImageResource(R.drawable.ic_property_cancel);
                this.thankyouTv.setTextColor(applyColor(R.color.normal_text_color));
                this.tvSubmitStatus.setText(getString(R.string.booking_cancel_submit_status));
                this.confirmationMsgTv.setText(getString(R.string.payment_success_conformation_msg_label));
                this.confirmationMsgTv.setTextColor(applyColor(R.color.bookings_rejected));
                this.infoTextView.setText(getString(R.string.payment_success_cancellation_info_msg));
                this.successTransactionLayout.setVisibility(8);
                this.cancellationLayout.setVisibility(0);
                this.paymentCancellationDateLabel.setText(String.format("%s : ", new Object[]{getString(R.string.payment_cancellation_date_label)}));
                this.cancelTransactionDate.setText(Utils.bookingsDateFormat(response.getDate()));
            } else {
                this.isFromGuest = cancelBookingEvent.isFromGuest();
                this.paymentImage.setImageResource(R.drawable.ic_success_tick);
                this.thankyouTv.setTextColor(applyColor(R.color.payment_success_color));
                setBookingRequestStatus(Integer.parseInt(response.getBookingType()));
                this.cancellationLayout.setVisibility(8);
                setTransactionDetails(Integer.parseInt(response.getBookingType()), response.getDate(), response.getTransactionId(), response.getAmount());
            }
            TextView textView = this.bookingIdLabel;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(getString(R.string.booking_id_label));
            stringBuilder.append(" : ");
            textView.setText(stringBuilder.toString());
            this.bookingId.setText(cancelBookingEvent.getResponse().getOrderId());
            loadPropertyImage(response.getPropertyImage());
            this.propertyNameTv.setText(response.getPropertyName());
            this.propertytype = response.getPropertyType();
            this.propertyaddress = response.getPropertyAddress();
            setBookingTypeImage(Integer.parseInt(response.getBookingType()));
            this.propertyCheckInDate.setText(Utils.bookingsDateFormat(response.getCheckInDate()));
            this.propertyCheckOutDate.setText(Utils.bookingsDateFormat(response.getCheckOutDate()));
            if (TextUtils.isEmpty(response.getNights())) {
                this.nightsCountTv.setText("1N");
            } else {
                FancyButton fancyButton = this.nightsCountTv;
                stringBuilder = new StringBuilder();
                stringBuilder.append(response.getNights());
                stringBuilder.append("N");
                fancyButton.setText(stringBuilder.toString());
            }
            textView = this.guestsCountTv;
            stringBuilder = new StringBuilder("%s ");
            stringBuilder.append(getString(R.string.payment_success_guests));
            textView.setText(String.format(stringBuilder.toString(), new Object[]{Integer.valueOf(response.getTotalGuests())}));
            EventBus.getDefault().removeStickyEvent(cancelBookingEvent);
        }
    }

    private void setBookingRequestStatus(int i) {
        switch (i) {
            case 0:
                this.tvSubmitStatus.setText(getString(R.string.payment_success_24hrs_request_status));
                this.confirmationMsgTv.setText(getString(R.string.payment_success_24hrs_submit_status));
                this.confirmationMsgTv.setTextColor(applyColor(R.color.payment_succes_24_request));
                this.infoTextView.setText(getString(R.string.payment_success_24hrs_sent_status));
                break;
            case 1:
                this.tvSubmitStatus.setText(getString(R.string.payment_success_instant_request_status));
                this.confirmationMsgTv.setText(getString(R.string.payment_success_instant_submit_status));
                this.confirmationMsgTv.setTextColor(applyColor(R.color.property_instant_color));
                this.infoTextView.setText(getString(R.string.payment_success_instant_sent_status));
                return;
            default:
                break;
        }
    }

    private void setTransactionDetails(int i, String str, String str2, int i2) {
        switch (i) {
            case 0:
                this.successTransactionLayout.setVisibility(8);
                break;
            case 1:
                this.successTransactionLayout.setVisibility(0);
                i = this.paymentSuccessTransactionDateLabel;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(getString(R.string.payment_success_transaction_date_label));
                stringBuilder.append(" : ");
                i.setText(stringBuilder.toString());
                i = this.paymentSuccessTransactionNumberLabel;
                stringBuilder = new StringBuilder();
                stringBuilder.append(getString(R.string.payment_success_transaction_number_label));
                stringBuilder.append(" : ");
                i.setText(stringBuilder.toString());
                i = this.paymentSuccessTransactionAmountLabel;
                stringBuilder = new StringBuilder();
                stringBuilder.append(getString(R.string.payment_success_transaction_amount_label));
                stringBuilder.append(" : ");
                i.setText(stringBuilder.toString());
                this.transactionDate.setText(Utils.bookingsDateFormat(str));
                this.transactionNumber.setText(str2);
                this.transactionAmount.setText(String.format(Locale.getDefault(), "%s %.2f", new Object[]{getString(R.string.payment_success_price_type), Float.valueOf(((float) i2) / 1120403456)}));
                return;
            default:
                break;
        }
    }

    private void setBookingTypeImage(int i) {
        switch (i) {
            case 0:
                if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar") == 0) {
                    this.propertyTypeTv.setText(this.propertytype);
                    this.propertyNameTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_24hours_booking, 0, 0, 0);
                    break;
                }
                this.propertyTypeTv.setText(this.propertytype);
                this.propertyNameTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_24hours_booking, 0);
                return;
            case 1:
                if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar") != 0) {
                    this.propertyTypeTv.setText(this.propertyaddress);
                    this.propertyNameTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_instant_booking, 0);
                    return;
                }
                this.propertyTypeTv.setText(this.propertyaddress);
                this.propertyNameTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_instant_booking, 0, 0, 0);
                return;
            default:
                break;
        }
    }

    private void loadPropertyImage(String str) {
        GlideApp.with(this).asBitmap().load(str).placeholder(R.drawable.default_logo_small).error(R.drawable.default_logo_small).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(this.propertyImage);
    }

    protected void onStop() {
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, DashBoardActivity.class);
        intent.setFlags(268468224);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
