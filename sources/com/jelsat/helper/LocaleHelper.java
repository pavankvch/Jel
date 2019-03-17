package com.jelsat.helper;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import java.util.Locale;

public class LocaleHelper {
    private static final String SELECTED_COUNTRY = "Locale.Helper.Selected.Country";
    private static final String SELECTED_LANGUAGE = "Locale.Helper.Selected.Language";

    public static Context onAttach(Context context) {
        return setLocale(context, getPersistedData(context, Locale.getDefault().getLanguage()), getPersistedCountryCode(context, LocaleManager.DEFAULT_COUNTRY));
    }

    public static Context onAttach(Context context, String str) {
        return setLocale(context, getPersistedData(context, str), getPersistedCountryCode(context, LocaleManager.DEFAULT_COUNTRY));
    }

    public static String getLanguage(Context context) {
        return getPersistedData(context, Locale.getDefault().getLanguage());
    }

    public static Context setLocale(Context context, String str, String str2) {
        persist(context, str, str2);
        if (VERSION.SDK_INT >= 24) {
            return updateResources(context, str, str2);
        }
        return updateResourcesLegacy(context, str, str2);
    }

    public static String getPersistedData(Context context, String str) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(SELECTED_LANGUAGE, str);
    }

    public static String getPersistedCountryCode(Context context, String str) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(SELECTED_COUNTRY, str);
    }

    private static void persist(Context context, String str, String str2) {
        context = PreferenceManager.getDefaultSharedPreferences(context).edit();
        context.putString(SELECTED_LANGUAGE, str);
        context.putString(SELECTED_COUNTRY, str2);
        context.apply();
    }

    @TargetApi(24)
    public static Context updateResources(Context context, String str, String str2) {
        Locale locale = new Locale(str, str2);
        Locale.setDefault(locale);
        str = context.getResources().getConfiguration();
        str.setLocale(locale);
        str.setLayoutDirection(locale);
        return context.createConfigurationContext(str);
    }

    private static Context updateResourcesLegacy(Context context, String str, String str2) {
        Locale locale = new Locale(str, str2);
        Locale.setDefault(locale);
        str = context.getResources();
        str2 = str.getConfiguration();
        str2.locale = locale;
        if (VERSION.SDK_INT >= 17) {
            str2.setLayoutDirection(locale);
        }
        str.updateConfiguration(str2, str.getDisplayMetrics());
        return context;
    }
}
