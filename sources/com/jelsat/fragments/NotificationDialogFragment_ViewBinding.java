package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.customclasses.CustomTextView;
import com.jelsat.widgets.FancyButton;
import de.hdodenhof.circleimageview.CircleImageView;

public class NotificationDialogFragment_ViewBinding implements Unbinder {
    private NotificationDialogFragment target;
    private View view2131361892;

    @UiThread
    public NotificationDialogFragment_ViewBinding(final NotificationDialogFragment notificationDialogFragment, View view) {
        this.target = notificationDialogFragment;
        notificationDialogFragment.propertyImage = (CircleImageView) Utils.findRequiredViewAsType(view, R.id.property_image, "field 'propertyImage'", CircleImageView.class);
        notificationDialogFragment.propertyName = (TextView) Utils.findRequiredViewAsType(view, R.id.property_name_TV, "field 'propertyName'", TextView.class);
        notificationDialogFragment.propertyStatus = (TextView) Utils.findRequiredViewAsType(view, R.id.property_status, "field 'propertyStatus'", TextView.class);
        notificationDialogFragment.hostName = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_hostname, "field 'hostName'", TextView.class);
        notificationDialogFragment.propertyCheckInDate = (TextView) Utils.findRequiredViewAsType(view, R.id.property_check_in_date, "field 'propertyCheckInDate'", TextView.class);
        notificationDialogFragment.proeprtyCheckOutDate = (TextView) Utils.findRequiredViewAsType(view, R.id.property_check_out_date, "field 'proeprtyCheckOutDate'", TextView.class);
        notificationDialogFragment.noOfNights = (FancyButton) Utils.findRequiredViewAsType(view, R.id.tv_nights_numbers, "field 'noOfNights'", FancyButton.class);
        notificationDialogFragment.noOfGuests = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.noOfGuests, "field 'noOfGuests'", CustomTextView.class);
        notificationDialogFragment.bookingId = (TextView) Utils.findRequiredViewAsType(view, R.id.booking_id, "field 'bookingId'", TextView.class);
        notificationDialogFragment.datesLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.dates_layout, "field 'datesLayout'", LinearLayout.class);
        notificationDialogFragment.invisibleDatesLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.invisible_dates_layout, "field 'invisibleDatesLayout'", LinearLayout.class);
        notificationDialogFragment.view1 = Utils.findRequiredView(view, R.id.view1, "field 'view1'");
        notificationDialogFragment.view2 = Utils.findRequiredView(view, R.id.view2, "field 'view2'");
        view = Utils.findRequiredView(view, R.id.back_arrow_IV, "method 'clickClose'");
        this.view2131361892 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                notificationDialogFragment.clickClose();
            }
        });
    }

    @CallSuper
    public void unbind() {
        NotificationDialogFragment notificationDialogFragment = this.target;
        if (notificationDialogFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        notificationDialogFragment.propertyImage = null;
        notificationDialogFragment.propertyName = null;
        notificationDialogFragment.propertyStatus = null;
        notificationDialogFragment.hostName = null;
        notificationDialogFragment.propertyCheckInDate = null;
        notificationDialogFragment.proeprtyCheckOutDate = null;
        notificationDialogFragment.noOfNights = null;
        notificationDialogFragment.noOfGuests = null;
        notificationDialogFragment.bookingId = null;
        notificationDialogFragment.datesLayout = null;
        notificationDialogFragment.invisibleDatesLayout = null;
        notificationDialogFragment.view1 = null;
        notificationDialogFragment.view2 = null;
        this.view2131361892.setOnClickListener(null);
        this.view2131361892 = null;
    }
}
