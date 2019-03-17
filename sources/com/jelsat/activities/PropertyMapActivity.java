package com.jelsat.activities;

import android.support.v4.app.Fragment;
import com.jelsat.baseuiframework.BaseFragmentActivity;
import com.jelsat.fragments.PropertyMapFragment;

public class PropertyMapActivity extends BaseFragmentActivity {
    protected Fragment getFragment() {
        return new PropertyMapFragment();
    }
}
