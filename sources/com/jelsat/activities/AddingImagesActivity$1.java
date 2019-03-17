package com.jelsat.activities;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class AddingImagesActivity$1 implements OnClickListener {
    final /* synthetic */ AddingImagesActivity this$0;
    final /* synthetic */ int val$position;

    AddingImagesActivity$1(AddingImagesActivity addingImagesActivity, int i) {
        this.this$0 = addingImagesActivity;
        this.val$position = i;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        AddingImagesActivity.access$000(this.this$0).dismissDialog();
        AddingImagesActivity.access$100(this.this$0, this.val$position);
    }
}
