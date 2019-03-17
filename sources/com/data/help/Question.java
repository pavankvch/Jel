package com.data.help;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Question {
    @SerializedName("question_id")
    @Expose
    private int questionId;
    @SerializedName("questions")
    @Expose
    private String questions;

    public int getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(int i) {
        this.questionId = i;
    }

    public String getQuestions() {
        return this.questions;
    }

    public void setQuestions(String str) {
        this.questions = str;
    }
}
