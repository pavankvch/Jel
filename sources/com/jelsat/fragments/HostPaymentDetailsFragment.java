package com.jelsat.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.data.payments.PaymentsDetailsData;
import com.jelsat.R;
import com.jelsat.adapters.HostPaymentsDetailsAdapter;
import com.jelsat.baseuiframework.BaseFragment;
import java.util.List;

public class HostPaymentDetailsFragment extends BaseFragment {
    @BindView(2131362371)
    TextView noResultTextView;
    private HostPaymentsDetailsAdapter paymentsDetailsAdapter;
    @BindView(2131362498)
    RecyclerView recyclerView;

    public int getFragmentLayoutId() {
        return R.layout.fragment_recyclerview;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        layoutInflater = super.onCreateView(layoutInflater, viewGroup, bundle);
        setupRecyclerView();
        return layoutInflater;
    }

    private void setupRecyclerView() {
        this.paymentsDetailsAdapter = new HostPaymentsDetailsAdapter(null, getActivity());
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerView.setBackgroundColor(getResources().getColor(R.color.booking_background));
        this.recyclerView.setAdapter(this.paymentsDetailsAdapter);
    }

    public void setData(List<PaymentsDetailsData> list) {
        if (list == null || list.size() <= 0) {
            this.noResultTextView.setVisibility(0);
            this.noResultTextView.setText(getText(R.string.no_records_found));
            return;
        }
        this.paymentsDetailsAdapter.setData(list);
        this.noResultTextView.setVisibility(8);
    }
}
