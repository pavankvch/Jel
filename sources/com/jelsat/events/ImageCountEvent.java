package com.jelsat.events;

public class ImageCountEvent {
    private int imageCount;

    public ImageCountEvent(int i) {
        this.imageCount = i;
    }

    public int getImageCount() {
        return this.imageCount;
    }
}
