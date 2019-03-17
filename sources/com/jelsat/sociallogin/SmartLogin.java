package com.jelsat.sociallogin;

import android.content.Context;
import android.content.Intent;

public abstract class SmartLogin {
    public abstract void login(SmartLoginConfig smartLoginConfig);

    public abstract boolean logout(Context context);

    public abstract void onActivityResult(int i, int i2, Intent intent, SmartLoginConfig smartLoginConfig);
}
