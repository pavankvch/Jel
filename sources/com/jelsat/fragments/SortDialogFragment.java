package com.jelsat.fragments;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.data.utils.PrefUtils;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.jelsat.R;
import com.jelsat.utils.AnimationUtils;
import com.jelsat.utils.AnimationUtils.AnimationListener;
import java.util.Locale;

public class SortDialogFragment extends DialogFragment {
    private static final String TAG = "SortDialogFragment";
    @BindView(2131362210)
    AppCompatRadioButton highLowRadioButton;
    private boolean isAnimation = false;
    @BindView(2131362313)
    AppCompatRadioButton lowHighRadioButton;
    private OnClickListener mListener;
    private View mRootView;
    @BindView(2131362607)
    RadioGroup sortRadioGroup;
    @BindView(2131362608)
    RadioButton sortRatingRadioButton;
    private Unbinder unbinder;

    public interface OnClickListener {
        void getSelectedIndex(int i);
    }

    public static SortDialogFragment newInstance(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt(Param.INDEX, i);
        i = new SortDialogFragment();
        i.setArguments(bundle);
        return i;
    }

    public void setListener(OnClickListener onClickListener) {
        this.mListener = onClickListener;
    }

    @OnClick({2131362104})
    public void clickOnDone() {
        if (this.mListener != null) {
            this.mListener.getSelectedIndex(getSelectedRadioButtonIndex());
        }
        dismiss();
    }

    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        LayoutParams attributes = window.getAttributes();
        attributes.gravity = 80;
        attributes.width = -1;
        window.setAttributes(attributes);
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.getDecorView().setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == null) {
                    SortDialogFragment.this.dismiss();
                }
                return true;
            }
        });
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        getDialog().requestWindowFeature(1);
        this.mRootView = layoutInflater.inflate(R.layout.sort_dialog_fragment, viewGroup, false);
        this.unbinder = ButterKnife.bind(this, this.mRootView);
        initData();
        AnimationUtils.slideToUp(this.mRootView);
        if (PrefUtils.getInstance().getSeedResponse().isRateSort() != null) {
            this.sortRatingRadioButton.setVisibility(0);
        }
        if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar") != null) {
            this.highLowRadioButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_radiobutton_custom_icon, 0, R.drawable.ic_arrow_downward_black_24dp, 0);
            this.lowHighRadioButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_radiobutton_custom_icon, 0, R.drawable.ic_arrow_upward_black_24dp, 0);
            this.sortRatingRadioButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_radiobutton_custom_icon, 0, R.drawable.ic_star_rating, 0);
        } else {
            this.highLowRadioButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_arrow_downward_black_24dp, 0, R.drawable.ic_radiobutton_custom_icon, 0);
            this.lowHighRadioButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_arrow_upward_black_24dp, 0, R.drawable.ic_radiobutton_custom_icon, 0);
            this.sortRatingRadioButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_star_rating, 0, R.drawable.ic_radiobutton_custom_icon, 0);
        }
        return this.mRootView;
    }

    private void initData() {
        int i = getArguments().getInt(Param.INDEX);
        if (i != -1) {
            this.sortRadioGroup.check(((RadioButton) this.sortRadioGroup.getChildAt(i)).getId());
            return;
        }
        this.sortRadioGroup.setOnCheckedChangeListener(null);
        this.sortRadioGroup.clearCheck();
    }

    private int getSelectedRadioButtonIndex() {
        if (this.sortRadioGroup.getCheckedRadioButtonId() == -1) {
            return -1;
        }
        int indexOfChild = this.sortRadioGroup.indexOfChild(this.sortRadioGroup.findViewById(this.sortRadioGroup.getCheckedRadioButtonId()));
        Log.d(TAG, String.valueOf(indexOfChild));
        return indexOfChild;
    }

    public void dismiss() {
        if (!this.isAnimation) {
            this.isAnimation = true;
            AnimationUtils.slideToDown(this.mRootView, new AnimationListener() {
                public void onFinish() {
                    SortDialogFragment.this.isAnimation = false;
                    super.dismiss();
                }
            });
        }
    }

    public void onDestroyView() {
        if (this.mListener != null) {
            this.mListener = null;
        }
        if (this.unbinder != null) {
            this.unbinder.unbind();
        }
        super.onDestroyView();
    }
}
