package com.jelsat.sociallogin;

import android.app.Activity;
import android.support.annotation.NonNull;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import java.util.ArrayList;

public class SmartLoginConfig {
    private Activity activity;
    private SmartLoginCallbacks callback;
    private String facebookAppId;
    private ArrayList<String> facebookPermissions;
    private GoogleSignInClient googleSignInClient;

    public SmartLoginConfig(@NonNull Activity activity, SmartLoginCallbacks smartLoginCallbacks) {
        this.activity = activity;
        this.callback = smartLoginCallbacks;
    }

    public Activity getActivity() {
        return this.activity;
    }

    public SmartLoginCallbacks getCallback() {
        return this.callback;
    }

    public GoogleSignInClient getGoogleSignInClient() {
        return this.googleSignInClient;
    }

    public void setGoogleSignInClient(GoogleSignInClient googleSignInClient) {
        this.googleSignInClient = googleSignInClient;
    }

    public String getFacebookAppId() {
        return this.facebookAppId;
    }

    public void setFacebookAppId(String str) {
        this.facebookAppId = str;
    }

    public ArrayList<String> getFacebookPermissions() {
        return this.facebookPermissions;
    }

    public void setFacebookPermissions(ArrayList<String> arrayList) {
        this.facebookPermissions = arrayList;
    }

    public static ArrayList<String> getDefaultFacebookPermissions() {
        ArrayList<String> arrayList = new ArrayList();
        arrayList.add("public_profile");
        arrayList.add("email");
        arrayList.add("user_birthday");
        arrayList.add("user_friends");
        return arrayList;
    }
}
