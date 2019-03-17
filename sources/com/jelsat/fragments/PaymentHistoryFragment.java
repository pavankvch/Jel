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
import com.data.paymenthistory.PaymentHistory;
import com.jelsat.R;
import com.jelsat.adapters.paymenthistoryadapter.PaymentHistoryAdapter;
import com.jelsat.adapters.paymenthistoryadapter.PaymentHistorySectionModel;
import com.jelsat.baseuiframework.BaseFragment;
import java.util.ArrayList;
import java.util.List;

public class PaymentHistoryFragment extends BaseFragment {
    PaymentHistoryAdapter adapterRecycler;
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
        this.adapterRecycler = new PaymentHistoryAdapter(getActivity(), null);
        this.recyclerView.setAdapter(this.adapterRecycler);
    }

    public void setData(List<PaymentHistory> list) {
        int i = 0;
        if (list == null || list.size() <= 0) {
            this.noResultTextView.setVisibility(0);
            this.noResultTextView.setText(getText(R.string.no_records_found));
            return;
        }
        List arrayList = new ArrayList();
        while (i < list.size()) {
            arrayList.add(new PaymentHistorySectionModel(((PaymentHistory) list.get(i)).getMonth(), ((PaymentHistory) list.get(i)).getData()));
            i++;
        }
        this.adapterRecycler.notifyDataChanged(arrayList);
        this.noResultTextView.setVisibility(8);
    }
}
