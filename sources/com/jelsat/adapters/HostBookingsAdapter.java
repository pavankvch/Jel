package com.jelsat.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import com.data.hostbookings.HostBookingProperty;
import com.data.searchproperty.SearchProperty;
import com.facebook.appevents.AppEventsConstants;
import com.jelsat.R;
import com.jelsat.activities.PublicProfileActivity;
import com.jelsat.constants.StringConstants;
import com.jelsat.customclasses.CustomTextView;
import com.jelsat.events.PropertyDetailEvent;
import com.jelsat.utils.BookingsUtil;
import com.jelsat.utils.Utils;
import com.jelsat.widgets.FancyButton;
import java.util.List;
import java.util.Locale;

public class HostBookingsAdapter extends Adapter<HostBookingsHolder> {
    public static boolean feedbackSubmitted = true;
    private List<HostBookingProperty> bookingsList;
    private BookingsUtil bookingsUtil;
    private Context context;
    private OnListItemClickListener listItemClickListener;

    public interface OnListItemClickListener {
        void clickOnCancel(int i, String str, HostBookingProperty hostBookingProperty);

        void clickOnConfirm(int i, HostBookingProperty hostBookingProperty);

        void clickOnFeedBack(int i, HostBookingProperty hostBookingProperty);

        void clickOnListItem(PropertyDetailEvent propertyDetailEvent);

        void clickOnMsg(int i, HostBookingProperty hostBookingProperty);

        void clickOnReject(int i, HostBookingProperty hostBookingProperty);
    }

    class HostBookingsHolder extends ViewHolder {
        @BindView(2131362706)
        TextView bookedDateTv;
        @BindView(2131361915)
        TextView bookingID;
        @BindView(2131361961)
        CustomTextView cancelButton;
        @BindView(2131362455)
        TextView checkinDateTv;
        @BindView(2131362456)
        TextView checkoutDateTv;
        @BindView(2131362131)
        TextView endWeekDay;
        @BindView(2131362760)
        CustomTextView feedbackTv;
        @BindView(2131362731)
        TextView guestNameTv;
        @BindView(2131362732)
        TextView guestsCountTv;
        @BindView(2131362747)
        FancyButton nightsCountTv;
        @BindView(2131362759)
        CustomTextView propertyBookingTypeTv;
        @BindView(2131362249)
        ImageView propertyIV;
        @BindView(2131362761)
        TextView propertyNameTv;
        @BindView(2131362503)
        LinearLayout rejectConfirmLayout;
        @BindView(2131362609)
        Space space;
        @BindView(2131362621)
        TextView startWeekDay;
        @BindView(2131362623)
        TextView statusBooking;

        @OnClick({2131362287})
        void clickOnPropertyImage() {
            goToPropertyDetailScreen();
        }

        @OnClick({2131361961})
        void clickOnCancelButton() {
            HostBookingsAdapter.this.listItemClickListener.clickOnCancel(getAdapterPosition(), null, (HostBookingProperty) HostBookingsAdapter.this.bookingsList.get(getAdapterPosition()));
        }

        @OnClick({2131361941})
        void clickOnMsgButton() {
            HostBookingsAdapter.this.listItemClickListener.clickOnMsg(getAdapterPosition(), (HostBookingProperty) HostBookingsAdapter.this.bookingsList.get(getAdapterPosition()));
        }

        @OnClick({2131362731})
        void goToPublicprofile() {
            if (!TextUtils.isEmpty(((HostBookingProperty) HostBookingsAdapter.this.bookingsList.get(getAdapterPosition())).getGuestId().trim())) {
                Bundle bundle = new Bundle();
                bundle.putString(StringConstants.UID, ((HostBookingProperty) HostBookingsAdapter.this.bookingsList.get(getAdapterPosition())).getGuestId());
                bundle.putInt(StringConstants.NEED_REVIEW, 1);
                Intent intent = new Intent(HostBookingsAdapter.this.context, PublicProfileActivity.class);
                intent.putExtras(bundle);
                HostBookingsAdapter.this.context.startActivity(intent);
            }
        }

        @OnClick({2131361939})
        void clickedOnReject() {
            HostBookingsAdapter.this.listItemClickListener.clickOnReject(getAdapterPosition(), (HostBookingProperty) HostBookingsAdapter.this.bookingsList.get(getAdapterPosition()));
        }

        @OnClick({2131361933})
        void clickedOnConfirm() {
            HostBookingsAdapter.this.listItemClickListener.clickOnConfirm(getAdapterPosition(), (HostBookingProperty) HostBookingsAdapter.this.bookingsList.get(getAdapterPosition()));
        }

        @OnClick({2131362760})
        void goToFeedback() {
            HostBookingsAdapter.this.listItemClickListener.clickOnFeedBack(getAdapterPosition(), (HostBookingProperty) HostBookingsAdapter.this.bookingsList.get(getAdapterPosition()));
        }

        HostBookingsHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void goToPropertyDetailScreen() {
            HostBookingProperty hostBookingProperty = (HostBookingProperty) HostBookingsAdapter.this.bookingsList.get(getAdapterPosition());
            if (!TextUtils.isEmpty(hostBookingProperty.getPropertyId())) {
                SearchProperty searchProperty = new SearchProperty();
                searchProperty.setPropertyId(hostBookingProperty.getPropertyId());
                searchProperty.setType(hostBookingProperty.getBookingType());
                PropertyDetailEvent propertyDetailEvent = new PropertyDetailEvent(searchProperty);
                propertyDetailEvent.setCheckInDate(Utils.bookingsCheckInCheckoutDateInDateFormat(hostBookingProperty.getStartDate()));
                propertyDetailEvent.setCheckoutDate(Utils.bookingsCheckInCheckoutDateInDateFormat(hostBookingProperty.getEndDate()));
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(hostBookingProperty.getTotalGuests());
                propertyDetailEvent.setGuestCount(stringBuilder.toString());
                propertyDetailEvent.setBookingId(hostBookingProperty.getOrderId());
                propertyDetailEvent.setFromBookings(true);
                propertyDetailEvent.setShowMessage(false);
                propertyDetailEvent.setLocationName(((HostBookingProperty) HostBookingsAdapter.this.bookingsList.get(getAdapterPosition())).getCountry());
                HostBookingsAdapter.this.listItemClickListener.clickOnListItem(propertyDetailEvent);
            }
        }
    }

    public class HostBookingsHolder_ViewBinding implements Unbinder {
        private HostBookingsHolder target;
        private View view2131361933;
        private View view2131361939;
        private View view2131361941;
        private View view2131361961;
        private View view2131362287;
        private View view2131362731;
        private View view2131362760;

