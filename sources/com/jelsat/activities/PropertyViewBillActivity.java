package com.jelsat.activities;

import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.businesslogic.viewbill.IPropertyViewBillView;
import com.businesslogic.viewbill.PropertyViewBillPresenter;
import com.data.amenitiesandhouserules.HouseRuleSafetyPolicy;
import com.data.amenitiesandhouserules.HouseSafety;
import com.data.amenitiesandhouserules.Houserule;
import com.data.retrofit.RetrofitClient;
import com.data.signin.UserModel;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;
import com.data.viewbill.PropertyViewBillRequest;
import com.data.viewbill.PropertyViewBillResponse;
import com.data.viewbill.Propertydata;
import com.facebook.appevents.AppEventsConstants;
import com.jelsat.R;
import com.jelsat.adapters.CustomExpandableListAdapter;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import com.jelsat.customclasses.CustomTextView;
import com.jelsat.customclasses.NonScrollExpandableListView;
import com.jelsat.events.BookPersonalDetailsEvent;
import com.jelsat.events.DateGuestPropertyDialogEvent;
import com.jelsat.events.PropertyBookEvent;
import com.jelsat.events.PropertyViewBillEvent;
import com.jelsat.fragments.DateGuestPropertySelectionDialog;
import com.jelsat.payfort.PayFortPayment;
import com.jelsat.utils.AlertDialogUtil;
import com.jelsat.utils.GlideApp;
import com.jelsat.widgets.FancyButton;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class PropertyViewBillActivity extends BaseAppCompactActivity implements IPropertyViewBillView {
    @BindView(2131361846)
    LinearLayout addOnsLayout;
    private AlertDialogUtil alertDialog;
    private Date checkInDate;
    @BindView(2131362003)
    CustomTextView checkinCheckoutLay;
    private Date checkoutDate;
    @BindView(2131362102)
    CustomTextView distanceTV;
    @BindView(2131362131)
    TextView endWeekDay;
    @BindView(2131362151)
    NonScrollExpandableListView expandableListView;
    @BindView(2131362164)
    ImageView favourite;
    private String guestCount = AppEventsConstants.EVENT_PARAM_VALUE_YES;
    private String locationName;
    private String maxGuestCount;
    private int minimumNights;
    @BindView(2131362357)
    FancyButton nextButton;
    @BindView(2131362407)
    LinearLayout payableAmountLayout;
    @BindView(2131362406)
    TextView payableAmountTV;
    @BindView(2131362409)
    LinearLayout paymentBreakDownLayout;
    @BindView(2131362431)
    TextView pricePerNightAmountTV;
    @BindView(2131362430)
    TextView pricePerNightTv;
    @BindView(2131362427)
    TextView priceTV;
    private String propertyAddress;
    @BindView(2131362449)
    TextView propertyAddressTV;
    @BindView(2131362453)
    TextView propertyBookingUserName;
    @BindView(2131362455)
    TextView propertyCheckInDateTV;
    @BindView(2131362456)
    TextView propertyCheckOutDateTV;
    @BindView(2131362463)
    FancyButton propertyDurationTV;
    private String propertyId;
    @BindView(2131362468)
    ImageView propertyImage;
    @BindView(2131362477)
    TextView propertyNameTV;
    @BindView(2131362578)
    TextView selectedGuestCountTV;
    @BindView(2131362621)
    TextView startWeekDay;
    @BindView(2131362686)
    TextView totalAmountTV;
    @BindView(2131362687)
    CardView totalBillCardView;
    private PropertyViewBillPresenter viewBillPresenter = new PropertyViewBillPresenter(this, RetrofitClient.getAPIService());

    public int getActivityLayout() {
        return R.layout.activity_property_viewbill;
    }

    @OnClick({2131361892})
    public void clickOnBack() {
        goToPropertyDetailScreen();
    }

    @OnClick({2131361959})
    public void clickOnCancel() {
        goToPropertyDetailScreen();
    }

    @OnClick({2131362357})
    public void clickOnNext() {
        UserModel user = PrefUtils.getInstance().getUser();
        if (!TextUtils.isEmpty(user.getDateOfBirth().trim())) {
            if (!TextUtils.isEmpty(user.getNationalId().trim())) {
                PropertyBookEvent propertyBookEvent = new PropertyBookEvent(this.propertyId);
                propertyBookEvent.setCheckInDate(this.checkInDate);
                propertyBookEvent.setCheckOutDate(this.checkoutDate);
                propertyBookEvent.setSelectedGuestCount(this.guestCount);
                EventBus.getDefault().postSticky(propertyBookEvent);
                startActivity(new Intent(this, BookingPaymentActivity.class));
                return;
            }
        }
        BookPersonalDetailsEvent bookPersonalDetailsEvent = new BookPersonalDetailsEvent(this.propertyId);
        bookPersonalDetailsEvent.setCheckInDate(this.checkInDate);
        bookPersonalDetailsEvent.setCheckOutDate(this.checkoutDate);
        bookPersonalDetailsEvent.setSelectedGuestCount(this.guestCount);
        EventBus.getDefault().postSticky(bookPersonalDetailsEvent);
        startActivity(new Intent(this, BookingPersonalDetailsActivity.class));
    }

    @OnClick({2131362823})
    public void clickOnViewBillButton() {
        this.payableAmountLayout.setVisibility(8);
        this.paymentBreakDownLayout.setVisibility(0);
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (VERSION.SDK_INT >= 21) {
            bundle = getWindow();
            bundle.addFlags(Integer.MIN_VALUE);
            bundle.setStatusBarColor(applyColor(R.color.dialog_save));
        }
        this.alertDialog = new AlertDialogUtil(this);
        this.propertyBookingUserName.setText(capitalize(PrefUtils.getInstance().getUser().getFullName()));
    }

    private String capitalize(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        str = Pattern.compile("([a-z])([a-z]*)", 2).matcher(str);
        while (str.find()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str.group(1).toUpperCase());
            stringBuilder.append(str.group(2).toLowerCase());
            str.appendReplacement(stringBuffer, stringBuilder.toString());
        }
        return str.appendTail(stringBuffer).toString();
    }

    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getProperty(PropertyViewBillEvent propertyViewBillEvent) {
        if (propertyViewBillEvent != null && propertyViewBillEvent.getResponse().getPropertydata() != null) {
            this.propertyId = propertyViewBillEvent.getResponse().getPropertydata().getPropertyId();
            initPropertyImage(propertyViewBillEvent.getResponse().getPropertydata().getImage());
            if (!TextUtils.isEmpty(propertyViewBillEvent.getAddressName())) {
                this.propertyAddress = propertyViewBillEvent.getAddressName();
            }
            initPropertyDetails(propertyViewBillEvent.getResponse().getPropertydata());
            if (!(propertyViewBillEvent.getResponse().getCheckIn() == null || propertyViewBillEvent.getResponse().getCheckOut() == null)) {
                this.checkinCheckoutLay.setText(String.format("%s %s, %s %s", new Object[]{getString(R.string.check_in_after), propertyViewBillEvent.getResponse().getCheckIn(), getString(R.string.check_in_before), propertyViewBillEvent.getResponse().getCheckOut()}));
                Log.e("checkin :", propertyViewBillEvent.getResponse().getCheckIn());
                Log.e("checkout :", propertyViewBillEvent.getResponse().getCheckOut());
            }
            this.checkInDate = propertyViewBillEvent.getCheckInDate();
            this.checkoutDate = propertyViewBillEvent.getCheckOutDate();
            this.locationName = propertyViewBillEvent.getLocationName();
            this.minimumNights = propertyViewBillEvent.getMinimumNights();
            if (propertyViewBillEvent.getResponse().getGuests() != null) {
                this.guestCount = propertyViewBillEvent.getResponse().getGuests();
            }
            if (propertyViewBillEvent.getMaxGuestCount() != null) {
                this.maxGuestCount = propertyViewBillEvent.getMaxGuestCount();
            }
            initSelectedDatesAndPriceOfProperty(propertyViewBillEvent.getResponse());
            EventBus.getDefault().removeStickyEvent(propertyViewBillEvent);
        }
    }

    private void initPropertyImage(String str) {
        GlideApp.with(this).asBitmap().load(str).placeholder(R.drawable.default_logo_dashboard).diskCacheStrategy(DiskCacheStrategy.DATA).fitCenter().into(this.propertyImage);
    }

    private void initPropertyDetails(Propertydata propertydata) {
        this.favourite.setImageResource(propertydata.getFavourite() == 1 ? R.drawable.ic_favorite_red_24dp : R.drawable.ic_favourite);
        if (propertydata.getCurrencyCode() != null && propertydata.getCurrencyCode().equalsIgnoreCase(PayFortPayment.CURRENCY_TYPE)) {
            if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar")) {
                this.priceTV.setText(Html.fromHtml(String.format(Locale.getDefault(), "<b>%.2f</b>%s/ %s", new Object[]{Float.valueOf(propertydata.getPrice()), getString(R.string.saudi_currency), getString(R.string.per_night_label)})));
            } else {
                this.priceTV.setText(Html.fromHtml(String.format(Locale.getDefault(), "%s <b>%.2f</b>/ %s", new Object[]{getString(R.string.saudi_currency), Float.valueOf(propertydata.getPrice()), getString(R.string.per_night_label)})));
            }
        }
        this.propertyNameTV.setText(propertydata.getName());
        this.propertyAddressTV.setText(this.propertyAddress);
        List<String> arrayList = new ArrayList();
        for (Houserule id : propertydata.getHouseRules()) {
            arrayList.add(id.getId());
        }
        if (!TextUtils.isEmpty(propertydata.getCustomHouseRule())) {
            arrayList.add("111");
        }
        Map linkedHashMap = new LinkedHashMap();
        for (Houserule houserule : PrefUtils.getInstance().getSeedResponse().getHouserules()) {
            HouseRuleSafetyPolicy houseRuleSafetyPolicy = new HouseRuleSafetyPolicy();
            houseRuleSafetyPolicy.setId(houserule.getId());
            houseRuleSafetyPolicy.setName(houserule.getName());
            houseRuleSafetyPolicy.setNotPolicy(true);
            linkedHashMap.put(houserule.getId(), houseRuleSafetyPolicy);
        }
        if (!TextUtils.isEmpty(propertydata.getCustomHouseRule())) {
            HouseRuleSafetyPolicy houseRuleSafetyPolicy2 = new HouseRuleSafetyPolicy();
            houseRuleSafetyPolicy2.setId("111");
            houseRuleSafetyPolicy2.setName(propertydata.getCustomHouseRule());
            houseRuleSafetyPolicy2.setNotPolicy(true);
            linkedHashMap.put("111", houseRuleSafetyPolicy2);
        }
        for (String str : arrayList) {
            if (linkedHashMap.containsKey(str)) {
                ((HouseRuleSafetyPolicy) linkedHashMap.get(str)).setChecked(true);
            }
        }
        arrayList = new ArrayList();
        for (HouseSafety id2 : propertydata.getHouseSafety()) {
            arrayList.add(id2.getId());
        }
        Map linkedHashMap2 = new LinkedHashMap();
        for (HouseSafety houseSafety : PrefUtils.getInstance().getSeedResponse().getHouseSafety()) {
            HouseRuleSafetyPolicy houseRuleSafetyPolicy3 = new HouseRuleSafetyPolicy();
            houseRuleSafetyPolicy3.setId(houseSafety.getId());
            houseRuleSafetyPolicy3.setName(houseSafety.getName());
            houseRuleSafetyPolicy3.setNotPolicy(true);
            linkedHashMap2.put(houseSafety.getId(), houseRuleSafetyPolicy3);
        }
        for (String str2 : arrayList) {
            if (linkedHashMap2.containsKey(str2)) {
                ((HouseRuleSafetyPolicy) linkedHashMap2.get(str2)).setChecked(true);
            }
        }
        List arrayList2 = new ArrayList();
        HouseRuleSafetyPolicy houseRuleSafetyPolicy4 = new HouseRuleSafetyPolicy();
        houseRuleSafetyPolicy4.setName(propertydata.getCancelDescription());
        houseRuleSafetyPolicy4.setNotPolicy(false);
        arrayList2.add(houseRuleSafetyPolicy4);
        Map linkedHashMap3 = new LinkedHashMap();
        linkedHashMap3.put(getString(R.string.house_rules_label), new ArrayList(linkedHashMap.values()));
        linkedHashMap3.put(getString(R.string.house_safety_label), new ArrayList(linkedHashMap2.values()));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getString(R.string.property_details_canaelation_policy));
        stringBuilder.append("-%s");
        linkedHashMap3.put(String.format(stringBuilder.toString(), new Object[]{propertydata.getCancellationPolicy()}), arrayList2);
        this.expandableListView.setAdapter(new CustomExpandableListAdapter(this, new ArrayList(linkedHashMap3.keySet()), linkedHashMap3, true));
    }

    private String convertDateToString(String str, Date date) {
        return new SimpleDateFormat(str, Locale.ENGLISH).format(date);
    }

    private void showDateGuestPropertyDialog(int i) {
        if (this.propertyId != null && !this.propertyId.equalsIgnoreCase("")) {
            DateGuestPropertySelectionDialog.newInstance(i, this.checkInDate, this.checkoutDate, this.guestCount, this.maxGuestCount, this.locationName, Integer.parseInt(this.propertyId), this.minimumNights).show(getSupportFragmentManager(), "DateGuestPropertySelectionDialog");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getDateGuestPropertyValues(DateGuestPropertyDialogEvent dateGuestPropertyDialogEvent) {
        if (dateGuestPropertyDialogEvent != null) {
            this.guestCount = dateGuestPropertyDialogEvent.getGuestCount();
            this.checkInDate = dateGuestPropertyDialogEvent.getCheckInDate();
            this.checkoutDate = dateGuestPropertyDialogEvent.getCheckOutDate();
            if (this.checkInDate != null) {
                this.propertyCheckInDateTV.setText(convertDateToString("dd-MM-yy", this.checkInDate));
            } else {
                this.propertyCheckInDateTV.setText(getString(R.string.select_date));
            }
            if (this.checkoutDate != null) {
                this.propertyCheckOutDateTV.setText(convertDateToString("dd-MM-yy", this.checkoutDate));
            } else {
                this.propertyCheckOutDateTV.setText(getString(R.string.select_date));
            }
            if (this.checkInDate == null || this.checkoutDate == null) {
                this.propertyDurationTV.setText(String.format("0%s", new Object[]{getString(R.string.nights)}));
            } else {
                this.propertyDurationTV.setText(String.format("%s %s", new Object[]{Integer.valueOf(getDifferenceOfDates(this.checkInDate, this.checkoutDate)), getString(R.string.nights)}));
            }
            TextView textView = this.selectedGuestCountTV;
            StringBuilder stringBuilder = new StringBuilder("%s ");
            stringBuilder.append(getString(R.string.guests));
            textView.setText(String.format(stringBuilder.toString(), new Object[]{this.guestCount}));
            PropertyViewBillRequest propertyViewBillRequest = new PropertyViewBillRequest();
            propertyViewBillRequest.setPropertyId(this.propertyId);
            if (dateGuestPropertyDialogEvent.getCheckInDate() != null) {
                propertyViewBillRequest.setStartDate(convertDateToString("yyyy-MM-dd", dateGuestPropertyDialogEvent.getCheckInDate()));
            }
            if (dateGuestPropertyDialogEvent.getCheckOutDate() != null) {
                propertyViewBillRequest.setEndDate(convertDateToString("yyyy-MM-dd", dateGuestPropertyDialogEvent.getCheckOutDate()));
            }
            if (dateGuestPropertyDialogEvent.getGuestCount() != null) {
                propertyViewBillRequest.setGuests(dateGuestPropertyDialogEvent.getGuestCount());
            }
            this.viewBillPresenter.getPropertyBill(getString(R.string.please_wait), propertyViewBillRequest);
            EventBus.getDefault().removeStickyEvent(dateGuestPropertyDialogEvent);
        }
    }

    private int getDifferenceOfDates(Date date, Date date2) {
        return (int) ((date2.getTime() - date.getTime()) / 86400000);
    }

    public void onSuccess(PropertyViewBillResponse propertyViewBillResponse) {
        initSelectedDatesAndPriceOfProperty(propertyViewBillResponse);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.annotation.SuppressLint({"DefaultLocale"})
    private void initSelectedDatesAndPriceOfProperty(com.data.viewbill.PropertyViewBillResponse r19) {
        /*
        r18 = this;
        r0 = r18;
        if (r19 == 0) goto L_0x0611;
    L_0x0004:
        r2 = r0.totalBillCardView;
        r3 = 0;
        r2.setVisibility(r3);
        r2 = r0.nextButton;
        r4 = 1;
        r2.setEnabled(r4);
        r2 = r0.nextButton;
        r2.setClickable(r4);
        r2 = r0.paymentBreakDownLayout;
        r5 = 8;
        r2.setVisibility(r5);
        r2 = r19.getPropertydata();
        r2 = r2.getCurrencyCode();
        r5 = 3;
        r6 = 2131821311; // 0x7f1102ff float:1.9275362E38 double:1.053259673E-314;
        r7 = 2;
        if (r2 == 0) goto L_0x009d;
    L_0x002b:
        r2 = r19.getPropertydata();
        r2 = r2.getCurrencyCode();
        r8 = "SAR";
        r2 = r2.equalsIgnoreCase(r8);
        if (r2 == 0) goto L_0x009d;
    L_0x003b:
        r2 = java.util.Locale.getDefault();
        r2 = r2.getLanguage();
        r8 = "ar";
        r2 = r2.equalsIgnoreCase(r8);
        r8 = 2131821148; // 0x7f11025c float:1.927503E38 double:1.0532595923E-314;
        if (r2 == 0) goto L_0x0076;
    L_0x004e:
        r2 = r0.priceTV;
        r9 = "<b>%.2f</b>%s/ %s";
        r10 = new java.lang.Object[r5];
        r11 = r19.getPerNightPrice();
        r11 = java.lang.Float.valueOf(r11);
        r10[r3] = r11;
        r11 = r0.getString(r6);
        r10[r4] = r11;
        r8 = r0.getString(r8);
        r10[r7] = r8;
        r8 = java.lang.String.format(r9, r10);
        r8 = android.text.Html.fromHtml(r8);
        r2.setText(r8);
        goto L_0x009d;
    L_0x0076:
        r2 = r0.priceTV;
        r9 = "%s <b>%.2f</b>/ %s";
        r10 = new java.lang.Object[r5];
        r11 = r0.getString(r6);
        r10[r3] = r11;
        r11 = r19.getPerNightPrice();
        r11 = java.lang.Float.valueOf(r11);
        r10[r4] = r11;
        r8 = r0.getString(r8);
        r10[r7] = r8;
        r8 = java.lang.String.format(r9, r10);
        r8 = android.text.Html.fromHtml(r8);
        r2.setText(r8);
    L_0x009d:
        r2 = r0.checkInDate;
        r8 = 2131821349; // 0x7f110325 float:1.9275439E38 double:1.0532596916E-314;
        if (r2 == 0) goto L_0x00b2;
    L_0x00a4:
        r2 = r0.propertyCheckInDateTV;
        r9 = "dd-MM-yy";
        r10 = r0.checkInDate;
        r9 = r0.convertDateToString(r9, r10);
        r2.setText(r9);
        goto L_0x00bb;
    L_0x00b2:
        r2 = r0.propertyCheckInDateTV;
        r9 = r0.getString(r8);
        r2.setText(r9);
    L_0x00bb:
        r2 = r0.checkoutDate;
        if (r2 == 0) goto L_0x00cd;
    L_0x00bf:
        r2 = r0.propertyCheckOutDateTV;
        r8 = "dd-MM-yy";
        r9 = r0.checkoutDate;
        r8 = r0.convertDateToString(r8, r9);
        r2.setText(r8);
        goto L_0x00d6;
    L_0x00cd:
        r2 = r0.propertyCheckOutDateTV;
        r8 = r0.getString(r8);
        r2.setText(r8);
    L_0x00d6:
        r2 = r19.getStartDay();
        r9 = 4;
        r11 = 5;
        if (r2 == 0) goto L_0x02e9;
    L_0x00de:
        r2 = java.util.Locale.getDefault();
        r2 = r2.getLanguage();
        r12 = "ar";
        r2 = r2.equalsIgnoreCase(r12);
        if (r2 == 0) goto L_0x02d7;
    L_0x00ee:
        r2 = r19.getStartDay();
        r12 = r2.hashCode();
        switch(r12) {
            case 70909: goto L_0x0136;
            case 77548: goto L_0x012c;
            case 82886: goto L_0x0122;
            case 83500: goto L_0x0118;
            case 84065: goto L_0x010e;
            case 84452: goto L_0x0104;
            case 86838: goto L_0x00fa;
            default: goto L_0x00f9;
        };
    L_0x00f9:
        goto L_0x0140;
    L_0x00fa:
        r12 = "Wed";
        r2 = r2.equals(r12);
        if (r2 == 0) goto L_0x0140;
    L_0x0102:
        r2 = 2;
        goto L_0x0141;
    L_0x0104:
        r12 = "Tue";
        r2 = r2.equals(r12);
        if (r2 == 0) goto L_0x0140;
    L_0x010c:
        r2 = 1;
        goto L_0x0141;
    L_0x010e:
        r12 = "Thu";
        r2 = r2.equals(r12);
        if (r2 == 0) goto L_0x0140;
    L_0x0116:
        r2 = 3;
        goto L_0x0141;
    L_0x0118:
        r12 = "Sun";
        r2 = r2.equals(r12);
        if (r2 == 0) goto L_0x0140;
    L_0x0120:
        r2 = 6;
        goto L_0x0141;
    L_0x0122:
        r12 = "Sat";
        r2 = r2.equals(r12);
        if (r2 == 0) goto L_0x0140;
    L_0x012a:
        r2 = 5;
        goto L_0x0141;
    L_0x012c:
        r12 = "Mon";
        r2 = r2.equals(r12);
        if (r2 == 0) goto L_0x0140;
    L_0x0134:
        r2 = 0;
        goto L_0x0141;
    L_0x0136:
        r12 = "Fri";
        r2 = r2.equals(r12);
        if (r2 == 0) goto L_0x0140;
    L_0x013e:
        r2 = 4;
        goto L_0x0141;
    L_0x0140:
        r2 = -1;
    L_0x0141:
        r12 = 2131820696; // 0x7f110098 float:1.9274114E38 double:1.053259369E-314;
        r13 = 2131820698; // 0x7f11009a float:1.9274118E38 double:1.05325937E-314;
        r14 = 2131820699; // 0x7f11009b float:1.927412E38 double:1.0532593705E-314;
        switch(r2) {
            case 0: goto L_0x01d0;
            case 1: goto L_0x01b9;
            case 2: goto L_0x01a2;
            case 3: goto L_0x018b;
            case 4: goto L_0x0177;
            case 5: goto L_0x0163;
            case 6: goto L_0x014e;
            default: goto L_0x014d;
        };
    L_0x014d:
        return;
    L_0x014e:
        r2 = r0.startWeekDay;
        r15 = "%s";
        r8 = new java.lang.Object[r4];
        r16 = r0.getString(r14);
        r8[r3] = r16;
        r8 = java.lang.String.format(r15, r8);
        r2.setText(r8);
        goto L_0x01e6;
    L_0x0163:
        r2 = r0.startWeekDay;
        r8 = "%s";
        r15 = new java.lang.Object[r4];
        r16 = r0.getString(r13);
        r15[r3] = r16;
        r8 = java.lang.String.format(r8, r15);
        r2.setText(r8);
        goto L_0x01e6;
    L_0x0177:
        r2 = r0.startWeekDay;
        r8 = "%s";
        r15 = new java.lang.Object[r4];
        r16 = r0.getString(r12);
        r15[r3] = r16;
        r8 = java.lang.String.format(r8, r15);
        r2.setText(r8);
        goto L_0x01e6;
    L_0x018b:
        r2 = r0.startWeekDay;
        r8 = "%s";
        r15 = new java.lang.Object[r4];
        r10 = 2131820700; // 0x7f11009c float:1.9274122E38 double:1.053259371E-314;
        r10 = r0.getString(r10);
        r15[r3] = r10;
        r8 = java.lang.String.format(r8, r15);
        r2.setText(r8);
        goto L_0x01e6;
    L_0x01a2:
        r2 = r0.startWeekDay;
        r8 = "%s";
        r10 = new java.lang.Object[r4];
        r15 = 2131820703; // 0x7f11009f float:1.9274128E38 double:1.0532593724E-314;
        r15 = r0.getString(r15);
        r10[r3] = r15;
        r8 = java.lang.String.format(r8, r10);
        r2.setText(r8);
        goto L_0x01e6;
    L_0x01b9:
        r2 = r0.startWeekDay;
        r8 = "%s";
        r10 = new java.lang.Object[r4];
        r15 = 2131820701; // 0x7f11009d float:1.9274124E38 double:1.0532593715E-314;
        r15 = r0.getString(r15);
        r10[r3] = r15;
        r8 = java.lang.String.format(r8, r10);
        r2.setText(r8);
        goto L_0x01e6;
    L_0x01d0:
        r2 = r0.startWeekDay;
        r8 = "%s";
        r10 = new java.lang.Object[r4];
        r15 = 2131820697; // 0x7f110099 float:1.9274116E38 double:1.0532593695E-314;
        r15 = r0.getString(r15);
        r10[r3] = r15;
        r8 = java.lang.String.format(r8, r10);
        r2.setText(r8);
    L_0x01e6:
        r2 = r19.getEndDay();
        r8 = r2.hashCode();
        switch(r8) {
            case 70909: goto L_0x022e;
            case 77548: goto L_0x0224;
            case 82886: goto L_0x021a;
            case 83500: goto L_0x0210;
            case 84065: goto L_0x0206;
            case 84452: goto L_0x01fc;
            case 86838: goto L_0x01f2;
            default: goto L_0x01f1;
        };
    L_0x01f1:
        goto L_0x0238;
    L_0x01f2:
        r8 = "Wed";
        r2 = r2.equals(r8);
        if (r2 == 0) goto L_0x0238;
    L_0x01fa:
        r2 = 2;
        goto L_0x0239;
    L_0x01fc:
        r8 = "Tue";
        r2 = r2.equals(r8);
        if (r2 == 0) goto L_0x0238;
    L_0x0204:
        r2 = 1;
        goto L_0x0239;
    L_0x0206:
        r8 = "Thu";
        r2 = r2.equals(r8);
        if (r2 == 0) goto L_0x0238;
    L_0x020e:
        r2 = 3;
        goto L_0x0239;
    L_0x0210:
        r8 = "Sun";
        r2 = r2.equals(r8);
        if (r2 == 0) goto L_0x0238;
    L_0x0218:
        r2 = 6;
        goto L_0x0239;
    L_0x021a:
        r8 = "Sat";
        r2 = r2.equals(r8);
        if (r2 == 0) goto L_0x0238;
    L_0x0222:
        r2 = 5;
        goto L_0x0239;
    L_0x0224:
        r8 = "Mon";
        r2 = r2.equals(r8);
        if (r2 == 0) goto L_0x0238;
    L_0x022c:
        r2 = 0;
        goto L_0x0239;
    L_0x022e:
        r8 = "Fri";
        r2 = r2.equals(r8);
        if (r2 == 0) goto L_0x0238;
    L_0x0236:
        r2 = 4;
        goto L_0x0239;
    L_0x0238:
        r2 = -1;
    L_0x0239:
        switch(r2) {
            case 0: goto L_0x02c0;
            case 1: goto L_0x02a9;
            case 2: goto L_0x0292;
            case 3: goto L_0x027b;
            case 4: goto L_0x0267;
            case 5: goto L_0x0252;
            case 6: goto L_0x023d;
            default: goto L_0x023c;
        };
    L_0x023c:
        return;
    L_0x023d:
        r2 = r0.endWeekDay;
        r8 = "%s";
        r10 = new java.lang.Object[r4];
        r12 = r0.getString(r14);
        r10[r3] = r12;
        r8 = java.lang.String.format(r8, r10);
        r2.setText(r8);
        goto L_0x02e9;
    L_0x0252:
        r2 = r0.endWeekDay;
        r8 = "%s";
        r10 = new java.lang.Object[r4];
        r12 = r0.getString(r13);
        r10[r3] = r12;
        r8 = java.lang.String.format(r8, r10);
        r2.setText(r8);
        goto L_0x02e9;
    L_0x0267:
        r2 = r0.endWeekDay;
        r8 = "%s";
        r10 = new java.lang.Object[r4];
        r12 = r0.getString(r12);
        r10[r3] = r12;
        r8 = java.lang.String.format(r8, r10);
        r2.setText(r8);
        goto L_0x02e9;
    L_0x027b:
        r2 = r0.endWeekDay;
        r8 = "%s";
        r10 = new java.lang.Object[r4];
        r12 = 2131820700; // 0x7f11009c float:1.9274122E38 double:1.053259371E-314;
        r12 = r0.getString(r12);
        r10[r3] = r12;
        r8 = java.lang.String.format(r8, r10);
        r2.setText(r8);
        goto L_0x02e9;
    L_0x0292:
        r2 = r0.endWeekDay;
        r8 = "%s";
        r10 = new java.lang.Object[r4];
        r12 = 2131820703; // 0x7f11009f float:1.9274128E38 double:1.0532593724E-314;
        r12 = r0.getString(r12);
        r10[r3] = r12;
        r8 = java.lang.String.format(r8, r10);
        r2.setText(r8);
        goto L_0x02e9;
    L_0x02a9:
        r2 = r0.endWeekDay;
        r8 = "%s";
        r10 = new java.lang.Object[r4];
        r12 = 2131820701; // 0x7f11009d float:1.9274124E38 double:1.0532593715E-314;
        r12 = r0.getString(r12);
        r10[r3] = r12;
        r8 = java.lang.String.format(r8, r10);
        r2.setText(r8);
        goto L_0x02e9;
    L_0x02c0:
        r2 = r0.endWeekDay;
        r8 = "%s";
        r10 = new java.lang.Object[r4];
        r12 = 2131820697; // 0x7f110099 float:1.9274116E38 double:1.0532593695E-314;
        r12 = r0.getString(r12);
        r10[r3] = r12;
        r8 = java.lang.String.format(r8, r10);
        r2.setText(r8);
        goto L_0x02e9;
    L_0x02d7:
        r2 = r0.startWeekDay;
        r8 = r19.getStartDay();
        r2.setText(r8);
        r2 = r0.endWeekDay;
        r8 = r19.getEndDay();
        r2.setText(r8);
    L_0x02e9:
        r2 = r0.checkInDate;
        if (r2 == 0) goto L_0x0312;
    L_0x02ed:
        r2 = r0.checkoutDate;
        if (r2 == 0) goto L_0x0312;
    L_0x02f1:
        r2 = r0.propertyDurationTV;
        r8 = "%d %s";
        r10 = new java.lang.Object[r7];
        r12 = r19.getNights();
        r12 = java.lang.Integer.valueOf(r12);
        r10[r3] = r12;
        r12 = 2131821067; // 0x7f11020b float:1.9274867E38 double:1.0532595523E-314;
        r12 = r0.getString(r12);
        r10[r4] = r12;
        r8 = java.lang.String.format(r8, r10);
        r2.setText(r8);
        goto L_0x0332;
    L_0x0312:
        r2 = r0.propertyDurationTV;
        r8 = java.util.Locale.getDefault();
        r10 = "%d %s";
        r12 = new java.lang.Object[r7];
        r13 = java.lang.Integer.valueOf(r3);
        r12[r3] = r13;
        r13 = 2131821067; // 0x7f11020b float:1.9274867E38 double:1.0532595523E-314;
        r13 = r0.getString(r13);
        r12[r4] = r13;
        r8 = java.lang.String.format(r8, r10, r12);
        r2.setText(r8);
    L_0x0332:
        r2 = r0.selectedGuestCountTV;
        r8 = java.util.Locale.getDefault();
        r10 = "%d %s";
        r12 = new java.lang.Object[r7];
        r13 = r0.guestCount;
        r13 = java.lang.Integer.parseInt(r13);
        r13 = java.lang.Integer.valueOf(r13);
        r12[r3] = r13;
        r13 = 2131820916; // 0x7f110174 float:1.927456E38 double:1.0532594777E-314;
        r13 = r0.getString(r13);
        r12[r4] = r13;
        r8 = java.lang.String.format(r8, r10, r12);
        r2.setText(r8);
        r2 = r19.getPropertydata();
        r2 = r2.getCurrencyCode();
        if (r2 == 0) goto L_0x04bc;
    L_0x0362:
        r2 = r19.getPropertydata();
        r2 = r2.getCurrencyCode();
        r8 = "SAR";
        r2 = r2.equalsIgnoreCase(r8);
        if (r2 == 0) goto L_0x04bc;
    L_0x0372:
        r2 = java.util.Locale.getDefault();
        r2 = r2.getLanguage();
        r8 = "ar";
        r2 = r2.equalsIgnoreCase(r8);
        if (r2 == 0) goto L_0x0420;
    L_0x0382:
        r2 = r0.payableAmountTV;
        r8 = java.util.Locale.getDefault();
        r10 = "<b>%.2f</b> %s";
        r12 = new java.lang.Object[r7];
        r13 = r19.getTotal();
        r13 = java.lang.Float.valueOf(r13);
        r12[r3] = r13;
        r13 = r0.getString(r6);
        r12[r4] = r13;
        r8 = java.lang.String.format(r8, r10, r12);
        r8 = android.text.Html.fromHtml(r8);
        r2.setText(r8);
        r2 = r0.pricePerNightTv;
        r8 = java.util.Locale.getDefault();
        r10 = "%.2f %s %s %d %s";
        r12 = new java.lang.Object[r11];
        r13 = r19.getPerNightPrice();
        r13 = java.lang.Float.valueOf(r13);
        r12[r3] = r13;
        r13 = r0.getString(r6);
        r12[r4] = r13;
        r13 = 2131821060; // 0x7f110204 float:1.9274853E38 double:1.053259549E-314;
        r13 = r0.getString(r13);
        r12[r7] = r13;
        r13 = r19.getNights();
        r13 = java.lang.Integer.valueOf(r13);
        r12[r5] = r13;
        r5 = 2131820677; // 0x7f110085 float:1.9274076E38 double:1.0532593596E-314;
        r5 = r0.getString(r5);
        r12[r9] = r5;
        r5 = java.lang.String.format(r8, r10, r12);
        r2.setText(r5);
        r2 = r0.pricePerNightAmountTV;
        r5 = "%.2f %s";
        r8 = new java.lang.Object[r7];
        r9 = r19.getPrice();
        r9 = java.lang.Float.valueOf(r9);
        r8[r3] = r9;
        r9 = r0.getString(r6);
        r8[r4] = r9;
        r5 = java.lang.String.format(r5, r8);
        r2.setText(r5);
        r2 = r0.totalAmountTV;
        r5 = "%.2f %s";
        r8 = new java.lang.Object[r7];
        r9 = r19.getTotal();
        r9 = java.lang.Float.valueOf(r9);
        r8[r3] = r9;
        r9 = r0.getString(r6);
        r8[r4] = r9;
        r5 = java.lang.String.format(r5, r8);
        r2.setText(r5);
        goto L_0x04bc;
    L_0x0420:
        r2 = r0.payableAmountTV;
        r8 = java.util.Locale.getDefault();
        r10 = "%s <b>%.2f</b>";
        r12 = new java.lang.Object[r7];
        r13 = r0.getString(r6);
        r12[r3] = r13;
        r13 = r19.getTotal();
        r13 = java.lang.Float.valueOf(r13);
        r12[r4] = r13;
        r8 = java.lang.String.format(r8, r10, r12);
        r8 = android.text.Html.fromHtml(r8);
        r2.setText(r8);
        r2 = r0.pricePerNightTv;
        r8 = java.util.Locale.getDefault();
        r10 = "%s %.2f %s %d %s";
        r12 = new java.lang.Object[r11];
        r13 = r0.getString(r6);
        r12[r3] = r13;
        r13 = r19.getPerNightPrice();
        r13 = java.lang.Float.valueOf(r13);
        r12[r4] = r13;
        r13 = 2131821060; // 0x7f110204 float:1.9274853E38 double:1.053259549E-314;
        r13 = r0.getString(r13);
        r12[r7] = r13;
        r13 = r19.getNights();
        r13 = java.lang.Integer.valueOf(r13);
        r12[r5] = r13;
        r5 = 2131820677; // 0x7f110085 float:1.9274076E38 double:1.0532593596E-314;
        r5 = r0.getString(r5);
        r12[r9] = r5;
        r5 = java.lang.String.format(r8, r10, r12);
        r2.setText(r5);
        r2 = r0.pricePerNightAmountTV;
        r5 = "%s %.2f";
        r8 = new java.lang.Object[r7];
        r9 = r0.getString(r6);
        r8[r3] = r9;
        r9 = r19.getPrice();
        r9 = java.lang.Float.valueOf(r9);
        r8[r4] = r9;
        r5 = java.lang.String.format(r5, r8);
        r2.setText(r5);
        r2 = r0.totalAmountTV;
        r5 = "%s %.2f";
        r8 = new java.lang.Object[r7];
        r9 = r0.getString(r6);
        r8[r3] = r9;
        r9 = r19.getTotal();
        r9 = java.lang.Float.valueOf(r9);
        r8[r4] = r9;
        r5 = java.lang.String.format(r5, r8);
        r2.setText(r5);
    L_0x04bc:
        r2 = new java.util.ArrayList;
        r2.<init>();
        r5 = r19.getAddon();
        if (r5 == 0) goto L_0x04d8;
    L_0x04c7:
        r5 = r19.getAddon();
        r5 = r5.size();
        if (r5 == 0) goto L_0x04d8;
    L_0x04d1:
        r5 = r19.getAddon();
        r2.addAll(r5);
    L_0x04d8:
        r5 = r19.getServicefee();
        if (r5 == 0) goto L_0x04ef;
    L_0x04de:
        r5 = r19.getServicefee();
        r5 = r5.size();
        if (r5 == 0) goto L_0x04ef;
    L_0x04e8:
        r5 = r19.getServicefee();
        r2.addAll(r5);
    L_0x04ef:
        r5 = r19.getTax();
        r8 = 0;
        r5 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1));
        if (r5 <= 0) goto L_0x0511;
    L_0x04f8:
        r5 = new com.data.viewbill.Addon;
        r5.<init>();
        r8 = 2131821401; // 0x7f110359 float:1.9275544E38 double:1.0532597173E-314;
        r8 = r0.getString(r8);
        r5.setName(r8);
        r1 = r19.getTax();
        r5.setAmount(r1);
        r2.add(r5);
    L_0x0511:
        r1 = 0;
    L_0x0512:
        r5 = r2.size();
        if (r1 >= r5) goto L_0x0611;
    L_0x0518:
        r5 = new android.widget.LinearLayout;
        r5.<init>(r0);
        r8 = new android.widget.LinearLayout$LayoutParams;
        r9 = -2;
        r10 = -1;
        r8.<init>(r10, r9);
        r10 = 12;
        r10 = com.jelsat.utils.Utils.getPixelsFromDPs(r0, r10);
        r12 = com.jelsat.utils.Utils.getPixelsFromDPs(r0, r11);
        r13 = 16;
        r13 = com.jelsat.utils.Utils.getPixelsFromDPs(r0, r13);
        r14 = com.jelsat.utils.Utils.getPixelsFromDPs(r0, r11);
        r8.setMargins(r10, r12, r13, r14);
        r5.setLayoutParams(r8);
        r5.setOrientation(r3);
        r8 = new android.widget.TextView;
        r8.<init>(r0);
        r10 = 1096810496; // 0x41600000 float:14.0 double:5.41896386E-315;
        r8.setTextSize(r10);
        r10 = 8388611; // 0x800003 float:1.1754948E-38 double:4.1445245E-317;
        r8.setGravity(r10);
        r10 = android.os.Build.VERSION.SDK_INT;
        r12 = 17;
        if (r10 < r12) goto L_0x055a;
    L_0x0557:
        r8.setTextAlignment(r11);
    L_0x055a:
        r10 = 2131099844; // 0x7f0600c4 float:1.7812053E38 double:1.052903221E-314;
        r10 = r0.applyColor(r10);
        r8.setTextColor(r10);
        r10 = r2.get(r1);
        r10 = (com.data.viewbill.Addon) r10;
        r10 = r10.getName();
        r8.setText(r10);
        r10 = new android.widget.LinearLayout$LayoutParams;
        r12 = 1060320051; // 0x3f333333 float:0.7 double:5.23867711E-315;
        r13 = -1;
        r10.<init>(r9, r13, r12);
        r8.setLayoutParams(r10);
        r10 = new android.widget.TextView;
        r10.<init>(r0);
        r12 = java.util.Locale.getDefault();
        r12 = r12.getLanguage();
        r13 = "ar";
        r12 = r12.equalsIgnoreCase(r13);
        if (r12 == 0) goto L_0x05b4;
    L_0x0592:
        r12 = "%.2f %s";
        r13 = new java.lang.Object[r7];
        r14 = r2.get(r1);
        r14 = (com.data.viewbill.Addon) r14;
        r14 = r14.getAmount();
        r14 = java.lang.Float.valueOf(r14);
        r13[r3] = r14;
        r14 = r0.getString(r6);
        r13[r4] = r14;
        r12 = java.lang.String.format(r12, r13);
        r10.setText(r12);
        goto L_0x05d5;
    L_0x05b4:
        r12 = "%s %.2f";
        r13 = new java.lang.Object[r7];
        r14 = r0.getString(r6);
        r13[r3] = r14;
        r14 = r2.get(r1);
        r14 = (com.data.viewbill.Addon) r14;
        r14 = r14.getAmount();
        r14 = java.lang.Float.valueOf(r14);
        r13[r4] = r14;
        r12 = java.lang.String.format(r12, r13);
        r10.setText(r12);
    L_0x05d5:
        r12 = 1096810496; // 0x41600000 float:14.0 double:5.41896386E-315;
        r10.setTextSize(r12);
        r12 = 2131099844; // 0x7f0600c4 float:1.7812053E38 double:1.052903221E-314;
        r12 = r0.applyColor(r12);
        r10.setTextColor(r12);
        r12 = 8388613; // 0x800005 float:1.175495E-38 double:4.1445255E-317;
        r10.setGravity(r12);
        r12 = android.os.Build.VERSION.SDK_INT;
        r13 = 17;
        if (r12 < r13) goto L_0x05f5;
    L_0x05f0:
        r12 = 6;
        r10.setTextAlignment(r12);
        goto L_0x05f6;
    L_0x05f5:
        r12 = 6;
    L_0x05f6:
        r13 = new android.widget.LinearLayout$LayoutParams;
        r14 = 1050253722; // 0x3e99999a float:0.3 double:5.188942835E-315;
        r15 = -1;
        r13.<init>(r9, r15, r14);
        r10.setLayoutParams(r13);
        r5.addView(r8);
        r5.addView(r10);
        r8 = r0.addOnsLayout;
        r8.addView(r5);
        r1 = r1 + 1;
        goto L_0x0512;
    L_0x0611:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jelsat.activities.PropertyViewBillActivity.initSelectedDatesAndPriceOfProperty(com.data.viewbill.PropertyViewBillResponse):void");
    }

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            this.alertDialog.showAlertDialog(aPIError.getSeken_errors(), getString(R.string.ok), new PropertyViewBillActivity$1(this));
        }
        this.totalBillCardView.setVisibility(8);
        this.nextButton.setEnabled(false);
        this.nextButton.setClickable(false);
    }

    protected void onStop() {
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onDetachedFromWindow() {
        if (this.viewBillPresenter != null) {
            this.viewBillPresenter.unSubscribeDisposables();
        }
        super.onDetachedFromWindow();
    }

    private void goToPropertyDetailScreen() {
        Intent intent = new Intent(this, PropertyDetailActivity.class);
        intent.setFlags(67108864);
        intent.putExtra(StringConstants.FROM_BOOKING_PAYMENT, true);
        startActivity(intent);
    }

    public void onBackPressed() {
        goToPropertyDetailScreen();
        super.onBackPressed();
    }
}
