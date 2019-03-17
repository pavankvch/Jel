package com.data.utils;

public class FilterImage {
    private String filterName;
    private boolean isEnabled;
    private boolean isSelected;

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }

    public String getFilterName() {
        return this.filterName;
    }

    public void setFilterName(String str) {
        this.filterName = str;
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

    public void setEnabled(boolean z) {
        this.isEnabled = z;
    }
}
