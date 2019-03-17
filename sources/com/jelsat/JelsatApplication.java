package com.jelsat;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.util.Log;
import com.crashlytics.android.Crashlytics;
import com.data.utils.PrefUtils;
import com.jelsat.helper.ActivityLifecycleAdapter;
import com.jelsat.helper.LocaleManager;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Fabric.Builder;
import java.util.Locale;

public class JelsatApplication extends Application {
    private final String TAG = "App";
    private RefWatcher refWatcher;

    public void onCreate() {
        super.onCreate();
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            Locale locale;
            this.refWatcher = LeakCanary.install(this);
            if (VERSION.SDK_INT >= 24) {
                locale = Resources.getSystem().getConfiguration().getLocales().get(0);
            } else {
                locale = Resources.getSystem().getConfiguration().locale;
            }
            if (locale.getLanguage().equalsIgnoreCase("ar")) {
                LocaleManager.setNewLocale(this, "ar", "SA");
            } else {
                LocaleManager.setLocale(this);
            }
            PrefUtils.getInstance().setPrefContext(getApplicationContext());
            registerActivityLifecycleCallbacks(new ActivityLifecycleAdapter() {
                public void onActivityCreated(Activity activity, Bundle bundle) {
                    super.onActivityCreated(activity, bundle);
                }
            });
            Fabric.with(new Builder(this).kits(new Crashlytics()).debuggable(true).build());
        }
    }

    public static RefWatcher getRefWatcher(Context context) {
        return ((JelsatApplication) context.getApplicationContext()).refWatcher;
    }

    protected void attachBaseContext(Context context) {
        super.attachBaseContext(LocaleManager.setLocale(context));
        MultiDex.install(this);
        Log.d("App", "attachBaseContext");
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LocaleManager.setLocale(this);
        StringBuilder stringBuilder = new StringBuilder("onConfigurationChanged: ");
        stringBuilder.append(configuration.locale.getLanguage());
        Log.d("App", stringBuilder.toString());
    }
}
