package com.jelsat.utils;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog.Builder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Window;
import android.widget.TextView;
import com.jelsat.R;

public class ProgressDialogUtil {
    private ProgressDialogUtil() {
        throw new AssertionError();
    }

    public static ProgressDialog showLoading(Context context) {
        return showLoading(context, "", false);
    }

    public static ProgressDialog showLoading(Context context, boolean z) {
        return showLoading(context, "", z);
    }

    public static ProgressDialog showLoading(Context context, String str) {
        return showLoading(context, str, false);
    }

    public static ProgressDialog showLoading(Context context, @StringRes int i) {
        return showLoading(context, context.getString(i), false);
    }

    public static ProgressDialog showLoading(Context context, @StringRes int i, boolean z) {
        return showLoading(context, context.getString(i), z);
    }

    @SuppressLint({"InflateParams"})
    private static ProgressDialog showLoading(Context context, String str, boolean z) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        Window window = progressDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        context = LayoutInflater.from(context).inflate(R.layout.view_loading, null);
        TextView textView = (TextView) context.findViewById(R.id.loading_message);
        if (TextUtils.isEmpty(str)) {
            textView.setVisibility(8);
        } else {
            textView.setText(str);
            textView.setVisibility(0);
        }
        progressDialog.setContentView(context);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(z);
        progressDialog.setCanceledOnTouchOutside(z);
        return progressDialog;
    }

    public static Builder getAlertDialog(Context context, String str) {
        return getAlertDialog(context, "", str, false);
    }

    public static Builder getAlertDialog(Context context, @StringRes int i) {
        return getAlertDialog(context, "", context.getString(i), false);
    }

    public static Builder getAlertDialog(Context context, String str, boolean z) {
        return getAlertDialog(context, "", str, z);
    }

    public static Builder getAlertDialog(Context context, @StringRes int i, boolean z) {
        return getAlertDialog(context, "", context.getString(i), z);
    }

    public static Builder getAlertDialog(Context context, @StringRes int i, @StringRes int i2, boolean z) {
        return getAlertDialog(context, context.getString(i), context.getString(i2), z);
    }

    private static Builder getAlertDialog(Context context, String str, String str2, boolean z) {
        Builder builder = new Builder(context);
        if (TextUtils.isEmpty(str) == null) {
            builder.setTitle(str);
        }
        builder.setMessage(str2);
        builder.setCancelable(z);
        return builder;
    }
}
