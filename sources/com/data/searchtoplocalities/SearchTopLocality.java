package com.data.searchtoplocalities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SearchTopLocality {
    @SerializedName("recent")
    @Expose
    private List<Locality> recent = null;
    @SerializedName("toprated")
    @Expose
    private List<Locality> toprated = null;

    public List<Locality> getToprated() {
        return this.toprated;
    }

    public void setToprated(List<Locality> list) {
        this.toprated = list;
    }

    public List<Locality> getRecent() {
        return this.recent;
    }

    public void setRecent(List<Locality> list) {
        this.recent = list;
    }
}
