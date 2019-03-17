package com.jelsat.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.view.MenuItem;
import com.jelsat.R;

class DashBoardActivity$1 implements OnNavigationItemSelectedListener {
    final /* synthetic */ DashBoardActivity this$0;

    DashBoardActivity$1(DashBoardActivity dashBoardActivity) {
        this.this$0 = dashBoardActivity;
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.dash_board_bookings:
                DashBoardActivity.access$002(this.this$0, 2);
                DashBoardActivity.access$102(this.this$0, "tab3");
                break;
            case R.id.dash_board_home:
                DashBoardActivity.access$002(this.this$0, 0);
                DashBoardActivity.access$102(this.this$0, "tab1");
                break;
            case R.id.dash_board_host_bookings:
                DashBoardActivity.access$002(this.this$0, 0);
                DashBoardActivity.access$102(this.this$0, "tab1");
                break;
            case R.id.dash_board_host_inbox:
                DashBoardActivity.access$002(this.this$0, 1);
                DashBoardActivity.access$102(this.this$0, "tab2");
                break;
            case R.id.dash_board_host_listing:
                DashBoardActivity.access$002(this.this$0, 2);
                DashBoardActivity.access$102(this.this$0, "tab3");
                break;
            case R.id.dash_board_host_payments:
                DashBoardActivity.access$002(this.this$0, 3);
                DashBoardActivity.access$102(this.this$0, "tab4");
                break;
            case R.id.dash_board_host_profile:
                DashBoardActivity.access$002(this.this$0, 4);
                DashBoardActivity.access$102(this.this$0, "tab5");
                break;
            case R.id.dash_board_inbox:
                DashBoardActivity.access$002(this.this$0, 3);
                DashBoardActivity.access$102(this.this$0, "tab4");
                break;
            case R.id.dash_board_profile:
                DashBoardActivity.access$002(this.this$0, 4);
                DashBoardActivity.access$102(this.this$0, "tab5");
                break;
            case R.id.dash_board_saved:
                DashBoardActivity.access$002(this.this$0, 1);
                DashBoardActivity.access$102(this.this$0, "tab2");
                break;
            default:
                DashBoardActivity.access$002(this.this$0, 0);
                break;
        }
        if (!DashBoardActivity.access$200(this.this$0)) {
            if (DashBoardActivity.access$000(this.this$0) != 0) {
                DashBoardActivity.access$002(this.this$0, 0);
                DashBoardActivity.access$102(this.this$0, "tab1");
                DashBoardActivity.access$400(this.this$0, HomeActivity.class);
                this.this$0.finish();
                return false;
            }
        }
        menuItem.setChecked(true);
        DashBoardActivity.access$300(this.this$0, false);
        return false;
    }
}
