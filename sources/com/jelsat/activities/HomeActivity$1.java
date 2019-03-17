package com.jelsat.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import com.jelsat.R;

class HomeActivity$1 implements Runnable {
    final /* synthetic */ HomeActivity this$0;
    final /* synthetic */ Fragment val$fragment;

    HomeActivity$1(HomeActivity homeActivity, Fragment fragment) {
        this.this$0 = homeActivity;
        this.val$fragment = fragment;
    }

    public void run() {
        FragmentTransaction beginTransaction = this.this$0.getSupportFragmentManager().beginTransaction();
        beginTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        beginTransaction.replace(R.id.user_login_fragment_container, this.val$fragment);
        beginTransaction.commitAllowingStateLoss();
    }
}
