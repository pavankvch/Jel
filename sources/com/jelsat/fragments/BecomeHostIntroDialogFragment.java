package com.jelsat.fragments;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.jelsat.R;
import com.jelsat.adapters.ViewPagerAdapter;
import com.jelsat.customclasses.viewpagerindicator.InkPageIndicator;

public class BecomeHostIntroDialogFragment extends DialogFragment {
    @BindView(2131362005)
    InkPageIndicator circlePageIndicator;
    private int heightInDp;
    private Unbinder unbinder;
    @BindView(2131362828)
    ViewPager viewPager;
    private int widthInDp;

    @OnClick({2131362016})
    public void clickOnClose() {
        getDialog().dismiss();
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        bundle = super.onCreateDialog(bundle);
        if (bundle.getWindow() != null) {
            bundle.getWindow().requestFeature(1);
        }
        bundle.setCancelable(false);
        bundle.getWindow().setBackgroundDrawableResource(R.drawable.corners);
        return bundle;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = layoutInflater.inflate(R.layout.host_guide_dialog, viewGroup, false);
        this.unbinder = ButterKnife.bind(this, layoutInflater);
        viewGroup = new Rect();
        getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(viewGroup);
        if (layoutInflater != null) {
            layoutInflater.setMinimumWidth((int) (((float) viewGroup.width()) * 1061997773));
            layoutInflater.setMinimumHeight((int) (((float) viewGroup.height()) * 1061997773));
        }
        setupViewPager(this.viewPager);
        return layoutInflater;
    }

    private void setupViewPager(ViewPager viewPager) {
        PagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFragment(BecomeHostIntroFragment.newInstance(R.drawable.host_slide2, getString(R.string.become_host_heading_2), getString(R.string.become_host_data_2)), "");
        viewPagerAdapter.addFragment(BecomeHostIntroFragment.newInstance(R.drawable.host_slide3, getString(R.string.become_host_heading_3), getString(R.string.become_host_data_3)), "");
        viewPagerAdapter.addFragment(BecomeHostIntroFragment.newInstance(R.drawable.host_slide4, getString(R.string.become_host_heading_4), getString(R.string.become_host_data_4)), "");
        viewPagerAdapter.addFragment(BecomeHostIntroFragment.newInstance(R.drawable.host_slide5, getString(R.string.become_host_heading_5), getString(R.string.become_host_data_5)), "");
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOffscreenPageLimit(4);
        this.circlePageIndicator.setViewPager(viewPager);
    }

    public void onDestroyView() {
        if (this.unbinder != null) {
            this.unbinder.unbind();
        }
        super.onDestroyView();
    }

    public int convertPixelsToDp(int i, Context context) {
        return i / (context.getResources().getDisplayMetrics().densityDpi / 160);
    }
}
