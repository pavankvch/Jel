package com.data.bankdetails;

import com.google.gson.annotations.SerializedName;

public class AddBankDetailsRequest {
    @SerializedName("bank_holder_name")
    private String bankHolderName;
    @SerializedName("bank_name")
    private String bankName;
    private String country;
    @SerializedName("iban_number")
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
