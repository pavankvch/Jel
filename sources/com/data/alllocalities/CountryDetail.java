package com.data.alllocalities;

public class CountryDetail {
    private String countryName;
    private String countryShortName;

    public CountryDetail(String str, String str2) {
        this.countryName = str;
        this.countryShortName = str2;
    }

    public String getCountryName() {
        return this.countryName;
    }

    public String getCountryShortName() {
        return this.countryShortName;
    }

    public String toString() {
        return this.countryName;
    }
}
