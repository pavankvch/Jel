package com.jelsat.events;

import android.graphics.Bitmap;
import com.data.addproperty.ImageUploadResponse;
import java.io.File;

public class ImageUploadResponseEvent {
    private Bitmap bitmap;
    private File imageFile;
    private String imageType;
    private ImageUploadResponse imageUploadResponse;

    public ImageUploadResponseEvent(ImageUploadResponse imageUploadResponse, String str, Bitmap bitmap, File file) {
        this.imageUploadResponse = imageUploadResponse;
        this.imageType = str;
        this.bitmap = bitmap;
        this.imageFile = file;
    }

    public ImageUploadResponse getImageUploadResponse() {
        return this.imageUploadResponse;
    }

    public String getImageType() {
        return this.imageType;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public File getImageFile() {
        return this.imageFile;
    }
}
