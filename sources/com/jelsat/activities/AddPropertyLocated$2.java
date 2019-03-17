package com.jelsat.activities;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

class AddPropertyLocated$2 implements OnClickListener {
    final /* synthetic */ AddPropertyLocated this$0;

    AddPropertyLocated$2(AddPropertyLocated addPropertyLocated) {
        this.this$0 = addPropertyLocated;
    }

    public void onClick(View view) {
        this.this$0.checkClick = "nextbutton";
        if (this.this$0.forSkippingAddress != null) {
            view = new Intent(this.this$0, AddYourPropertySteps.class);
            view.setFlags(67108864);
            view.putExtras(this.this$0.extraBundle);
            view.putExtra("checkIntent", this.this$0.checkIntent);
            view.putExtra("FromActivity", "fromStep2");
            this.this$0.startActivity(view);
            return;
        }
        AddPropertyLocated.access$000(this.this$0);
    }
}
