package com.jelsat.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.wallet.IPaymentHistoryView;
import com.businesslogic.wallet.PaymentHistoryPresenter;
import com.data.paymenthistory.PaymentHistoryResponse;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.jelsat.R;
import com.jelsat.adapters.ViewPagerAdapter;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.fragments.PaymentHistoryFragment;

public class PaymentHistoryActivity extends BaseAppCompactActivity implements IPaymentHistoryView {
    @BindView(2131361892)
    ImageView backArrowIV;
    @BindView(2131362710)
    TextView closeTextView;
    @BindView(2131362733)
    TextView headingTv;
    private PaymentHistoryFragment[] paymentHistoryFragments = new PaymentHistoryFragment[3];
    private PaymentHistoryPresenter paymentHistoryPresenter;
    @BindView(2131362636)
    SwipeRefreshLayout swipeContainer;
    @BindView(2131362641)
    TabLayout tabs;
    @BindView(2131362832)
    ViewPager viewpager;

    public int getActivityLayout() {
        return R.layout.toolbar_tabs_viewpager;
    }

    @OnClick({2131362710})
    public void clickOnBack() {
        finish();
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        initSwipeToRefresh();
        this.paymentHistoryPresenter = new PaymentHistoryPresenter(this, RetrofitClient.getAPIService());
        this.paymentHistoryPresenter.getPaymentHistory(getString(R.string.please_wait), true);
        this.backArrowIV.setVisibility(4);
        this.closeTextView.setVisibility(0);
        this.headingTv.setText(getResources().getString(R.string.payment_history));
        this.viewpager.setCurrentItem(1);
        this.tabs.setupWithViewPager(this.viewpager);
        setupViewPager(this.viewpager);
    }

    private void setupViewPager(ViewPager viewPager) {
        PagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        PaymentHistoryFragment[] paymentHistoryFragmentArr = this.paymentHistoryFragments;
        Fragment paymentHistoryFragment = new PaymentHistoryFragment();
        paymentHistoryFragmentArr[0] = paymentHistoryFragment;
        viewPagerAdapter.addFragment(paymentHistoryFragment, getResources().getString(R.string.wallet_payment_history_all));
        paymentHistoryFragmentArr = this.paymentHistoryFragments;
        paymentHistoryFragment = new PaymentHistoryFragment();
        paymentHistoryFragmentArr[1] = paymentHistoryFragment;
        viewPagerAdapter.addFragment(paymentHistoryFragment, getResources().getString(R.string.wallet_payment_history_money_in));
        paymentHistoryFragmentArr = this.paymentHistoryFragments;
        paymentHistoryFragment = new PaymentHistoryFragment();
        paymentHistoryFragmentArr[2] = paymentHistoryFragment;
        viewPagerAdapter.addFragment(paymentHistoryFragment, getResources().getString(R.string.wallet_payment_history_money_out));
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOffscreenPageLimit(3);
    }

    private void initSwipeToRefresh() {
        this.swipeContainer.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh() {
                PaymentHistoryActivity.this.paymentHistoryPresenter.getPaymentHistory(PaymentHistoryActivity.this.getString(R.string.please_wait), false);
            }
        });
        this.swipeContainer.setColorSchemeResources(new int[]{17170459, 17170452, 17170456, 17170454});
    }

    public void showSwipeToRefresh(boolean z) {
        if (this.swipeContainer != null) {
            this.swipeContainer.setRefreshing(z);
        }
    }

    public void onSuccess(PaymentHistoryResponse paymentHistoryResponse) {
        this.paymentHistoryFragments[0].setData(paymentHistoryResponse.getAllPaymentsList());
        this.paymentHistoryFragments[1].setData(paymentHistoryResponse.getCreditedPaymentsList());
        this.paymentHistoryFragments[2].setData(paymentHistoryResponse.getDebitedPaymentsList());
    }

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void onDetachedFromWindow() {
        if (this.swipeContainer != null) {
            this.swipeContainer.setRefreshing(false);
        }
        if (this.paymentHistoryPresenter != null) {
            this.paymentHistoryPresenter.unSubscribeDisposables();
        }
        super.onDetachedFromWindow();
    }
}
