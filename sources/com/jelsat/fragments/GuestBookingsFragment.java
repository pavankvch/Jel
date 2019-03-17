package com.jelsat.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.businesslogic.location.LocationPresenter;
import com.data.bookings.BookingProperty;
import com.data.utils.APIConstants;
import com.google.android.gms.maps.model.LatLng;
import com.jelsat.R;
import com.jelsat.activities.PropertyDetailActivity;
import com.jelsat.adapters.GuestBookingsAdapter;
import com.jelsat.adapters.GuestBookingsAdapter.ListItemClickListener;
import com.jelsat.baseuiframework.BaseFragment;
import com.jelsat.events.PropertyDetailEvent;
import com.jelsat.utils.BookingsUtil;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

public class GuestBookingsFragment extends BaseFragment implements ListItemClickListener {
    private GuestBookingsAdapter bookingsAdapter;
    private LocationPresenter chooseLocationPresenter;
    private LatLng destinationLocation;
    @BindView(2131362371)
    TextView noResultTextView;
    @BindView(2131362498)
    RecyclerView recyclerView;

    public int getFragmentLayoutId() {
        return R.layout.fragment_recyclerview;
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        layoutInflater = super.onCreateView(layoutInflater, viewGroup, bundle);
        setUpRecyclerView();
        return layoutInflater;
    }

    private void setUpRecyclerView() {
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.bookingsAdapter = new GuestBookingsAdapter(null, getActivity(), this);
        StringBuilder stringBuilder = new StringBuilder("setUpRecyclerView() bookingAdapter Value : ");
        stringBuilder.append(this.bookingsAdapter);
        Log.e("GuestBookingFrag", stringBuilder.toString());
        this.recyclerView.setAdapter(this.bookingsAdapter);
    }

    public void setData(List<BookingProperty> list, BookingsUtil bookingsUtil) {
        if (list == null || list.size() <= 0) {
            this.noResultTextView.setVisibility(0);
            this.noResultTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_no_upcomming_bookings, 0, 0);
            this.noResultTextView.setText(getText(R.string.no_bookings_found));
            return;
        }
        this.bookingsAdapter.setData(list, bookingsUtil);
        bookingsUtil = new StringBuilder("setData() bookingAdapter Value : ");
        bookingsUtil.append(this.bookingsAdapter);
        Log.e("GuestBookingFrag", bookingsUtil.toString());
        this.noResultTextView.setVisibility(8);
    }

    public void clickOnGetDirections(LatLng latLng) {
        this.destinationLocation = latLng;
        if (this.chooseLocationPresenter != null) {
            this.chooseLocationPresenter.requestLocationUpdate();
        }
    }

    public void clickOnViewBill(BookingProperty bookingProperty) {
        DialogFragment viewBillDialogFragment = new ViewBillDialogFragment();
        ((ViewBillDialogFragment) viewBillDialogFragment).setBookingPropertyData(bookingProperty);
        viewBillDialogFragment.show(getChildFragmentManager(), "viewbill");
    }

    public void clickOnSendMessage(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder("ClickOnSendMessage() has called where propertyId : ");
        stringBuilder.append(str);
        stringBuilder.append("& booking Id : ");
        stringBuilder.append(str2);
        Log.e("GuestBookingsFragment", stringBuilder.toString());
        SendMessageDialogFragment.newInstance(str, str2).show(getChildFragmentManager(), "sendMessage");
    }

    public void clickOnFeedBack(String str, String str2, int i) {
        StringBuilder stringBuilder = new StringBuilder("clickOnFeedback() bookingAdapter Value : ");
        stringBuilder.append(this.bookingsAdapter);
        Log.e("GuestBookingFrag", stringBuilder.toString());
        new FeedbackDialogFragment().newInstance(str, true, str2, i, this.bookingsAdapter).show(getChildFragmentManager(), APIConstants.SUBMIT_FEEDBACK);
    }

    public void clickOnCancel(String str, String str2) {
        CancelBookingDialogFragment.newInstance(str, str2, false).show(getChildFragmentManager(), "cancelbooking");
    }

    public void gotoBookingDetailScreen(PropertyDetailEvent propertyDetailEvent) {
        EventBus.getDefault().postSticky(propertyDetailEvent);
        startActivity(new Intent(getActivity(), PropertyDetailActivity.class));
    }

    public void setLocationPresenter(LocationPresenter locationPresenter) {
        this.chooseLocationPresenter = locationPresenter;
    }

    public void setLocation(LatLng latLng) {
        StringBuilder stringBuilder = new StringBuilder("http://maps.google.com/maps?saddr=");
        stringBuilder.append(latLng.latitude);
        stringBuilder.append(",");
        stringBuilder.append(latLng.longitude);
        stringBuilder.append("&daddr=");
        stringBuilder.append(this.destinationLocation.latitude);
        stringBuilder.append(",");
        stringBuilder.append(this.destinationLocation.longitude);
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder.toString())));
    }
}
