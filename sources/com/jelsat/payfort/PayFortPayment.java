package com.jelsat.payfort;

import android.util.Log;
import com.data.payfort.PayFortData;
import com.google.gson.Gson;
import com.payfort.fort.android.sdk.base.FortSdk;
import com.payfort.fort.android.sdk.base.callbacks.FortCallBackManager;
import com.payfort.sdk.android.dependancies.base.FortInterfaces.OnTnxProcessed;
import com.payfort.sdk.android.dependancies.commons.Constants.FORT_PARAMS;
import com.payfort.sdk.android.dependancies.models.FortRequest;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class PayFortPayment implements OnTnxProcessed {
    public static final String AUTHORIZATION = "AUTHORIZATION";
    public static final String CURRENCY_TYPE = "SAR";
    public static final String PURCHASE = "PURCHASE";
    public static final int REQUEST_CODE = 222;
    public static final int RESPONSE_CANCEL = 333;
    public static final int RESPONSE_FAILURE = 555;
    public static final int RESPONSE_SUCCESS = 444;
    private FortCallBackManager fortCallback = null;
    private Gson gson;
    private IPaymentRequestCallBackView iPaymentRequestCallBackView;
    private PayFortData payFortData;

    public PayFortPayment(PayFortData payFortData, FortCallBackManager fortCallBackManager, IPaymentRequestCallBackView iPaymentRequestCallBackView) {
        this.payFortData = payFortData;
        this.fortCallback = fortCallBackManager;
        this.iPaymentRequestCallBackView = iPaymentRequestCallBackView;
        this.gson = new Gson();
    }

    public void requestPurchase() {
        this.iPaymentRequestCallBackView.showProgressDialog("Initializing Payment...");
        try {
            FortSdk.getInstance().registerCallback(this.iPaymentRequestCallBackView.getActivityInstance(), getPurchaseFortRequest(), "https://checkout.payfort.com", REQUEST_CODE, this.fortCallback, false, this);
        } catch (Exception e) {
            this.iPaymentRequestCallBackView.dismissProgress();
            e.printStackTrace();
        }
    }

    private FortRequest getPurchaseFortRequest() {
        FortRequest fortRequest = new FortRequest();
        if (this.payFortData != null) {
            Map hashMap = new HashMap();
            hashMap.put(FORT_PARAMS.AMOUNT, this.payFortData.getAmount());
            hashMap.put("command", this.payFortData.getCommand());
            hashMap.put("currency", this.payFortData.getCurrency());
            hashMap.put("customer_email", this.payFortData.getCustomerEmail());
            hashMap.put(FORT_PARAMS.LANGUAGE, this.payFortData.getLanguage());
            hashMap.put(FORT_PARAMS.MERCHSNT_REFERENCE, this.payFortData.getMerchantReference());
            hashMap.put(FORT_PARAMS.SDK_TOKEN, this.payFortData.getSdkToken());
            hashMap.put(FORT_PARAMS.CUSTOMER_NAME, this.payFortData.getCustomerName());
            hashMap.put("phone_number", this.payFortData.getPhoneNumber());
            hashMap.put("merchant_extra", this.payFortData.getMerchantExtra());
            hashMap.put("merchant_extra1", this.payFortData.getMerchantExtra1());
            if (this.payFortData.getTokenName() != null && this.payFortData.getTokenName().trim().length() > 0) {
                hashMap.put("token_name", this.payFortData.getTokenName());
            }
            Log.e("data", hashMap.toString());
            fortRequest.setRequestMap(hashMap);
        }
        return fortRequest;
    }

    public void onCancel(Map<String, Object> map, Map<String, Object> map2) {
        map = new JSONObject(map2);
        PayFortData payFortData = (PayFortData) this.gson.fromJson(map.toString(), PayFortData.class);
        Log.e("Cancel Response", map.toString());
        if (this.iPaymentRequestCallBackView != null) {
            this.iPaymentRequestCallBackView.dismissProgress();
            this.iPaymentRequestCallBackView.onPaymentRequestResponse(RESPONSE_CANCEL, payFortData);
        }
    }

    public void onSuccess(Map<String, Object> map, Map<String, Object> map2) {
        map = new JSONObject(map2);
        PayFortData payFortData = (PayFortData) this.gson.fromJson(map.toString(), PayFortData.class);
        Log.e("Success Response", map.toString());
        if (this.iPaymentRequestCallBackView != null) {
            this.iPaymentRequestCallBackView.dismissProgress();
            this.iPaymentRequestCallBackView.onPaymentRequestResponse(RESPONSE_SUCCESS, payFortData);
        }
    }

    public void onFailure(Map<String, Object> map, Map<String, Object> map2) {
        Log.e("data", new HashMap(map).toString());
        map = new JSONObject(map2);
        PayFortData payFortData = (PayFortData) this.gson.fromJson(map.toString(), PayFortData.class);
        Log.e("Failure Response", map.toString());
        if (this.iPaymentRequestCallBackView != null) {
            this.iPaymentRequestCallBackView.dismissProgress();
            this.iPaymentRequestCallBackView.onPaymentRequestResponse(RESPONSE_FAILURE, payFortData);
        }
    }
}
