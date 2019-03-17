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
import android.widget.ImageButton;
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

public class HostTotalEarningsAdapter extends SectionRecyclerViewAdapter<BookingsSectionModel, HostBookingData, SectionViewHolder, ChildViewHolder> {
    Context context;
    private ListItemClickListener listener;
    private List<BookingsSectionModel> sectionModelArrayList;

    public interface ListItemClickListener {
        void clickOnViewBill(HostBookingData hostBookingData);
    }

    public class ChildViewHolder extends ViewHolder {
        @BindView(2131361807)
        ImageButton billgetails;
        @BindView(2131362249)
        ImageView img_property;
        View itemView;
        @BindView(2131362755)
        TextView propertyAmountTv;
        @BindView(2131362761)
        TextView propertyNameTv;
        @BindView(2131362706)
        TextView tv_booked_date;
        @BindView(2131362455)
        TextView tv_date_checkin;
        @BindView(2131362456)
        TextView tv_date_checkout;
        @BindView(2131362731)
        TextView tv_guest_name;
        @BindView(2131362732)
        TextView tv_guests_count;
        @BindView(2131362747)
        FancyButton tv_nights_numbers;
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
            childViewHolder.tv_guest_name = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_guest_name, "field 'tv_guest_name'", TextView.class);
            childViewHolder.tv_booked_date = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_booked_date, "field 'tv_booked_date'", TextView.class);
            childViewHolder.tv_date_checkin = (TextView) Utils.findRequiredViewAsType(view, R.id.property_check_in_date, "field 'tv_date_checkin'", TextView.class);
            childViewHolder.tv_date_checkout = (TextView) Utils.findRequiredViewAsType(view, R.id.property_check_out_date, "field 'tv_date_checkout'", TextView.class);
            childViewHolder.tv_property_booking_type = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.tv_property_booking_type, "field 'tv_property_booking_type'", CustomTextView.class);
            childViewHolder.tv_guests_count = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_guests_count, "field 'tv_guests_count'", TextView.class);
            childViewHolder.tv_nights_numbers = (FancyButton) Utils.findRequiredViewAsType(view, R.id.tv_nights_numbers, "field 'tv_nights_numbers'", FancyButton.class);
            childViewHolder.propertyAmountTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_property_amount, "field 'propertyAmountTv'", TextView.class);
            childViewHolder.img_property = (ImageView) Utils.findRequiredViewAsType(view, R.id.img_property, "field 'img_property'", ImageView.class);
            childViewHolder.billgetails = (ImageButton) Utils.findRequiredViewAsType(view, R.id.Iv_view_bill_preivous, "field 'billgetails'", ImageButton.class);
        }

        @CallSuper
        public void unbind() {
            ChildViewHolder childViewHolder = this.target;
            if (childViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            childViewHolder.propertyNameTv = null;
            childViewHolder.tv_guest_name = null;
            childViewHolder.tv_booked_date = null;
            childViewHolder.tv_date_checkin = null;
            childViewHolder.tv_date_checkout = null;
            childViewHolder.tv_property_booking_type = null;
            childViewHolder.tv_guests_count = null;
            childViewHolder.tv_nights_numbers = null;
            childViewHolder.propertyAmountTv = null;
            childViewHolder.img_property = null;
            childViewHolder.billgetails = null;
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

    public HostTotalEarningsAdapter(Context context, List<BookingsSectionModel> list, ListItemClickListener listItemClickListener) {
        super(context, list);
        this.sectionModelArrayList = list;
        this.context = context;
        this.listener = listItemClickListener;
    }

    public SectionViewHolder onCreateSectionViewHolder(ViewGroup viewGroup, int i) {
        return new SectionViewHolder(LayoutInflater.from(this.context).inflate(R.layout.host_total_bookings_date_section, viewGroup, false));
    }

    public ChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup, int i) {
        return new ChildViewHolder(LayoutInflater.from(this.context).inflate(R.layout.host_total_earnings_single_view, viewGroup, false));
    }

    public void onBindSectionViewHolder(SectionViewHolder sectionViewHolder, int i, BookingsSectionModel bookingsSectionModel) {
        sectionViewHolder.section_label.setText(bookingsSectionModel.getSectionLabel());
    }

    public void onBindChildViewHolder(com.jelsat.adapters.hostpaymentstotalbookingsadapter.HostTotalEarningsAdapter.ChildViewHolder r8, int r9, int r10, final com.data.payments.totalbookings.HostBookingData r11) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
*/
        /*
        r7 = this;
        r9 = r8.propertyNameTv;
        r10 = new java.lang.StringBuilder;
        r10.<init>();
        r0 = r7.context;
        r1 = 2131820956; // 0x7f11019c float:1.9274642E38 double:1.0532594974E-314;
        r0 = r0.getString(r1);
        r10.append(r0);
        r0 = r11.getProperty_id();
        r10.append(r0);
        r0 = " (";
        r10.append(r0);
        r0 = r11.getName();
        r10.append(r0);
        r0 = ")";
        r10.append(r0);
        r10 = r10.toString();
        r9.setText(r10);
        r9 = r8.tv_guest_name;
        r10 = r11.getGuestName();
        r9.setText(r10);
        r9 = r8.billgetails;
        r10 = new com.jelsat.adapters.hostpaymentstotalbookingsadapter.HostTotalEarningsAdapter$1;
        r10.<init>(r11);
        r9.setOnClickListener(r10);
        r9 = r8.tv_booked_date;
        r10 = new java.lang.StringBuilder;
        r10.<init>();
        r0 = r7.context;
        r0 = r0.getResources();
        r1 = 2131821417; // 0x7f110369 float:1.9275577E38 double:1.053259725E-314;
        r0 = r0.getString(r1);
        r10.append(r0);
        r0 = " ";
        r10.append(r0);
        r0 = r11.getBookedOn();
        r0 = com.jelsat.utils.Utils.bookingsDateFormat(r0);
        r10.append(r0);
        r10 = r10.toString();
        r9.setText(r10);
        r9 = r8.tv_date_checkin;
        r10 = r11.getStartDate();
        r10 = com.jelsat.utils.Utils.bookingsCheckInCheckoutDateFormat(r10);
        r9.setText(r10);
        r9 = r8.tv_date_checkout;
        r10 = r11.getEndDate();
        r10 = com.jelsat.utils.Utils.bookingsCheckInCheckoutDateFormat(r10);
        r9.setText(r10);
        r9 = r8.tv_nights_numbers;
        r10 = new java.lang.StringBuilder;
        r10.<init>();
        r0 = r11.getNights();
        r10.append(r0);
        r0 = "N";
        r10.append(r0);
        r10 = r10.toString();
        r9.setText(r10);
        r9 = java.util.Locale.getDefault();
        r9 = r9.getLanguage();
        r10 = "ar";
        r9 = r9.equalsIgnoreCase(r10);
        r10 = 2131821423; // 0x7f11036f float:1.9275589E38 double:1.053259728E-314;
        r0 = 2;
        r1 = 2131821421; // 0x7f11036d float:1.9275585E38 double:1.053259727E-314;
        r2 = 3;
        r3 = 1;
        r4 = 0;
        if (r9 == 0) goto L_0x00ed;
    L_0x00c1:
        r9 = r8.propertyAmountTv;
        r5 = "%s %s %s";
        r2 = new java.lang.Object[r2];
        r6 = r7.context;
        r6 = r6.getResources();
        r1 = r6.getString(r1);
        r2[r4] = r1;
        r1 = r11.getEarnings();
        r2[r3] = r1;
        r1 = r7.context;
        r1 = r1.getResources();
        r10 = r1.getString(r10);
        r2[r0] = r10;
        r10 = java.lang.String.format(r5, r2);
        r9.setText(r10);
        goto L_0x0118;
    L_0x00ed:
        r9 = r8.propertyAmountTv;
        r5 = "%s %s %s";
        r2 = new java.lang.Object[r2];
        r6 = r7.context;
        r6 = r6.getResources();
        r1 = r6.getString(r1);
        r2[r4] = r1;
        r1 = r7.context;
        r1 = r1.getResources();
        r10 = r1.getString(r10);
        r2[r3] = r10;
        r10 = r11.getEarnings();
        r2[r0] = r10;
        r10 = java.lang.String.format(r5, r2);
        r9.setText(r10);
    L_0x0118:
        r9 = r8.tv_guests_count;
        r10 = new java.lang.StringBuilder;
        r0 = "%s ";
        r10.<init>(r0);
        r0 = r7.context;
        r1 = 2131821422; // 0x7f11036e float:1.9275587E38 double:1.0532597277E-314;
        r0 = r0.getString(r1);
        r10.append(r0);
        r10 = r10.toString();
        r0 = new java.lang.Object[r3];
        r1 = r11.getTotalGuests();
        r1 = java.lang.Integer.valueOf(r1);
        r0[r4] = r1;
        r10 = java.lang.String.format(r10, r0);
        r9.setText(r10);
        r9 = r7.context;
        r9 = com.jelsat.utils.GlideApp.with(r9);
        r10 = r11.getImage();
        r9 = r9.load(r10);
        r10 = 2131230886; // 0x7f0800a6 float:1.8077837E38 double:1.052967964E-314;
        r9 = r9.placeholder(r10);
        r0 = new com.bumptech.glide.request.RequestOptions;
        r0.<init>();
        r1 = new com.bumptech.glide.load.resource.bitmap.RoundedCorners;
        r2 = 8;
        r1.<init>(r2);
        r0 = r0.transform(r1);
        r9 = r9.apply(r0);
        r9 = r9.error(r10);
        r10 = r8.img_property;
        r9.into(r10);
        r9 = r11.getBookingType();	 Catch:{ Exception -> 0x017f }
        r9 = java.lang.Integer.parseInt(r9);	 Catch:{ Exception -> 0x017f }
        goto L_0x0180;
    L_0x017f:
        r9 = 0;
    L_0x0180:
        r7.setBookingTypeImage(r8, r9);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jelsat.adapters.hostpaymentstotalbookingsadapter.HostTotalEarningsAdapter.onBindChildViewHolder(com.jelsat.adapters.hostpaymentstotalbookingsadapter.HostTotalEarningsAdapter$ChildViewHolder, int, int, com.data.payments.totalbookings.HostBookingData):void");
    }

    private void setBookingTypeImage(ChildViewHolder childViewHolder, int i) {
        switch (i) {
            case 0:
                if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar") != 0) {
                    childViewHolder.tv_property_booking_type.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_24hours_booking, 0);
                } else {
                    childViewHolder.tv_property_booking_type.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_24hours_booking, 0, 0, 0);
                }
                childViewHolder.tv_property_booking_type.setText(this.context.getString(R.string.total_earnings_booking_type_regular));
                childViewHolder.tv_property_booking_type.setTextColor(applyColor(R.color.light_text_color));
                return;
            case 1:
                if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar") != 0) {
                    childViewHolder.tv_property_booking_type.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_instant_booking, 0);
                } else {
                    childViewHolder.tv_property_booking_type.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_instant_booking, 0, 0, 0);
                }
                childViewHolder.tv_property_booking_type.setText(this.context.getString(R.string.total_earnings_booking_type_instant));
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
