package com.jelsat.events;

import com.jelsat.sociallogin.user.SmartUser;

public class SocialDataEvent {
    private int loginType;
    private SmartUser smartUser;

    public SocialDataEvent(SmartUser smartUser, int i) {
        this.smartUser = smartUser;
        this.loginType = i;
    }

    public SmartUser getSmartUser() {
        return this.smartUser;
    }

    public int getLoginType() {
        return this.loginType;
    }
}
