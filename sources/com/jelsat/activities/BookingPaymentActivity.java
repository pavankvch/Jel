package com.jelsat.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.applycoupon.ApplyCouponPresenter;
import com.businesslogic.applycoupon.IApplyCouponView;
import com.businesslogic.framework.ErrorCodes;
import com.businesslogic.propertybook.IPropertyBookView;
import com.businesslogic.propertybook.PropertyBookPresenter;
import com.businesslogic.propertypayment.IPropertyPaymentView;
import com.businesslogic.propertypayment.PropertyPaymentPresenter;
import com.businesslogic.sdktoken.ISDKTokenView;
import com.businesslogic.sdktoken.SDKTokenPresenter;
import com.data.applycoupon.ApplyCouponRequest;
import com.data.applycoupon.ApplyCouponResponse;
import com.data.payfort.PayFortData;
import com.data.propertybook.Card;
import com.data.propertybook.PropertyBookResponse;
import com.data.propertypayment.PropertyPaymentRequest;
import com.data.propertypayment.PropertyPaymentResponse;
import com.data.retrofit.RetrofitClient;
import com.data.sdktoken.SDKTokenRequest;
import com.data.sdktoken.SDKTokenResponse;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;
import com.data.viewbill.PropertyViewBillRequest;
import com.facebook.appevents.AppEventsConstants;
import com.jelsat.R;
import com.jelsat.adapters.CardsAdapter;
import com.jelsat.adapters.CardsAdapter$OnListItemClickListener;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import com.jelsat.customclasses.CustomTextView;
import com.jelsat.events.CancelBookingEvent;
import com.jelsat.events.PropertyBookEvent;
import com.jelsat.payfort.IPaymentRequestCallBackView;
import com.jelsat.payfort.PayFortPayment;
import com.jelsat.utils.AlertDialogUtil;
import com.jelsat.utils.DialogUtil;
import com.jelsat.widgets.FancyButton;
import com.payfort.fort.android.sdk.base.FortSdk;
import com.payfort.fort.android.sdk.base.callbacks.FortCallBackManager;
import com.payfort.fort.android.sdk.base.callbacks.FortCallBackManager.Factory;
import com.payfort.sdk.android.dependancies.commons.Constants.CREDIT_CARDS_TYPES;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class BookingPaymentActivity extends BaseAppCompactActivity implements IApplyCouponView, IPropertyBookView, IPropertyPaymentView, ISDKTokenView, CardsAdapter$OnListItemClickListener, IPaymentRequestCallBackView {
    private AlertDialogUtil alertDialogUtil;
    @BindView(2131361879)
    TextView appliedCouponAmountTv;
    @BindView(2131361880)
    LinearLayout appliedCouponLayout;
    private ApplyCouponPresenter applyCouponPresenter = new ApplyCouponPresenter(this, RetrofitClient.getAPIService());
    @BindView(2131361882)
    TextView applyTV;
    private String bookingType;
    private Card card;
    private List<Card> cards;
    private CardsAdapter cardsAdapter;
    @BindView(2131361975)
    RecyclerView cardsRecyclerView;
    private int couponAmount = 0;
    @BindView(2131362042)
    EditText couponEditText;
    @BindView(2131362081)
    TextView deductWalletAmountTv;
    private DialogUtil dialogUtil;
    public FortCallBackManager fortCallback = null;
    private boolean isCouponApplied = false;
    private KeyListener keyListener;
    private String madaEnable;
    private int modifiedAmount;
    private int myWalletAmount;
    @BindView(2131362350)
    NestedScrollView nestedScrollView;
    @BindView(2131362369)
    ImageView noResultImage;
    @BindView(2131362370)
    LinearLayout noResultLayout;
    @BindView(2131362371)
    TextView noResultTV;
    private String orderId;
    @BindView(2131362405)
    FancyButton payNowButton;
    @BindView(2131362406)
    CustomTextView payableAmountTv;
    private int previousPosition = 0;
    private PropertyBookPresenter propertyBookPresenter = new PropertyBookPresenter(this, RetrofitClient.getAPIService());
    private PropertyPaymentPresenter propertyPaymentPresenter = new PropertyPaymentPresenter(this, RetrofitClient.getAPIService());
    @BindView(2131362504)
    LinearLayout remainingAmountLayout;
    @BindView(2131362505)
    TextView remainingAmountPriceTv;
    private PropertyViewBillRequest request;
    @BindView(2131362517)
    FancyButton retryButton;
    private SDKTokenPresenter sdkTokenPresenter = new SDKTokenPresenter(this, RetrofitClient.getAPIService());
    private int totalAmount;
    @BindView(2131362834)
    CheckBox walletCheckBox;
    @BindView(2131362835)
    LinearLayout walletLayout;
    TextWatcher watcher = new BookingPaymentActivity$5(this);

    public Activity getActivityInstance() {
        return this;
    }

    public int getActivityLayout() {
        return R.layout.activity_booking_payment;
    }

    @OnClick({2131362517})
    public void clickOnRetryButton() {
        doPropertyBookApiCall();
    }

    @OnClick({2131361892})
    public void clickOnBack() {
        goToPropertyDetailScreen();
    }

    @OnClick({2131362405})
    public void clickOnPayNow() {
        if (this.walletCheckBox.isChecked()) {
            if (this.modifiedAmount <= this.myWalletAmount) {
                if (isNetworkConnected()) {
                    PropertyPaymentRequest propertyPaymentRequest = new PropertyPaymentRequest();
                    propertyPaymentRequest.setOrderId(this.orderId);
                    propertyPaymentRequest.setBookingType(this.bookingType);
                    propertyPaymentRequest.setSavedCard(false);
                    propertyPaymentRequest.setCard(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                    propertyPaymentRequest.setWallet(String.valueOf(this.modifiedAmount));
                    propertyPaymentRequest.setPayload(new PayFortData());
                    this.propertyPaymentPresenter.doPropertyPayment(getString(R.string.payment_processing), propertyPaymentRequest);
                    return;
                }
                this.dialogUtil.showOkDialog(getResources().getString(R.string.error_message_network));
            } else if (this.card == null || !this.card.getChecked()) {
                showToast(getString(R.string.plz_check_one_card_option));
            } else {
                doSDKTokenApiCall();
            }
        } else if (this.card == null || !this.card.getChecked()) {
            showToast(getString(R.string.plz_check_one_card_option));
        } else {
            doSDKTokenApiCall();
        }
    }

    @OnClick({2131361959})
    public void clickOnCancel() {
        if (isNetworkConnected()) {
            goToPropertyDetailScreen();
        } else {
            this.dialogUtil.showOkDialog(getResources().getString(R.string.error_message_network));
        }
    }

    @OnClick({2131361882})
    public void clickOnApplyCoupon() {
        if (!this.applyTV.getText().toString().equalsIgnoreCase(getString(R.string.apply_coupoun))) {
            doRemoveCouponApiCall();
        } else if (validateCoupon()) {
            hideKeyboard();
            doApplyCouponApiCall();
        }
    }

    private void clickOnWallet(boolean z) {
        this.walletCheckBox.setChecked(z);
        if (z) {
            if (this.myWalletAmount >= this.modifiedAmount) {
                z = false;
            } else {
                z = this.modifiedAmount - this.myWalletAmount;
            }
            if (this.modifiedAmount <= this.myWalletAmount) {
                this.cardsAdapter.setData(getCardList(this.cards, false));
            }
            if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar")) {
                this.remainingAmountPriceTv.setText(String.format(Locale.getDefault(), "%.2f %s", new Object[]{Float.valueOf(((float) z) / true), getString(R.string.saudi_currency)}));
                return;
            }
            TextView textView = this.remainingAmountPriceTv;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(getString(R.string.saudi_currency));
            stringBuilder.append(" %.2f");
            textView.setText(String.format(stringBuilder.toString(), new Object[]{Float.valueOf(((float) z) / true)}));
            return;
        }
        this.cardsAdapter.setData(getCardList(this.cards, true));
        z = this.remainingAmountPriceTv;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(getString(R.string.saudi_currency));
        stringBuilder2.append(" %.2f");
        z.setText(String.format(stringBuilder2.toString(), new Object[]{Float.valueOf(((float) this.modifiedAmount) / 100.0f)}));
    }

    private void doRemoveCouponApiCall() {
        if (isNetworkConnected()) {
            ApplyCouponRequest applyCouponRequest = new ApplyCouponRequest();
            applyCouponRequest.setType("remove");
            applyCouponRequest.setCoupon(this.couponEditText.getText().toString());
            this.applyCouponPresenter.applyCoupon(applyCouponRequest, getString(R.string.removing_coupon));
            return;
        }
        this.dialogUtil.showOkDialog(getResources().getString(R.string.error_message_network));
    }

    private void doApplyCouponApiCall() {
        if (isNetworkConnected()) {
            ApplyCouponRequest applyCouponRequest = new ApplyCouponRequest();
            applyCouponRequest.setCoupon(this.couponEditText.getText().toString());
            applyCouponRequest.setType("add");
            this.applyCouponPresenter.applyCoupon(applyCouponRequest, getString(R.string.applying_coupoun));
            return;
        }
        this.dialogUtil.showOkDialog(getResources().getString(R.string.error_message_network));
    }

    private void doSDKTokenApiCall() {
        if (isNetworkConnected()) {
            SDKTokenRequest sDKTokenRequest = new SDKTokenRequest();
            sDKTokenRequest.setDeviceId(FortSdk.getDeviceId(getApplicationContext()));
            this.sdkTokenPresenter.getSdkToken(getString(R.string.please_wait), sDKTokenRequest);
            return;
        }
        this.dialogUtil.showOkDialog(getResources().getString(R.string.error_message_network));
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (VERSION.SDK_INT >= 21) {
            bundle = getWindow();
            bundle.addFlags(Integer.MIN_VALUE);
            bundle.setStatusBarColor(applyColor(R.color.dialog_save));
        }
        this.alertDialogUtil = new AlertDialogUtil(this);
        this.dialogUtil = new DialogUtil(this);
        this.keyListener = this.couponEditText.getKeyListener();
        this.couponEditText.addTextChangedListener(this.watcher);
        this.cardsAdapter = new CardsAdapter(this, null, this);
        this.cardsRecyclerView.setLayoutManager(new BookingPaymentActivity$1(this, this));
        Adapter bookingPaymentActivity$3 = new BookingPaymentActivity$3(this, new BookingPaymentActivity$2(this), this.cardsAdapter);
        this.walletCheckBox.setOnCheckedChangeListener(new BookingPaymentActivity$4(this));
        this.cardsRecyclerView.setAdapter(bookingPaymentActivity$3);
        initilizePayFortSDK();
    }

    private void initilizePayFortSDK() {
        this.fortCallback = Factory.create();
    }

    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getSelectedDates(PropertyBookEvent propertyBookEvent) {
        if (propertyBookEvent != null) {
            this.request = new PropertyViewBillRequest();
            this.request.setPropertyId(propertyBookEvent.getPropertyId());
            if (propertyBookEvent.getCheckInDate() != null) {
                this.request.setStartDate(convertDateToString("yyyy-MM-dd", propertyBookEvent.getCheckInDate()));
            }
            if (propertyBookEvent.getCheckOutDate() != null) {
                this.request.setEndDate(convertDateToString("yyyy-MM-dd", propertyBookEvent.getCheckOutDate()));
            }
            if (propertyBookEvent.getSelectedGuestCount() != null) {
                this.request.setGuests(propertyBookEvent.getSelectedGuestCount());
            }
            doPropertyBookApiCall();
            EventBus.getDefault().removeStickyEvent(propertyBookEvent);
        }
    }

    private void doPropertyBookApiCall() {
        if (isNetworkConnected()) {
            this.payNowButton.setVisibility(0);
            this.noResultLayout.setVisibility(8);
            this.nestedScrollView.setVisibility(0);
            if (this.propertyBookPresenter != null) {
                this.propertyBookPresenter.bookProperty(getString(R.string.getting_result), this.request);
                return;
            }
        }
        this.payNowButton.setVisibility(8);
        this.nestedScrollView.setVisibility(8);
        this.noResultLayout.setVisibility(0);
        this.noResultImage.setImageResource(R.drawable.ic_nointernet);
        this.noResultTV.setText(getString(R.string.error_message_network));
        showToast(getString(R.string.internet_connection_label));
    }

    private String convertDateToString(String str, Date date) {
        return new SimpleDateFormat(str, Locale.ENGLISH).format(date);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (PayFortPayment.REQUEST_CODE == i) {
            this.fortCallback.onActivityResult(i, i2, intent);
        }
    }

    public void onSuccess(PropertyBookResponse propertyBookResponse) {
        if (propertyBookResponse != null) {
            Object obj;
            TextView textView;
            StringBuilder stringBuilder;
            this.orderId = propertyBookResponse.getOrderId();
            this.bookingType = propertyBookResponse.getBookingType();
            this.totalAmount = propertyBookResponse.getAmount();
            this.modifiedAmount = propertyBookResponse.getAmount();
            this.madaEnable = propertyBookResponse.getMadaEnabled();
            if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar")) {
                this.payableAmountTv.setText(String.format(Locale.getDefault(), "%.2f %s", new Object[]{Float.valueOf(((float) this.totalAmount) / 100.0f), getString(R.string.saudi_currency)}));
            } else {
                CustomTextView customTextView = this.payableAmountTv;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(getString(R.string.saudi_currency));
                stringBuilder2.append(" %.2f");
                customTextView.setText(String.format(stringBuilder2.toString(), new Object[]{Float.valueOf(((float) this.totalAmount) / 100.0f)}));
            }
            this.myWalletAmount = propertyBookResponse.getWallet().getMainBalance() + propertyBookResponse.getWallet().getReferral();
            if (propertyBookResponse.getWallet().getMainBalance() == 0) {
                if (propertyBookResponse.getWallet().getReferral() == 0) {
                    obj = null;
                    if (obj == null) {
                        this.walletCheckBox.setClickable(true);
                        this.walletCheckBox.setEnabled(true);
                    } else {
                        this.walletCheckBox.setClickable(false);
                        this.walletCheckBox.setEnabled(false);
                    }
                    if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar")) {
                        textView = this.deductWalletAmountTv;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(getString(R.string.saudi_currency));
                        stringBuilder.append(" %.2f");
                        textView.setText(String.format(stringBuilder.toString(), new Object[]{Float.valueOf(((float) this.myWalletAmount) / 100.0f)}));
                        textView = this.remainingAmountPriceTv;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(getString(R.string.saudi_currency));
                        stringBuilder.append(" %.2f");
                        textView.setText(String.format(stringBuilder.toString(), new Object[]{Float.valueOf(((float) this.modifiedAmount) / 100.0f)}));
                    } else {
                        this.deductWalletAmountTv.setText(String.format(Locale.getDefault(), "%.2f %s", new Object[]{Float.valueOf(((float) this.myWalletAmount) / 100.0f), getString(R.string.saudi_currency)}));
                        this.remainingAmountPriceTv.setText(String.format(Locale.getDefault(), "%.2f %s", new Object[]{Float.valueOf(((float) this.modifiedAmount) / 100.0f), getString(R.string.saudi_currency)}));
                    }
                    this.cards = propertyBookResponse.getCards();
                    this.cardsAdapter.setData(getCardList(propertyBookResponse.getCards(), true));
                }
            }
            obj = 1;
            if (obj == null) {
                this.walletCheckBox.setClickable(false);
                this.walletCheckBox.setEnabled(false);
            } else {
                this.walletCheckBox.setClickable(true);
                this.walletCheckBox.setEnabled(true);
            }
            if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar")) {
                textView = this.deductWalletAmountTv;
                stringBuilder = new StringBuilder();
                stringBuilder.append(getString(R.string.saudi_currency));
                stringBuilder.append(" %.2f");
                textView.setText(String.format(stringBuilder.toString(), new Object[]{Float.valueOf(((float) this.myWalletAmount) / 100.0f)}));
                textView = this.remainingAmountPriceTv;
                stringBuilder = new StringBuilder();
                stringBuilder.append(getString(R.string.saudi_currency));
                stringBuilder.append(" %.2f");
                textView.setText(String.format(stringBuilder.toString(), new Object[]{Float.valueOf(((float) this.modifiedAmount) / 100.0f)}));
            } else {
                this.deductWalletAmountTv.setText(String.format(Locale.getDefault(), "%.2f %s", new Object[]{Float.valueOf(((float) this.myWalletAmount) / 100.0f), getString(R.string.saudi_currency)}));
                this.remainingAmountPriceTv.setText(String.format(Locale.getDefault(), "%.2f %s", new Object[]{Float.valueOf(((float) this.modifiedAmount) / 100.0f), getString(R.string.saudi_currency)}));
            }
            this.cards = propertyBookResponse.getCards();
            this.cardsAdapter.setData(getCardList(propertyBookResponse.getCards(), true));
        }
    }

    public void onPropertyBookError(APIError aPIError, int i) {
        showErrorMessage(aPIError, i);
        goToPropertyDetailScreen();
    }

    private List<Card> getCardList(List<Card> list, boolean z) {
        List<Card> arrayList = new ArrayList();
        for (Card card : list) {
            card.setSavedCard(true);
            if (card.getCardType().equalsIgnoreCase(CREDIT_CARDS_TYPES.VISA)) {
                card.setClickable(z);
                card.setCardDrawable(R.drawable.ic_visa);
            } else {
                if (!card.getCardNo().startsWith("34")) {
                    if (!card.getCardNo().startsWith("37")) {
                        if (card.getCardType().equalsIgnoreCase(CREDIT_CARDS_TYPES.MASTERCARD)) {
                            card.setClickable(z);
                            card.setCardDrawable(R.drawable.ic_mastercard);
                        } else if (card.getCardType().equalsIgnoreCase(CREDIT_CARDS_TYPES.MADA)) {
                            if (this.bookingType.equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_YES)) {
                                card.setClickable(z);
                            } else {
                                card.setClickable(false);
                            }
                            card.setCardDrawable(R.drawable.ic_mada_card);
                        }
                    }
                }
                card.setClickable(z);
                card.setCardDrawable(R.drawable.ic_american_express);
            }
            arrayList.add(card);
        }
        list = new Card();
        list.setCardNo(getString(R.string.credit_card));
        list.setSavedCard(false);
        list.setCardDrawable(R.drawable.ic_new_creditcard);
        list.setClickable(z);
        arrayList.add(list);
        list = new Card();
        list.setCardNo(getString(R.string.mada_Debit_Card));
        list.setSavedCard(false);
        list.setCardDrawable(R.drawable.ic_mada_card);
        if (this.bookingType.equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_YES)) {
            list.setClickable(z);
        } else {
            list.setClickable(false);
        }
        if (this.madaEnable.equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_YES)) {
            arrayList.add(list);
        }
        return arrayList;
    }

    private boolean validateCoupon() {
        if (!TextUtils.isEmpty(this.couponEditText.getText().toString().trim())) {
            return true;
        }
        this.couponEditText.setError(getString(R.string.invalid_coupon));
        return false;
    }

    public void onSuccess(ApplyCouponResponse applyCouponResponse, ApplyCouponRequest applyCouponRequest) {
        if (applyCouponResponse != null) {
            StringBuilder stringBuilder;
            this.totalAmount = applyCouponResponse.getAmount();
            this.modifiedAmount = applyCouponResponse.getPayableAmount();
            this.couponAmount = applyCouponResponse.getCouponAmount();
            if (applyCouponRequest.getType().equalsIgnoreCase("add")) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(applyCouponRequest.getCoupon());
                stringBuilder.append(" ");
                stringBuilder.append(getString(R.string.coupon_applied_label));
                showToast(stringBuilder.toString());
                this.isCouponApplied = true;
                this.couponEditText.setEnabled(false);
                this.couponEditText.setKeyListener(null);
                this.applyTV.setText(getString(R.string.remove_coupoun));
                this.applyTV.setTextColor(getResources().getColor(R.color.bookings_rejected));
                this.appliedCouponLayout.setVisibility(0);
                if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar") != null) {
                    this.appliedCouponAmountTv.setText(String.format(Locale.getDefault(), "%.2f %s", new Object[]{Float.valueOf(((float) applyCouponResponse.getCouponAmount()) / 1120403456), getString(R.string.saudi_currency)}));
                } else {
                    this.appliedCouponAmountTv.setText(String.format(Locale.getDefault(), "%s %.2f", new Object[]{getString(R.string.saudi_currency), Float.valueOf(((float) applyCouponResponse.getCouponAmount()) / 1120403456)}));
                }
            } else {
                applyCouponResponse = new StringBuilder();
                applyCouponResponse.append(applyCouponRequest.getCoupon());
                applyCouponResponse.append(" ");
                applyCouponResponse.append(getString(R.string.coupon_removed_label));
                showToast(applyCouponResponse.toString());
                this.isCouponApplied = false;
                this.couponEditText.setEnabled(true);
                this.couponEditText.setKeyListener(this.keyListener);
                this.couponEditText.setText("");
                this.applyTV.setText(getString(R.string.apply_coupoun));
                this.applyTV.setTextColor(getResources().getColor(R.color.bookings_propertyname_color));
                this.appliedCouponLayout.setVisibility(8);
            }
            this.walletCheckBox.setChecked(false);
            if (this.modifiedAmount == null) {
                this.cardsAdapter.setData(getCardList(this.cards, false));
            } else {
                this.cardsAdapter.setData(getCardList(this.cards, true));
            }
            if (this.walletCheckBox.isChecked() != null) {
                if (this.myWalletAmount >= this.modifiedAmount) {
                    applyCouponResponse = null;
                } else {
                    applyCouponResponse = this.modifiedAmount - this.myWalletAmount;
                }
                if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar") != null) {
                    this.remainingAmountPriceTv.setText(String.format(Locale.getDefault(), "%.2f %s", new Object[]{Float.valueOf(((float) applyCouponResponse) / 1120403456), getString(R.string.saudi_currency)}));
                    return;
                }
                applyCouponRequest = this.remainingAmountPriceTv;
                stringBuilder = new StringBuilder();
                stringBuilder.append(getString(R.string.saudi_currency));
                stringBuilder.append(" %.2f");
                applyCouponRequest.setText(String.format(stringBuilder.toString(), new Object[]{Float.valueOf(((float) applyCouponResponse) / 1120403456)}));
            } else if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar") != null) {
                this.remainingAmountPriceTv.setText(String.format(Locale.getDefault(), "%.2f %s", new Object[]{Float.valueOf(((float) this.modifiedAmount) / 100.0f), getString(R.string.saudi_currency)}));
            } else {
                applyCouponResponse = this.remainingAmountPriceTv;
                applyCouponRequest = new StringBuilder();
                applyCouponRequest.append(getString(R.string.saudi_currency));
                applyCouponRequest.append(" %.2f");
                applyCouponResponse.setText(String.format(applyCouponRequest.toString(), new Object[]{Float.valueOf(((float) this.modifiedAmount) / 100.0f)}));
            }
        }
    }

    private void goToPropertyDetailScreen() {
        Intent intent = new Intent(this, PropertyDetailActivity.class);
        intent.setFlags(67108864);
        intent.putExtra(StringConstants.FROM_BOOKING_PAYMENT, true);
        startActivity(intent);
    }

    public void onSuccess(SDKTokenResponse sDKTokenResponse) {
        if (sDKTokenResponse != null) {
            Log.d("zzz", sDKTokenResponse.getToken());
            requestForPayfortPayment(sDKTokenResponse.getToken());
        }
    }

    public void onSuccess(PropertyPaymentResponse propertyPaymentResponse) {
        EventBus.getDefault().postSticky(new CancelBookingEvent(propertyPaymentResponse, true));
        startActivity(new Intent(this, PaymentSuccessActivity.class));
    }

    public void onError(APIError aPIError, int i) {
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
        this.alertDialogUtil.showAlertDialog(getString(R.string.due_to_booking_not_confirmed), getString(R.string.ok), new BookingPaymentActivity$6(this));
    }

    private void goToBookingsPage() {
        Intent intent = new Intent(this, DashBoardActivity.class);
        intent.setFlags(268468224);
        intent.putExtra(StringConstants.FROM_PAYMENT_SUCCESS, true);
        intent.putExtra(StringConstants.FROM_GUEST, true);
        startActivity(intent);
        finish();
    }

    private void showErrorMessage(APIError aPIError, int i) {
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

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void clickOnListItem(Card card, int i, boolean z) {
        this.card = card;
        this.cardsAdapter.setItemStatusChanged(this.previousPosition, i, z);
        this.previousPosition = i;
    }

    protected void onStop() {
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onDetachedFromWindow() {
        if (this.propertyBookPresenter != null) {
            this.propertyBookPresenter.unSubscribeDisposables();
        }
        if (this.applyCouponPresenter != null) {
            this.applyCouponPresenter.unSubscribeDisposables();
        }
        if (this.sdkTokenPresenter != null) {
            this.sdkTokenPresenter.unSubscribeDisposables();
        }
        if (this.propertyPaymentPresenter != null) {
            this.propertyPaymentPresenter.unSubscribeDisposables();
        }
        super.onDetachedFromWindow();
    }

    private void requestForPayfortPayment(String str) {
        int i;
        PayFortData payFortData = new PayFortData();
        if (!this.walletCheckBox.isChecked()) {
            i = this.modifiedAmount;
        } else if (this.myWalletAmount >= this.modifiedAmount) {
            i = 0;
        } else {
            i = this.modifiedAmount - this.myWalletAmount;
        }
        payFortData.setAmount(String.valueOf(i));
        if (this.bookingType == null || Integer.parseInt(this.bookingType) != 0) {
            payFortData.setCommand(PayFortPayment.PURCHASE);
        } else {
            payFortData.setCommand(PayFortPayment.AUTHORIZATION);
        }
        payFortData.setCurrency(PayFortPayment.CURRENCY_TYPE);
        payFortData.setCustomerEmail(PrefUtils.getInstance().getUser().getMail());
        payFortData.setPhoneNumber(PrefUtils.getInstance().getUser().getPhoneNumber());
        if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar")) {
            payFortData.setLanguage("ar");
        } else {
            payFortData.setLanguage("en");
        }
        payFortData.setMerchantReference(this.orderId);
        payFortData.setSdkToken(str);
        if (this.card == null || this.card.isSavedCard() == null) {
            payFortData.setMerchantExtra1(AppEventsConstants.EVENT_PARAM_VALUE_NO);
        } else {
            payFortData.setTokenName(this.card.getToken());
            payFortData.setMerchantExtra1(AppEventsConstants.EVENT_PARAM_VALUE_YES);
        }
        if (this.walletCheckBox.isChecked() != null) {
            payFortData.setMerchantExtra(AppEventsConstants.EVENT_PARAM_VALUE_YES);
        } else {
            payFortData.setMerchantExtra(AppEventsConstants.EVENT_PARAM_VALUE_NO);
        }
        new PayFortPayment(payFortData, this.fortCallback, this).requestPurchase();
    }

    public void onPaymentRequestResponse(int i, PayFortData payFortData) {
        if (i == PayFortPayment.RESPONSE_CANCEL) {
            showToast(getString(R.string.payment_cancelled));
            goToPropertyDetailScreen();
            Log.e("onPaymentResponse", "Payment cancelled");
        } else if (i == PayFortPayment.RESPONSE_FAILURE) {
            showToast(getString(R.string.payment_failed));
            Log.e("onPaymentResponse", "Payment failed");
            goToPropertyDetailScreen();
        } else {
            showToast(getString(R.string.payment_successful));
            Log.e("onPaymentResponse", "Payment successful");
            i = new PropertyPaymentRequest();
            i.setOrderId(this.orderId);
            i.setBookingType(this.bookingType);
            if (this.card != null) {
                i.setSavedCard(this.card.isSavedCard());
            }
            if (!this.walletCheckBox.isChecked()) {
                i.setWallet(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                i.setCard(String.valueOf(this.modifiedAmount));
            } else if (this.myWalletAmount >= this.modifiedAmount) {
                i.setCard(String.valueOf(AppEventsConstants.EVENT_PARAM_VALUE_NO));
                i.setWallet(String.valueOf(this.myWalletAmount));
            } else {
                i.setCard(String.valueOf(this.modifiedAmount - this.myWalletAmount));
                i.setWallet(String.valueOf(this.myWalletAmount));
            }
            i.setPayload(payFortData);
            EventBus.getDefault().postSticky(i);
            startActivity(new Intent(this, DoNotRefreshActivity.class));
        }
    }

    public void onBackPressed() {
        goToPropertyDetailScreen();
        super.onBackPressed();
    }
}
