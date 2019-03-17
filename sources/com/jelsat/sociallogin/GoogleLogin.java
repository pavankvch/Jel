package com.jelsat.sociallogin;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.jelsat.R;
import com.jelsat.activities.HomeActivity;

public class GoogleLogin extends SmartLogin {
    private Activity activity;
    private GoogleSignInClient googleSignInClient;

    public void login(@NonNull SmartLoginConfig smartLoginConfig) {
        this.googleSignInClient = smartLoginConfig.getGoogleSignInClient();
        this.activity = smartLoginConfig.getActivity();
        if (this.activity == null) {
            throw new RuntimeException("context cannot be null.");
        }
        smartLoginConfig = ProgressDialog.show(this.activity, "", this.activity.getString(R.string.loading_holder), true);
        this.activity.startActivityForResult(this.googleSignInClient.getSignInIntent(), Constants.GOOGLE_LOGIN_REQUEST);
        smartLoginConfig.dismiss();
    }

    public boolean logout(Context context) {
        if (this.googleSignInClient == null) {
            return null;
        }
        this.googleSignInClient.signOut().addOnCompleteListener(this.activity, new OnCompleteListener<Void>() {
            public void onComplete(@NonNull Task<Void> task) {
                try {
                    task.getResult(ApiException.class);
                } catch (Task<Void> task2) {
                    task2.printStackTrace();
                }
            }
        });
        return true;
    }

    public void onActivityResult(int i, int i2, Intent intent, SmartLoginConfig smartLoginConfig) {
        if (i == Constants.GOOGLE_LOGIN_REQUEST) {
            handleSignInResult(GoogleSignIn.getSignedInAccountFromIntent(intent), smartLoginConfig);
            return;
        }
        Log.d("GOOGLE SIGN IN", String.valueOf(i));
        ((HomeActivity) smartLoginConfig.getActivity()).dismissProgress();
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task, SmartLoginConfig smartLoginConfig) {
        try {
            smartLoginConfig.getCallback().onLoginSuccess(UserUtil.populateGoogleUser((GoogleSignInAccount) task.getResult(ApiException.class)), 0);
            ((HomeActivity) smartLoginConfig.getActivity()).dismissProgress();
        } catch (Task<GoogleSignInAccount> task2) {
            task2.printStackTrace();
            StringBuilder stringBuilder = new StringBuilder("signInResult:failed code= ");
            stringBuilder.append(task2.getStatusCode());
            Log.w("aaa", stringBuilder.toString());
            ((HomeActivity) smartLoginConfig.getActivity()).dismissProgress();
        } catch (Task<GoogleSignInAccount> task22) {
            task22.printStackTrace();
            StringBuilder stringBuilder2 = new StringBuilder("signInResult:failed code= ");
            stringBuilder2.append(task22.getMessage());
            Log.w("aaa", stringBuilder2.toString());
            smartLoginConfig.getCallback().onLoginFailure(new SmartLoginException(this.activity.getString(R.string.google_login_failed), 0));
            ((HomeActivity) smartLoginConfig.getActivity()).dismissProgress();
        }
    }
}
