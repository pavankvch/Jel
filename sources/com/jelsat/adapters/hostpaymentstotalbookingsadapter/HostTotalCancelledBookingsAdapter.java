package com.jelsat.adapters.hostpaymentstotalbookingsadapter;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.ColorRes;
import android.support.annotation.UiThread;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.data.payments.totalbookings.HostBookingData;
import com.jelsat.R;
import com.jelsat.adapters.Inboxmessagesadapter.SectionRecyclerViewAdapter;
import com.jelsat.customclasses.CustomTextView;
import com.jelsat.widgets.FancyButton;
import java.util.List;
import java.util.Locale;

public class HostTotalCancelledBookingsAdapter extends SectionRecyclerViewAdapter<BookingsSectionModel, HostBookingData, SectionViewHolder, ChildViewHolder> {
    Context context;

    public class ChildViewHolder extends ViewHolder {
        @BindView(2131362706)
        TextView bookedDateTv;
        @BindView(2131362709)
        TextView cancelledDateTv;
        @BindView(2131362455)
        TextView checkinDateTv;
        @BindView(2131362456)
        TextView checkoutDateTv;
        @BindView(2131362731)
        TextView guestNameTV;
        @BindView(2131362732)
        TextView guestsCountTv;
        @BindView(2131362249)
        ImageView img_property;
        View itemView;
        @BindView(2131362747)
        FancyButton nightsCountTv;
        @BindView(2131362761)
        TextView propertyNameTv;
        @BindView(2131362759)
        CustomTextView tv_property_booking_type;

        public ChildViewHolder(View view) {
            super(view);
            this.itemView = view;
            ButterKnife.bind(this, view);
        }
    }

    public class ChildViewHolder_ViewBinding implements Unbinder {
        private ChildViewHolder target;

