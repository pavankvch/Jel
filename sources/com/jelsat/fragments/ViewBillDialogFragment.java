package com.jelsat.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.data.bookings.BookingProperty;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseDialogFragment;
import java.util.Locale;

public class ViewBillDialogFragment extends BaseDialogFragment {
    @BindView(2131361915)
    TextView bookingId;
    private BookingProperty bookingProperty;
    @BindView(2131362099)
    TextView discountAmount;
    @BindView(2131362100)
    View discount_view;
    @BindView(2131362284)
    LinearLayout layout_discount;
    @BindView(2131362362)
    TextView noOfNightsCalTxt;
    @BindView(2131362429)
    TextView pricePerNight;
    @BindView(2131362584)
    TextView serviceFeeAmount;
    @BindView(2131362585)
    TextView serviceFeeTxt;
    @BindView(2131362644)
    TextView taxFeeAmount;
    @BindView(2131362645)
    TextView taxTv;
    @BindView(2131362685)
    TextView totalAmount;
    @BindView(2131362686)
    TextView totalAmountTv;

    public int getDialogFragmentLayoutId() {
        return R.layout.dialog_viewbill;
    }

    @OnClick({2131362246})
    public void clickOnClose() {
        dismiss();
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        bundle = super.onCreateDialog(bundle);
        if (bundle.getWindow() != null) {
            bundle.getWindow().requestFeature(1);
        }
        bundle.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        return bundle;
    }

    @SuppressLint({"DefaultLocale"})
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.bookingId.setText(String.format("%s : %s", new Object[]{getString(R.string.booking_id_label), this.bookingProperty.getOrderId()}));
        view = this.bookingProperty.getTax() + this.bookingProperty.getServiceFee();
        if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar") != null) {
            bundle = this.noOfNightsCalTxt;
            Object[] objArr = new Object[4];
            objArr[0] = Float.valueOf(this.bookingProperty.getUnitPrice());
            objArr[1] = getString(R.string.payment_success_price_type);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.bookingProperty.getNights());
            stringBuilder.append("X");
            objArr[2] = stringBuilder.toString();
            objArr[3] = getString(R.string.per_night_label);
            bundle.setText(String.format("%.2f %s %s %s", objArr));
            this.pricePerNight.setText(String.format("%.2f %s", new Object[]{Float.valueOf(this.bookingProperty.getBasePrice()), getString(R.string.payment_success_price_type)}));
            this.taxFeeAmount.setText(String.format("%.2f %s", new Object[]{Float.valueOf(view), getString(R.string.payment_success_price_type)}));
            this.totalAmount.setText(String.format("%.2f %s", new Object[]{Float.valueOf(this.bookingProperty.getPrice()), getString(R.string.payment_success_price_type)}));
        } else {
            this.noOfNightsCalTxt.setText(String.format("%s %.2f X %s  %s", new Object[]{getString(R.string.payment_success_price_type), Float.valueOf(this.bookingProperty.getUnitPrice()), this.bookingProperty.getNights(), getString(R.string.per_night_label)}));
            this.pricePerNight.setText(String.format("%s %.2f", new Object[]{getString(R.string.payment_success_price_type), Float.valueOf(this.bookingProperty.getBasePrice())}));
            this.taxFeeAmount.setText(String.format("%s %.2f", new Object[]{getString(R.string.payment_success_price_type), Float.valueOf(view)}));
            this.totalAmount.setText(String.format("%s %.2f", new Object[]{getString(R.string.payment_success_price_type), Float.valueOf(this.bookingProperty.getPrice())}));
        }
        if (this.bookingProperty.getCouponAmount() == null) {
            this.layout_discount.setVisibility(8);
            this.discount_view.setVisibility(8);
            return;
        }
        this.layout_discount.setVisibility(0);
        this.discount_view.setVisibility(0);
        this.discountAmount.setText(String.format("%.2f", new Object[]{Float.valueOf(this.bookingProperty.getCouponAmount())}));
    }

    public void setBookingPropertyData(BookingProperty bookingProperty) {
        this.bookingProperty = bookingProperty;
    }
}
