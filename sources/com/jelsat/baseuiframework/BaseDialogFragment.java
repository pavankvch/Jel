package com.jelsat.baseuiframework;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.businesslogic.framework.IBaseView;
import com.jelsat.utils.NetworkUtil;
import com.jelsat.utils.ProgressDialogUtil;

public abstract class BaseDialogFragment extends DialogFragment implements IBaseView {
    protected Dialog progressDialog;
    private Unbinder unbinder;

    public abstract int getDialogFragmentLayoutId();

    public void showProgressDialog(String str) {
        this.progressDialog = ProgressDialogUtil.showLoading(getActivity(), str);
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        layoutInflater = layoutInflater.inflate(getDialogFragmentLayoutId(), viewGroup, false);
        this.unbinder = ButterKnife.bind(this, layoutInflater);
        return layoutInflater;
    }

    public void showLoading() {
        this.progressDialog = ProgressDialogUtil.showLoading(getActivity());
    }

    public void dismissProgress() {
        if (this.progressDialog != null && this.progressDialog.isShowing()) {
            this.progressDialog.cancel();
        }
        this.progressDialog = null;
    }

    public boolean isNetworkConnected() {
        if (!NetworkUtil.isConnectedToNetwork(requireActivity())) {
            if (!NetworkUtil.isNetworkConnectedThroughWifi(requireActivity())) {
                return false;
            }
        }
        return true;
    }

    public void showToast(String str) {
        Toast.makeText(getActivity(), str, 0).show();
    }

    public int applyColor(@ColorRes int i) {
        return ContextCompat.getColor(requireActivity(), i);
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
}
