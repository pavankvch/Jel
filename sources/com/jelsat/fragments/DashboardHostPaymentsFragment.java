package com.jelsat.fragments;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.payments.HostPaymentsPresenter;
import com.businesslogic.payments.IHostPaymentsView;
import com.data.payments.HostPaymentsResponse;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.jelsat.R;
import com.jelsat.activities.HostCancelledPropertiesActivity;
import com.jelsat.activities.HostPropertiesActivity;
import com.jelsat.activities.HostTotalBookingsActivity;
import com.jelsat.activities.HostTotalEarningsActivity;
import com.jelsat.adapters.ViewPagerAdapter;
import com.jelsat.baseuiframework.BaseFragment;
import com.jelsat.constants.StringConstants;
import com.jelsat.widgets.NonSwipeableViewPager;
import java.util.Locale;

public class DashboardHostPaymentsFragment extends BaseFragment implements IHostPaymentsView {
    @BindView(2131362052)
    TextView currencyText;
    private HostPaymentDetailsFragment[] hostPaymentDetailsFragments = new HostPaymentDetailsFragment[3];
    private HostPaymentsPresenter hostPaymentsPresenter = new HostPaymentsPresenter(this, RetrofitClient.getAPIService());
    @BindView(2131362642)
    TabLayout paymentCategoriesTabs;
    @BindView(2131362753)
    TextView propertiesCountTv;
    @BindView(2131362781)
    TextView totalBalanceTv;
    @BindView(2131362782)
    TextView totalBookingsCountTv;
    @BindView(2131362783)
    TextView totalCancellsTv;
    @BindView(2131362832)
    NonSwipeableViewPager viewpager;

    public int getFragmentLayoutId() {
        return R.layout.fragment_host_payments;
    }

    @OnClick({2131362830})
    public void clickOnTotalBookings() {
        Bundle bundle = new Bundle();
        bundle.putString(StringConstants.TOTAL_COUNT, this.totalBookingsCountTv.getText().toString());
        goToActivity(HostTotalBookingsActivity.class, bundle);
    }

    @OnClick({2131362290})
    public void clickOnProperties() {
        goToActivity(HostPropertiesActivity.class);
    }

    @OnClick({2131362282})
    public void clickOnCancelled() {
        Bundle bundle = new Bundle();
        bundle.putString(StringConstants.TOTAL_COUNT, this.totalCancellsTv.getText().toString());
        goToActivity(HostCancelledPropertiesActivity.class, bundle);
    }

    @OnClick({2131362281})
    public void clickOnAmount() {
        Bundle bundle = new Bundle();
        if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar")) {
            bundle.putString(StringConstants.TOTAL_COUNT, this.currencyText.getText().toString());
        } else {
            bundle.putString(StringConstants.TOTAL_COUNT, this.totalBalanceTv.getText().toString());
        }
        goToActivity(HostTotalEarningsActivity.class, bundle);
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        layoutInflater = super.onCreateView(layoutInflater, viewGroup, bundle);
        setupViewPager(this.viewpager);
        this.paymentCategoriesTabs.setupWithViewPager(this.viewpager);
        this.hostPaymentsPresenter.getHostPayments(getString(R.string.please_wait));
        return layoutInflater;
    }

    private void setupViewPager(NonSwipeableViewPager nonSwipeableViewPager) {
        PagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        HostPaymentDetailsFragment[] hostPaymentDetailsFragmentArr = this.hostPaymentDetailsFragments;
        Fragment hostPaymentDetailsFragment = new HostPaymentDetailsFragment();
        hostPaymentDetailsFragmentArr[0] = hostPaymentDetailsFragment;
        viewPagerAdapter.addFragment(hostPaymentDetailsFragment, getActivity().getResources().getString(R.string.host_payments_all));
        hostPaymentDetailsFragmentArr = this.hostPaymentDetailsFragments;
        hostPaymentDetailsFragment = new HostPaymentDetailsFragment();
        hostPaymentDetailsFragmentArr[1] = hostPaymentDetailsFragment;
        viewPagerAdapter.addFragment(hostPaymentDetailsFragment, getActivity().getResources().getString(R.string.host_payments_recieved));
        hostPaymentDetailsFragmentArr = this.hostPaymentDetailsFragments;
        hostPaymentDetailsFragment = new HostPaymentDetailsFragment();
        hostPaymentDetailsFragmentArr[2] = hostPaymentDetailsFragment;
        viewPagerAdapter.addFragment(hostPaymentDetailsFragment, getActivity().getResources().getString(R.string.host_payments_upcoming));
        nonSwipeableViewPager.setAdapter(viewPagerAdapter);
        nonSwipeableViewPager.setOffscreenPageLimit(3);
    }

    public void onStop() {
        if (this.hostPaymentsPresenter != null) {
            this.hostPaymentsPresenter.unSubscribeDisposables();
        }
        super.onStop();
    }

    public void onSuccess(HostPaymentsResponse hostPaymentsResponse) {
        if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar")) {
            this.currencyText.setText(String.format(Locale.getDefault(), "%.2f", new Object[]{Float.valueOf(hostPaymentsResponse.getTotal())}));
            this.currencyText.setTextSize(30.0f);
            if (VERSION.SDK_INT >= 26) {
                this.currencyText.setTypeface(getResources().getFont(R.font.sf_ui_display_bold));
                this.totalBalanceTv.setTypeface(getResources().getFont(R.font.sf_ui_display_regular));
            }
            this.totalBalanceTv.setText(getString(R.string.host_payments_price_type));
            this.totalBalanceTv.setTextSize(15.0f);
        } else {
            this.totalBalanceTv.setText(String.format(Locale.getDefault(), "%.2f", new Object[]{Float.valueOf(hostPaymentsResponse.getTotal())}));
        }
        this.propertiesCountTv.setText(String.valueOf(hostPaymentsResponse.getTotalProperties()));
        this.totalBookingsCountTv.setText(String.valueOf(hostPaymentsResponse.getTotalBookings()));
        this.totalCancellsTv.setText(String.valueOf(hostPaymentsResponse.getTotalCancels()));
        if (hostPaymentsResponse.getPaymentsDetails().getAll() != null) {
            this.hostPaymentDetailsFragments[0].setData(hostPaymentsResponse.getPaymentsDetails().getAll());
        }
        if (hostPaymentsResponse.getPaymentsDetails().getRecieved() != null) {
            this.hostPaymentDetailsFragments[1].setData(hostPaymentsResponse.getPaymentsDetails().getRecieved());
        }
        if (hostPaymentsResponse.getPaymentsDetails().getUpcoming() != null) {
            this.hostPaymentDetailsFragments[2].setData(hostPaymentsResponse.getPaymentsDetails().getUpcoming());
        }
    }

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }
}
