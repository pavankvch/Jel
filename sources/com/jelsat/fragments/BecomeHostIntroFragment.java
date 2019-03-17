package com.jelsat.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseFragment;
import com.jelsat.constants.StringConstants;
import com.jelsat.utils.GlideApp;

public class BecomeHostIntroFragment extends BaseFragment {
    @BindView(2131362548)
    TextView screenDescriptionTv;
    @BindView(2131362549)
    ImageView screenImg;
    @BindView(2131362550)
    TextView screenTitle;

    public int getFragmentLayoutId() {
        return R.layout.host_guide_fragment;
    }

    public static BecomeHostIntroFragment newInstance(int i, String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(StringConstants.SCREEN_TITLE, str);
        bundle.putString(StringConstants.SCREEN_DESCRIPTION, str2);
        bundle.putInt(StringConstants.SCREEN_IMG, i);
        i = new BecomeHostIntroFragment();
        i.setArguments(bundle);
        return i;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (getArguments() != null) {
            loadScreenImage(getArguments().getInt(StringConstants.SCREEN_IMG));
            this.screenTitle.setText(getArguments().getString(StringConstants.SCREEN_TITLE));
            this.screenDescriptionTv.setText(getArguments().getString(StringConstants.SCREEN_DESCRIPTION));
        }
    }

    private void loadScreenImage(int i) {
        GlideApp.with(getActivity()).load(Integer.valueOf(i)).into(this.screenImg);
    }
}
