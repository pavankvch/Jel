package com.jelsat.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.businesslogic.hostbookings.HostBookingsUpcomingView;
import com.businesslogic.hostbookings.HostUpcomingBookingsPresenter;
import com.data.bookings.BookingCancelRequest;
import com.data.bookings.BookingCancelResponse;
import com.data.hostbookings.HostBookingProperty;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.jelsat.R;
import com.jelsat.activities.PropertyDetailActivity;
import com.jelsat.adapters.HostBookingsAdapter;
import com.jelsat.adapters.HostBookingsAdapter.OnListItemClickListener;
import com.jelsat.baseuiframework.BaseFragment;
import com.jelsat.constants.StringConstants;
import com.jelsat.events.PropertyDetailEvent;
import com.jelsat.utils.BookingsUtil;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

public class HostBookingsFragment extends BaseFragment implements HostBookingsUpcomingView, OnListItemClickListener {
    private HostBookingsAdapter bookingsAdapter;
    private HostUpcomingBookingsPresenter bookingsPresenter = new HostUpcomingBookingsPresenter(this, RetrofitClient.getAPIService());
    @BindView(2131362371)
    TextView noResultTextView;
    @BindView(2131362498)
    RecyclerView recyclerView;

    public int getFragmentLayoutId() {
        return R.layout.fragment_recyclerview;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        layoutInflater = super.onCreateView(layoutInflater, viewGroup, bundle);
        setUpRecyclerView();
        return layoutInflater;
    }

    private void setUpRecyclerView() {
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.bookingsAdapter = new HostBookingsAdapter(null, getActivity(), this);
        this.recyclerView.setAdapter(this.bookingsAdapter);
    }

    public void onRejectSuccess(BookingCancelResponse bookingCancelResponse, int i) {
        this.bookingsAdapter.refreshListItem(i, StringConstants.BOOKING_REJECTED);
        showToast(getString(R.string.hostbooking_booking_rejected));
    }

    public void onAcceptSuccess(BookingCancelResponse bookingCancelResponse, int i) {
        Log.d("Booking response", bookingCancelResponse.toString());
        this.bookingsAdapter.refreshListItem(i, StringConstants.BOOKING_CONFIRMED);
        showToast(getString(R.string.hostbooking_booking_confirmed));
    }

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void setData(List<HostBookingProperty> list, BookingsUtil bookingsUtil) {
        if (list == null || list.size() <= 0) {
            this.noResultTextView.setVisibility(0);
            this.noResultTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_no_upcomming_bookings, 0, 0);
            this.noResultTextView.setText(getText(R.string.no_bookings_found));
            return;
        }
        this.bookingsAdapter.setData(list, bookingsUtil);
        this.noResultTextView.setVisibility(8);
    }

    public void clickOnConfirm(int i, HostBookingProperty hostBookingProperty) {
        if (isNetworkConnected()) {
            BookingCancelRequest bookingCancelRequest = new BookingCancelRequest();
            bookingCancelRequest.setOrderId(hostBookingProperty.getOrderId());
            this.bookingsPresenter.confirmBooking(getString(R.string.please_wait), bookingCancelRequest, i);
            return;
        }
        showToast(getString(R.string.error_message_network));
    }

    public void clickOnMsg(int i, HostBookingProperty hostBookingProperty) {
        SendMessageDialogFragmentHost.newInstance(hostBookingProperty.getPropertyId(), hostBookingProperty.getOrderId(), hostBookingProperty.getGuestId()).show(getChildFragmentManager(), "sendMessage");
    }

    public void clickOnReject(int i, HostBookingProperty hostBookingProperty) {
        if (isNetworkConnected()) {
            BookingCancelRequest bookingCancelRequest = new BookingCancelRequest();
            bookingCancelRequest.setOrderId(hostBookingProperty.getOrderId());
            this.bookingsPresenter.rejectBooking(getString(R.string.please_wait), bookingCancelRequest, i);
            return;
        }
        showToast(getString(R.string.error_message_network));
    }

    public void clickOnListItem(PropertyDetailEvent propertyDetailEvent) {
        EventBus.getDefault().postSticky(propertyDetailEvent);
        startActivity(new Intent(getActivity(), PropertyDetailActivity.class));
    }

    public void clickOnFeedBack(int i, HostBookingProperty hostBookingProperty) {
        new BookingFeedbackDialogFragment().newInstance(hostBookingProperty.getGuestId(), hostBookingProperty.getOrderId()).show(getChildFragmentManager(), "bookingFeedbackDialog");
    }

    public void clickOnCancel(int i, String str, HostBookingProperty hostBookingProperty) {
        CancelBookingDialogFragment.newInstance(hostBookingProperty.getOrderId(), null, true).show(getChildFragmentManager(), "cancelbooking");
    }
}
