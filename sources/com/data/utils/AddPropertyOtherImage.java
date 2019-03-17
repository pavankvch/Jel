package com.data.utils;

import android.graphics.Bitmap;
import java.io.File;

public class AddPropertyOtherImage {
    private Bitmap bitmap;
    private File file;
    private String id;
    private boolean isUploaded;
    private String title;
    private String url;

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public File getFile() {
        return this.file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public boolean isUploaded() {
        return this.isUploaded;
    }

    public void setUploaded(boolean z) {
        this.isUploaded = z;
    }
}
