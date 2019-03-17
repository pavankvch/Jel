package com.jelsat.fragments;

import android.os.Handler;

class SearchPropertyListMapFragment$3 implements Runnable {
    final /* synthetic */ SearchPropertyListMapFragment this$0;

    SearchPropertyListMapFragment$3(SearchPropertyListMapFragment searchPropertyListMapFragment) {
        this.this$0 = searchPropertyListMapFragment;
    }

    public void run() {
        this.this$0.showProgressDialog("");
        SearchPropertyListMapFragment.access$100(this.this$0).loadMarkers(SearchPropertyListMapFragment.access$000(this.this$0));
        new Handler().postDelayed(new Runnable() {
            public void run() {
                SearchPropertyListMapFragment$3.this.this$0.dismissProgress();
            }
        }, 3000);
    }
}
