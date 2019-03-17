package com.jelsat.baseuiframework;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.crashlytics.android.Crashlytics;
import com.jelsat.R;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;

public abstract class BaseFragmentActivity extends BaseAppCompactActivity {
    public int getActivityLayout() {
        return R.layout.fragment_base;
    }

    public abstract Fragment getFragment();

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Fabric.with(this, new Kit[]{new Crashlytics()});
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, getFragment()).commit();
    }

    protected Fragment getContainingFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.fragment_container);
    }
}
