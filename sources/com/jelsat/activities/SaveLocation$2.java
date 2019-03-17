package com.jelsat.activities;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;

class SaveLocation$2 implements OnClickListener {
    final /* synthetic */ SaveLocation this$0;

    SaveLocation$2(SaveLocation saveLocation) {
        this.this$0 = saveLocation;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface = new Intent();
        dialogInterface.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        dialogInterface.setData(Uri.fromParts("package", this.this$0.getActivityInstance().getPackageName(), null));
        this.this$0.startActivity(dialogInterface);
    }
}
