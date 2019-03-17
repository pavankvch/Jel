package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.customclasses.CustomTextView;
import com.jelsat.widgets.FancyButton;

public class BookingPaymentActivity_ViewBinding implements Unbinder {
    private BookingPaymentActivity target;
    private View view2131361882;
    private View view2131361892;
    private View view2131361959;
    private View view2131362405;
    private View view2131362517;

    @UiThread
    public BookingPaymentActivity_ViewBinding(BookingPaymentActivity bookingPaymentActivity) {
        this(bookingPaymentActivity, bookingPaymentActivity.getWindow().getDecorView());
    }

    @UiThread
    public BookingPaymentActivity_ViewBinding(final BookingPaymentActivity bookingPaymentActivity, View view) {
        this.target = bookingPaymentActivity;
        bookingPaymentActivity.cardsRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.cards_recyclerview, "field 'cardsRecyclerView'", RecyclerView.class);
        bookingPaymentActivity.payableAmountTv = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.payable_amount_TV, "field 'payableAmountTv'", CustomTextView.class);
        bookingPaymentActivity.couponEditText = (EditText) Utils.findRequiredViewAsType(view, R.id.coupon_editText, "field 'couponEditText'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.apply_tv, "field 'applyTV' and method 'clickOnApplyCoupon'");
        bookingPaymentActivity.applyTV = (TextView) Utils.castView(findRequiredView, R.id.apply_tv, "field 'applyTV'", TextView.class);
        this.view2131361882 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                bookingPaymentActivity.clickOnApplyCoupon();
            }
        });
        bookingPaymentActivity.walletLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.wallet_layout, "field 'walletLayout'", LinearLayout.class);
        bookingPaymentActivity.walletCheckBox = (CheckBox) Utils.findRequiredViewAsType(view, R.id.wallet_checkbox, "field 'walletCheckBox'", CheckBox.class);
        bookingPaymentActivity.deductWalletAmountTv = (TextView) Utils.findRequiredViewAsType(view, R.id.deduct_wallet_amount, "field 'deductWalletAmountTv'", TextView.class);
        bookingPaymentActivity.appliedCouponLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.applied_coupon_layout, "field 'appliedCouponLayout'", LinearLayout.class);
        bookingPaymentActivity.appliedCouponAmountTv = (TextView) Utils.findRequiredViewAsType(view, R.id.applied_coupon_amount_tv, "field 'appliedCouponAmountTv'", TextView.class);
        bookingPaymentActivity.remainingAmountLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.remaining_amount_layout, "field 'remainingAmountLayout'", LinearLayout.class);
        bookingPaymentActivity.remainingAmountPriceTv = (TextView) Utils.findRequiredViewAsType(view, R.id.remaining_amount_price_tv, "field 'remainingAmountPriceTv'", TextView.class);
        findRequiredView = Utils.findRequiredView(view, R.id.pay_now_button, "field 'payNowButton' and method 'clickOnPayNow'");
        bookingPaymentActivity.payNowButton = (FancyButton) Utils.castView(findRequiredView, R.id.pay_now_button, "field 'payNowButton'", FancyButton.class);
        this.view2131362405 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                bookingPaymentActivity.clickOnPayNow();
            }
        });
        bookingPaymentActivity.nestedScrollView = (NestedScrollView) Utils.findRequiredViewAsType(view, R.id.nestedScrollView, "field 'nestedScrollView'", NestedScrollView.class);
        bookingPaymentActivity.noResultTV = (TextView) Utils.findRequiredViewAsType(view, R.id.no_result_textView, "field 'noResultTV'", TextView.class);
        bookingPaymentActivity.noResultLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.no_result_layout, "field 'noResultLayout'", LinearLayout.class);
        bookingPaymentActivity.noResultImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.no_result_image, "field 'noResultImage'", ImageView.class);
        findRequiredView = Utils.findRequiredView(view, R.id.retry_button, "field 'retryButton' and method 'clickOnRetryButton'");
        bookingPaymentActivity.retryButton = (FancyButton) Utils.castView(findRequiredView, R.id.retry_button, "field 'retryButton'", FancyButton.class);
        this.view2131362517 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                bookingPaymentActivity.clickOnRetryButton();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.back_arrow_IV, "method 'clickOnBack'");
        this.view2131361892 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                bookingPaymentActivity.clickOnBack();
            }
        });
        view = Utils.findRequiredView(view, R.id.cancel_TV, "method 'clickOnCancel'");
        this.view2131361959 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                bookingPaymentActivity.clickOnCancel();
            }
        });
    }

    @CallSuper
    public void unbind() {
        BookingPaymentActivity bookingPaymentActivity = this.target;
        if (bookingPaymentActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        bookingPaymentActivity.cardsRecyclerView = null;
        bookingPaymentActivity.payableAmountTv = null;
        bookingPaymentActivity.couponEditText = null;
        bookingPaymentActivity.applyTV = null;
        bookingPaymentActivity.walletLayout = null;
        bookingPaymentActivity.walletCheckBox = null;
        bookingPaymentActivity.deductWalletAmountTv = null;
        bookingPaymentActivity.appliedCouponLayout = null;
        bookingPaymentActivity.appliedCouponAmountTv = null;
        bookingPaymentActivity.remainingAmountLayout = null;
        bookingPaymentActivity.remainingAmountPriceTv = null;
        bookingPaymentActivity.payNowButton = null;
        bookingPaymentActivity.nestedScrollView = null;
        bookingPaymentActivity.noResultTV = null;
        bookingPaymentActivity.noResultLayout = null;
        bookingPaymentActivity.noResultImage = null;
        bookingPaymentActivity.retryButton = null;
        this.view2131361882.setOnClickListener(null);
        this.view2131361882 = null;
        this.view2131362405.setOnClickListener(null);
        this.view2131362405 = null;
        this.view2131362517.setOnClickListener(null);
        this.view2131362517 = null;
        this.view2131361892.setOnClickListener(null);
        this.view2131361892 = null;
        this.view2131361959.setOnClickListener(null);
        this.view2131361959 = null;
    }
}
