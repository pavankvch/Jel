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

public class IntroFragment extends BaseFragment {
    @BindView(2131362548)
    TextView screenDescriptionTv;
    @BindView(2131362549)
    ImageView screenImg;
    @BindView(2131362550)
    TextView screenTitle;

    public int getFragmentLayoutId() {
        return R.layout.fragment_intro;
    }

    public static IntroFragment newInstance(int i, String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(StringConstants.SCREEN_TITLE, str);
        bundle.putString(StringConstants.SCREEN_DESCRIPTION, str2);
        bundle.putInt(StringConstants.SCREEN_IMG, i);
        i = new IntroFragment();
        i.setArguments(bundle);
        return i;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (getArguments() != null) {
            this.screenImg.setImageResource(getArguments().getInt(StringConstants.SCREEN_IMG));
            this.screenTitle.setText(getArguments().getString(StringConstants.SCREEN_TITLE));
            this.screenDescriptionTv.setText(getArguments().getString(StringConstants.SCREEN_DESCRIPTION));
        }
    }
}
