package com.jelsat.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import com.jelsat.R;

class DashBoardActivity$3 implements Runnable {
    final /* synthetic */ DashBoardActivity this$0;
    final /* synthetic */ boolean val$isFromSettingsScreen;

    DashBoardActivity$3(DashBoardActivity dashBoardActivity, boolean z) {
        this.this$0 = dashBoardActivity;
        this.val$isFromSettingsScreen = z;
    }

    public void run() {
        Fragment access$500 = DashBoardActivity.access$500(this.this$0, this.val$isFromSettingsScreen);
        FragmentTransaction beginTransaction = this.this$0.getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.main_container, access$500, DashBoardActivity.access$100(this.this$0));
        beginTransaction.commitAllowingStateLoss();
    }
}