        @UiThread
        public HostBookingsHolder_ViewBinding(final HostBookingsHolder hostBookingsHolder, View view) {
            this.target = hostBookingsHolder;
            hostBookingsHolder.bookingID = (TextView) butterknife.internal.Utils.findRequiredViewAsType(view, R.id.booking_id, "field 'bookingID'", TextView.class);
            hostBookingsHolder.propertyNameTv = (TextView) butterknife.internal.Utils.findRequiredViewAsType(view, R.id.tv_property_name, "field 'propertyNameTv'", TextView.class);
            hostBookingsHolder.bookedDateTv = (TextView) butterknife.internal.Utils.findRequiredViewAsType(view, R.id.tv_booked_date, "field 'bookedDateTv'", TextView.class);
            hostBookingsHolder.checkinDateTv = (TextView) butterknife.internal.Utils.findRequiredViewAsType(view, R.id.property_check_in_date, "field 'checkinDateTv'", TextView.class);
            hostBookingsHolder.checkoutDateTv = (TextView) butterknife.internal.Utils.findRequiredViewAsType(view, R.id.property_check_out_date, "field 'checkoutDateTv'", TextView.class);
            hostBookingsHolder.propertyBookingTypeTv = (CustomTextView) butterknife.internal.Utils.findRequiredViewAsType(view, R.id.tv_property_booking_type, "field 'propertyBookingTypeTv'", CustomTextView.class);
            hostBookingsHolder.guestsCountTv = (TextView) butterknife.internal.Utils.findRequiredViewAsType(view, R.id.tv_guests_count, "field 'guestsCountTv'", TextView.class);
            hostBookingsHolder.nightsCountTv = (FancyButton) butterknife.internal.Utils.findRequiredViewAsType(view, R.id.tv_nights_numbers, "field 'nightsCountTv'", FancyButton.class);
            View findRequiredView = butterknife.internal.Utils.findRequiredView(view, R.id.tv_guest_name, "field 'guestNameTv' and method 'goToPublicprofile'");
            hostBookingsHolder.guestNameTv = (TextView) butterknife.internal.Utils.castView(findRequiredView, R.id.tv_guest_name, "field 'guestNameTv'", TextView.class);
            this.view2131362731 = findRequiredView;
            findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    hostBookingsHolder.goToPublicprofile();
                }
            });
            findRequiredView = butterknife.internal.Utils.findRequiredView(view, R.id.tv_property_feedback, "field 'feedbackTv' and method 'goToFeedback'");
            hostBookingsHolder.feedbackTv = (CustomTextView) butterknife.internal.Utils.castView(findRequiredView, R.id.tv_property_feedback, "field 'feedbackTv'", CustomTextView.class);
            this.view2131362760 = findRequiredView;
            findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    hostBookingsHolder.goToFeedback();
                }
            });
            hostBookingsHolder.propertyIV = (ImageView) butterknife.internal.Utils.findRequiredViewAsType(view, R.id.img_property, "field 'propertyIV'", ImageView.class);
            hostBookingsHolder.statusBooking = (TextView) butterknife.internal.Utils.findRequiredViewAsType(view, R.id.status_bookings, "field 'statusBooking'", TextView.class);
            hostBookingsHolder.rejectConfirmLayout = (LinearLayout) butterknife.internal.Utils.findRequiredViewAsType(view, R.id.reject_confirm_layout, "field 'rejectConfirmLayout'", LinearLayout.class);
            findRequiredView = butterknife.internal.Utils.findRequiredView(view, R.id.cancel_button, "field 'cancelButton' and method 'clickOnCancelButton'");
            hostBookingsHolder.cancelButton = (CustomTextView) butterknife.internal.Utils.castView(findRequiredView, R.id.cancel_button, "field 'cancelButton'", CustomTextView.class);
            this.view2131361961 = findRequiredView;
            findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    hostBookingsHolder.clickOnCancelButton();
                }
            });
            hostBookingsHolder.space = (Space) butterknife.internal.Utils.findRequiredViewAsType(view, R.id.space, "field 'space'", Space.class);
            hostBookingsHolder.startWeekDay = (TextView) butterknife.internal.Utils.findRequiredViewAsType(view, R.id.start_week_day, "field 'startWeekDay'", TextView.class);
            hostBookingsHolder.endWeekDay = (TextView) butterknife.internal.Utils.findRequiredViewAsType(view, R.id.end_week_day, "field 'endWeekDay'", TextView.class);
            findRequiredView = butterknife.internal.Utils.findRequiredView(view, R.id.layout_main, "method 'clickOnPropertyImage'");
            this.view2131362287 = findRequiredView;
            findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    hostBookingsHolder.clickOnPropertyImage();
                }
            });
            findRequiredView = butterknife.internal.Utils.findRequiredView(view, R.id.btn_sendmsg, "method 'clickOnMsgButton'");
            this.view2131361941 = findRequiredView;
            findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    hostBookingsHolder.clickOnMsgButton();
                }
            });
            findRequiredView = butterknife.internal.Utils.findRequiredView(view, R.id.btn_reject, "method 'clickedOnReject'");
            this.view2131361939 = findRequiredView;
            findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    hostBookingsHolder.clickedOnReject();
                }
            });
            view = butterknife.internal.Utils.findRequiredView(view, R.id.btn_confirm, "method 'clickedOnConfirm'");
            this.view2131361933 = view;
            view.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    hostBookingsHolder.clickedOnConfirm();
                }
            });
        }

        @CallSuper
        public void unbind() {
            HostBookingsHolder hostBookingsHolder = this.target;
            if (hostBookingsHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            hostBookingsHolder.bookingID = null;
            hostBookingsHolder.propertyNameTv = null;
            hostBookingsHolder.bookedDateTv = null;
            hostBookingsHolder.checkinDateTv = null;
            hostBookingsHolder.checkoutDateTv = null;
            hostBookingsHolder.propertyBookingTypeTv = null;
            hostBookingsHolder.guestsCountTv = null;
            hostBookingsHolder.nightsCountTv = null;
            hostBookingsHolder.guestNameTv = null;
            hostBookingsHolder.feedbackTv = null;
            hostBookingsHolder.propertyIV = null;
            hostBookingsHolder.statusBooking = null;
            hostBookingsHolder.rejectConfirmLayout = null;
            hostBookingsHolder.cancelButton = null;
            hostBookingsHolder.space = null;
            hostBookingsHolder.startWeekDay = null;
            hostBookingsHolder.endWeekDay = null;
            this.view2131362731.setOnClickListener(null);
            this.view2131362731 = null;
            this.view2131362760.setOnClickListener(null);
            this.view2131362760 = null;
            this.view2131361961.setOnClickListener(null);
            this.view2131361961 = null;
            this.view2131362287.setOnClickListener(null);
            this.view2131362287 = null;
            this.view2131361941.setOnClickListener(null);
            this.view2131361941 = null;
            this.view2131361939.setOnClickListener(null);
            this.view2131361939 = null;
            this.view2131361933.setOnClickListener(null);
            this.view2131361933 = null;
        }
    }

    public HostBookingsAdapter(List<HostBookingProperty> list, Context context, OnListItemClickListener onListItemClickListener) {
        this.bookingsList = list;
        this.context = context;
        this.listItemClickListener = onListItemClickListener;
    }

    @NonNull
    public HostBookingsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new HostBookingsHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.host_booking_row_list_item, viewGroup, false));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onBindViewHolder(@android.support.annotation.NonNull com.jelsat.adapters.HostBookingsAdapter.HostBookingsHolder r19, int r20) {
        /*
        r18 = this;
        r1 = r18;
        r2 = r19;
        r3 = r20;
        r4 = r2.propertyNameTv;
        r5 = "%s %s (%s)";
        r6 = 3;
        r7 = new java.lang.Object[r6];
        r8 = r1.context;
        r9 = 2131820956; // 0x7f11019c float:1.9274642E38 double:1.0532594974E-314;
        r8 = r8.getString(r9);
        r9 = 0;
        r7[r9] = r8;
        r8 = r1.bookingsList;
        r8 = r8.get(r3);
        r8 = (com.data.hostbookings.HostBookingProperty) r8;
        r8 = r8.getPropertyId();
        r10 = 1;
        r7[r10] = r8;
        r8 = r1.bookingsList;
        r8 = r8.get(r3);
        r8 = (com.data.hostbookings.HostBookingProperty) r8;
        r8 = r8.getName();
        r11 = 2;
        r7[r11] = r8;
        r5 = java.lang.String.format(r5, r7);
        r4.setText(r5);
        r4 = r2.guestNameTv;
        r5 = r1.bookingsList;
        r5 = r5.get(r3);
        r5 = (com.data.hostbookings.HostBookingProperty) r5;
        r5 = r5.getGuestName();
        r4.setText(r5);
        r4 = r2.bookedDateTv;
        r5 = "%s %s";
        r7 = new java.lang.Object[r11];
        r8 = r1.context;
        r8 = r8.getResources();
        r12 = 2131820950; // 0x7f110196 float:1.927463E38 double:1.0532594945E-314;
        r8 = r8.getString(r12);
        r7[r9] = r8;
        r8 = r1.bookingsList;
        r8 = r8.get(r3);
        r8 = (com.data.hostbookings.HostBookingProperty) r8;
        r8 = r8.getBookedOn();
        r8 = com.jelsat.utils.Utils.bookingsDateFormat(r8);
        r7[r10] = r8;
        r5 = java.lang.String.format(r5, r7);
        r4.setText(r5);
        r4 = r2.checkinDateTv;
        r5 = r1.bookingsList;
        r5 = r5.get(r3);
        r5 = (com.data.hostbookings.HostBookingProperty) r5;
        r5 = r5.getStartDate();
        r5 = com.jelsat.utils.Utils.bookingsDateFormat(r5);
        r4.setText(r5);
        r4 = r2.checkoutDateTv;
        r5 = r1.bookingsList;
        r5 = r5.get(r3);
        r5 = (com.data.hostbookings.HostBookingProperty) r5;
        r5 = r5.getEndDate();
        r5 = com.jelsat.utils.Utils.bookingsDateFormat(r5);
        r4.setText(r5);
        r4 = r2.nightsCountTv;
        r5 = "%s %s";
        r7 = new java.lang.Object[r11];
        r8 = r1.bookingsList;
        r8 = r8.get(r3);
        r8 = (com.data.hostbookings.HostBookingProperty) r8;
        r8 = r8.getNights();
        r7[r9] = r8;
        r8 = r1.context;
        r8 = r8.getResources();
        r12 = 2131821067; // 0x7f11020b float:1.9274867E38 double:1.0532595523E-314;
        r8 = r8.getString(r12);
        r7[r10] = r8;
        r5 = java.lang.String.format(r5, r7);
        r4.setText(r5);
        r4 = java.util.Locale.getDefault();
        r4 = r4.getLanguage();
        r5 = "ar";
        r4 = r4.equalsIgnoreCase(r5);
        if (r4 == 0) goto L_0x0332;
    L_0x00e1:
        r4 = r1.bookingsList;
        r4 = r4.get(r3);
        r4 = (com.data.hostbookings.HostBookingProperty) r4;
        r4 = r4.getStartDay();
        r5 = r4.hashCode();
        switch(r5) {
            case 70909: goto L_0x0131;
            case 77548: goto L_0x0127;
            case 82886: goto L_0x011d;
            case 83500: goto L_0x0113;
            case 84065: goto L_0x0109;
            case 84452: goto L_0x00ff;
            case 86838: goto L_0x00f5;
            default: goto L_0x00f4;
        };
    L_0x00f4:
        goto L_0x013b;
    L_0x00f5:
        r5 = "Wed";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x013b;
    L_0x00fd:
        r4 = 2;
        goto L_0x013c;
    L_0x00ff:
        r5 = "Tue";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x013b;
    L_0x0107:
        r4 = 1;
        goto L_0x013c;
    L_0x0109:
        r5 = "Thu";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x013b;
    L_0x0111:
        r4 = 3;
        goto L_0x013c;
    L_0x0113:
        r5 = "Sun";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x013b;
    L_0x011b:
        r4 = 6;
        goto L_0x013c;
    L_0x011d:
        r5 = "Sat";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x013b;
    L_0x0125:
        r4 = 5;
        goto L_0x013c;
    L_0x0127:
        r5 = "Mon";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x013b;
    L_0x012f:
        r4 = 0;
        goto L_0x013c;
    L_0x0131:
        r5 = "Fri";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x013b;
    L_0x0139:
        r4 = 4;
        goto L_0x013c;
    L_0x013b:
        r4 = -1;
    L_0x013c:
        r14 = 2131820701; // 0x7f11009d float:1.9274124E38 double:1.0532593715E-314;
        r15 = 2131820703; // 0x7f11009f float:1.9274128E38 double:1.0532593724E-314;
        r6 = 2131820700; // 0x7f11009c float:1.9274122E38 double:1.053259371E-314;
        r7 = 2131820696; // 0x7f110098 float:1.9274114E38 double:1.053259369E-314;
        r8 = 2131820698; // 0x7f11009a float:1.9274118E38 double:1.05325937E-314;
        r12 = 2131820699; // 0x7f11009b float:1.927412E38 double:1.0532593705E-314;
        switch(r4) {
            case 0: goto L_0x01f0;
            case 1: goto L_0x01d6;
            case 2: goto L_0x01bc;
            case 3: goto L_0x01a2;
            case 4: goto L_0x0188;
            case 5: goto L_0x016d;
            case 6: goto L_0x0152;
            default: goto L_0x0151;
        };
    L_0x0151:
        return;
    L_0x0152:
        r4 = r2.startWeekDay;
        r13 = "%s";
        r11 = new java.lang.Object[r10];
        r5 = r1.context;
        r5 = r5.getResources();
        r5 = r5.getString(r12);
        r11[r9] = r5;
        r5 = java.lang.String.format(r13, r11);
        r4.setText(r5);
        goto L_0x020c;
    L_0x016d:
        r4 = r2.startWeekDay;
        r5 = "%s";
        r11 = new java.lang.Object[r10];
        r13 = r1.context;
        r13 = r13.getResources();
        r13 = r13.getString(r8);
        r11[r9] = r13;
        r5 = java.lang.String.format(r5, r11);
        r4.setText(r5);
        goto L_0x020c;
    L_0x0188:
        r4 = r2.startWeekDay;
        r5 = "%s";
        r11 = new java.lang.Object[r10];
        r13 = r1.context;
        r13 = r13.getResources();
        r13 = r13.getString(r7);
        r11[r9] = r13;
        r5 = java.lang.String.format(r5, r11);
        r4.setText(r5);
        goto L_0x020c;
    L_0x01a2:
        r4 = r2.startWeekDay;
        r5 = "%s";
        r11 = new java.lang.Object[r10];
        r13 = r1.context;
        r13 = r13.getResources();
        r13 = r13.getString(r6);
        r11[r9] = r13;
        r5 = java.lang.String.format(r5, r11);
        r4.setText(r5);
        goto L_0x020c;
    L_0x01bc:
        r4 = r2.startWeekDay;
        r5 = "%s";
        r11 = new java.lang.Object[r10];
        r13 = r1.context;
        r13 = r13.getResources();
        r13 = r13.getString(r15);
        r11[r9] = r13;
        r5 = java.lang.String.format(r5, r11);
        r4.setText(r5);
        goto L_0x020c;
    L_0x01d6:
        r4 = r2.startWeekDay;
        r5 = "%s";
        r11 = new java.lang.Object[r10];
        r13 = r1.context;
        r13 = r13.getResources();
        r13 = r13.getString(r14);
        r11[r9] = r13;
        r5 = java.lang.String.format(r5, r11);
        r4.setText(r5);
        goto L_0x020c;
    L_0x01f0:
        r4 = r2.startWeekDay;
        r5 = "%s";
        r11 = new java.lang.Object[r10];
        r13 = r1.context;
        r13 = r13.getResources();
        r14 = 2131820697; // 0x7f110099 float:1.9274116E38 double:1.0532593695E-314;
        r13 = r13.getString(r14);
        r11[r9] = r13;
        r5 = java.lang.String.format(r5, r11);
        r4.setText(r5);
    L_0x020c:
        r4 = r1.bookingsList;
        r4 = r4.get(r3);
        r4 = (com.data.hostbookings.HostBookingProperty) r4;
        r4 = r4.getEndDay();
        r5 = r4.hashCode();
        switch(r5) {
            case 70909: goto L_0x0262;
            case 77548: goto L_0x0257;
            case 82886: goto L_0x024c;
            case 83500: goto L_0x0241;
            case 84065: goto L_0x0236;
            case 84452: goto L_0x022b;
            case 86838: goto L_0x0220;
            default: goto L_0x021f;
        };
    L_0x021f:
        goto L_0x026d;
    L_0x0220:
        r5 = "Wed";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x026d;
    L_0x0228:
        r16 = 2;
        goto L_0x026f;
    L_0x022b:
        r5 = "Tue";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x026d;
    L_0x0233:
        r16 = 1;
        goto L_0x026f;
    L_0x0236:
        r5 = "Thu";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x026d;
    L_0x023e:
        r16 = 3;
        goto L_0x026f;
    L_0x0241:
        r5 = "Sun";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x026d;
    L_0x0249:
        r16 = 6;
        goto L_0x026f;
    L_0x024c:
        r5 = "Sat";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x026d;
    L_0x0254:
        r16 = 5;
        goto L_0x026f;
    L_0x0257:
        r5 = "Mon";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x026d;
    L_0x025f:
        r16 = 0;
        goto L_0x026f;
    L_0x0262:
        r5 = "Fri";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x026d;
    L_0x026a:
        r16 = 4;
        goto L_0x026f;
    L_0x026d:
        r16 = -1;
    L_0x026f:
        switch(r16) {
            case 0: goto L_0x0315;
            case 1: goto L_0x02f8;
            case 2: goto L_0x02de;
            case 3: goto L_0x02c4;
            case 4: goto L_0x02a9;
            case 5: goto L_0x028e;
            case 6: goto L_0x0273;
            default: goto L_0x0272;
        };
    L_0x0272:
        return;
    L_0x0273:
        r4 = r2.endWeekDay;
        r5 = "%s";
        r6 = new java.lang.Object[r10];
        r7 = r1.context;
        r7 = r7.getResources();
        r7 = r7.getString(r12);
        r6[r9] = r7;
        r5 = java.lang.String.format(r5, r6);
        r4.setText(r5);
        goto L_0x0354;
    L_0x028e:
        r4 = r2.endWeekDay;
        r5 = "%s";
        r6 = new java.lang.Object[r10];
        r7 = r1.context;
        r7 = r7.getResources();
        r7 = r7.getString(r8);
        r6[r9] = r7;
        r5 = java.lang.String.format(r5, r6);
        r4.setText(r5);
        goto L_0x0354;
    L_0x02a9:
        r4 = r2.endWeekDay;
        r5 = "%s";
        r6 = new java.lang.Object[r10];
        r8 = r1.context;
        r8 = r8.getResources();
        r7 = r8.getString(r7);
        r6[r9] = r7;
        r5 = java.lang.String.format(r5, r6);
        r4.setText(r5);
        goto L_0x0354;
    L_0x02c4:
        r4 = r2.endWeekDay;
        r5 = "%s";
        r7 = new java.lang.Object[r10];
        r8 = r1.context;
        r8 = r8.getResources();
        r6 = r8.getString(r6);
        r7[r9] = r6;
        r5 = java.lang.String.format(r5, r7);
        r4.setText(r5);
        goto L_0x0354;
    L_0x02de:
        r4 = r2.endWeekDay;
        r5 = "%s";
        r6 = new java.lang.Object[r10];
        r7 = r1.context;
        r7 = r7.getResources();
        r7 = r7.getString(r15);
        r6[r9] = r7;
        r5 = java.lang.String.format(r5, r6);
        r4.setText(r5);
        goto L_0x0354;
    L_0x02f8:
        r4 = r2.endWeekDay;
        r5 = "%s";
        r6 = new java.lang.Object[r10];
        r7 = r1.context;
        r7 = r7.getResources();
        r8 = 2131820701; // 0x7f11009d float:1.9274124E38 double:1.0532593715E-314;
        r7 = r7.getString(r8);
        r6[r9] = r7;
        r5 = java.lang.String.format(r5, r6);
        r4.setText(r5);
        goto L_0x0354;
    L_0x0315:
        r4 = r2.endWeekDay;
        r5 = "%s";
        r6 = new java.lang.Object[r10];
        r7 = r1.context;
        r7 = r7.getResources();
        r8 = 2131820697; // 0x7f110099 float:1.9274116E38 double:1.0532593695E-314;
        r7 = r7.getString(r8);
        r6[r9] = r7;
        r5 = java.lang.String.format(r5, r6);
        r4.setText(r5);
        goto L_0x0354;
    L_0x0332:
        r4 = r2.startWeekDay;
        r5 = r1.bookingsList;
        r5 = r5.get(r3);
        r5 = (com.data.hostbookings.HostBookingProperty) r5;
        r5 = r5.getStartDay();
        r4.setText(r5);
        r4 = r2.endWeekDay;
        r5 = r1.bookingsList;
        r5 = r5.get(r3);
        r5 = (com.data.hostbookings.HostBookingProperty) r5;
        r5 = r5.getEndDay();
        r4.setText(r5);
    L_0x0354:
        r4 = r2.guestsCountTv;
        r5 = new java.lang.StringBuilder;
        r6 = "%s ";
        r5.<init>(r6);
        r6 = r1.context;
        r7 = 2131820953; // 0x7f110199 float:1.9274635E38 double:1.053259496E-314;
        r6 = r6.getString(r7);
        r5.append(r6);
        r5 = r5.toString();
        r6 = new java.lang.Object[r10];
        r7 = r1.bookingsList;
        r7 = r7.get(r3);
        r7 = (com.data.hostbookings.HostBookingProperty) r7;
        r7 = r7.getTotalGuests();
        r7 = java.lang.Integer.valueOf(r7);
        r6[r9] = r7;
        r5 = java.lang.String.format(r5, r6);
        r4.setText(r5);
        r4 = r2.bookingID;
        r5 = "%s : %s";
        r6 = 2;
        r6 = new java.lang.Object[r6];
        r7 = r1.context;
        r8 = 2131820668; // 0x7f11007c float:1.9274057E38 double:1.053259355E-314;
        r7 = r7.getString(r8);
        r6[r9] = r7;
        r7 = r1.bookingsList;
        r7 = r7.get(r3);
        r7 = (com.data.hostbookings.HostBookingProperty) r7;
        r7 = r7.getOrderId();
        r6[r10] = r7;
        r5 = java.lang.String.format(r5, r6);
        r4.setText(r5);
        r4 = r1.context;
        r4 = com.jelsat.utils.GlideApp.with(r4);
        r5 = r1.bookingsList;
        r5 = r5.get(r3);
        r5 = (com.data.hostbookings.HostBookingProperty) r5;
        r5 = r5.getImage();
        r4 = r4.load(r5);
        r5 = 2131230888; // 0x7f0800a8 float:1.8077841E38 double:1.052967965E-314;
        r4 = r4.placeholder(r5);
        r5 = new com.bumptech.glide.request.RequestOptions;
        r5.<init>();
        r6 = new com.bumptech.glide.load.resource.bitmap.RoundedCorners;
        r7 = 8;
        r6.<init>(r7);
        r5 = r5.transform(r6);
        r4 = r4.apply(r5);
        r5 = 2131230888; // 0x7f0800a8 float:1.8077841E38 double:1.052967965E-314;
        r4 = r4.error(r5);
        r5 = r2.propertyIV;
        r4.into(r5);
        r4 = r1.bookingsList;	 Catch:{ Exception -> 0x03fd }
        r4 = r4.get(r3);	 Catch:{ Exception -> 0x03fd }
        r4 = (com.data.hostbookings.HostBookingProperty) r4;	 Catch:{ Exception -> 0x03fd }
        r4 = r4.getBookingType();	 Catch:{ Exception -> 0x03fd }
        r4 = java.lang.Integer.parseInt(r4);	 Catch:{ Exception -> 0x03fd }
        goto L_0x0403;
    L_0x03fd:
        r0 = move-exception;
        r4 = r0;
        r4.printStackTrace();
        r4 = 0;
    L_0x0403:
        r1.setBookingTypeImage(r2, r4);
        r4 = r1.bookingsUtil;
        r5 = r1.bookingsList;
        r5 = r5.get(r3);
        r5 = (com.data.hostbookings.HostBookingProperty) r5;
        r5 = r5.getBookingStatusCode();
        r6 = r1.bookingsList;
        r3 = r6.get(r3);
        r3 = (com.data.hostbookings.HostBookingProperty) r3;
        r3 = r3.getBookingStatus();
        r1.updateViews(r2, r4, r5, r3);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jelsat.adapters.HostBookingsAdapter.onBindViewHolder(com.jelsat.adapters.HostBookingsAdapter$HostBookingsHolder, int):void");
    }

    private void setBookingTypeImage(HostBookingsHolder hostBookingsHolder, int i) {
        switch (i) {
            case 0:
                if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar") != 0) {
                    hostBookingsHolder.propertyBookingTypeTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_24hours_booking, 0);
                } else {
                    hostBookingsHolder.propertyBookingTypeTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_24hours_booking, 0, 0, 0);
                }
                hostBookingsHolder.propertyBookingTypeTv.setText(this.context.getString(R.string.host_bookings_regular));
                break;
            case 1:
                if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar") != 0) {
                    hostBookingsHolder.propertyBookingTypeTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_instant_booking, 0);
                } else {
                    hostBookingsHolder.propertyBookingTypeTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_instant_booking, 0, 0, 0);
                }
                hostBookingsHolder.propertyBookingTypeTv.setText(this.context.getString(R.string.host_bookings_instant));
                return;
            default:
                break;
        }
    }

    public int applyColor(@ColorRes int i) {
        return ContextCompat.getColor(this.context, i);
    }

    public int getItemCount() {
        return this.bookingsList != null ? this.bookingsList.size() : 0;
    }

    public void setData(List<HostBookingProperty> list, BookingsUtil bookingsUtil) {
        this.bookingsList = list;
        this.bookingsUtil = bookingsUtil;
        notifyDataSetChanged();
    }

    public void refreshListItem(int i, String str) {
        ((HostBookingProperty) this.bookingsList.get(i)).setBookingStatusCode(str);
        ((HostBookingProperty) this.bookingsList.get(i)).setBookingStatus(str);
        notifyItemChanged(i);
    }

    private void updateViews(HostBookingsHolder hostBookingsHolder, BookingsUtil bookingsUtil, String str, String str2) {
        if (bookingsUtil.getBookingType() == 2) {
            hostBookingsHolder.rejectConfirmLayout.setVisibility(8);
            hostBookingsHolder.cancelButton.setVisibility(8);
            hostBookingsHolder.statusBooking.setVisibility(0);
            hostBookingsHolder.statusBooking.setText(str2);
            hostBookingsHolder.statusBooking.setTextColor(applyColor(getBookingStatusColor(str)));
            if (str.equalsIgnoreCase(StringConstants.BOOKING_SETTLED) == null) {
                if (str.equalsIgnoreCase(StringConstants.BOOKING_CONFIRMED) == null) {
                    feedbackSubmitted = true;
                    hostBookingsHolder.space.setVisibility(8);
                    hostBookingsHolder.feedbackTv.setVisibility(8);
                    return;
                }
            }
            hostBookingsHolder.space.setVisibility(0);
            if (!(((HostBookingProperty) this.bookingsList.get(hostBookingsHolder.getAdapterPosition())).getHostFeedback().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_YES) == null || feedbackSubmitted == null)) {
                Log.e("HostBookingAdapter", "UpdateView()if Condition");
                hostBookingsHolder.feedbackTv.setVisibility(0);
                return;
            }
        }
        hostBookingsHolder.statusBooking.setVisibility(8);
        hostBookingsHolder.feedbackTv.setVisibility(8);
        hostBookingsHolder.space.setVisibility(8);
        if (str.equalsIgnoreCase(StringConstants.BOOKING_WAITING) != null) {
            hostBookingsHolder.rejectConfirmLayout.setVisibility(0);
            hostBookingsHolder.cancelButton.setVisibility(8);
        } else if (str.equalsIgnoreCase(StringConstants.BOOKING_CONFIRMED) != null) {
            hostBookingsHolder.rejectConfirmLayout.setVisibility(8);
            hostBookingsHolder.cancelButton.setVisibility(0);
        } else {
            hostBookingsHolder.statusBooking.setVisibility(0);
            hostBookingsHolder.statusBooking.setText(str2);
            hostBookingsHolder.statusBooking.setTextColor(applyColor(getBookingStatusColor(str)));
            hostBookingsHolder.cancelButton.setVisibility(8);
            hostBookingsHolder.rejectConfirmLayout.setVisibility(8);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.ColorRes
    private int getBookingStatusColor(java.lang.String r2) {
        /*
        r1 = this;
        r0 = r2.hashCode();
        switch(r0) {
            case -1402931637: goto L_0x0030;
            case -608496514: goto L_0x0026;
            case -253384684: goto L_0x001c;
            case -123173735: goto L_0x0012;
            case 1925736384: goto L_0x0008;
            default: goto L_0x0007;
        };
    L_0x0007:
        goto L_0x003a;
    L_0x0008:
        r0 = "dropped";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x003a;
    L_0x0010:
        r2 = 4;
        goto L_0x003b;
    L_0x0012:
        r0 = "canceled";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x003a;
    L_0x001a:
        r2 = 3;
        goto L_0x003b;
    L_0x001c:
        r0 = "checkout_24hours";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x003a;
    L_0x0024:
        r2 = 1;
        goto L_0x003b;
    L_0x0026:
        r0 = "rejected";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x003a;
    L_0x002e:
        r2 = 2;
        goto L_0x003b;
    L_0x0030:
        r0 = "completed";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x003a;
    L_0x0038:
        r2 = 0;
        goto L_0x003b;
    L_0x003a:
        r2 = -1;
    L_0x003b:
        switch(r2) {
            case 0: goto L_0x0052;
            case 1: goto L_0x004e;
            case 2: goto L_0x004a;
            case 3: goto L_0x0046;
            case 4: goto L_0x0042;
            default: goto L_0x003e;
        };
    L_0x003e:
        r2 = 2131099844; // 0x7f0600c4 float:1.7812053E38 double:1.052903221E-314;
        return r2;
    L_0x0042:
        r2 = 2131099694; // 0x7f06002e float:1.7811748E38 double:1.0529031467E-314;
        return r2;
    L_0x0046:
        r2 = 2131099692; // 0x7f06002c float:1.7811744E38 double:1.0529031457E-314;
        return r2;
    L_0x004a:
        r2 = 2131099699; // 0x7f060033 float:1.7811759E38 double:1.052903149E-314;
        return r2;
    L_0x004e:
        r2 = 2131099701; // 0x7f060035 float:1.7811763E38 double:1.05290315E-314;
        return r2;
    L_0x0052:
        r2 = 2131099697; // 0x7f060031 float:1.7811755E38 double:1.052903148E-314;
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jelsat.adapters.HostBookingsAdapter.getBookingStatusColor(java.lang.String):int");
    }
}
