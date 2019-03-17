package com.jelsat.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import com.jelsat.R;
import com.jelsat.constants.StringConstants;
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil;
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil.PhoneNumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

public class Utils {
    private static Map<String, Typeface> cachedFontMap = new HashMap();

    public static int pxToSp(Context context, float f) {
        return Math.round(f / context.getResources().getDisplayMetrics().scaledDensity);
    }

    public static int spToPx(Context context, float f) {
        return Math.round(f * context.getResources().getDisplayMetrics().scaledDensity);
    }

    public static android.graphics.Typeface findFont(android.content.Context r6, java.lang.String r7, java.lang.String r8) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
*/
        /*
        if (r7 != 0) goto L_0x0005;
    L_0x0002:
        r6 = android.graphics.Typeface.DEFAULT;
        return r6;
    L_0x0005:
        r0 = new java.io.File;
        r0.<init>(r7);
        r0 = r0.getName();
        r1 = "";
        r2 = android.text.TextUtils.isEmpty(r8);
        if (r2 != 0) goto L_0x001f;
    L_0x0016:
        r1 = new java.io.File;
        r1.<init>(r8);
        r1 = r1.getName();
    L_0x001f:
        r2 = cachedFontMap;
        r2 = r2.containsKey(r0);
        if (r2 == 0) goto L_0x0030;
    L_0x0027:
        r6 = cachedFontMap;
        r6 = r6.get(r0);
        r6 = (android.graphics.Typeface) r6;
        return r6;
    L_0x0030:
        r2 = 0;
        r3 = 1;
        r4 = r6.getResources();	 Catch:{ Exception -> 0x00d4 }
        r4 = r4.getAssets();	 Catch:{ Exception -> 0x00d4 }
        r5 = "";	 Catch:{ Exception -> 0x00d4 }
        r5 = r4.list(r5);	 Catch:{ Exception -> 0x00d4 }
        r5 = java.util.Arrays.asList(r5);	 Catch:{ Exception -> 0x00d4 }
        r7 = r5.contains(r7);	 Catch:{ Exception -> 0x00d4 }
        if (r7 == 0) goto L_0x0058;	 Catch:{ Exception -> 0x00d4 }
    L_0x004a:
        r6 = r6.getAssets();	 Catch:{ Exception -> 0x00d4 }
        r6 = android.graphics.Typeface.createFromAsset(r6, r0);	 Catch:{ Exception -> 0x00d4 }
        r7 = cachedFontMap;	 Catch:{ Exception -> 0x00d4 }
        r7.put(r0, r6);	 Catch:{ Exception -> 0x00d4 }
        return r6;	 Catch:{ Exception -> 0x00d4 }
    L_0x0058:
        r7 = "fonts";	 Catch:{ Exception -> 0x00d4 }
        r7 = r4.list(r7);	 Catch:{ Exception -> 0x00d4 }
        r7 = java.util.Arrays.asList(r7);	 Catch:{ Exception -> 0x00d4 }
        r7 = r7.contains(r0);	 Catch:{ Exception -> 0x00d4 }
        if (r7 == 0) goto L_0x0080;	 Catch:{ Exception -> 0x00d4 }
    L_0x0068:
        r6 = r6.getAssets();	 Catch:{ Exception -> 0x00d4 }
        r7 = "fonts/%s";	 Catch:{ Exception -> 0x00d4 }
        r8 = new java.lang.Object[r3];	 Catch:{ Exception -> 0x00d4 }
        r8[r2] = r0;	 Catch:{ Exception -> 0x00d4 }
        r7 = java.lang.String.format(r7, r8);	 Catch:{ Exception -> 0x00d4 }
        r6 = android.graphics.Typeface.createFromAsset(r6, r7);	 Catch:{ Exception -> 0x00d4 }
        r7 = cachedFontMap;	 Catch:{ Exception -> 0x00d4 }
        r7.put(r0, r6);	 Catch:{ Exception -> 0x00d4 }
        return r6;	 Catch:{ Exception -> 0x00d4 }
    L_0x0080:
        r7 = "iconfonts";	 Catch:{ Exception -> 0x00d4 }
        r7 = r4.list(r7);	 Catch:{ Exception -> 0x00d4 }
        r7 = java.util.Arrays.asList(r7);	 Catch:{ Exception -> 0x00d4 }
        r7 = r7.contains(r0);	 Catch:{ Exception -> 0x00d4 }
        if (r7 == 0) goto L_0x00a8;	 Catch:{ Exception -> 0x00d4 }
    L_0x0090:
        r6 = r6.getAssets();	 Catch:{ Exception -> 0x00d4 }
        r7 = "iconfonts/%s";	 Catch:{ Exception -> 0x00d4 }
        r8 = new java.lang.Object[r3];	 Catch:{ Exception -> 0x00d4 }
        r8[r2] = r0;	 Catch:{ Exception -> 0x00d4 }
        r7 = java.lang.String.format(r7, r8);	 Catch:{ Exception -> 0x00d4 }
        r6 = android.graphics.Typeface.createFromAsset(r6, r7);	 Catch:{ Exception -> 0x00d4 }
        r7 = cachedFontMap;	 Catch:{ Exception -> 0x00d4 }
        r7.put(r0, r6);	 Catch:{ Exception -> 0x00d4 }
        return r6;	 Catch:{ Exception -> 0x00d4 }
    L_0x00a8:
        r7 = android.text.TextUtils.isEmpty(r8);	 Catch:{ Exception -> 0x00d4 }
        if (r7 != 0) goto L_0x00cc;	 Catch:{ Exception -> 0x00d4 }
    L_0x00ae:
        r7 = "";	 Catch:{ Exception -> 0x00d4 }
        r7 = r4.list(r7);	 Catch:{ Exception -> 0x00d4 }
        r7 = java.util.Arrays.asList(r7);	 Catch:{ Exception -> 0x00d4 }
        r7 = r7.contains(r8);	 Catch:{ Exception -> 0x00d4 }
        if (r7 == 0) goto L_0x00cc;	 Catch:{ Exception -> 0x00d4 }
    L_0x00be:
        r6 = r6.getAssets();	 Catch:{ Exception -> 0x00d4 }
        r6 = android.graphics.Typeface.createFromAsset(r6, r8);	 Catch:{ Exception -> 0x00d4 }
        r7 = cachedFontMap;	 Catch:{ Exception -> 0x00d4 }
        r7.put(r1, r6);	 Catch:{ Exception -> 0x00d4 }
        return r6;	 Catch:{ Exception -> 0x00d4 }
    L_0x00cc:
        r6 = new java.lang.Exception;	 Catch:{ Exception -> 0x00d4 }
        r7 = "Font not Found";	 Catch:{ Exception -> 0x00d4 }
        r6.<init>(r7);	 Catch:{ Exception -> 0x00d4 }
        throw r6;	 Catch:{ Exception -> 0x00d4 }
    L_0x00d4:
        r6 = com.jelsat.widgets.FancyButton.TAG;
        r7 = "Unable to find %s font. Using Typeface.DEFAULT instead.";
        r8 = new java.lang.Object[r3];
        r8[r2] = r0;
        r7 = java.lang.String.format(r7, r8);
        android.util.Log.e(r6, r7);
        r6 = cachedFontMap;
        r7 = android.graphics.Typeface.DEFAULT;
        r6.put(r0, r7);
        r6 = android.graphics.Typeface.DEFAULT;
        return r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jelsat.utils.Utils.findFont(android.content.Context, java.lang.String, java.lang.String):android.graphics.Typeface");
    }

    public static String getDateToString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(date);
    }

    public static java.lang.String reviewsDateFormatter(java.lang.String r4) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
*/
        /*
        r0 = "";
        if (r4 == 0) goto L_0x0020;
    L_0x0004:
        r0 = new java.text.SimpleDateFormat;
        r1 = "yyyy-MM-dd";
        r2 = java.util.Locale.ENGLISH;
        r0.<init>(r1, r2);
        r1 = new java.text.SimpleDateFormat;
        r2 = "MMM dd, yyyy";
        r3 = java.util.Locale.ENGLISH;
        r1.<init>(r2, r3);
        r0 = r0.parse(r4);	 Catch:{ ParseException -> 0x001f }
        r0 = r1.format(r0);	 Catch:{ ParseException -> 0x001f }
        goto L_0x0020;
    L_0x001f:
        r0 = r4;
    L_0x0020:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jelsat.utils.Utils.reviewsDateFormatter(java.lang.String):java.lang.String");
    }

    public static java.lang.String inboxDateFormatter(java.lang.String r4) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
*/
        /*
        r0 = "";
        if (r4 == 0) goto L_0x0020;
    L_0x0004:
        r0 = new java.text.SimpleDateFormat;
        r1 = "yyyy-MM-dd";
        r2 = java.util.Locale.ENGLISH;
        r0.<init>(r1, r2);
        r1 = new java.text.SimpleDateFormat;
        r2 = "dd-MM-yyyy";
        r3 = java.util.Locale.ENGLISH;
        r1.<init>(r2, r3);
        r0 = r0.parse(r4);	 Catch:{ ParseException -> 0x001f }
        r0 = r1.format(r0);	 Catch:{ ParseException -> 0x001f }
        goto L_0x0020;
    L_0x001f:
        r0 = r4;
    L_0x0020:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jelsat.utils.Utils.inboxDateFormatter(java.lang.String):java.lang.String");
    }

    public static java.lang.String bookingsCheckInCheckoutDateFormat(java.lang.String r4) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
*/
        /*
        r0 = new java.text.SimpleDateFormat;
        r1 = "yyyy-MM-dd";
        r2 = java.util.Locale.ENGLISH;
        r0.<init>(r1, r2);
        r1 = new java.text.SimpleDateFormat;
        r2 = "dd-MM-yyyy";
        r3 = java.util.Locale.ENGLISH;
        r1.<init>(r2, r3);
        r0 = r0.parse(r4);	 Catch:{ ParseException -> 0x001b }
        r0 = r1.format(r0);	 Catch:{ ParseException -> 0x001b }
        r4 = r0;
    L_0x001b:
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jelsat.utils.Utils.bookingsCheckInCheckoutDateFormat(java.lang.String):java.lang.String");
    }

    public static java.util.Date bookingsCheckInCheckoutDateInDateFormat(java.lang.String r3) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
*/
        /*
        r0 = new java.text.SimpleDateFormat;
        r1 = "yyyy-MM-dd";
        r2 = java.util.Locale.ENGLISH;
        r0.<init>(r1, r2);
        r3 = r0.parse(r3);	 Catch:{ ParseException -> 0x000e }
        goto L_0x000f;
    L_0x000e:
        r3 = 0;
    L_0x000f:
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jelsat.utils.Utils.bookingsCheckInCheckoutDateInDateFormat(java.lang.String):java.util.Date");
    }

    public static java.lang.String bookingsDateFormat(java.lang.String r4) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
*/
        /*
        r0 = new java.text.SimpleDateFormat;
        r1 = "yyyy-MM-dd";
        r2 = java.util.Locale.ENGLISH;
        r0.<init>(r1, r2);
        r1 = new java.text.SimpleDateFormat;
        r2 = "dd-MM-yyyy";
        r3 = java.util.Locale.ENGLISH;
        r1.<init>(r2, r3);
        r0 = r0.parse(r4);	 Catch:{ ParseException -> 0x001b }
        r0 = r1.format(r0);	 Catch:{ ParseException -> 0x001b }
        r4 = r0;
    L_0x001b:
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jelsat.utils.Utils.bookingsDateFormat(java.lang.String):java.lang.String");
    }

    public static java.lang.String showTimeWithTwelveTimeStamp(java.lang.String r4) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
*/
        /*
        r0 = new java.text.SimpleDateFormat;
        r1 = "hh:mm:ss";
        r2 = java.util.Locale.ENGLISH;
        r0.<init>(r1, r2);
        r1 = new java.text.SimpleDateFormat;
        r2 = "hh:mm aa";
        r3 = java.util.Locale.ENGLISH;
        r1.<init>(r2, r3);
        r0 = r0.parse(r4);	 Catch:{ ParseException -> 0x001b }
        r0 = r1.format(r0);	 Catch:{ ParseException -> 0x001b }
        r4 = r0;
    L_0x001b:
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jelsat.utils.Utils.showTimeWithTwelveTimeStamp(java.lang.String):java.lang.String");
    }

    public static java.lang.String showDateWithTwelveHoursTimeStamp(java.lang.String r4) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
*/
        /*
        r0 = new java.text.SimpleDateFormat;
        r1 = "yyyy-MM-dd hh:mm:ss";
        r2 = java.util.Locale.ENGLISH;
        r0.<init>(r1, r2);
        r1 = new java.text.SimpleDateFormat;
        r2 = "dd-MM-yyyy hh:mm aa";
        r3 = java.util.Locale.ENGLISH;
        r1.<init>(r2, r3);
        r0 = r0.parse(r4);	 Catch:{ ParseException -> 0x001b }
        r0 = r1.format(r0);	 Catch:{ ParseException -> 0x001b }
        r4 = r0;
    L_0x001b:
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jelsat.utils.Utils.showDateWithTwelveHoursTimeStamp(java.lang.String):java.lang.String");
    }

    public static int getPixelsFromDPs(@NonNull Context context, int i) {
        return (int) TypedValue.applyDimension(1, (float) i, context.getResources().getDisplayMetrics());
    }

    public static int applyColor(@NonNull Context context, @ColorRes int i) {
        return ContextCompat.getColor(context, i);
    }

    public static boolean validateEmail(String str) {
        return Pattern.compile(StringConstants.REGEX_EMAIL).matcher(str).matches();
    }

    public static boolean isNumeric(String str) {
        return Pattern.compile(StringConstants.REGEX_NUMBER).matcher(str).matches();
    }

    public static void hideKeyboard(Context context) {
        View currentFocus = ((Activity) context).getCurrentFocus();
        if (currentFocus != null) {
            ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(currentFocus.getWindowToken(), 2);
        }
    }

    public static Date convertStringToDate(String str, String str2) {
        try {
            return new SimpleDateFormat(str, Locale.getDefault()).parse(str2);
        } catch (String str3) {
            str3.printStackTrace();
            return null;
        }
    }

    public static String convertDateToString(String str, Date date) {
        return new SimpleDateFormat(str, Locale.US).format(date);
    }

    public static String getPropertyAddressWithoutNumbers(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            for (String trim : str.split(",")) {
                for (String str2 : trim.trim().split("\\s+")) {
                    if (!isNumeric(str2.trim())) {
                        stringBuilder.append(str2);
                        stringBuilder.append(" ");
                    }
                }
                stringBuilder.append(",");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    public static boolean isValidPhoneNumber(Context context, String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str2) || !Patterns.PHONE.matcher(str2).matches()) {
            return false;
        }
        PhoneNumberUtil createInstance = PhoneNumberUtil.createInstance(context);
        try {
            str = createInstance.parse(str2, createInstance.getRegionCodeForCountryCode(Integer.parseInt(str)));
            str2 = createInstance.isValidNumber(str);
            if (str2 != null) {
                Log.e("num ---->", createInstance.format(str, PhoneNumberFormat.INTERNATIONAL));
            } else if (z) {
                Log.e("sda", Locale.getDefault().getLanguage());
                Toast.makeText(context, String.format("%s - %s", new Object[]{str, context.getString(R.string.home_not_valid_mobile_number_label)}), 1).show();
            }
            return str2;
        } catch (Context context2) {
            context2.printStackTrace();
            System.err.println(context2.toString());
            return false;
        }
    }

    public static int getScreenWidthInPixels(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        }
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeightInPixels(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        }
        return displayMetrics.heightPixels;
    }
}
