package com.jelsat.activities;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.jelsat.constants.StringConstants;

class AddPropertyLocated$1 implements OnClickListener {
    final /* synthetic */ AddPropertyLocated this$0;

    AddPropertyLocated$1(AddPropertyLocated addPropertyLocated) {
        this.this$0 = addPropertyLocated;
    }

    public void onClick(View view) {
        this.this$0.checkClick = "saveAndExit";
        if (this.this$0.forSkippingAddress != null) {
            view = new Intent(this.this$0, DashBoardActivity.class);
            view.addFlags(67108864);
            view.putExtra(StringConstants.FROM_PUBLISH_PAGE, true);
            this.this$0.startActivity(view);
            return;
        }
        AddPropertyLocated.access$000(this.this$0);
    }
}
