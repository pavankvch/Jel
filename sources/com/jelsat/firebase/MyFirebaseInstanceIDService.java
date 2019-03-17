package com.jelsat.firebase;

import com.data.utils.PrefUtils;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    public void onTokenRefresh() {
        super.onTokenRefresh();
        storeRegIdInPref(FirebaseInstanceId.getInstance().getToken());
    }

    private void storeRegIdInPref(String str) {
        PrefUtils.getInstance().saveFcmToken(str);
        PrefUtils.getInstance().setDeviceTokenUpdated(false);
    }
}
