package com.jelsat.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.AdapterDataObserver;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class RecyclerViewSectionAdapter<T, RowAdapter extends Adapter & GetObject<T>> extends Adapter<ViewHolder> {
    public final int SECTION_HEADER_VIEW_TYPE = 0;
    private final AdapterDataObserver mDataObserver;
    RowAdapter mRowAdapter;
    Adapter mSectionHeaderAdapter;
    private List<Integer> sectionIndices;
    private Map<Integer, Section> sections;

    public interface GetObject<T> {
        T getObject(int i);
    }

    public abstract int getIndexOfSection(T t);

    public RecyclerViewSectionAdapter(Adapter adapter, RowAdapter rowAdapter) {
        this.mSectionHeaderAdapter = adapter;
        this.mRowAdapter = rowAdapter;
        this.mDataObserver = new AdapterDataObserver() {
            public void onChanged() {
                super.onChanged();
                RecyclerViewSectionAdapter.this.initSections();
                RecyclerViewSectionAdapter.this.notifyDataSetChanged();
            }
        };
        this.mRowAdapter.registerAdapterDataObserver(this.mDataObserver);
        initSections();
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i != 0) {
            return this.mRowAdapter.onCreateViewHolder(viewGroup, (i / 10) - 1);
        }
        return this.mSectionHeaderAdapter.onCreateViewHolder(viewGroup, i);
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (getItemViewType(i) != 0) {
            this.mRowAdapter.onBindViewHolder(viewHolder, getRowActualPosition(i));
        } else {
            this.mSectionHeaderAdapter.onBindViewHolder(viewHolder, getSectionPositionInList(i));
        }
    }

    private int getSectionPositionInList(int i) {
        for (Integer intValue : this.sectionIndices) {
            int intValue2 = intValue.intValue();
            int indexOfSectionInList = ((Section) this.sections.get(Integer.valueOf(intValue2))).getIndexOfSectionInList();
            if (indexOfSectionInList == i) {
                return intValue2;
            }
            if (i < indexOfSectionInList) {
                return -1;
            }
        }
        return -1;
    }

    private int getRowActualPosition(int i) {
        for (Integer intValue : this.sectionIndices) {
            i--;
            Section section = (Section) this.sections.get(Integer.valueOf(intValue.intValue()));
            int size = section.getSize();
            if (i < size) {
                return section.getObjectIndex(i);
            }
            i -= size;
        }
        return -1;
    }

    private void initSections() {
        if (this.sections != null) {
            this.sections.clear();
        }
        this.sections = new HashMap();
        for (int i = 0; i < this.mRowAdapter.getItemCount(); i++) {
            int indexOfSection = getIndexOfSection(((GetObject) this.mRowAdapter).getObject(i));
            if (this.sections.containsKey(Integer.valueOf(indexOfSection))) {
                ((Section) this.sections.get(Integer.valueOf(indexOfSection))).addObjectIndex(i);
            } else {
                Section section = new Section();
                section.addObjectIndex(i);
                this.sections.put(Integer.valueOf(indexOfSection), section);
            }
        }
        initSectionIndexesInList();
    }

    private void initSectionIndexesInList() {
        this.sectionIndices = new ArrayList(this.sections.keySet());
        Collections.sort(this.sectionIndices);
        int i = 0;
        for (Integer intValue : this.sectionIndices) {
            Section section = (Section) this.sections.get(Integer.valueOf(intValue.intValue()));
            section.setListIndex(i);
            i += section.getSize() + 1;
        }
    }

    public int getItemCount() {
        return this.mRowAdapter.getItemCount() + this.sections.size();
    }

    public int getItemViewType(int i) {
        return getSectionPositionInList(i) == -1 ? (this.mRowAdapter.getItemViewType(i) + 1) * 10 : 0;
    }
}
