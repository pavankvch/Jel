package com.data.help;

import java.util.List;

public class FaqCombinedResponse {
    private List<Faq> guestHelp;
    private List<Faq> hostHelp;

    public FaqCombinedResponse(List<Faq> list, List<Faq> list2) {
        this.guestHelp = list;
        this.hostHelp = list2;
    }

    public List<Faq> getGuestHelp() {
        return this.guestHelp;
    }

    public List<Faq> getHostHelp() {
        return this.hostHelp;
    }
}
