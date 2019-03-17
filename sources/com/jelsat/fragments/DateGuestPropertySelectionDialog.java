package com.jelsat.fragments;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.data.amenitiesandhouserules.PropertyType;
import com.data.propertycostcalendar.ModifiedPrice;
import com.data.propertycostcalendar.PropertyCostCalendarResponse;
import com.jelsat.R;
import com.jelsat.adapters.ViewPagerAdapter;
import com.jelsat.constants.StringConstants;
import com.jelsat.events.DateGuestPropertyDialogEvent;
import com.jelsat.events.GuestCountEvent;
import com.jelsat.events.OnDaySelectedEvent;
import com.jelsat.events.PropertyTypeSelectionEvent;
import com.jelsat.utils.NetworkUtil;
import com.jelsat.widgets.FancyButton;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class DateGuestPropertySelectionDialog extends DialogFragment {
    private final String TAG = DateGuestPropertySelectionDialog.class.getSimpleName();
    private StringBuilder builder = new StringBuilder();
    @BindView(2131361961)
    FancyButton cancelButton;
    private TextView checkInCheckoutDate;
    private Date checkInDate;
    private Date checkOutDate;
    private CostCalendarFragment costCalendarFragment;
    private DateFragment dateFragment;
    private String guestCount;
    private TextView guestCountTV;
    private boolean isFromPropertyDetail;
    private String locationName;
    @BindView(2131362310)
    TextView locationText;
    private String maxGuestCount;
    private int minimumNights;
    private int propertyId;
    private Map<String, PropertyType> propertyTypeHashMap = new HashMap();
    private TextView propertyTypeTextView;
    PropertyCostCalendarResponse responsevalue;
    @BindView(2131362533)
    FancyButton saveButton;
    @BindView(2131362534)
    LinearLayout saveButtonLayout;
    private int tab;
    @BindView(2131362637)
    TabLayout tabLayout;
    float temp = 0.0f;
    private Unbinder unbinder;
    @BindView(2131362820)
    ViewPager viewPager;

    public static DateGuestPropertySelectionDialog newInstance(int i, Date date, Date date2, String str, Map<String, PropertyType> map, String str2) {
        Bundle bundle = new Bundle();
        bundle.putInt(StringConstants.TAB, i);
        bundle.putSerializable(StringConstants.CHECK_IN_DATE, date);
        bundle.putSerializable(StringConstants.CHECK_OUT_DATE, date2);
        bundle.putSerializable(StringConstants.PROPERTY_MAP, (Serializable) map);
        bundle.putString(StringConstants.GUEST_COUNT, str);
        bundle.putString(StringConstants.LOCATION_NAME, str2);
        i = new DateGuestPropertySelectionDialog();
        i.setArguments(bundle);
        return i;
    }

    public static DateGuestPropertySelectionDialog newInstance(int i, Date date, Date date2, String str, String str2, String str3, int i2, int i3) {
        Bundle bundle = new Bundle();
        bundle.putInt(StringConstants.TAB, i);
        bundle.putSerializable(StringConstants.CHECK_IN_DATE, date);
        bundle.putInt(StringConstants.PROPERTY_ID, i2);
        bundle.putSerializable(StringConstants.CHECK_OUT_DATE, date2);
        bundle.putString(StringConstants.GUEST_COUNT, str);
        bundle.putString(StringConstants.MAX_GUEST_COUNT, str2);
        bundle.putString(StringConstants.LOCATION_NAME, str3);
        bundle.putInt(StringConstants.MIN_NIGHTS, i3);
        bundle.putBoolean("from_property_detail", true);
        i = new DateGuestPropertySelectionDialog();
        i.setArguments(bundle);
        return i;
    }

    @OnClick({2131361892})
    public void clickOnBack(View view) {
        getDialog().dismiss();
    }

    @OnClick({2131361961})
    public void clickOnCancel() {
        getDialog().dismiss();
    }

    @OnClick({2131362533})
    public void clickOnSave() {
        List selectedRange;
        if (!NetworkUtil.isConnectedToNetwork(requireActivity())) {
            if (!NetworkUtil.isNetworkConnectedThroughWifi(requireActivity())) {
                showToast(getString(R.string.internet_connection_label));
                return;
            }
        }
        if (this.isFromPropertyDetail) {
            selectedRange = this.costCalendarFragment.getSelectedRange();
            this.responsevalue = this.costCalendarFragment.getSelectedRangeCost();
        } else {
            selectedRange = this.dateFragment.getSelectedRange();
        }
        if (selectedRange.size() == 1) {
            showToast(getString(R.string.please_select_checkout_date));
            return;
        }
        Date[] dateArr = new Date[2];
        if (selectedRange.size() > 0) {
            dateArr[0] = (Date) selectedRange.get(0);
            dateArr[1] = (Date) selectedRange.get(selectedRange.size() - 1);
        }
        if (this.isFromPropertyDetail) {
            this.temp = ((float) (selectedRange.size() - 1)) * this.responsevalue.getPrice();
            for (int i = 0; i < selectedRange.size() - 1; i++) {
                for (int i2 = 0; i2 < this.responsevalue.getModifiedPrice().size(); i2++) {
                    if (((ModifiedPrice) this.responsevalue.getModifiedPrice().get(i2)).getDate().equals(convertDateToStringForSearch((Date) selectedRange.get(i)))) {
                        this.temp -= this.responsevalue.getPrice() - ((ModifiedPrice) this.responsevalue.getModifiedPrice().get(i2)).getPrice();
                        break;
                    }
                }
            }
        }
        DateGuestPropertyDialogEvent dateGuestPropertyDialogEvent = new DateGuestPropertyDialogEvent();
        dateGuestPropertyDialogEvent.setSelectedPropertyTypes(this.propertyTypeHashMap);
        dateGuestPropertyDialogEvent.setGuestCount(this.guestCount);
        dateGuestPropertyDialogEvent.setCheckInDate(dateArr[0]);
        dateGuestPropertyDialogEvent.setCheckOutDate(dateArr[1]);
        this.temp /= (float) (selectedRange.size() - 1);
        dateGuestPropertyDialogEvent.setCost(String.format("%.2f", new Object[]{Float.valueOf(this.temp)}));
        EventBus.getDefault().post(dateGuestPropertyDialogEvent);
        dismiss();
    }

    public void showToast(String str) {
        if (getActivity() != null) {
            Toast.makeText(getActivity(), str, 0).show();
        }
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        bundle = super.onCreateDialog(bundle);
        if (bundle.getWindow() != null) {
            bundle.getWindow().requestFeature(1);
        }
        bundle.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        bundle.setCanceledOnTouchOutside(false);
        return bundle;
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.date_guest_property_dialog, viewGroup, false);
        this.unbinder = ButterKnife.bind((Object) this, inflate);
        if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar") != null) {
            this.saveButton.setGravity(GravityCompat.START);
        }
        if (getArguments() != null) {
            this.tab = getArguments().getInt(StringConstants.TAB);
            this.propertyTypeHashMap = (Map) getArguments().getSerializable(StringConstants.PROPERTY_MAP);
            this.guestCount = getArguments().getString(StringConstants.GUEST_COUNT, "01");
            this.maxGuestCount = getArguments().getString(StringConstants.MAX_GUEST_COUNT);
            this.locationName = getArguments().getString(StringConstants.LOCATION_NAME);
            this.checkInDate = (Date) getArguments().getSerializable(StringConstants.CHECK_IN_DATE);
            this.checkOutDate = (Date) getArguments().getSerializable(StringConstants.CHECK_OUT_DATE);
            this.isFromPropertyDetail = getArguments().getBoolean("from_property_detail", false);
            this.propertyId = getArguments().getInt(StringConstants.PROPERTY_ID);
            this.minimumNights = getArguments().getInt(StringConstants.MIN_NIGHTS);
        }
        setupViewPager(this.viewPager);
        return inflate;
    }

    private void setupViewPager(ViewPager viewPager) {
        PagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        if (this.isFromPropertyDetail) {
            this.costCalendarFragment = CostCalendarFragment.newInstance(this.propertyId, this.checkInDate, this.checkOutDate, this.minimumNights);
            viewPagerAdapter.addFragment(this.costCalendarFragment, "costCalendar");
        } else {
            this.dateFragment = DateFragment.newInstance(this.checkInDate, this.checkOutDate);
            viewPagerAdapter.addFragment(this.dateFragment, "Date");
        }
        viewPagerAdapter.addFragment(NumberOfGuestsFragment.newInstance(this.guestCount, this.maxGuestCount, this.isFromPropertyDetail), "Guests");
        if (!this.isFromPropertyDetail) {
            viewPagerAdapter.addFragment(PropertyTypeFragment.newInstance(this.propertyTypeHashMap), "Property");
        }
        viewPager.setAdapter(viewPagerAdapter);
        this.tabLayout.setupWithViewPager(viewPager);
        setTabIcons();
        viewPager.setCurrentItem(this.tab);
        if (this.isFromPropertyDetail) {
            viewPager.setOffscreenPageLimit(2);
        } else {
            viewPager.setOffscreenPageLimit(3);
        }
    }

    private void setTabIcons() {
        this.tabLayout.getTabAt(0).setCustomView(getDateCustomView());
        this.tabLayout.getTabAt(1).setCustomView(getGuestsCustomView());
        if (!this.isFromPropertyDetail) {
            this.tabLayout.getTabAt(2).setCustomView(getPropertyCustomView());
        }
    }

    private View getDateCustomView() {
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.custom_tab, null);
        this.checkInCheckoutDate = (TextView) inflate.findViewById(R.id.tab_detail);
        if (this.checkInDate == null || this.checkOutDate == null) {
            this.checkInCheckoutDate.setText(getString(R.string.checkin_checkout));
        } else {
            this.checkInCheckoutDate.setText(String.format("%s / %s", new Object[]{convertDateToString(this.checkInDate), convertDateToString(this.checkOutDate)}));
        }
        TextView textView = (TextView) inflate.findViewById(R.id.tab_title);
        textView.setText(getString(R.string.dates));
        textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_calander_small, 0, 0, 0);
        return inflate;
    }

    private View getGuestsCustomView() {
        TextView textView;
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.custom_tab, null);
        this.guestCountTV = (TextView) inflate.findViewById(R.id.tab_detail);
        StringBuilder stringBuilder;
        if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar")) {
            textView = this.guestCountTV;
            stringBuilder = new StringBuilder("%s ");
            stringBuilder.append(this.guestCount);
            textView.setText(String.format(stringBuilder.toString(), new Object[]{getString(R.string.search_person_label)}));
        } else {
            textView = this.guestCountTV;
            stringBuilder = new StringBuilder("%s ");
            stringBuilder.append(getString(R.string.search_person_label));
            textView.setText(String.format(stringBuilder.toString(), new Object[]{this.guestCount}));
        }
        textView = (TextView) inflate.findViewById(R.id.tab_title);
        textView.setText(getString(R.string.search_guests_label));
        textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_guests_small, 0, 0, 0);
        return inflate;
    }

    private View getPropertyCustomView() {
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.custom_tab, null);
        this.propertyTypeTextView = (TextView) inflate.findViewById(R.id.tab_detail);
        setSelectedPropertiesToTextView(this.propertyTypeHashMap);
        TextView textView = (TextView) inflate.findViewById(R.id.tab_title);
        textView.setText(getString(R.string.search_property_label));
        textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_property_type_small, 0, 0, 0);
        return inflate;
    }

    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        if (this.locationName == null) {
            this.locationName = "";
        }
        if (TextUtils.isEmpty(this.locationName)) {
            TextView textView = this.locationText;
            Object[] objArr = new Object[2];
            objArr[0] = getString(R.string.search_you_are_in_label);
            StringBuilder stringBuilder = new StringBuilder("<font color='");
            stringBuilder.append(getResources().getColor(R.color.app_background));
            stringBuilder.append("'>");
            stringBuilder.append(getString(R.string.dashboard_home_all_cities));
            stringBuilder.append("</font>");
            objArr[1] = stringBuilder.toString();
            textView.setText(Html.fromHtml(String.format("%s %s", objArr)));
            return;
        }
        textView = this.locationText;
        objArr = new Object[2];
        objArr[0] = getString(R.string.search_you_are_in_label);
        stringBuilder = new StringBuilder("<font color='");
        stringBuilder.append(getResources().getColor(R.color.app_background));
        stringBuilder.append("'>");
        stringBuilder.append(this.locationName);
        stringBuilder.append("</font>");
        objArr[1] = stringBuilder.toString();
        textView.setText(Html.fromHtml(String.format("%s %s", objArr)));
    }

    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(-1, -1);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getGuestCount(GuestCountEvent guestCountEvent) {
        if (guestCountEvent != null) {
            this.guestCount = guestCountEvent.getGuestCount();
            if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar")) {
                TextView textView = this.guestCountTV;
                StringBuilder stringBuilder = new StringBuilder("%s ");
                stringBuilder.append(guestCountEvent.getGuestCount());
                textView.setText(String.format(stringBuilder.toString(), new Object[]{getString(R.string.search_person_label)}));
                return;
            }
            guestCountEvent = this.guestCountTV;
            StringBuilder stringBuilder2 = new StringBuilder("%s ");
            stringBuilder2.append(getString(R.string.search_person_label));
            guestCountEvent.setText(String.format(stringBuilder2.toString(), new Object[]{this.guestCount}));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getDaySelectedEvent(OnDaySelectedEvent onDaySelectedEvent) {
        if (onDaySelectedEvent != null) {
            this.builder.delete(0, this.builder.length());
            if (onDaySelectedEvent.getDayList() == null || onDaySelectedEvent.getDayList().size() <= 1) {
                this.builder.append(convertDateToString((Date) onDaySelectedEvent.getDayList().get(0)));
                this.builder.append(" - ");
                Log.d(this.TAG, convertDateToString((Date) onDaySelectedEvent.getDayList().get(0)));
            } else {
                this.builder.append(convertDateToString((Date) onDaySelectedEvent.getDayList().get(0)));
                this.builder.append(" / ");
                this.builder.append(convertDateToString((Date) onDaySelectedEvent.getDayList().get(onDaySelectedEvent.getDayList().size() - 1)));
                Log.d(this.TAG, convertDateToString((Date) onDaySelectedEvent.getDayList().get(onDaySelectedEvent.getDayList().size() - 1)));
            }
            this.checkInCheckoutDate.setText(this.builder.toString());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void selectedPropertyType(PropertyTypeSelectionEvent propertyTypeSelectionEvent) {
        if (propertyTypeSelectionEvent != null) {
            PropertyType propertyType = propertyTypeSelectionEvent.getPropertyType();
            if (propertyTypeSelectionEvent.isChecked() != null) {
                this.propertyTypeHashMap.put(propertyType.getId(), propertyType);
            } else if (this.propertyTypeHashMap.containsKey(propertyType.getId()) != null) {
                this.propertyTypeHashMap.remove(propertyType.getId());
            }
            setSelectedPropertiesToTextView(this.propertyTypeHashMap);
        }
    }

    private void setSelectedPropertiesToTextView(Map<String, PropertyType> map) {
        if (map.isEmpty()) {
            this.propertyTypeTextView.setText(getString(R.string.search_select_property_label));
        } else if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar")) {
            r0 = this.propertyTypeTextView;
            r4 = new StringBuilder("%s ");
            r4.append(map.size());
            r0.setText(String.format(r4.toString(), new Object[]{getString(R.string.search_property_types)}));
        } else {
            r0 = this.propertyTypeTextView;
            r4 = new StringBuilder("%s ");
            r4.append(getString(R.string.search_property_types));
            r0.setText(String.format(r4.toString(), new Object[]{Integer.valueOf(map.size())}));
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getDataFromSearchPropertyActivity(DateGuestPropertyDialogEvent dateGuestPropertyDialogEvent) {
        if (dateGuestPropertyDialogEvent != null) {
            EventBus.getDefault().post(new GuestCountEvent(dateGuestPropertyDialogEvent.getGuestCount()));
            EventBus.getDefault().removeStickyEvent(DateGuestPropertyDialogEvent.class);
        }
    }

    public void onStop() {
        super.onStop();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
    }

    public void onDestroyView() {
        if (this.unbinder != null) {
            this.unbinder.unbind();
        }
        super.onDestroyView();
    }

    private String convertDateToString(Date date) {
        return new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).format(date);
    }

    private String convertDateToStringForSearch(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(date);
    }
}
