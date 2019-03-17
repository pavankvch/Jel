package com.data.utils;

public class ApiCallStatus {
    private boolean isSuccess;

    public ApiCallStatus(boolean z) {
        this.isSuccess = z;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }
}
