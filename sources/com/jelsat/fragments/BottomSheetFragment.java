package com.jelsat.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.data.utils.BottomSheetData;
import com.jelsat.R;
import com.jelsat.adapters.BottomSheetAdapter;
import com.jelsat.adapters.BottomSheetAdapter.BottomSheetListItemClickListener;
import com.jelsat.constants.StringConstants;
import com.jelsat.events.AddPropertyRoomsAndGuestEvent;
import com.jelsat.utils.AddPropertyRoomsAndGuestSUtil;
import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.i18n.TextBundle;
import org.greenrobot.eventbus.EventBus;

public class BottomSheetFragment extends BottomSheetDialogFragment implements BottomSheetListItemClickListener {
    private AddPropertyRoomsAndGuestSUtil addPropertyRoomsAndGuestSUtil;
    @BindView(2131362498)
    RecyclerView recyclerView;
    @BindView(2131362655)
    TextView textView;
    private Unbinder unbinder;

    public static BottomSheetFragment newInstance(List<BottomSheetData> list, String str) {
        Bundle bundle = new Bundle();
        bundle.putString(TextBundle.TEXT_ENTRY, str);
        bundle.putParcelableArrayList(StringConstants.ADD_PROPERTY_ROOMS_GUESTS_LIST, (ArrayList) list);
        list = new BottomSheetFragment();
        list.setArguments(bundle);
        return list;
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        layoutInflater = layoutInflater.inflate(R.layout.fragment_bottom_sheet, viewGroup, false);
        this.unbinder = ButterKnife.bind(this, layoutInflater);
        if (!(getArguments() == null || getArguments().getParcelableArrayList(StringConstants.ADD_PROPERTY_ROOMS_GUESTS_LIST) == null)) {
            this.textView.setText(Html.fromHtml(getArguments().getString(TextBundle.TEXT_ENTRY)));
            initRecyclerView(getArguments().getParcelableArrayList(StringConstants.ADD_PROPERTY_ROOMS_GUESTS_LIST));
        }
        return layoutInflater;
    }

    private void initRecyclerView(List<BottomSheetData> list) {
        LayoutManager anonymousClass1 = new LinearLayoutManager(getActivity(), 1, false) {
            public boolean canScrollHorizontally() {
                return false;
            }
        };
        Adapter bottomSheetAdapter = new BottomSheetAdapter(list, this);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(anonymousClass1);
        this.recyclerView.setAdapter(bottomSheetAdapter);
    }

    public void onDestroyView() {
        if (this.unbinder != null) {
            this.unbinder.unbind();
        }
        super.onDestroyView();
    }

    public void clickOnItem(BottomSheetData bottomSheetData, int i) {
        EventBus.getDefault().post(new AddPropertyRoomsAndGuestEvent(bottomSheetData, this.addPropertyRoomsAndGuestSUtil));
        getDialog().dismiss();
    }

    public void setData(AddPropertyRoomsAndGuestSUtil addPropertyRoomsAndGuestSUtil) {
        this.addPropertyRoomsAndGuestSUtil = addPropertyRoomsAndGuestSUtil;
    }
}
