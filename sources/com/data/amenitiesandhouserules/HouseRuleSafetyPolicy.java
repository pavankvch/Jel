package com.data.amenitiesandhouserules;

public class HouseRuleSafetyPolicy {
    private String id;
    private boolean isChecked;
    private boolean isNotPolicy;
    private String name;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public void setChecked(boolean z) {
        this.isChecked = z;
    }

    public boolean isNotPolicy() {
        return this.isNotPolicy;
    }

    public void setNotPolicy(boolean z) {
        this.isNotPolicy = z;
    }
}
