package com.jelsat.sociallogin;

public class SmartLoginException extends Exception {
    private int loginType;

    public SmartLoginException(String str, int i) {
        super(str);
        this.loginType = i;
    }

    public SmartLoginException(String str, Throwable th, int i) {
        super(str, th);
        this.loginType = i;
    }

    public int getLoginType() {
        return this.loginType;
    }
}
