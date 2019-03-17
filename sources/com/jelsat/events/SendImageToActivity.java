package com.jelsat.events;

import android.graphics.Bitmap;
import java.io.File;

public class SendImageToActivity {
    private Bitmap bitmap;
    private File file;
    private String imageType;
    private String propertyId;

    public SendImageToActivity(Bitmap bitmap, File file, String str, String str2) {
        this.bitmap = bitmap;
        this.file = file;
        this.imageType = str;
        this.propertyId = str2;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public File getFile() {
        return this.file;
    }

    public String getImageType() {
        return this.imageType;
    }

    public String getPropertyId() {
        return this.propertyId;
    }
}
