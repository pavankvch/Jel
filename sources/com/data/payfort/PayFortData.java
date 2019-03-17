package com.data.payfort;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PayFortData {
    @SerializedName("access_code")
    @Expose
    private String accessCode;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("authorization_code")
    @Expose
    private String authorizationCode;
    @SerializedName("card_number")
    @Expose
    private String cardNumber;
    @SerializedName("command")
    @Expose
    private String command;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("customer_email")
    @Expose
    private String customerEmail;
    @SerializedName("customer_ip")
    @Expose
    private String customerIp;
    @SerializedName("customer_name")
    @Expose
    private String customerName;
    @SerializedName("device_id")
    @Expose
    private String deviceId;
    @SerializedName("eci")
    @Expose
    private String eci;
    @SerializedName("expiry_date")
    @Expose
    private String expiryDate;
    @SerializedName("fort_id")
    @Expose
    private String fortId;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("merchant_extra")
    @Expose
    private String merchantExtra;
    @SerializedName("merchant_extra1")
    @Expose
    private String merchantExtra1;
    @SerializedName("merchant_identifier")
    @Expose
    private String merchantIdentifier;
    @SerializedName("merchant_reference")
    @Expose
    private String merchantReference;
    @SerializedName("payment_option")
    @Expose
    private String paymentOption;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("response_code")
    @Expose
    private String responseCode;
    @SerializedName("response_message")
    @Expose
    private String responseMessage;
    @SerializedName("sdk_token")
    @Expose
    private String sdkToken;
    @SerializedName("service_command")
    @Expose
    private String serviceCommand;
    @SerializedName("signature")
    @Expose
    private String signature;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("token_name")
    @Expose
    private String tokenName;

    public String getAccessCode() {
        return this.accessCode;
    }

    public void setAccessCode(String str) {
        this.accessCode = str;
    }

    public String getSdkToken() {
        return this.sdkToken;
    }

    public void setSdkToken(String str) {
        this.sdkToken = str;
    }

    public String getResponseMessage() {
        return this.responseMessage;
    }

    public void setResponseMessage(String str) {
        this.responseMessage = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getResponseCode() {
        return this.responseCode;
    }

    public void setResponseCode(String str) {
        this.responseCode = str;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public String getServiceCommand() {
        return this.serviceCommand;
    }

    public void setServiceCommand(String str) {
        this.serviceCommand = str;
    }

    public String getSignature() {
        return this.signature;
    }

    public void setSignature(String str) {
        this.signature = str;
    }

    public String getMerchantIdentifier() {
        return this.merchantIdentifier;
    }

    public void setMerchantIdentifier(String str) {
        this.merchantIdentifier = str;
    }

    public String getEci() {
        return this.eci;
    }

    public void setEci(String str) {
        this.eci = str;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public void setCardNumber(String str) {
        this.cardNumber = str;
    }

    public String getFortId() {
        return this.fortId;
    }

    public void setFortId(String str) {
        this.fortId = str;
    }

    public String getCustomerEmail() {
        return this.customerEmail;
    }

    public void setCustomerEmail(String str) {
        this.customerEmail = str;
    }

    public String getCustomerIp() {
        return this.customerIp;
    }

    public void setCustomerIp(String str) {
        this.customerIp = str;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String str) {
        this.currency = str;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String str) {
        this.amount = str;
    }

    public String getMerchantReference() {
        return this.merchantReference;
    }

    public void setMerchantReference(String str) {
        this.merchantReference = str;
    }

    public String getCommand() {
        return this.command;
    }

    public void setCommand(String str) {
        this.command = str;
    }

    public String getPaymentOption() {
        return this.paymentOption;
    }

    public void setPaymentOption(String str) {
        this.paymentOption = str;
    }

    public String getExpiryDate() {
        return this.expiryDate;
    }

    public void setExpiryDate(String str) {
        this.expiryDate = str;
    }

    public String getAuthorizationCode() {
        return this.authorizationCode;
    }

    public void setAuthorizationCode(String str) {
        this.authorizationCode = str;
    }

    public String getTokenName() {
        return this.tokenName;
    }

    public void setTokenName(String str) {
        this.tokenName = str;
    }

    public String getMerchantExtra() {
        return this.merchantExtra;
    }

    public void setMerchantExtra(String str) {
        this.merchantExtra = str;
    }

    public String getMerchantExtra1() {
        return this.merchantExtra1;
    }

    public void setMerchantExtra1(String str) {
        this.merchantExtra1 = str;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String str) {
        this.phoneNumber = str;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String str) {
        this.customerName = str;
    }
}
