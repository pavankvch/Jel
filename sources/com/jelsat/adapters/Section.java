package com.jelsat.adapters;

import java.util.ArrayList;
import java.util.List;

/* compiled from: RecyclerViewSectionAdapter */
class Section {
    private int listIndex;
    private List<Integer> objectIndexes = new ArrayList();

    public void addObjectIndex(int i) {
        this.objectIndexes.add(Integer.valueOf(i));
    }

    public int getObjectIndex(int i) {
        return ((Integer) this.objectIndexes.get(i)).intValue();
    }

    public void setListIndex(int i) {
        this.listIndex = i;
    }

    public int getIndexOfSectionInList() {
        return this.listIndex;
    }

    public int getSize() {
        return this.objectIndexes.size();
    }
}
