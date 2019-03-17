package com.data.utils;

public class APIError {
    private int code;
    private String seken_errors;

    public String getSeken_errors() {
        return this.seken_errors;
    }

    public int getCode() {
        return this.code;
    }

    public void setSeken_errors(String str) {
        this.seken_errors = str;
    }

    public void setCode(int i) {
        this.code = i;
    }
}
