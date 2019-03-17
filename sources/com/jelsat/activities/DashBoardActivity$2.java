package com.jelsat.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView.OnNavigationItemReselectedListener;
import android.util.Log;
import android.view.MenuItem;

class DashBoardActivity$2 implements OnNavigationItemReselectedListener {
    final /* synthetic */ DashBoardActivity this$0;

    DashBoardActivity$2(DashBoardActivity dashBoardActivity) {
        this.this$0 = dashBoardActivity;
    }

    public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
        Log.e("DASH", menuItem.getTitle().toString());
    }
}