        @UiThread
        public ChildViewHolder_ViewBinding(ChildViewHolder childViewHolder, View view) {
            this.target = childViewHolder;
            childViewHolder.propertyNameTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_property_name, "field 'propertyNameTv'", TextView.class);
            childViewHolder.guestNameTV = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_guest_name, "field 'guestNameTV'", TextView.class);
            childViewHolder.cancelledDateTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_cancelled_date, "field 'cancelledDateTv'", TextView.class);
            childViewHolder.bookedDateTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_booked_date, "field 'bookedDateTv'", TextView.class);
            childViewHolder.checkinDateTv = (TextView) Utils.findRequiredViewAsType(view, R.id.property_check_in_date, "field 'checkinDateTv'", TextView.class);
            childViewHolder.checkoutDateTv = (TextView) Utils.findRequiredViewAsType(view, R.id.property_check_out_date, "field 'checkoutDateTv'", TextView.class);
            childViewHolder.tv_property_booking_type = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.tv_property_booking_type, "field 'tv_property_booking_type'", CustomTextView.class);
            childViewHolder.guestsCountTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_guests_count, "field 'guestsCountTv'", TextView.class);
            childViewHolder.nightsCountTv = (FancyButton) Utils.findRequiredViewAsType(view, R.id.tv_nights_numbers, "field 'nightsCountTv'", FancyButton.class);
            childViewHolder.img_property = (ImageView) Utils.findRequiredViewAsType(view, R.id.img_property, "field 'img_property'", ImageView.class);
        }

        @CallSuper
        public void unbind() {
            ChildViewHolder childViewHolder = this.target;
            if (childViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            childViewHolder.propertyNameTv = null;
            childViewHolder.guestNameTV = null;
            childViewHolder.cancelledDateTv = null;
            childViewHolder.bookedDateTv = null;
            childViewHolder.checkinDateTv = null;
            childViewHolder.checkoutDateTv = null;
            childViewHolder.tv_property_booking_type = null;
            childViewHolder.guestsCountTv = null;
            childViewHolder.nightsCountTv = null;
            childViewHolder.img_property = null;
        }
    }

    public class SectionViewHolder extends ViewHolder {
        View itemView;
        @BindView(2131362574)
        TextView section_label;

        public SectionViewHolder(View view) {
            super(view);
            this.itemView = view;
            ButterKnife.bind(this, view);
        }
    }

    public class SectionViewHolder_ViewBinding implements Unbinder {
        private SectionViewHolder target;

        @UiThread
        public SectionViewHolder_ViewBinding(SectionViewHolder sectionViewHolder, View view) {
            this.target = sectionViewHolder;
            sectionViewHolder.section_label = (TextView) Utils.findRequiredViewAsType(view, R.id.section_label, "field 'section_label'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            SectionViewHolder sectionViewHolder = this.target;
            if (sectionViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            sectionViewHolder.section_label = null;
        }
    }

    public HostTotalCancelledBookingsAdapter(Context context, List<BookingsSectionModel> list) {
        super(context, list);
        this.context = context;
    }

    public SectionViewHolder onCreateSectionViewHolder(ViewGroup viewGroup, int i) {
        return new SectionViewHolder(LayoutInflater.from(this.context).inflate(R.layout.host_total_bookings_date_section, viewGroup, false));
    }

    public ChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup, int i) {
        return new ChildViewHolder(LayoutInflater.from(this.context).inflate(R.layout.host_cancelled_bookings_single_view, viewGroup, false));
    }

    public void onBindSectionViewHolder(SectionViewHolder sectionViewHolder, int i, BookingsSectionModel bookingsSectionModel) {
        sectionViewHolder.section_label.setText(bookingsSectionModel.getSectionLabel());
    }

    public void onBindChildViewHolder(com.jelsat.adapters.hostpaymentstotalbookingsadapter.HostTotalCancelledBookingsAdapter.ChildViewHolder r5, int r6, int r7, com.data.payments.totalbookings.HostBookingData r8) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
*/
        /*
        r4 = this;
        r6 = r5.propertyNameTv;
        r7 = new java.lang.StringBuilder;
        r7.<init>();
        r0 = r4.context;
        r1 = 2131820956; // 0x7f11019c float:1.9274642E38 double:1.0532594974E-314;
        r0 = r0.getString(r1);
        r7.append(r0);
        r0 = r8.getProperty_id();
        r7.append(r0);
        r0 = " (";
        r7.append(r0);
        r0 = r8.getName();
        r7.append(r0);
        r0 = ")";
        r7.append(r0);
        r7 = r7.toString();
        r6.setText(r7);
        r6 = r5.bookedDateTv;
        r7 = new java.lang.StringBuilder;
        r7.<init>();
        r0 = r4.context;
        r0 = r0.getResources();
        r1 = 2131821411; // 0x7f110363 float:1.9275564E38 double:1.053259722E-314;
        r0 = r0.getString(r1);
        r7.append(r0);
        r0 = " ";
        r7.append(r0);
        r0 = r8.getBookedOn();
        r0 = com.jelsat.utils.Utils.bookingsDateFormat(r0);
        r7.append(r0);
        r7 = r7.toString();
        r6.setText(r7);
        r6 = r5.guestNameTV;
        r7 = r8.getGuestName();
        r6.setText(r7);
        r6 = r5.checkinDateTv;
        r7 = r8.getStartDate();
        r7 = com.jelsat.utils.Utils.bookingsCheckInCheckoutDateFormat(r7);
        r6.setText(r7);
        r6 = r5.checkoutDateTv;
        r7 = r8.getEndDate();
        r7 = com.jelsat.utils.Utils.bookingsCheckInCheckoutDateFormat(r7);
        r6.setText(r7);
        r6 = r5.nightsCountTv;
        r7 = new java.lang.StringBuilder;
        r7.<init>();
        r0 = r8.getNights();
        r7.append(r0);
        r0 = "N";
        r7.append(r0);
        r7 = r7.toString();
        r6.setText(r7);
        r6 = r5.guestsCountTv;
        r7 = new java.lang.StringBuilder;
        r0 = "%s ";
        r7.<init>(r0);
        r0 = r4.context;
        r1 = 2131821415; // 0x7f110367 float:1.9275573E38 double:1.053259724E-314;
        r0 = r0.getString(r1);
        r7.append(r0);
        r7 = r7.toString();
        r0 = 1;
        r1 = new java.lang.Object[r0];
        r2 = r8.getTotalGuests();
        r2 = java.lang.Integer.valueOf(r2);
        r3 = 0;
        r1[r3] = r2;
        r7 = java.lang.String.format(r7, r1);
        r6.setText(r7);
        r6 = r5.cancelledDateTv;
        r7 = new java.lang.StringBuilder;
        r7.<init>();
        r1 = r4.context;
        r2 = 2131821414; // 0x7f110366 float:1.927557E38 double:1.0532597237E-314;
        r1 = r1.getString(r2);
        r7.append(r1);
        r1 = " %s ";
        r7.append(r1);
        r7 = r7.toString();
        r0 = new java.lang.Object[r0];
        r1 = r8.getCanceledDate();
        r1 = com.jelsat.utils.Utils.showDateWithTwelveHoursTimeStamp(r1);
        r0[r3] = r1;
        r7 = java.lang.String.format(r7, r0);
        r6.setText(r7);
        r6 = r4.context;
        r6 = com.jelsat.utils.GlideApp.with(r6);
        r7 = r8.getImage();
        r6 = r6.load(r7);
        r7 = 2131230886; // 0x7f0800a6 float:1.8077837E38 double:1.052967964E-314;
        r6 = r6.placeholder(r7);
        r0 = new com.bumptech.glide.request.RequestOptions;
        r0.<init>();
        r1 = new com.bumptech.glide.load.resource.bitmap.RoundedCorners;
        r2 = 8;
        r1.<init>(r2);
        r0 = r0.transform(r1);
        r6 = r6.apply(r0);
        r6 = r6.error(r7);
        r7 = r5.img_property;
        r6.into(r7);
        r6 = r8.getBookingType();	 Catch:{ Exception -> 0x0135 }
        r6 = java.lang.Integer.parseInt(r6);	 Catch:{ Exception -> 0x0135 }
        goto L_0x0136;
    L_0x0135:
        r6 = 0;
    L_0x0136:
        r4.setBookingTypeImage(r5, r6);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jelsat.adapters.hostpaymentstotalbookingsadapter.HostTotalCancelledBookingsAdapter.onBindChildViewHolder(com.jelsat.adapters.hostpaymentstotalbookingsadapter.HostTotalCancelledBookingsAdapter$ChildViewHolder, int, int, com.data.payments.totalbookings.HostBookingData):void");
    }

    private void setBookingTypeImage(ChildViewHolder childViewHolder, int i) {
        switch (i) {
            case 0:
                if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar") != 0) {
                    childViewHolder.tv_property_booking_type.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_24hours_booking, 0);
                } else {
                    childViewHolder.tv_property_booking_type.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_24hours_booking, 0, 0, 0);
                }
                childViewHolder.tv_property_booking_type.setText(this.context.getString(R.string.total_cancelations_booking_type_regular));
                childViewHolder.tv_property_booking_type.setTextColor(applyColor(R.color.light_text_color));
                return;
            case 1:
                if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar") != 0) {
                    childViewHolder.tv_property_booking_type.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_instant_booking, 0);
                } else {
                    childViewHolder.tv_property_booking_type.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_instant_booking, 0, 0, 0);
                }
                childViewHolder.tv_property_booking_type.setText(this.context.getString(R.string.total_cancelations_booking_type_instant));
                childViewHolder.tv_property_booking_type.setTextColor(applyColor(R.color.light_text_color));
                break;
            default:
                break;
        }
    }

    public int applyColor(@ColorRes int i) {
        return ContextCompat.getColor(this.context, i);
    }
}
