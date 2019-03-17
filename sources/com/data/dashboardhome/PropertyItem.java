package com.data.dashboardhome;

import com.data.searchproperty.SearchProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PropertyItem {
    @SerializedName("items")
    private List<SearchProperty> items;
    @SerializedName("is_instant")
    @Expose
    private int propertyInstant;
    @SerializedName("name")
    private String sectionName;

    public String getSectionName() {
        return this.sectionName;
    }

    public List<SearchProperty> getItems() {
        return this.items;
    }

    public int getPropertyInstant() {
        return this.propertyInstant;
    }
}
