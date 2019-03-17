package com.jelsat.sociallogin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import com.facebook.CallbackManager;
import com.facebook.CallbackManager.Factory;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.GraphJSONObjectCallback;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.jelsat.R;
import com.jelsat.activities.HomeActivity;
import org.json.JSONObject;

public class FacebookLogin extends SmartLogin {
    private CallbackManager callbackManager = Factory.create();

    public void login(@NonNull SmartLoginConfig smartLoginConfig) {
        final Activity activity = smartLoginConfig.getActivity();
        final SmartLoginCallbacks callback = smartLoginConfig.getCallback();
        smartLoginConfig = smartLoginConfig.getFacebookPermissions();
        if (smartLoginConfig == null) {
            smartLoginConfig = SmartLoginConfig.getDefaultFacebookPermissions();
        }
        LoginManager.getInstance().logInWithReadPermissions(activity, smartLoginConfig);
        LoginManager.getInstance().registerCallback(this.callbackManager, new FacebookCallback<LoginResult>() {
            public void onSuccess(final LoginResult loginResult) {
                ((HomeActivity) activity).showProgressDialog(activity.getString(R.string.getting_data));
                loginResult = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphJSONObjectCallback() {
                    public void onCompleted(JSONObject jSONObject, GraphResponse graphResponse) {
                        ((HomeActivity) activity).dismissProgress();
                        callback.onLoginSuccess(UserUtil.populateFacebookUser(jSONObject, loginResult.getAccessToken()), 1);
                    }
                });
                Bundle bundle = new Bundle();
                bundle.putString(GraphRequest.FIELDS_PARAM, "id,name,email,picture,first_name,last_name");
                loginResult.setParameters(bundle);
                loginResult.executeAsync();
            }

            public void onCancel() {
                ((HomeActivity) activity).dismissProgress();
                Log.d("Facebook Login", "User cancelled the login process");
                callback.onLoginFailure(new SmartLoginException("User cancelled the login request", 1));
            }

            public void onError(FacebookException facebookException) {
                ((HomeActivity) activity).dismissProgress();
                callback.onLoginFailure(new SmartLoginException(facebookException.getMessage(), facebookException, 1));
            }
        });
    }

    public boolean logout(Context context) {
        try {
            LoginManager.getInstance().logOut();
            return true;
        } catch (Context context2) {
            Log.e("FacebookLogin", context2.getMessage());
            return null;
        }
    }

    public void onActivityResult(int i, int i2, Intent intent, SmartLoginConfig smartLoginConfig) {
        this.callbackManager.onActivityResult(i, i2, intent);
    }
}
