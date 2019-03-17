package com.data.SeedAndLanguage;

import com.data.amenitiesandhouserules.SeedResponse;

public class SeedAndLanguageCombinedResponse {
    private ChangeLanguageResponse changeLanguageResponse;
    private SeedResponse seedResponse;

    public SeedAndLanguageCombinedResponse(ChangeLanguageResponse changeLanguageResponse, SeedResponse seedResponse) {
        this.changeLanguageResponse = changeLanguageResponse;
        this.seedResponse = seedResponse;
    }

    public ChangeLanguageResponse getChangeLanguageResponse() {
        return this.changeLanguageResponse;
    }

    public SeedResponse getSeedResponse() {
        return this.seedResponse;
    }
}
