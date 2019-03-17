package com.jelsat.constants;

import java.util.ArrayList;

public class ListItemModel {
    ArrayList<EdittextValues> arrayList = new ArrayList();
    String title;

    public ListItemModel(String str) {
        this.title = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public ArrayList<EdittextValues> getArrayList() {
        return this.arrayList;
    }

    public void setArrayList(ArrayList<EdittextValues> arrayList) {
        this.arrayList = arrayList;
    }
}
