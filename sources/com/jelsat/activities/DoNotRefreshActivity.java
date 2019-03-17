package com.jelsat.activities;

import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.propertypayment.IPropertyPaymentView;
import com.businesslogic.propertypayment.PropertyPaymentPresenter;
import com.data.propertypayment.PropertyPaymentRequest;
import com.data.propertypayment.PropertyPaymentResponse;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import com.jelsat.events.CancelBookingEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class DoNotRefreshActivity extends BaseAppCompactActivity implements IPropertyPaymentView {
    @BindView(2131361918)
    LinearLayout bookingsLayout;
    @BindView(2131362105)
    LinearLayout doNotRefreshLayout;
    @BindView(2131362253)
    TextView infoTextView;
    private String orderId;
    private PropertyPaymentPresenter propertyPaymentPresenter = new PropertyPaymentPresenter(this, RetrofitClient.getAPIService());

    public int getActivityLayout() {
        return R.layout.activity_dont_refresh_layout;
    }

    @OnClick({2131362196})
    public void clickOnBookings() {
        goToBookingScreen();
    }

    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void bookingProcessing(PropertyPaymentRequest propertyPaymentRequest) {
        if (propertyPaymentRequest != null) {
            this.orderId = propertyPaymentRequest.getOrderId();
            this.propertyPaymentPresenter.doPropertyPayment(getString(R.string.payment_processing), propertyPaymentRequest);
            EventBus.getDefault().removeStickyEvent(propertyPaymentRequest);
        }
    }

    protected void onStop() {
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onSuccess(PropertyPaymentResponse propertyPaymentResponse) {
        EventBus.getDefault().postSticky(new CancelBookingEvent(propertyPaymentResponse, true));
        startActivity(new Intent(this, PaymentSuccessActivity.class));
    }

    public void onError(APIError aPIError, int i) {
        this.infoTextView.setText(String.format("%s \n %s: %s", new Object[]{getString(R.string.due_to_booking_not_confirmed), getString(R.string.booking_id_label), this.orderId}));
        this.doNotRefreshLayout.setVisibility(8);
        this.bookingsLayout.setVisibility(0);
    }

    public void onBackPressed() {
        goToBookingScreen();
        super.onBackPressed();
    }

    private void goToBookingScreen() {
        Intent intent = new Intent(this, DashBoardActivity.class);
        intent.setFlags(268468224);
        intent.putExtra(StringConstants.FROM_PAYMENT_SUCCESS, true);
        intent.putExtra(StringConstants.FROM_GUEST, true);
        startActivity(intent);
        finish();
    }

    public void onDetachedFromWindow() {
        if (this.propertyPaymentPresenter != null) {
            this.propertyPaymentPresenter.unSubscribeDisposables();
        }
        super.onDetachedFromWindow();
    }
}
