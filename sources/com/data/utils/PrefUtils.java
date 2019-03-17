package com.data.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.data.amenitiesandhouserules.SeedResponse;
import com.data.signin.UserModel;
import com.google.gson.Gson;

public class PrefUtils {
    private static PrefUtils prefUtils;
    private Context context;

    class PrefKeys {
        static final String CLICK_SKIP = "CLICK_SKIP";
        static final String COOKIE = "cookie";
        static final String FCM_TOKEN = "fcm_token";
        static final String HOST_OR_GUEST_SELECTION = "host_or_guest_selection";
        static final String IS_DEVICE_TOKEN_UPDATED = "is_device_token_updated";
        static final String IS_SOCIAL_SIGNIN = "is_social_signin";
        static final String SEED_RESPONSE = "seed_response";
        static final String SEKEN_SHARED_PREF = "seken_shared_pref";
        static final String SHOW_BECOME_HOST_INTRO_DIALOG = "show_become_host_intro_dialog";
        static final String SKIP_ADDRESS = "SKIP_ADDRESS";
        static final String USER = "user";
        static final String USER_TOKEN = "user_token";

        private PrefKeys() {
        }
    }

    private PrefUtils() {
    }

    public static PrefUtils getInstance() {
        if (prefUtils == null) {
            synchronized (PrefUtils.class) {
                prefUtils = new PrefUtils();
            }
        }
        return prefUtils;
    }

    public void setPrefContext(Context context) {
        this.context = context;
    }

    private SharedPreferences getPreferences() {
        return this.context.getSharedPreferences("seken_shared_pref", 0);
    }

    public void saveUserToken(String str) {
        getPreferences().edit().putString("user_token", str).apply();
    }

    public String getUserAccessToken() {
        return getPreferences().getString("user_token", null);
    }

    public void saveCookie(String str) {
        getPreferences().edit().putString("cookie", str).apply();
    }

    public void saveSocialSignin(boolean z) {
        getPreferences().edit().putBoolean("is_social_signin", z).apply();
    }

    public boolean isSocialSignin() {
        return getPreferences().getBoolean("is_social_signin", false);
    }

    public String getCookie() {
        return getPreferences().getString("cookie", null);
    }

    public void saveSeedResponse(SeedResponse seedResponse) {
        getPreferences().edit().putString("seed_response", new Gson().toJson(seedResponse)).apply();
    }

    public SeedResponse getSeedResponse() {
        return (SeedResponse) new Gson().fromJson(getPreferences().getString("seed_response", null), SeedResponse.class);
    }

    public void saveUser(UserModel userModel) {
        getPreferences().edit().putString(APIConstants.SIGN_UP, new Gson().toJson(userModel)).apply();
    }

    public UserModel getUser() {
        return (UserModel) new Gson().fromJson(getPreferences().getString(APIConstants.SIGN_UP, null), UserModel.class);
    }

    public void clearSharedPreferences() {
        getPreferences().edit().clear().apply();
    }

    public void saveHostSelection(boolean z) {
        getPreferences().edit().putBoolean("host_or_guest_selection", z).apply();
    }

    public boolean isHostSelected() {
        return getPreferences().getBoolean("host_or_guest_selection", false);
    }

    public void saveToShowHostIntroDialog(boolean z) {
        getPreferences().edit().putBoolean("show_become_host_intro_dialog", z).apply();
    }

    public boolean isNeedShowIntroDialog() {
        return getPreferences().getBoolean("show_become_host_intro_dialog", true);
    }

    public void saveFcmToken(String str) {
        getPreferences().edit().putString("fcm_token", str).apply();
    }

    public String getFcmToken() {
        return getPreferences().getString("fcm_token", null);
    }

    public void saveSkipAddressBollen(Boolean bool) {
        getPreferences().edit().putBoolean("SKIP_ADDRESS", bool.booleanValue()).apply();
    }

    public Boolean getSkipAddressBollen() {
        return Boolean.valueOf(getPreferences().getBoolean("SKIP_ADDRESS", false));
    }

    public boolean isDeviceTokenUpdated() {
        return getPreferences().getBoolean("is_device_token_updated", false);
    }

    public void setDeviceTokenUpdated(boolean z) {
        getPreferences().edit().putBoolean("is_device_token_updated", z).apply();
    }

    public boolean isSkipClicked() {
        return getPreferences().getBoolean("CLICK_SKIP", false);
    }

    public void setClickSkip(boolean z) {
        getPreferences().edit().putBoolean("CLICK_SKIP", z).apply();
    }
}
