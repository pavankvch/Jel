package com.data;

import com.google.gson.annotations.SerializedName;

public class SystemConnect {
    @SerializedName("sessid")
    private String sessionId;
    @SerializedName("session_name")
    private String sessionName;

    public String getSessionId() {
        return this.sessionId;
    }

    public String getSessionName() {
        return this.sessionName;
    }
}
