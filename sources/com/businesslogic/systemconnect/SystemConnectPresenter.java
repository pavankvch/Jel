package com.businesslogic.systemconnect;

import com.data.SystemConnect;
import com.data.retrofit.APIService;
import com.data.utils.PrefUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import org.greenrobot.eventbus.EventBus;

public class SystemConnectPresenter {
    private APIService apiService;
    private Disposable disposable;
    private PrefUtils prefUtils;

    public SystemConnectPresenter(APIService aPIService, PrefUtils prefUtils) {
        this.apiService = aPIService;
        this.prefUtils = prefUtils;
    }

    public void getCookie() {
        this.disposable = this.apiService.getCookie().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<SystemConnect>() {
            public void accept(SystemConnect systemConnect) throws Exception {
                PrefUtils access$000 = SystemConnectPresenter.this.prefUtils;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(systemConnect.getSessionName());
                stringBuilder.append("=");
                stringBuilder.append(systemConnect.getSessionId());
                access$000.saveCookie(stringBuilder.toString());
                EventBus.getDefault().post(Boolean.valueOf(true));
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                th.printStackTrace();
                EventBus.getDefault().post(Boolean.valueOf(false));
            }
        });
    }

    public void unSubScribeDisposable() {
        if (this.disposable != null && !this.disposable.isDisposed()) {
            this.disposable.dispose();
        }
    }
}
