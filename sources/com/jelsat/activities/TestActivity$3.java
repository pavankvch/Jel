package com.jelsat.activities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;

class TestActivity$3 implements Comparator<String> {
    final /* synthetic */ TestActivity this$0;

    TestActivity$3(TestActivity testActivity) {
        this.this$0 = testActivity;
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
