package com.businesslogic.framework;

import android.content.Context;

public interface IBaseView {
    void dismissProgress();

    Context getContext();

    boolean isNetworkConnected();

    void showLoading();

    void showProgressDialog(String str);

    void showToast(String str);
}
