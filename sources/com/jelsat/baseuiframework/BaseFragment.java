package com.jelsat.baseuiframework;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.businesslogic.framework.IBaseView;
import com.crashlytics.android.Crashlytics;
import com.jelsat.JelsatApplication;
import com.jelsat.R;
import com.jelsat.helper.LocaleManager;
import com.jelsat.utils.NetworkUtil;
import io.fabric.sdk.android.Fabric;

public abstract class BaseFragment extends Fragment implements IBaseView {
    private BaseAppCompactActivity baseActivity;
    protected ProgressDialog progressDialog;
    private Unbinder unbinder;

    public abstract int getFragmentLayoutId();

    public void onAttach(Context context) {
        super.onAttach(LocaleManager.setLocale(context));
        if (context instanceof BaseAppCompactActivity) {
            this.baseActivity = (BaseAppCompactActivity) context;
        }
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        bundle = getFragmentLayoutId();
        Fabric.with(getActivity(), new Crashlytics());
        View inflate = layoutInflater.inflate(R.layout.frag_base, viewGroup, false);
        ViewStub viewStub = (ViewStub) inflate.findViewById(R.id.layout_fragment);
        viewStub.setLayoutResource(bundle);
        viewStub.inflate();
        this.unbinder = ButterKnife.bind((Object) this, inflate);
        return inflate;
    }

    public void showProgressDialog(String str) {
        if (this.baseActivity != null) {
            this.baseActivity.showProgressDialog(str);
        }
    }

    public void showLoading() {
        if (this.baseActivity != null) {
            this.baseActivity.showLoading();
        }
    }

    public void dismissProgress() {
        if (this.baseActivity != null) {
            this.baseActivity.dismissProgress();
        }
    }

    public void showToast(String str) {
        if (this.baseActivity != null) {
            this.baseActivity.showToast(str);
        }
    }

    protected void goToActivity(Class cls) {
        if (this.baseActivity != null) {
            this.baseActivity.goToActivity(cls);
        }
    }

    protected void goToActivity(Class cls, Bundle bundle) {
        if (this.baseActivity != null) {
            this.baseActivity.goToActivity(cls, bundle);
        }
    }

    public void onStop() {
        dismissProgress();
        super.onStop();
    }

    public void onDestroyView() {
        this.progressDialog = null;
        this.unbinder.unbind();
        super.onDestroyView();
    }

    public void onDetach() {
        this.baseActivity = null;
        super.onDetach();
        JelsatApplication.getRefWatcher(requireActivity()).watch(this);
    }

    public boolean isNetworkConnected() {
        if (!NetworkUtil.isConnectedToNetwork(requireActivity())) {
            if (!NetworkUtil.isNetworkConnectedThroughWifi(requireActivity())) {
                return false;
            }
        }
        return true;
    }
}
