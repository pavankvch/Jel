package com.jelsat.activities;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.Log;

class PropertyDetailActivity$4 extends OnScrollListener {
    final /* synthetic */ PropertyDetailActivity this$0;
    final /* synthetic */ LinearLayoutManager val$llm;

    PropertyDetailActivity$4(PropertyDetailActivity propertyDetailActivity, LinearLayoutManager linearLayoutManager) {
        this.this$0 = propertyDetailActivity;
        this.val$llm = linearLayoutManager;
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
        super.onScrollStateChanged(recyclerView, i);
        if (i != 1 && i == 0) {
            Log.e("123", String.valueOf(this.val$llm.findFirstVisibleItemPosition()));
            if (PropertyDetailActivity.access$000(this.this$0) != null && this.this$0.noOfPropertyImagesCount != null) {
                this.this$0.noOfPropertyImagesCount.setText(String.format("%s/%s", new Object[]{Integer.valueOf(i + 1), Integer.valueOf(PropertyDetailActivity.access$200(this.this$0).size())}));
            }
        }
    }

    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        super.onScrolled(recyclerView, i, i2);
    }
}
