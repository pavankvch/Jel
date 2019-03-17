package com.jelsat.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.OnTextChanged;
import com.data.utils.FilterImage;
import com.jelsat.R;
import com.jelsat.adapters.GuestsAdapter;
import com.jelsat.adapters.GuestsAdapter.OnListItemClickListener;
import com.jelsat.baseuiframework.BaseFragment;
import com.jelsat.constants.StringConstants;
import com.jelsat.customclasses.ItemOffsetDecoration;
import com.jelsat.events.GuestCountEvent;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

public class NumberOfGuestsFragment extends BaseFragment implements OnListItemClickListener {
    private GuestsAdapter adapter;
    private String[] filterNames = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16+"};
    private String[] filterNamesArabic = new String[]{"۱", "۲", "۳", "٤", "٥", "٦", "۷", "۸", "۹", "۱۰", "۱۱", "۱۲", "۱۳", "۱٤", "۱٥", "+۱٦"};
    @BindView(2131362363)
    EditText guestcount;
    @BindView(2131362203)
    LinearLayout guestcountlayout;
    int maxGuestCountPosition = 0;
    private int previousPosition = 0;
    @BindView(2131362206)
    RecyclerView recyclerView;
    private int updatedPosition;

    public int getFragmentLayoutId() {
        return R.layout.fragment_guests;
    }

    @OnTextChanged({2131362363})
    protected void onTextChanged(CharSequence charSequence) {
        charSequence = charSequence.toString();
        if (charSequence.length() <= 0 || this.maxGuestCountPosition > 0) {
            if (charSequence.length() > 0) {
                if (Integer.parseInt(charSequence) > this.maxGuestCountPosition) {
                    charSequence = EventBus.getDefault();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(this.maxGuestCountPosition);
                    charSequence.post(new GuestCountEvent(stringBuilder.toString()));
                    charSequence = this.guestcount;
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(this.maxGuestCountPosition);
                    charSequence.setText(stringBuilder2.toString());
                    charSequence = new StringBuilder();
                    charSequence.append(getString(R.string.max_no_of_guests));
                    charSequence.append(" ");
                    charSequence.append(this.maxGuestCountPosition);
                    showToast(charSequence.toString());
                    return;
                }
                EventBus.getDefault().post(new GuestCountEvent(charSequence));
            }
        } else if (Integer.parseInt(charSequence) > 0) {
            EventBus.getDefault().post(new GuestCountEvent(charSequence));
        } else {
            EventBus.getDefault().post(new GuestCountEvent("01"));
        }
    }

    public static NumberOfGuestsFragment newInstance(String str, String str2, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString(StringConstants.GUEST_COUNT, str);
        bundle.putString(StringConstants.MAX_GUEST_COUNT, str2);
        bundle.putBoolean("from_property_detail", z);
        str = new NumberOfGuestsFragment();
        str.setArguments(bundle);
        return str;
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        layoutInflater = super.onCreateView(layoutInflater, viewGroup, bundle);
        viewGroup = null;
        this.maxGuestCountPosition = 0;
        if (getArguments() != null) {
            if (getArguments().getString(StringConstants.GUEST_COUNT) != null) {
                bundle = getArguments().getString(StringConstants.GUEST_COUNT);
                if (getArguments().getString(StringConstants.GUEST_COUNT, "01").equalsIgnoreCase("16+") && bundle != null) {
                    bundle = bundle.substring(0, bundle.length() - 1);
                }
                if (Integer.parseInt(bundle) > 15) {
                    this.previousPosition = 16;
                    this.guestcount.setText(bundle);
                } else {
                    this.previousPosition = Integer.parseInt(bundle);
                    this.guestcountlayout.setVisibility(4);
                    this.guestcount.setText(bundle);
                }
            }
            if (getArguments().getString(StringConstants.MAX_GUEST_COUNT) != null) {
                bundle = getArguments().getString(StringConstants.MAX_GUEST_COUNT);
                if (getArguments().getString(StringConstants.MAX_GUEST_COUNT, "01").equalsIgnoreCase("16+") && bundle != null) {
                    bundle = bundle.substring(0, bundle.length() - 1);
                }
                this.maxGuestCountPosition = Integer.parseInt(bundle);
            }
            viewGroup = getArguments().getBoolean("from_property_detail");
        }
        this.previousPosition--;
        this.adapter = new GuestsAdapter(getActivity(), getGuestsCount(this.previousPosition, this.maxGuestCountPosition, viewGroup), this);
        viewGroup = new GridLayoutManager(getActivity(), 4);
        this.recyclerView.addItemDecoration(new ItemOffsetDecoration(getActivity(), R.dimen.item_offset));
        this.recyclerView.setLayoutManager(viewGroup);
        this.recyclerView.setAdapter(this.adapter);
        return layoutInflater;
    }

    private List<FilterImage> getGuestsCount(int i, int i2, boolean z) {
        List<FilterImage> arrayList = new ArrayList();
        int i3 = 0;
        while (i3 < this.filterNames.length) {
            FilterImage filterImage = new FilterImage();
            filterImage.setFilterName(this.filterNames[i3]);
            if (i3 == i) {
                filterImage.setSelected(true);
            } else {
                filterImage.setSelected(false);
            }
            if (!z || i2 > i3) {
                filterImage.setEnabled(true);
            }
            arrayList.add(filterImage);
            i3++;
        }
        return arrayList;
    }

    public void clickOnListItem(FilterImage filterImage, int i, boolean z) {
        this.updatedPosition = i;
        this.adapter.setItemStatusChanged(filterImage, this.previousPosition, this.updatedPosition);
        if (this.updatedPosition) {
            this.guestcount.setText("16");
        } else {
            EventBus.getDefault().post(new GuestCountEvent(filterImage.getFilterName()));
            this.guestcountlayout.setVisibility(true);
            filterImage = this.guestcount;
            z = new StringBuilder();
            z.append(this.updatedPosition + 1);
            filterImage.setText(z.toString());
        }
        this.previousPosition = i;
    }
}
