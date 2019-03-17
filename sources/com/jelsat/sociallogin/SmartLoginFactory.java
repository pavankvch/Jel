package com.jelsat.sociallogin;

public class SmartLoginFactory {
    public static SmartLogin build(int i) {
        if (i == 1) {
            return new FacebookLogin();
        }
        return new GoogleLogin();
    }
}
