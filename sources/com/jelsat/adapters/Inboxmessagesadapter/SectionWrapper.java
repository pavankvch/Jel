package com.jelsat.adapters.Inboxmessagesadapter;

class SectionWrapper<S extends MessageSectionV2<C>, C> {
    private C child;
    private int childPosition;
    private S section;
    private boolean sectionItem = true;
    private int sectionPosition;

    public SectionWrapper(S s, int i) {
        this.section = s;
        this.sectionPosition = i;
        this.childPosition = -1;
    }

    public SectionWrapper(C c, int i, int i2) {
        this.child = c;
        this.sectionPosition = i;
        this.childPosition = i2;
    }

    public boolean isSection() {
        return this.sectionItem;
    }

    public S getSection() {
        return this.section;
    }

    public C getChild() {
        return this.child;
    }

    public int getSectionPosition() {
        return this.sectionPosition;
    }

    public int getChildPosition() {
        if (this.childPosition != -1) {
            return this.childPosition;
        }
        throw new IllegalAccessError("This is not child");
    }
}
