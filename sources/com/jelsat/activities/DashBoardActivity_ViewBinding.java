package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.jelsat.R;

public class DashBoardActivity_ViewBinding implements Unbinder {
    private DashBoardActivity target;

    @UiThread
    public DashBoardActivity_ViewBinding(DashBoardActivity dashBoardActivity) {
        this(dashBoardActivity, dashBoardActivity.getWindow().getDecorView());
    }

    @UiThread
    public DashBoardActivity_ViewBinding(DashBoardActivity dashBoardActivity, View view) {
        this.target = dashBoardActivity;
        dashBoardActivity.dashBoardBottomNavigation = (BottomNavigationViewEx) Utils.findRequiredViewAsType(view, R.id.dash_board_bottom_navigation, "field 'dashBoardBottomNavigation'", BottomNavigationViewEx.class);
    }

    @CallSuper
    public void unbind() {
        DashBoardActivity dashBoardActivity = this.target;
        if (dashBoardActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        dashBoardActivity.dashBoardBottomNavigation = null;
    }
}
