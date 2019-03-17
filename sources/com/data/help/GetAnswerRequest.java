package com.data.help;

import com.google.gson.annotations.SerializedName;

public class GetAnswerRequest {
    @SerializedName("group_id")
    private String groupId;
    @SerializedName("question_id")
    private String questionId;

    public String getGroupId() {
        return this.groupId;
    }

    public void setGroupId(String str) {
        this.groupId = str;
    }

    public String getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(String str) {
        this.questionId = str;
    }
}
