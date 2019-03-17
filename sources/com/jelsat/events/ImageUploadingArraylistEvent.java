package com.jelsat.events;

import android.graphics.Bitmap;
import java.io.File;

public class ImageUploadingArraylistEvent {
    private Bitmap bitmap;
    private File imageUploadFile;
    private String imageUploadTitle;

    public ImageUploadingArraylistEvent(Bitmap bitmap, File file, String str) {
        this.bitmap = bitmap;
        this.imageUploadFile = file;
        this.imageUploadTitle = str;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public File getImageUploadFile() {
        return this.imageUploadFile;
    }

    public String getImageUploadTitle() {
        return this.imageUploadTitle;
    }
}
