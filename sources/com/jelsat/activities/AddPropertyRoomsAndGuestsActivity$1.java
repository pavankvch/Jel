package com.jelsat.activities;

import android.view.View;
import android.view.View.OnClickListener;
import com.jelsat.R;
import com.jelsat.fragments.BottomSheetFragment;
import com.jelsat.utils.AddPropertyRoomsAndGuestSUtil;

class AddPropertyRoomsAndGuestsActivity$1 implements OnClickListener {
    final /* synthetic */ AddPropertyRoomsAndGuestsActivity this$0;

    AddPropertyRoomsAndGuestsActivity$1(AddPropertyRoomsAndGuestsActivity addPropertyRoomsAndGuestsActivity) {
        this.this$0 = addPropertyRoomsAndGuestsActivity;
    }

    public void onClick(View view) {
        if (AddPropertyRoomsAndGuestsActivity.access$000(this.this$0) != null) {
            view = new AddPropertyRoomsAndGuestSUtil(17);
            BottomSheetFragment newInstance = BottomSheetFragment.newInstance(AddPropertyRoomsAndGuestsActivity.access$100(this.this$0, AddPropertyRoomsAndGuestsActivity.access$000(this.this$0).getMaxGuests(), this.this$0.getString(R.string.guests)), this.this$0.getString(R.string.max_no_of_guests));
            newInstance.setData(view);
            newInstance.show(this.this$0.getSupportFragmentManager(), "max guests");
        }
    }
}
