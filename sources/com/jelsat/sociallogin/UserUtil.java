package com.jelsat.sociallogin;

import android.util.Log;
import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.jelsat.sociallogin.user.SmartUser;
import org.json.JSONObject;

public class UserUtil {
    public static SmartUser populateGoogleUser(GoogleSignInAccount googleSignInAccount) {
        SmartUser smartUser = new SmartUser();
        smartUser.setUsername(googleSignInAccount.getDisplayName());
        if (googleSignInAccount.getPhotoUrl() != null) {
            smartUser.setPhotoUrl(googleSignInAccount.getPhotoUrl().toString());
        }
        smartUser.setEmail(googleSignInAccount.getEmail());
        smartUser.setUserId(googleSignInAccount.getId());
        smartUser.setFirstName(googleSignInAccount.getGivenName());
        smartUser.setLastName(googleSignInAccount.getFamilyName());
        return smartUser;
    }

    public static SmartUser populateFacebookUser(JSONObject jSONObject, AccessToken accessToken) {
        accessToken = new SmartUser();
        try {
            if (jSONObject.has("email")) {
                accessToken.setEmail(jSONObject.getString("email"));
            }
            if (jSONObject.has(Constants.BIRTHDAY)) {
                accessToken.setBirthday(jSONObject.getString(Constants.BIRTHDAY));
            }
            if (jSONObject.has(Constants.GENDER)) {
                accessToken.setGender(jSONObject.getString(Constants.GENDER));
            }
            if (jSONObject.has("link")) {
                accessToken.setProfileLink(jSONObject.getString("link"));
            }
            if (jSONObject.has("id")) {
                accessToken.setUserId(jSONObject.getString("id"));
            }
            if (jSONObject.has("name")) {
                accessToken.setUsername(jSONObject.getString("name"));
            }
            if (jSONObject.has(Constants.FIRST_NAME)) {
                accessToken.setFirstName(jSONObject.getString(Constants.FIRST_NAME));
            }
            if (jSONObject.has(Constants.MIDDLE_NAME)) {
                accessToken.setMiddleName(jSONObject.getString(Constants.MIDDLE_NAME));
            }
            if (jSONObject.has(Constants.LAST_NAME)) {
                accessToken.setLastName(jSONObject.getString(Constants.LAST_NAME));
            }
            if (!jSONObject.has("picture")) {
                return accessToken;
            }
            accessToken.setPhotoUrl(jSONObject.getJSONObject("picture").getJSONObject("data").getString("url"));
            return accessToken;
        } catch (JSONObject jSONObject2) {
            Log.e("UserUtil", jSONObject2.getMessage());
            return null;
        }
    }
}
