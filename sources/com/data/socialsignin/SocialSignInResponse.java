package com.data.socialsignin;

import com.data.signin.UserModel;
import com.google.gson.annotations.SerializedName;

public class SocialSignInResponse {
    @SerializedName("sessid")
    private String sessionId;
    @SerializedName("session_name")
    private String sessionName;
    private String token;
    private UserModel user;

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(String str) {
        this.sessionId = str;
    }

    public String getSessionName() {
        return this.sessionName;
    }

    public void setSessionName(String str) {
        this.sessionName = str;
    }

    public UserModel getUser() {
        return this.user;
    }

    public void setUser(UserModel userModel) {
        this.user = userModel;
    }
}
