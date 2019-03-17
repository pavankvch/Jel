package com.jelsat.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import com.jelsat.R;

class SearchPropertyActivity$2 implements Runnable {
    final /* synthetic */ SearchPropertyActivity this$0;
    final /* synthetic */ Fragment val$fragment;

    SearchPropertyActivity$2(SearchPropertyActivity searchPropertyActivity, Fragment fragment) {
        this.this$0 = searchPropertyActivity;
        this.val$fragment = fragment;
    }

    public void run() {
        FragmentTransaction beginTransaction = this.this$0.getSupportFragmentManager().beginTransaction();
        beginTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        beginTransaction.replace(R.id.search_fragment_container, this.val$fragment);
        beginTransaction.commitAllowingStateLoss();
    }
}
