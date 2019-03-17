package com.jelsat.firebase;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import com.data.utils.PrefUtils;
import com.google.firebase.iid.FirebaseInstanceId;

public class DeleteTokenService extends IntentService {
    public static final String TAG = "DeleteTokenService";

    public DeleteTokenService() {
        super(TAG);
    }

    protected void onHandleIntent(Intent intent) {
        try {
            intent = PrefUtils.getInstance().getFcmToken();
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder("Token before deletion: ");
            stringBuilder.append(intent);
            Log.e(str, stringBuilder.toString());
            FirebaseInstanceId.getInstance().deleteInstanceId();
            PrefUtils.getInstance().saveFcmToken("");
            intent = PrefUtils.getInstance().getFcmToken();
            str = TAG;
            stringBuilder = new StringBuilder("Token deleted. Proof: ");
            stringBuilder.append(intent);
            Log.e(str, stringBuilder.toString());
            Log.e(TAG, "Getting new token");
            FirebaseInstanceId.getInstance().getToken();
        } catch (Intent intent2) {
            intent2.printStackTrace();
        }
    }
}
