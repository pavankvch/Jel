package com.data.alllocalities;

import com.data.searchtoplocalities.Locality;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class AllLocalitiesResponse {
    @SerializedName("values")
    @Expose
    private List<Locality> localities;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("short_name")
    @Expose
    private String shortName;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getShortName() {
        return this.shortName;
    }

    public void setShortName(String str) {
        this.shortName = str;
    }

    public List<Locality> getLocalities() {
        return this.localities;
    }

    public void setLocalities(List<Locality> list) {
        this.localities = list;
    }
}
