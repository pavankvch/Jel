package com.data.feedback;

import java.util.List;

public class FeedBackCombinedResponse {
    private List<FeedbackCategoryResponse> guestFeedback;
    private List<FeedbackCategoryResponse> hostFeedback;

    public FeedBackCombinedResponse(List<FeedbackCategoryResponse> list, List<FeedbackCategoryResponse> list2) {
        this.guestFeedback = list;
        this.hostFeedback = list2;
    }

    public List<FeedbackCategoryResponse> getGuestFeedback() {
        return this.guestFeedback;
    }

    public List<FeedbackCategoryResponse> getHostFeedback() {
        return this.hostFeedback;
    }
}
