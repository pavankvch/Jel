package com.jelsat.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import com.jelsat.R;

class DashBoardProfileFragment$1 implements Runnable {
    final /* synthetic */ DashBoardProfileFragment this$0;
    final /* synthetic */ Fragment val$fragment;

    DashBoardProfileFragment$1(DashBoardProfileFragment dashBoardProfileFragment, Fragment fragment) {
        this.this$0 = dashBoardProfileFragment;
        this.val$fragment = fragment;
    }

    public void run() {
        if (this.val$fragment != null && this.this$0.isAdded()) {
            FragmentTransaction beginTransaction = this.this$0.getChildFragmentManager().beginTransaction();
            beginTransaction.replace(R.id.profile_section_fragment_container, this.val$fragment);
            beginTransaction.setCustomAnimations(17432576, 17432577);
            beginTransaction.commitAllowingStateLoss();
        }
    }
}
