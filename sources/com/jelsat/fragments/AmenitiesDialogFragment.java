package com.jelsat.fragments;

import android.app.Dialog;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.data.amenitiesandhouserules.Amenity;
import com.jelsat.R;
import com.jelsat.adapters.AmenityDialogAdapter;
import java.util.ArrayList;
import java.util.List;

public class AmenitiesDialogFragment extends DialogFragment {
    @BindView(2131361864)
    RecyclerView amenityRecyclerView;
    private Unbinder unbinder;

    @OnClick({2131362016})
    public void clickOnClose() {
        getDialog().dismiss();
    }

    public static AmenitiesDialogFragment newInstance(List<Amenity> list) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("list", (ArrayList) list);
        list = new AmenitiesDialogFragment();
        list.setArguments(bundle);
        return list;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        bundle = super.onCreateDialog(bundle);
        if (bundle.getWindow() != null) {
            bundle.getWindow().requestFeature(1);
        }
        return bundle;
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = layoutInflater.inflate(R.layout.amenity_dialog, viewGroup, false);
        this.unbinder = ButterKnife.bind(this, layoutInflater);
        viewGroup = new Rect();
        if (getActivity() != null) {
            getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(viewGroup);
            layoutInflater.setMinimumWidth((int) (((float) viewGroup.width()) * 0.9f));
            layoutInflater.setMinimumHeight((int) (((float) viewGroup.height()) * 1058642330));
        }
        if (getArguments() != null) {
            initRecyclerView(getArguments().getParcelableArrayList("list"));
        }
        return layoutInflater;
    }

    private void initRecyclerView(List<Amenity> list) {
        this.amenityRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.amenityRecyclerView.setAdapter(new AmenityDialogAdapter(getActivity(), list));
    }

    public void onDestroyView() {
        if (this.unbinder != null) {
            this.unbinder.unbind();
        }
        super.onDestroyView();
    }
}
