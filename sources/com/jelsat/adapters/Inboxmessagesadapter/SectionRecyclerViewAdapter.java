package com.jelsat.adapters.Inboxmessagesadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public abstract class SectionRecyclerViewAdapter<S extends MessageSectionV2<C>, C, SVH extends ViewHolder, CVH extends ViewHolder> extends Adapter<ViewHolder> {
    private int CHILD_VIEW_TYPE = 2;
    private int SECTION_VIEW_TYPE = 1;
    private List<SectionWrapper<S, C>> flatItemList;
    private List<S> sectionItemList;

    public abstract void onBindChildViewHolder(CVH cvh, int i, int i2, C c);

    public abstract void onBindSectionViewHolder(SVH svh, int i, S s);

    public abstract CVH onCreateChildViewHolder(ViewGroup viewGroup, int i);

    public abstract SVH onCreateSectionViewHolder(ViewGroup viewGroup, int i);

    public SectionRecyclerViewAdapter(Context context, List<S> list) {
        this.sectionItemList = list;
        this.flatItemList = generateFlatItemList(list);
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (isSectionViewType(i)) {
            return onCreateSectionViewHolder(viewGroup, i);
        }
        return onCreateChildViewHolder(viewGroup, i);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (this.flatItemList != null) {
            if (i > this.flatItemList.size()) {
                StringBuilder stringBuilder = new StringBuilder("Trying to bind item out of bounds, size ");
                stringBuilder.append(this.flatItemList.size());
                stringBuilder.append(" flatPosition ");
                stringBuilder.append(i);
                stringBuilder.append(". Was the data changed without a call to notify...()?");
                throw new IllegalStateException(stringBuilder.toString());
            }
            SectionWrapper sectionWrapper = (SectionWrapper) this.flatItemList.get(i);
            if (sectionWrapper.isSection()) {
                onBindSectionViewHolder(viewHolder, sectionWrapper.getSectionPosition(), sectionWrapper.getSection());
                return;
            }
            onBindChildViewHolder(viewHolder, sectionWrapper.getSectionPosition(), sectionWrapper.getChildPosition(), sectionWrapper.getChild());
        }
    }

    private void generateSectionWrapper(List<SectionWrapper<S, C>> list, S s, int i) {
        if (list != null) {
            list.add(new SectionWrapper(s, i));
            s = s.getChildItems();
            for (int i2 = 0; i2 < s.size(); i2++) {
                list.add(new SectionWrapper(s.get(i2), i, i2));
            }
        }
    }

    private List<SectionWrapper<S, C>> generateFlatItemList(List<S> list) {
        List<SectionWrapper<S, C>> arrayList = new ArrayList();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                generateSectionWrapper(arrayList, (MessageSectionV2) list.get(i), i);
            }
        }
        return arrayList;
    }

    public int getItemCount() {
        return this.flatItemList != null ? this.flatItemList.size() : 0;
    }

    public int getItemViewType(int i) {
        return ((SectionWrapper) this.flatItemList.get(i)).isSection() != 0 ? this.SECTION_VIEW_TYPE : this.CHILD_VIEW_TYPE;
    }

    public boolean isSectionViewType(int i) {
        return i == this.SECTION_VIEW_TYPE;
    }

    public void insertNewSection(S s) {
        insertNewSection(s, this.sectionItemList.size());
    }

    public void insertNewSection(S s, int i) {
        if (i <= this.sectionItemList.size()) {
            if (i >= 0) {
                notifyDataChanged(this.sectionItemList);
                return;
            }
        }
        StringBuilder stringBuilder = new StringBuilder("sectionPosition =  ");
        stringBuilder.append(i);
        stringBuilder.append(" , Size is ");
        stringBuilder.append(this.sectionItemList.size());
        throw new IndexOutOfBoundsException(stringBuilder.toString());
    }

    public void removeSection(int i) {
        if (i <= this.sectionItemList.size() - 1) {
            if (i >= 0) {
                this.sectionItemList.remove(i);
                notifyDataChanged(this.sectionItemList);
                return;
            }
        }
        StringBuilder stringBuilder = new StringBuilder("sectionPosition =  ");
        stringBuilder.append(i);
        stringBuilder.append(" , Size is ");
        stringBuilder.append(this.sectionItemList.size());
        throw new IndexOutOfBoundsException(stringBuilder.toString());
    }

    public void insertNewChild(C c, int i) {
        if (i <= this.sectionItemList.size() - 1) {
            if (i >= 0) {
                insertNewChild(c, i, ((MessageSectionV2) this.sectionItemList.get(i)).getChildItems().size());
                return;
            }
        }
        StringBuilder stringBuilder = new StringBuilder("Invalid sectionPosition =  ");
        stringBuilder.append(i);
        stringBuilder.append(" , Size is ");
        stringBuilder.append(this.sectionItemList.size());
        throw new IndexOutOfBoundsException(stringBuilder.toString());
    }

    public void insertNewChild(C c, int i, int i2) {
        if (i <= this.sectionItemList.size() - 1) {
            if (i >= 0) {
                if (i2 <= ((MessageSectionV2) this.sectionItemList.get(i)).getChildItems().size()) {
                    if (i2 >= 0) {
                        ((MessageSectionV2) this.sectionItemList.get(i)).getChildItems().add(i2, c);
                        notifyDataChanged(this.sectionItemList);
                        return;
                    }
                }
                StringBuilder stringBuilder = new StringBuilder("Invalid childPosition =  ");
                stringBuilder.append(i2);
                stringBuilder.append(" , Size is ");
                stringBuilder.append(((MessageSectionV2) this.sectionItemList.get(i)).getChildItems().size());
                throw new IndexOutOfBoundsException(stringBuilder.toString());
            }
        }
        i2 = new StringBuilder("Invalid sectionPosition =  ");
        i2.append(i);
        i2.append(" , Size is ");
        i2.append(this.sectionItemList.size());
        throw new IndexOutOfBoundsException(i2.toString());
    }

    public void removeChild(int i, int i2) {
        if (i <= this.sectionItemList.size() - 1) {
            if (i >= 0) {
                if (i2 <= ((MessageSectionV2) this.sectionItemList.get(i)).getChildItems().size() - 1) {
                    if (i2 >= 0) {
                        ((MessageSectionV2) this.sectionItemList.get(i)).getChildItems().remove(i2);
                        notifyDataChanged(this.sectionItemList);
                        return;
                    }
                }
                StringBuilder stringBuilder = new StringBuilder("Invalid childPosition =  ");
                stringBuilder.append(i2);
                stringBuilder.append(" , Size is ");
                stringBuilder.append(((MessageSectionV2) this.sectionItemList.get(i)).getChildItems().size());
                throw new IndexOutOfBoundsException(stringBuilder.toString());
            }
        }
        StringBuilder stringBuilder2 = new StringBuilder("Invalid sectionPosition =  ");
        stringBuilder2.append(i);
        stringBuilder2.append(" , Size is ");
        stringBuilder2.append(this.sectionItemList.size());
        throw new IndexOutOfBoundsException(stringBuilder2.toString());
    }

    public void notifyDataChanged(List<S> list) {
        this.flatItemList = new ArrayList();
        this.flatItemList = generateFlatItemList(list);
        notifyDataSetChanged();
    }
}
