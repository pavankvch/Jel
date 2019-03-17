package com.jelsat.utils;

import android.content.Context;
import android.net.ConnectivityManager;

public class NetworkUtil {
    public static boolean isConnectedToNetwork(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        context = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return context != null ? context.isConnected() : null;
    }

    public static boolean isNetworkConnectedThroughWifi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        context = connectivityManager != null ? connectivityManager.getNetworkInfo(1) : null;
        return context != null ? context.isConnected() : null;
    }
}
