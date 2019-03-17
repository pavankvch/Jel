package com.data.messagehistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MessagesData {
    @SerializedName("chat")
    @Expose
    private List<MessageChat> messageChats;
    @SerializedName("user_details")
    @Expose
    private UserDetails userDetails;

    public UserDetails getUserDetails() {
        return this.userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public List<MessageChat> getMessageChats() {
        return this.messageChats;
    }

    public void setMessageChats(List<MessageChat> list) {
        this.messageChats = list;
    }
}
