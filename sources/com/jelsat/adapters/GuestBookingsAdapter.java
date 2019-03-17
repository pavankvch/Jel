package com.jelsat.adapters;

import android.content.Context;
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
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.data.bookings.BookingProperty;
import com.data.searchproperty.SearchProperty;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.maps.model.LatLng;
import com.jelsat.R;
import com.jelsat.constants.StringConstants;
import com.jelsat.customclasses.CustomTextView;
import com.jelsat.events.PropertyDetailEvent;
import com.jelsat.utils.BookingsUtil;
import com.jelsat.widgets.FancyButton;
import java.util.List;
import java.util.Locale;

public class GuestBookingsAdapter extends Adapter<GuestBookingsHolder> {
    private List<BookingProperty> bookings;
    private BookingsUtil bookingsUtil;
    private Context context;
    public boolean feedbackSubmitted = true;
    private ListItemClickListener listener;

    public interface ListItemClickListener {
        void clickOnCancel(String str, String str2);

        void clickOnFeedBack(String str, String str2, int i);

        void clickOnGetDirections(LatLng latLng);

        void clickOnSendMessage(String str, String str2);

        void clickOnViewBill(BookingProperty bookingProperty);

        void gotoBookingDetailScreen(PropertyDetailEvent propertyDetailEvent);
    }

    class GuestBookingsHolder extends ViewHolder {
        @BindView(2131362279)
        LinearLayout actionLayout;
        @BindView(2131362705)
        TextView areaTv;
        @BindView(2131361801)
        ImageView bookAgainTv;
        @BindView(2131362756)
        TextView bookedDateTv;
        @BindView(2131362757)
        TextView bookedDateWithTime;
        @BindView(2131362758)
        FancyButton bookingStatusTv;
        @BindView(2131362759)
        CustomTextView bookingTypeView;
        @BindView(2131361802)
        ImageView cancelIv;
        @BindView(2131362455)
        TextView checkInDateTv;
        @BindView(2131362456)
        TextView checkOutDateTv;
        @BindView(2131362131)
        TextView endWeekDay;
        @BindView(2131361803)
        ImageView feedbackIv;
        @BindView(2131361804)
        ImageView getDirectionIv;
        @BindView(2131361805)
        ImageView messageIv;
        @BindView(2131362747)
        FancyButton nightsCountTv;
        @BindView(2131362289)
        LinearLayout previousLayout;
        @BindView(2131362249)
        ImageView propertyImageView;
        @BindView(2131362761)
        TextView propertyNameTv;
        @BindView(2131362621)
        TextView startWeekDay;
        @BindView(2131361806)
        ImageView viewBillIv;
        @BindView(2131361807)
        ImageView viewBillPreivousIv;

        @OnClick({2131361802})
        void clickOnCancelImage() {
            GuestBookingsAdapter.this.listener.clickOnCancel(((BookingProperty) GuestBookingsAdapter.this.bookings.get(getAdapterPosition())).getOrderId(), ((BookingProperty) GuestBookingsAdapter.this.bookings.get(getAdapterPosition())).getBookingStatusCode());
        }

        @OnClick({2131361807})
        void clickOnViewBillPrevious() {
            GuestBookingsAdapter.this.listener.clickOnViewBill((BookingProperty) GuestBookingsAdapter.this.bookings.get(getAdapterPosition()));
        }

        @OnClick({2131361806})
        void clickOnViewBill() {
            GuestBookingsAdapter.this.listener.clickOnViewBill((BookingProperty) GuestBookingsAdapter.this.bookings.get(getAdapterPosition()));
        }

        @OnClick({2131361804})
        void clickOnGetDirections() {
            GuestBookingsAdapter.this.goToGetDirections(getAdapterPosition());
        }

        @OnClick({2131361805})
        void clickOnMessage() {
            GuestBookingsAdapter.this.goToMessages(getAdapterPosition());
        }

        @OnClick({2131362287})
        void clickOnRow() {
            GuestBookingsAdapter.this.goToPropertyDescription(getAdapterPosition(), GuestBookingsAdapter.this.bookingsUtil);
        }

        @OnClick({2131361803})
        void clickOnFeedback() {
            GuestBookingsAdapter.this.goToFeedback(getAdapterPosition());
        }

        @OnClick({2131361801})
        void clickOnbookAgain() {
            GuestBookingsAdapter.this.goBookAgain(getAdapterPosition());
        }

        GuestBookingsHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class GuestBookingsHolder_ViewBinding implements Unbinder {
        private GuestBookingsHolder target;
        private View view2131361801;
        private View view2131361802;
        private View view2131361803;
        private View view2131361804;
        private View view2131361805;
        private View view2131361806;
        private View view2131361807;
        private View view2131362287;

        @UiThread
        public GuestBookingsHolder_ViewBinding(final GuestBookingsHolder guestBookingsHolder, View view) {
            this.target = guestBookingsHolder;
            guestBookingsHolder.previousLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_previous, "field 'previousLayout'", LinearLayout.class);
            guestBookingsHolder.actionLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_action, "field 'actionLayout'", LinearLayout.class);
            guestBookingsHolder.propertyNameTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_property_name, "field 'propertyNameTv'", TextView.class);
            View findRequiredView = Utils.findRequiredView(view, R.id.Iv_book_again, "field 'bookAgainTv' and method 'clickOnbookAgain'");
            guestBookingsHolder.bookAgainTv = (ImageView) Utils.castView(findRequiredView, R.id.Iv_book_again, "field 'bookAgainTv'", ImageView.class);
            this.view2131361801 = findRequiredView;
            findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    guestBookingsHolder.clickOnbookAgain();
                }
            });
            findRequiredView = Utils.findRequiredView(view, R.id.Iv_feedback, "field 'feedbackIv' and method 'clickOnFeedback'");
            guestBookingsHolder.feedbackIv = (ImageView) Utils.castView(findRequiredView, R.id.Iv_feedback, "field 'feedbackIv'", ImageView.class);
            this.view2131361803 = findRequiredView;
            findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    guestBookingsHolder.clickOnFeedback();
                }
            });
            guestBookingsHolder.areaTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_area, "field 'areaTv'", TextView.class);
            guestBookingsHolder.bookedDateTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_property_booked_date, "field 'bookedDateTv'", TextView.class);
            guestBookingsHolder.bookedDateWithTime = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_property_booked_date_time, "field 'bookedDateWithTime'", TextView.class);
            guestBookingsHolder.checkInDateTv = (TextView) Utils.findRequiredViewAsType(view, R.id.property_check_in_date, "field 'checkInDateTv'", TextView.class);
            guestBookingsHolder.checkOutDateTv = (TextView) Utils.findRequiredViewAsType(view, R.id.property_check_out_date, "field 'checkOutDateTv'", TextView.class);
            guestBookingsHolder.bookingTypeView = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.tv_property_booking_type, "field 'bookingTypeView'", CustomTextView.class);
            guestBookingsHolder.bookingStatusTv = (FancyButton) Utils.findRequiredViewAsType(view, R.id.tv_property_booking_status, "field 'bookingStatusTv'", FancyButton.class);
            guestBookingsHolder.nightsCountTv = (FancyButton) Utils.findRequiredViewAsType(view, R.id.tv_nights_numbers, "field 'nightsCountTv'", FancyButton.class);
            guestBookingsHolder.propertyImageView = (ImageView) Utils.findRequiredViewAsType(view, R.id.img_property, "field 'propertyImageView'", ImageView.class);
            findRequiredView = Utils.findRequiredView(view, R.id.Iv_get_direction, "field 'getDirectionIv' and method 'clickOnGetDirections'");
            guestBookingsHolder.getDirectionIv = (ImageView) Utils.castView(findRequiredView, R.id.Iv_get_direction, "field 'getDirectionIv'", ImageView.class);
            this.view2131361804 = findRequiredView;
            findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    guestBookingsHolder.clickOnGetDirections();
                }
            });
            findRequiredView = Utils.findRequiredView(view, R.id.Iv_cancel, "field 'cancelIv' and method 'clickOnCancelImage'");
            guestBookingsHolder.cancelIv = (ImageView) Utils.castView(findRequiredView, R.id.Iv_cancel, "field 'cancelIv'", ImageView.class);
            this.view2131361802 = findRequiredView;
            findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    guestBookingsHolder.clickOnCancelImage();
                }
            });
            findRequiredView = Utils.findRequiredView(view, R.id.Iv_message, "field 'messageIv' and method 'clickOnMessage'");
            guestBookingsHolder.messageIv = (ImageView) Utils.castView(findRequiredView, R.id.Iv_message, "field 'messageIv'", ImageView.class);
            this.view2131361805 = findRequiredView;
            findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    guestBookingsHolder.clickOnMessage();
                }
            });
            findRequiredView = Utils.findRequiredView(view, R.id.Iv_view_bill, "field 'viewBillIv' and method 'clickOnViewBill'");
            guestBookingsHolder.viewBillIv = (ImageView) Utils.castView(findRequiredView, R.id.Iv_view_bill, "field 'viewBillIv'", ImageView.class);
            this.view2131361806 = findRequiredView;
            findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    guestBookingsHolder.clickOnViewBill();
                }
            });
            findRequiredView = Utils.findRequiredView(view, R.id.Iv_view_bill_preivous, "field 'viewBillPreivousIv' and method 'clickOnViewBillPrevious'");
            guestBookingsHolder.viewBillPreivousIv = (ImageView) Utils.castView(findRequiredView, R.id.Iv_view_bill_preivous, "field 'viewBillPreivousIv'", ImageView.class);
            this.view2131361807 = findRequiredView;
            findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    guestBookingsHolder.clickOnViewBillPrevious();
                }
            });
            guestBookingsHolder.startWeekDay = (TextView) Utils.findRequiredViewAsType(view, R.id.start_week_day, "field 'startWeekDay'", TextView.class);
            guestBookingsHolder.endWeekDay = (TextView) Utils.findRequiredViewAsType(view, R.id.end_week_day, "field 'endWeekDay'", TextView.class);
            view = Utils.findRequiredView(view, R.id.layout_main, "method 'clickOnRow'");
            this.view2131362287 = view;
            view.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    guestBookingsHolder.clickOnRow();
                }
            });
        }

        @CallSuper
        public void unbind() {
            GuestBookingsHolder guestBookingsHolder = this.target;
            if (guestBookingsHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            guestBookingsHolder.previousLayout = null;
            guestBookingsHolder.actionLayout = null;
            guestBookingsHolder.propertyNameTv = null;
            guestBookingsHolder.bookAgainTv = null;
            guestBookingsHolder.feedbackIv = null;
            guestBookingsHolder.areaTv = null;
            guestBookingsHolder.bookedDateTv = null;
            guestBookingsHolder.bookedDateWithTime = null;
            guestBookingsHolder.checkInDateTv = null;
            guestBookingsHolder.checkOutDateTv = null;
            guestBookingsHolder.bookingTypeView = null;
            guestBookingsHolder.bookingStatusTv = null;
            guestBookingsHolder.nightsCountTv = null;
            guestBookingsHolder.propertyImageView = null;
            guestBookingsHolder.getDirectionIv = null;
            guestBookingsHolder.cancelIv = null;
            guestBookingsHolder.messageIv = null;
            guestBookingsHolder.viewBillIv = null;
            guestBookingsHolder.viewBillPreivousIv = null;
            guestBookingsHolder.startWeekDay = null;
            guestBookingsHolder.endWeekDay = null;
            this.view2131361801.setOnClickListener(null);
            this.view2131361801 = null;
            this.view2131361803.setOnClickListener(null);
            this.view2131361803 = null;
            this.view2131361804.setOnClickListener(null);
            this.view2131361804 = null;
            this.view2131361802.setOnClickListener(null);
            this.view2131361802 = null;
            this.view2131361805.setOnClickListener(null);
            this.view2131361805 = null;
            this.view2131361806.setOnClickListener(null);
            this.view2131361806 = null;
            this.view2131361807.setOnClickListener(null);
            this.view2131361807 = null;
            this.view2131362287.setOnClickListener(null);
            this.view2131362287 = null;
        }
    }

    public GuestBookingsAdapter(List<BookingProperty> list, Context context, ListItemClickListener listItemClickListener) {
        this.bookings = list;
        this.context = context;
        this.listener = listItemClickListener;
    }

    @NonNull
    public GuestBookingsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new GuestBookingsHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.guest_booking_row_list_item, viewGroup, false));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onBindViewHolder(@android.support.annotation.NonNull com.jelsat.adapters.GuestBookingsAdapter.GuestBookingsHolder r19, int r20) {
        /*
        r18 = this;
        r1 = r18;
        r2 = r19;
        r3 = r20;
        r4 = r2.propertyNameTv;
        r5 = r1.bookings;
        r5 = r5.get(r3);
        r5 = (com.data.bookings.BookingProperty) r5;
        r5 = r5.getName();
        r4.setText(r5);
        r4 = r2.areaTv;
        r5 = r1.bookings;
        r5 = r5.get(r3);
        r5 = (com.data.bookings.BookingProperty) r5;
        r5 = r5.getCountry();
        r4.setText(r5);
        r4 = r2.bookedDateTv;
        r5 = "%s ";
        r6 = 1;
        r7 = new java.lang.Object[r6];
        r8 = r1.context;
        r8 = r8.getResources();
        r9 = 2131820909; // 0x7f11016d float:1.9274546E38 double:1.053259474E-314;
        r8 = r8.getString(r9);
        r9 = 0;
        r7[r9] = r8;
        r5 = java.lang.String.format(r5, r7);
        r4.setText(r5);
        r4 = r2.bookedDateWithTime;
        r5 = r1.bookings;
        r5 = r5.get(r3);
        r5 = (com.data.bookings.BookingProperty) r5;
        r5 = r5.getBookedOn();
        r5 = com.jelsat.utils.Utils.showDateWithTwelveHoursTimeStamp(r5);
        r4.setText(r5);
        r4 = r2.checkInDateTv;
        r5 = r1.bookings;
        r5 = r5.get(r3);
        r5 = (com.data.bookings.BookingProperty) r5;
        r5 = r5.getStartDate();
        r5 = com.jelsat.utils.Utils.bookingsDateFormat(r5);
        r4.setText(r5);
        r4 = r2.checkOutDateTv;
        r5 = r1.bookings;
        r5 = r5.get(r3);
        r5 = (com.data.bookings.BookingProperty) r5;
        r5 = r5.getEndDate();
        r5 = com.jelsat.utils.Utils.bookingsDateFormat(r5);
        r4.setText(r5);
        r4 = r2.nightsCountTv;
        r5 = "%s %s";
        r7 = 2;
        r8 = new java.lang.Object[r7];
        r10 = r1.bookings;
        r10 = r10.get(r3);
        r10 = (com.data.bookings.BookingProperty) r10;
        r10 = r10.getNights();
        r8[r9] = r10;
        r10 = r1.context;
        r10 = r10.getResources();
        r11 = 2131821067; // 0x7f11020b float:1.9274867E38 double:1.0532595523E-314;
        r10 = r10.getString(r11);
        r8[r6] = r10;
        r5 = java.lang.String.format(r5, r8);
        r4.setText(r5);
        r4 = java.util.Locale.getDefault();
        r4 = r4.getLanguage();
        r5 = "ar";
        r4 = r4.equalsIgnoreCase(r5);
        if (r4 == 0) goto L_0x0311;
    L_0x00c0:
        r4 = r1.bookings;
        r4 = r4.get(r3);
        r4 = (com.data.bookings.BookingProperty) r4;
        r4 = r4.getStartDay();
        r5 = r4.hashCode();
        switch(r5) {
            case 70909: goto L_0x0110;
            case 77548: goto L_0x0106;
            case 82886: goto L_0x00fc;
            case 83500: goto L_0x00f2;
            case 84065: goto L_0x00e8;
            case 84452: goto L_0x00de;
            case 86838: goto L_0x00d4;
            default: goto L_0x00d3;
        };
    L_0x00d3:
        goto L_0x011a;
    L_0x00d4:
        r5 = "Wed";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x011a;
    L_0x00dc:
        r4 = 2;
        goto L_0x011b;
    L_0x00de:
        r5 = "Tue";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x011a;
    L_0x00e6:
        r4 = 1;
        goto L_0x011b;
    L_0x00e8:
        r5 = "Thu";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x011a;
    L_0x00f0:
        r4 = 3;
        goto L_0x011b;
    L_0x00f2:
        r5 = "Sun";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x011a;
    L_0x00fa:
        r4 = 6;
        goto L_0x011b;
    L_0x00fc:
        r5 = "Sat";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x011a;
    L_0x0104:
        r4 = 5;
        goto L_0x011b;
    L_0x0106:
        r5 = "Mon";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x011a;
    L_0x010e:
        r4 = 0;
        goto L_0x011b;
    L_0x0110:
        r5 = "Fri";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x011a;
    L_0x0118:
        r4 = 4;
        goto L_0x011b;
    L_0x011a:
        r4 = -1;
    L_0x011b:
        r14 = 2131820701; // 0x7f11009d float:1.9274124E38 double:1.0532593715E-314;
        r15 = 2131820703; // 0x7f11009f float:1.9274128E38 double:1.0532593724E-314;
        r7 = 2131820700; // 0x7f11009c float:1.9274122E38 double:1.053259371E-314;
        r8 = 2131820696; // 0x7f110098 float:1.9274114E38 double:1.053259369E-314;
        r10 = 2131820698; // 0x7f11009a float:1.9274118E38 double:1.05325937E-314;
        r11 = 2131820699; // 0x7f11009b float:1.927412E38 double:1.0532593705E-314;
        switch(r4) {
            case 0: goto L_0x01cf;
            case 1: goto L_0x01b5;
            case 2: goto L_0x019b;
            case 3: goto L_0x0181;
            case 4: goto L_0x0167;
            case 5: goto L_0x014c;
            case 6: goto L_0x0131;
            default: goto L_0x0130;
        };
    L_0x0130:
        return;
    L_0x0131:
        r4 = r2.startWeekDay;
        r12 = "%s";
        r13 = new java.lang.Object[r6];
        r5 = r1.context;
        r5 = r5.getResources();
        r5 = r5.getString(r11);
        r13[r9] = r5;
        r5 = java.lang.String.format(r12, r13);
        r4.setText(r5);
        goto L_0x01eb;
    L_0x014c:
        r4 = r2.startWeekDay;
        r5 = "%s";
        r12 = new java.lang.Object[r6];
        r13 = r1.context;
        r13 = r13.getResources();
        r13 = r13.getString(r10);
        r12[r9] = r13;
        r5 = java.lang.String.format(r5, r12);
        r4.setText(r5);
        goto L_0x01eb;
    L_0x0167:
        r4 = r2.startWeekDay;
        r5 = "%s";
        r12 = new java.lang.Object[r6];
        r13 = r1.context;
        r13 = r13.getResources();
        r13 = r13.getString(r8);
        r12[r9] = r13;
        r5 = java.lang.String.format(r5, r12);
        r4.setText(r5);
        goto L_0x01eb;
    L_0x0181:
        r4 = r2.startWeekDay;
        r5 = "%s";
        r12 = new java.lang.Object[r6];
        r13 = r1.context;
        r13 = r13.getResources();
        r13 = r13.getString(r7);
        r12[r9] = r13;
        r5 = java.lang.String.format(r5, r12);
        r4.setText(r5);
        goto L_0x01eb;
    L_0x019b:
        r4 = r2.startWeekDay;
        r5 = "%s";
        r12 = new java.lang.Object[r6];
        r13 = r1.context;
        r13 = r13.getResources();
        r13 = r13.getString(r15);
        r12[r9] = r13;
        r5 = java.lang.String.format(r5, r12);
        r4.setText(r5);
        goto L_0x01eb;
    L_0x01b5:
        r4 = r2.startWeekDay;
        r5 = "%s";
        r12 = new java.lang.Object[r6];
        r13 = r1.context;
        r13 = r13.getResources();
        r13 = r13.getString(r14);
        r12[r9] = r13;
        r5 = java.lang.String.format(r5, r12);
        r4.setText(r5);
        goto L_0x01eb;
    L_0x01cf:
        r4 = r2.startWeekDay;
        r5 = "%s";
        r12 = new java.lang.Object[r6];
        r13 = r1.context;
        r13 = r13.getResources();
        r14 = 2131820697; // 0x7f110099 float:1.9274116E38 double:1.0532593695E-314;
        r13 = r13.getString(r14);
        r12[r9] = r13;
        r5 = java.lang.String.format(r5, r12);
        r4.setText(r5);
    L_0x01eb:
        r4 = r1.bookings;
        r4 = r4.get(r3);
        r4 = (com.data.bookings.BookingProperty) r4;
        r4 = r4.getEndDay();
        r5 = r4.hashCode();
        switch(r5) {
            case 70909: goto L_0x0241;
            case 77548: goto L_0x0236;
            case 82886: goto L_0x022b;
            case 83500: goto L_0x0220;
            case 84065: goto L_0x0215;
            case 84452: goto L_0x020a;
            case 86838: goto L_0x01ff;
            default: goto L_0x01fe;
        };
    L_0x01fe:
        goto L_0x024c;
    L_0x01ff:
        r5 = "Wed";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x024c;
    L_0x0207:
        r16 = 2;
        goto L_0x024e;
    L_0x020a:
        r5 = "Tue";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x024c;
    L_0x0212:
        r16 = 1;
        goto L_0x024e;
    L_0x0215:
        r5 = "Thu";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x024c;
    L_0x021d:
        r16 = 3;
        goto L_0x024e;
    L_0x0220:
        r5 = "Sun";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x024c;
    L_0x0228:
        r16 = 6;
        goto L_0x024e;
    L_0x022b:
        r5 = "Sat";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x024c;
    L_0x0233:
        r16 = 5;
        goto L_0x024e;
    L_0x0236:
        r5 = "Mon";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x024c;
    L_0x023e:
        r16 = 0;
        goto L_0x024e;
    L_0x0241:
        r5 = "Fri";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x024c;
    L_0x0249:
        r16 = 4;
        goto L_0x024e;
    L_0x024c:
        r16 = -1;
    L_0x024e:
        switch(r16) {
            case 0: goto L_0x02f4;
            case 1: goto L_0x02d7;
            case 2: goto L_0x02bd;
            case 3: goto L_0x02a3;
            case 4: goto L_0x0288;
            case 5: goto L_0x026d;
            case 6: goto L_0x0252;
            default: goto L_0x0251;
        };
    L_0x0251:
        return;
    L_0x0252:
        r4 = r2.endWeekDay;
        r5 = "%s";
        r6 = new java.lang.Object[r6];
        r7 = r1.context;
        r7 = r7.getResources();
        r7 = r7.getString(r11);
        r6[r9] = r7;
        r5 = java.lang.String.format(r5, r6);
        r4.setText(r5);
        goto L_0x0333;
    L_0x026d:
        r4 = r2.endWeekDay;
        r5 = "%s";
        r6 = new java.lang.Object[r6];
        r7 = r1.context;
        r7 = r7.getResources();
        r7 = r7.getString(r10);
        r6[r9] = r7;
        r5 = java.lang.String.format(r5, r6);
        r4.setText(r5);
        goto L_0x0333;
    L_0x0288:
        r4 = r2.endWeekDay;
        r5 = "%s";
        r6 = new java.lang.Object[r6];
        r7 = r1.context;
        r7 = r7.getResources();
        r7 = r7.getString(r8);
        r6[r9] = r7;
        r5 = java.lang.String.format(r5, r6);
        r4.setText(r5);
        goto L_0x0333;
    L_0x02a3:
        r4 = r2.endWeekDay;
        r5 = "%s";
        r6 = new java.lang.Object[r6];
        r8 = r1.context;
        r8 = r8.getResources();
        r7 = r8.getString(r7);
        r6[r9] = r7;
        r5 = java.lang.String.format(r5, r6);
        r4.setText(r5);
        goto L_0x0333;
    L_0x02bd:
        r4 = r2.endWeekDay;
        r5 = "%s";
        r6 = new java.lang.Object[r6];
        r7 = r1.context;
        r7 = r7.getResources();
        r7 = r7.getString(r15);
        r6[r9] = r7;
        r5 = java.lang.String.format(r5, r6);
        r4.setText(r5);
        goto L_0x0333;
    L_0x02d7:
        r4 = r2.endWeekDay;
        r5 = "%s";
        r6 = new java.lang.Object[r6];
        r7 = r1.context;
        r7 = r7.getResources();
        r8 = 2131820701; // 0x7f11009d float:1.9274124E38 double:1.0532593715E-314;
        r7 = r7.getString(r8);
        r6[r9] = r7;
        r5 = java.lang.String.format(r5, r6);
        r4.setText(r5);
        goto L_0x0333;
    L_0x02f4:
        r4 = r2.endWeekDay;
        r5 = "%s";
        r6 = new java.lang.Object[r6];
        r7 = r1.context;
        r7 = r7.getResources();
        r8 = 2131820697; // 0x7f110099 float:1.9274116E38 double:1.0532593695E-314;
        r7 = r7.getString(r8);
        r6[r9] = r7;
        r5 = java.lang.String.format(r5, r6);
        r4.setText(r5);
        goto L_0x0333;
    L_0x0311:
        r4 = r2.startWeekDay;
        r5 = r1.bookings;
        r5 = r5.get(r3);
        r5 = (com.data.bookings.BookingProperty) r5;
        r5 = r5.getStartDay();
        r4.setText(r5);
        r4 = r2.endWeekDay;
        r5 = r1.bookings;
        r5 = r5.get(r3);
        r5 = (com.data.bookings.BookingProperty) r5;
        r5 = r5.getEndDay();
        r4.setText(r5);
    L_0x0333:
        r4 = r2.bookingStatusTv;
        r5 = r1.bookings;
        r5 = r5.get(r3);
        r5 = (com.data.bookings.BookingProperty) r5;
        r5 = r5.getBookingStatus();
        r4.setText(r5);
        r4 = r1.context;
        r4 = com.jelsat.utils.GlideApp.with(r4);
        r5 = r1.bookings;
        r5 = r5.get(r3);
        r5 = (com.data.bookings.BookingProperty) r5;
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
        r5 = r2.propertyImageView;
        r4.into(r5);
        r4 = r1.bookings;	 Catch:{ Exception -> 0x0392 }
        r4 = r4.get(r3);	 Catch:{ Exception -> 0x0392 }
        r4 = (com.data.bookings.BookingProperty) r4;	 Catch:{ Exception -> 0x0392 }
        r4 = r4.getBookingType();	 Catch:{ Exception -> 0x0392 }
        r4 = java.lang.Integer.parseInt(r4);	 Catch:{ Exception -> 0x0392 }
        goto L_0x0398;
    L_0x0392:
        r0 = move-exception;
        r4 = r0;
        r4.printStackTrace();
        r4 = 0;
    L_0x0398:
        r1.setBookingTypeImage(r2, r4);
        r4 = r1.bookingsUtil;
        r5 = r1.bookings;
        r3 = r5.get(r3);
        r3 = (com.data.bookings.BookingProperty) r3;
        r3 = r3.getBookingStatusCode();
        r1.updateViews(r2, r4, r3);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jelsat.adapters.GuestBookingsAdapter.onBindViewHolder(com.jelsat.adapters.GuestBookingsAdapter$GuestBookingsHolder, int):void");
    }

    private void setBookingTypeImage(GuestBookingsHolder guestBookingsHolder, int i) {
        switch (i) {
            case 0:
                if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar") != 0) {
                    guestBookingsHolder.bookingTypeView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_24hours_booking, 0);
                } else {
                    guestBookingsHolder.bookingTypeView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_24hours_booking, 0, 0, 0);
                }
                guestBookingsHolder.bookingTypeView.setText(this.context.getString(R.string.guest_bookings_regular));
                guestBookingsHolder.bookingTypeView.setTextColor(applyColor(R.color.bookings_type_color));
                break;
            case 1:
                if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar") != 0) {
                    guestBookingsHolder.bookingTypeView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_instant_booking, 0);
                } else {
                    guestBookingsHolder.bookingTypeView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_instant_booking, 0, 0, 0);
                }
                guestBookingsHolder.bookingTypeView.setText(this.context.getString(R.string.guest_bookings_instant));
                guestBookingsHolder.bookingTypeView.setTextColor(applyColor(R.color.bookings_type_color));
                return;
            default:
                break;
        }
    }

    public int applyColor(@ColorRes int i) {
        return ContextCompat.getColor(this.context, i);
    }

    public int getItemCount() {
        return this.bookings != null ? this.bookings.size() : 0;
    }

    public void setData(List<BookingProperty> list, BookingsUtil bookingsUtil) {
        this.bookings = list;
        this.bookingsUtil = bookingsUtil;
        notifyDataSetChanged();
    }

    private void updateViews(GuestBookingsHolder guestBookingsHolder, BookingsUtil bookingsUtil, String str) {
        Log.e("GuestBookingAdapter", "updateViews() has called");
        guestBookingsHolder.bookingStatusTv.setTextColor(applyColor(getBookingStatusColor(str)));
        makePreviousViewBillEnable(guestBookingsHolder);
        makeViewBillEnable(guestBookingsHolder);
        if (bookingsUtil.getBookingType() == 2) {
            guestBookingsHolder.actionLayout.setVisibility(8);
            guestBookingsHolder.previousLayout.setVisibility(0);
            if (str.equalsIgnoreCase(StringConstants.BOOKING_SETTLED) == null) {
                if (str.equalsIgnoreCase(StringConstants.BOOKING_CONFIRMED) == null) {
                    Log.e("GuestBookingAdapter", "UpdateView() else condition");
                    makeFeedabckDisable(guestBookingsHolder);
                    makeBookAgainDisable(guestBookingsHolder);
                    this.feedbackSubmitted = true;
                    return;
                }
            }
            if (!(((BookingProperty) this.bookings.get(guestBookingsHolder.getAdapterPosition())).getGuestFeedback().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_YES) == null || this.feedbackSubmitted == null)) {
                Log.e("GuestBookingAdapter", "UpdateView()if Condition");
                makeFeedabckEnable(guestBookingsHolder);
            }
            makeBookAgainEnable(guestBookingsHolder);
            return;
        }
        guestBookingsHolder.actionLayout.setVisibility(0);
        guestBookingsHolder.previousLayout.setVisibility(8);
        if (str.equalsIgnoreCase(StringConstants.BOOKING_CONFIRMED) != null) {
            makeGetDirectionEnable(guestBookingsHolder);
            makeMessageEnable(guestBookingsHolder);
            makeCancelEnable(guestBookingsHolder);
        } else if (str.equalsIgnoreCase(StringConstants.BOOKING_WAITING) != null) {
            makeGetDirectionDisable(guestBookingsHolder);
            makeMessageDisable(guestBookingsHolder);
            makeCancelEnable(guestBookingsHolder);
        } else {
            makeGetDirectionDisable(guestBookingsHolder);
            makeMessageDisable(guestBookingsHolder);
            makeCancelDisable(guestBookingsHolder);
        }
    }

    public void refreshListItem(int i, boolean z) {
        ((BookingProperty) this.bookings.get(i)).setGuestFeedback(AppEventsConstants.EVENT_PARAM_VALUE_NO);
        StringBuilder stringBuilder = new StringBuilder("refreshListItem() has called, pos = ");
        stringBuilder.append(i);
        stringBuilder.append("& feedbackSub : ");
        stringBuilder.append(z);
        Log.e("GuestBookingsAdapter", stringBuilder.toString());
        this.feedbackSubmitted = z;
        notifyItemChanged(i);
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
        throw new UnsupportedOperationException("Method not decompiled: com.jelsat.adapters.GuestBookingsAdapter.getBookingStatusColor(java.lang.String):int");
    }

    private void goToPropertyDescription(int i, BookingsUtil bookingsUtil) {
        bookingsUtil = ((BookingProperty) this.bookings.get(i)).getPropertyId().trim();
        String bookingType = ((BookingProperty) this.bookings.get(i)).getBookingType();
        String image = ((BookingProperty) this.bookings.get(i)).getImage();
        String bookingStatusCode = ((BookingProperty) this.bookings.get(i)).getBookingStatusCode();
        if (!TextUtils.isEmpty(bookingsUtil)) {
            SearchProperty searchProperty = new SearchProperty();
            searchProperty.setPropertyId(bookingsUtil);
            searchProperty.setType(bookingType);
            searchProperty.setImage(image);
            bookingsUtil = new PropertyDetailEvent(searchProperty);
            bookingsUtil.setCheckInDate(com.jelsat.utils.Utils.bookingsCheckInCheckoutDateInDateFormat(((BookingProperty) this.bookings.get(i)).getStartDate()));
            bookingsUtil.setCheckoutDate(com.jelsat.utils.Utils.bookingsCheckInCheckoutDateInDateFormat(((BookingProperty) this.bookings.get(i)).getEndDate()));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(((BookingProperty) this.bookings.get(i)).getTotalGuests());
            bookingsUtil.setGuestCount(stringBuilder.toString());
            bookingsUtil.setBookingId(((BookingProperty) this.bookings.get(i)).getOrderId());
            bookingsUtil.setLocationName(((BookingProperty) this.bookings.get(i)).getCountry());
            bookingsUtil.setFromBookings(true);
            if (bookingStatusCode.equalsIgnoreCase(StringConstants.BOOKING_CONFIRMED)) {
                bookingsUtil.setShowMessage(true);
            } else {
                bookingsUtil.setShowMessage(0);
            }
            this.listener.gotoBookingDetailScreen(bookingsUtil);
        }
    }

    private void goToFeedback(int i) {
        Log.e("GuestBookingAdapter", "goToFeedback() is called");
        String propertyId = ((BookingProperty) this.bookings.get(i)).getPropertyId();
        String orderId = ((BookingProperty) this.bookings.get(i)).getOrderId();
        if (propertyId != null && !propertyId.equals("")) {
            this.listener.clickOnFeedBack(propertyId, orderId, i);
        }
    }

    private void goBookAgain(int i) {
        if (((BookingProperty) this.bookings.get(i)).getPropertyId() != null && !((BookingProperty) this.bookings.get(i)).getPropertyId().equals("")) {
            SearchProperty searchProperty = new SearchProperty();
            searchProperty.setPropertyId(((BookingProperty) this.bookings.get(i)).getPropertyId());
            searchProperty.setType(((BookingProperty) this.bookings.get(i)).getBookingType());
            PropertyDetailEvent propertyDetailEvent = new PropertyDetailEvent(searchProperty);
            propertyDetailEvent.setBookingId(((BookingProperty) this.bookings.get(i)).getOrderId());
            propertyDetailEvent.setLocationName(((BookingProperty) this.bookings.get(i)).getCountry());
            this.listener.gotoBookingDetailScreen(propertyDetailEvent);
        }
    }

    private void goToGetDirections(int i) {
        this.listener.clickOnGetDirections(new LatLng(Double.parseDouble(((BookingProperty) this.bookings.get(i)).getLat()), Double.parseDouble(((BookingProperty) this.bookings.get(i)).getLng())));
    }

    private void goToMessages(int i) {
        String propertyId = ((BookingProperty) this.bookings.get(i)).getPropertyId();
        i = ((BookingProperty) this.bookings.get(i)).getOrderId();
        int i2 = 0;
        int i3 = propertyId != null ? 1 : 0;
        if (i != 0) {
            i2 = 1;
        }
        if ((i2 & i3) != 0) {
            this.listener.clickOnSendMessage(propertyId, i);
        }
    }

    private void makeGetDirectionDisable(GuestBookingsHolder guestBookingsHolder) {
        guestBookingsHolder.getDirectionIv.setClickable(false);
        guestBookingsHolder.getDirectionIv.setImageAlpha(70);
    }

    private void makeGetDirectionEnable(GuestBookingsHolder guestBookingsHolder) {
        guestBookingsHolder.getDirectionIv.setClickable(true);
        guestBookingsHolder.getDirectionIv.setImageAlpha(255);
    }

    private void makeFeedabckDisable(GuestBookingsHolder guestBookingsHolder) {
        guestBookingsHolder.feedbackIv.setClickable(false);
        guestBookingsHolder.feedbackIv.setImageResource(R.drawable.iv_grey_bookings_feedback);
        guestBookingsHolder.feedbackIv.setImageAlpha(70);
    }

    private void makeFeedabckEnable(GuestBookingsHolder guestBookingsHolder) {
        guestBookingsHolder.feedbackIv.setClickable(true);
        guestBookingsHolder.feedbackIv.setImageResource(R.drawable.ic_bookings_feedback);
        guestBookingsHolder.feedbackIv.setImageAlpha(255);
    }

    private void makeCancelDisable(GuestBookingsHolder guestBookingsHolder) {
        guestBookingsHolder.cancelIv.setClickable(false);
        guestBookingsHolder.cancelIv.setImageResource(R.drawable.iv_grey_bookings_cancel);
        guestBookingsHolder.cancelIv.setImageAlpha(70);
    }

    private void makeCancelEnable(GuestBookingsHolder guestBookingsHolder) {
        guestBookingsHolder.cancelIv.setClickable(true);
        guestBookingsHolder.cancelIv.setImageResource(R.drawable.ic_bookings_cancel);
        guestBookingsHolder.cancelIv.setImageAlpha(255);
    }

    private void makePreviousViewBillEnable(GuestBookingsHolder guestBookingsHolder) {
        guestBookingsHolder.viewBillPreivousIv.setClickable(true);
        guestBookingsHolder.viewBillPreivousIv.setImageAlpha(255);
    }

    private void makeViewBillEnable(GuestBookingsHolder guestBookingsHolder) {
        guestBookingsHolder.viewBillIv.setClickable(true);
        guestBookingsHolder.viewBillIv.setImageAlpha(255);
    }

    private void makeMessageDisable(GuestBookingsHolder guestBookingsHolder) {
        guestBookingsHolder.messageIv.setClickable(false);
        guestBookingsHolder.messageIv.setImageResource(R.drawable.iv_grey_booking_message);
        guestBookingsHolder.messageIv.setImageAlpha(70);
    }

    private void makeMessageEnable(GuestBookingsHolder guestBookingsHolder) {
        guestBookingsHolder.messageIv.setClickable(true);
        guestBookingsHolder.messageIv.setImageResource(R.drawable.ic_booking_message);
        guestBookingsHolder.messageIv.setImageAlpha(255);
    }

    private void makeBookAgainEnable(GuestBookingsHolder guestBookingsHolder) {
        guestBookingsHolder.bookAgainTv.setClickable(true);
        guestBookingsHolder.bookAgainTv.setImageResource(R.drawable.ic_book_again);
        guestBookingsHolder.bookAgainTv.setImageAlpha(255);
    }

    private void makeBookAgainDisable(GuestBookingsHolder guestBookingsHolder) {
        guestBookingsHolder.bookAgainTv.setClickable(false);
        guestBookingsHolder.bookAgainTv.setImageResource(R.drawable.iv_grey_book_again);
        guestBookingsHolder.bookAgainTv.setImageAlpha(70);
    }
}
