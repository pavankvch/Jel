package com.data.searchproperty;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SearchPropertyResponse {
    @SerializedName("amenities")
    @Expose
    private List<String> amenities;
    @SerializedName("properties")
    @Expose
    private List<SearchProperty> properties = null;

    public List<SearchProperty> getProperties() {
        return this.properties;
    }

    public void setProperties(List<SearchProperty> list) {
        this.properties = list;
    }

    public List<String> getAmenities() {
        return this.amenities;
    }

    public void setAmenities(List<String> list) {
        this.amenities = list;
    }
}
