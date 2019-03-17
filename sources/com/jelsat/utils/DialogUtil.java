package com.jelsat.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;

public class DialogUtil {
    private static final String TAG = "DialogUtil";
    private static boolean flag = false;
    private AlertDialog alertDialog;
    private Builder builder;
    private Context context;
    private Activity mActivity;

    public DialogUtil(Context context) {
        this.context = context;
        this.builder = new Builder(context);
    }

    public void showOkDialog(java.lang.String r3) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
*/
        /*
        r2 = this;
        if (r3 != 0) goto L_0x0004;
    L_0x0002:
        r3 = "A problem has occurred, please check connection or try again later";
    L_0x0004:
        r0 = r2.builder;	 Catch:{ Exception -> 0x0039 }
        r1 = 2131689473; // 0x7f0f0001 float:1.9007962E38 double:1.053194536E-314;	 Catch:{ Exception -> 0x0039 }
        r0.setIcon(r1);	 Catch:{ Exception -> 0x0039 }
        r0 = r2.builder;	 Catch:{ Exception -> 0x0039 }
        r1 = "Jelsat";	 Catch:{ Exception -> 0x0039 }
        r0.setTitle(r1);	 Catch:{ Exception -> 0x0039 }
        r0 = r2.builder;	 Catch:{ Exception -> 0x0039 }
        r0.setMessage(r3);	 Catch:{ Exception -> 0x0039 }
        r3 = r2.builder;	 Catch:{ Exception -> 0x0039 }
        r0 = 17039370; // 0x104000a float:2.42446E-38 double:8.4185673E-317;	 Catch:{ Exception -> 0x0039 }
        r1 = new com.jelsat.utils.DialogUtil$1;	 Catch:{ Exception -> 0x0039 }
        r1.<init>();	 Catch:{ Exception -> 0x0039 }
        r3.setPositiveButton(r0, r1);	 Catch:{ Exception -> 0x0039 }
        r3 = r2.builder;	 Catch:{ Exception -> 0x0039 }
        r3 = r3.create();	 Catch:{ Exception -> 0x0039 }
        r2.alertDialog = r3;	 Catch:{ Exception -> 0x0039 }
        r3 = r2.alertDialog;	 Catch:{ Exception -> 0x0039 }
        r0 = 0;	 Catch:{ Exception -> 0x0039 }
        r3.setCanceledOnTouchOutside(r0);	 Catch:{ Exception -> 0x0039 }
        r3 = r2.alertDialog;	 Catch:{ Exception -> 0x0039 }
        r3.show();	 Catch:{ Exception -> 0x0039 }
        return;
    L_0x0039:
        r3 = TAG;
        r0 = "---alertExecption--";
        android.util.Log.e(r3, r0);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jelsat.utils.DialogUtil.showOkDialog(java.lang.String):void");
    }

    public boolean isShowing() {
        return this.alertDialog.isShowing();
    }

    public void dismiss() {
        this.alertDialog.dismiss();
    }
}
