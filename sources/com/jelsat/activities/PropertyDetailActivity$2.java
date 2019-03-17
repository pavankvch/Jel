package com.jelsat.activities;

import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;

class PropertyDetailActivity$2 implements OnRefreshListener {
    final /* synthetic */ PropertyDetailActivity this$0;

    PropertyDetailActivity$2(PropertyDetailActivity propertyDetailActivity) {
        this.this$0 = propertyDetailActivity;
    }

    public void onRefresh() {
        if (PropertyDetailActivity.access$000(this.this$0) != null) {
            PropertyDetailActivity.access$100(this.this$0, PropertyDetailActivity.access$000(this.this$0).getPropertyId(), false);
        }
    }
}
