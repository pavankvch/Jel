package com.data.bankdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BankDetailsResponse {
    @SerializedName("bank_holder_name")
    @Expose
    private String bankHolderName;
    @SerializedName("bank_name")
    @Expose
    private String bankName;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("iban_number")
    @Expose
    private String ibanNumber;

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public String getBankName() {
        return this.bankName;
    }

    public void setBankName(String str) {
        this.bankName = str;
    }

    public String getBankHolderName() {
        return this.bankHolderName;
    }

    public void setBankHolderName(String str) {
        this.bankHolderName = str;
    }

    public String getIbanNumber() {
        return this.ibanNumber;
    }

    public void setIbanNumber(String str) {
        this.ibanNumber = str;
    }
}
