package com.jelsat.baseuiframework;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.businesslogic.framework.IBaseView;
import com.crashlytics.android.Crashlytics;
import com.jelsat.R;
import com.jelsat.helper.LocaleManager;
import com.jelsat.utils.GlideApp;
import com.jelsat.utils.NetworkUtil;
import com.jelsat.utils.ProgressDialogUtil;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;

public abstract class BaseAppCompactActivity extends AppCompatActivity implements IBaseView {
    private static final String TAG = "BaseAppCompatActivity";
    protected Dialog progressDialog;
    private Unbinder unbinder;

    public abstract int getActivityLayout();

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setRequestedOrientation(7);
        Fabric.with(this, new Kit[]{new Crashlytics()});
        setContentView((int) R.layout.activity_base);
        ViewStub viewStub = (ViewStub) findViewById(R.id.layout_activity);
        viewStub.setLayoutResource(getActivityLayout());
        viewStub.inflate();
        this.unbinder = ButterKnife.bind((Activity) this);
    }

    public void showProgressDialog(String str) {
        this.progressDialog = ProgressDialogUtil.showLoading(this, str);
    }

    public void showLoading() {
        this.progressDialog = ProgressDialogUtil.showLoading(this);
    }

    public void dismissProgress() {
        if (this.progressDialog != null && this.progressDialog.isShowing()) {
            this.progressDialog.cancel();
        }
        this.progressDialog = null;
    }

    public boolean isNetworkConnected() {
        if (!NetworkUtil.isConnectedToNetwork(this)) {
            if (!NetworkUtil.isNetworkConnectedThroughWifi(this)) {
                return false;
            }
        }
        return true;
    }

    public void showToast(String str) {
        Toast.makeText(this, str, 0).show();
    }

    public Context getContext() {
        return getApplicationContext();
    }

    @TargetApi(23)
    public void requestPermissionsSafely(int i, String... strArr) {
        if (VERSION.SDK_INT >= 23) {
            requestPermissions(strArr, i);
        }
    }

    @TargetApi(23)
    public boolean hasPermission(String str) {
        if (VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(str) != null) {
                return null;
            }
        }
        return true;
    }

    protected void goToActivity(Class cls) {
        if (cls != null) {
            startActivity(new Intent(this, cls));
        }
    }

    protected void goToActivity(Class cls, Bundle bundle) {
        if (cls != null) {
            Intent intent = new Intent(this, cls);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    public void hideKeyboard() {
        View currentFocus = getCurrentFocus();
        if (currentFocus != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(currentFocus.getWindowToken(), 2);
        }
    }

    public int applyColor(@ColorRes int i) {
        return ContextCompat.getColor(this, i);
    }

    protected void onPause() {
        dismissProgress();
        super.onPause();
    }

    public void onStop() {
        dismissProgress();
        super.onStop();
    }

    protected void onDestroy() {
        if (this.unbinder != null) {
            this.unbinder.unbind();
        }
        super.onDestroy();
    }

    protected void attachBaseContext(Context context) {
        super.attachBaseContext(LocaleManager.setLocale(context));
        Log.d(TAG, "attachBaseContext");
    }

    public void onDetachedFromWindow() {
        GlideApp.get(getApplicationContext()).clearMemory();
        super.onDetachedFromWindow();
    }
}
