package com.jelsat.utils;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import com.jelsat.R;

public class AlertDialogUtil {
    private Context mContext = null;
    private AlertDialog mDialog = null;

    public AlertDialogUtil(Context context) {
        this.mContext = context;
    }

    public void showDialogNeutral(String str, String str2, OnClickListener onClickListener) {
        if (str == null) {
            str = "A problem has occured, please check connection or try again later";
        }
        this.mDialog = new Builder(this.mContext).setMessage(str).setNeutralButton(str2, onClickListener).setCancelable(false).show();
        this.mDialog.setCanceledOnTouchOutside(false);
    }

    public void showAlertDialog(String str, String str2, String str3, String str4, OnClickListener onClickListener, OnClickListener onClickListener2) {
        if (str2 == null) {
            str2 = "A problem has occured, please check connection or try again later";
        }
        this.mDialog = new Builder(this.mContext).setTitle(str).setMessage(str2).setIcon(R.mipmap.ic_app_icon).setPositiveButton(str3, onClickListener).setCancelable(false).setNegativeButton(str4, onClickListener2).show();
        this.mDialog.setCanceledOnTouchOutside(false);
    }

    public void showAlertDialog(String str, String str2, OnClickListener onClickListener) {
        if (str == null) {
            str = "A problem has occured, please check connection or try again later";
        }
        try {
            this.mDialog = new Builder(this.mContext).setTitle(R.string.app_name).setIcon(R.mipmap.ic_app_icon).setCancelable(false).setMessage(str).setPositiveButton(str2, onClickListener).show();
            this.mDialog.setCanceledOnTouchOutside(false);
        } catch (String str3) {
            str3.printStackTrace();
        }
    }

    public void dismissDialog() {
        if (this.mDialog != null && this.mDialog.isShowing()) {
            this.mDialog.dismiss();
            this.mDialog = null;
        }
    }
}
