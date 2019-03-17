package com.jelsat.sociallogin;

import com.jelsat.sociallogin.user.SmartUser;

public interface SmartLoginCallbacks {
    void onLoginFailure(SmartLoginException smartLoginException);

    void onLoginSuccess(SmartUser smartUser, int i);
}
