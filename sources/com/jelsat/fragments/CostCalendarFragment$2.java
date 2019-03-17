package com.jelsat.fragments;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;

class CostCalendarFragment$2 implements Comparator<String> {
    final /* synthetic */ CostCalendarFragment this$0;

    CostCalendarFragment$2(CostCalendarFragment costCalendarFragment) {
        this.this$0 = costCalendarFragment;
    }

    public int compare(String str, String str2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return simpleDateFormat.parse(str).compareTo(simpleDateFormat.parse(str2));
        } catch (ParseException e) {
            e.printStackTrace();
            return str.compareTo(str2);
        }
    }
}
