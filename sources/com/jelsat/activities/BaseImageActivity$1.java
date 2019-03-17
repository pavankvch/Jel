package com.jelsat.activities;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;

class BaseImageActivity$1 implements OnClickListener {
    final /* synthetic */ BaseImageActivity this$0;

    BaseImageActivity$1(BaseImageActivity baseImageActivity) {
        this.this$0 = baseImageActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface = new Intent();
        dialogInterface.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        dialogInterface.setData(Uri.fromParts("package", this.this$0.getPackageName(), null));
        this.this$0.startActivity(dialogInterface);
    }
}
