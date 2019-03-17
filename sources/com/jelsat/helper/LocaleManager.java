package com.jelsat.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import android.util.Log;
import java.util.Locale;

public class LocaleManager {
    private static final String COUNTRY_KEY = "country_key";
    public static final String DEFAULT_COUNTRY = "US";
    public static final String LANGUAGE_ARABIC = "ar";
    public static final String LANGUAGE_ENGLISH = "en";
    private static final String LANGUAGE_KEY = "language_key";

    public static Context setLocale(Context context) {
        return updateResources(context, getLanguage(context), getCountryCode(context));
    }

    public static Context setNewLocale(Context context, String str, String str2) {
        persistLanguage(context, str, str2);
        return updateResources(context, str, str2);
    }

    public static String getLanguage(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(LANGUAGE_KEY, "en");
    }

    public static String getCountryCode(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(COUNTRY_KEY, DEFAULT_COUNTRY);
    }

    @SuppressLint({"ApplySharedPref"})
    private static void persistLanguage(Context context, String str, String str2) {
        context = PreferenceManager.getDefaultSharedPreferences(context);
        context.edit().putString(LANGUAGE_KEY, str).commit();
        context.edit().putString(COUNTRY_KEY, str2).commit();
    }

    private static Context updateResources(Context context, String str, String str2) {
        Locale locale = new Locale(str, str2);
        Locale.setDefault(locale);
        Log.e("aaa", locale.getLanguage());
        Log.e("aaa", locale.getCountry());
        Log.e("aaa", locale.getDisplayLanguage());
        str = context.getResources();
        str2 = new Configuration(str.getConfiguration());
        if (VERSION.SDK_INT >= 17) {
            str2.setLocale(locale);
            return context.createConfigurationContext(str2);
        }
        str2.locale = locale;
        str.updateConfiguration(str2, str.getDisplayMetrics());
        return context;
    }

    public static String getDefaultCountry() {
        Locale locale;
        if (VERSION.SDK_INT >= 24) {
            locale = Resources.getSystem().getConfiguration().getLocales().get(0);
        } else {
            locale = Resources.getSystem().getConfiguration().locale;
        }
        Log.e("bbb", locale.getLanguage());
        Log.e("bbb", locale.getCountry());
        Log.e("bbb", locale.getDisplayLanguage());
        return locale.getCountry();
    }
}
