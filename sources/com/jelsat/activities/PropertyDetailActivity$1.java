package com.jelsat.activities;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.AppBarLayout.OnOffsetChangedListener;

class PropertyDetailActivity$1 implements OnOffsetChangedListener {
    final /* synthetic */ PropertyDetailActivity this$0;
    final /* synthetic */ AppBarLayout val$abl;

    PropertyDetailActivity$1(PropertyDetailActivity propertyDetailActivity, AppBarLayout appBarLayout) {
        this.this$0 = propertyDetailActivity;
        this.val$abl = appBarLayout;
    }

    public void onOffsetChanged(final AppBarLayout appBarLayout, final int i) {
        this.val$abl.post(new Runnable() {
            public void run() {
                if (Math.abs(i) - appBarLayout.getTotalScrollRange() != 0) {
                    PropertyDetailActivity$1.this.this$0.toolbar_property_name.setVisibility(4);
                    PropertyDetailActivity$1.this.this$0.toolbar_property_price.setVisibility(4);
                } else if (!(PropertyDetailActivity$1.this.this$0.toolbar_property_name.getText() == null || PropertyDetailActivity$1.this.this$0.toolbar_property_price.getText() == null)) {
                    PropertyDetailActivity$1.this.this$0.toolbar_property_name.setVisibility(0);
                    PropertyDetailActivity$1.this.this$0.toolbar_property_price.setVisibility(0);
                }
            }
        });
    }
}
