package com.jelsat.googlemap;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.jelsat.R;

class BubbleDrawable extends Drawable {
    private int mColor = -1;
    private final Drawable mMask;
    private final Drawable mShadow;

    public int getOpacity() {
        return -3;
    }

    public BubbleDrawable(Resources resources) {
        this.mMask = resources.getDrawable(R.drawable.amu_bubble_mask);
        this.mShadow = resources.getDrawable(R.drawable.amu_bubble_shadow);
    }

    public void setColor(int i) {
        this.mColor = i;
    }

    public void draw(Canvas canvas) {
        this.mMask.draw(canvas);
        canvas.drawColor(this.mColor, Mode.SRC_IN);
        this.mShadow.draw(canvas);
    }

    public void setAlpha(int i) {
        throw new UnsupportedOperationException();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        throw new UnsupportedOperationException();
    }

    public void setBounds(int i, int i2, int i3, int i4) {
        this.mMask.setBounds(i, i2, i3, i4);
        this.mShadow.setBounds(i, i2, i3, i4);
    }

    public void setBounds(Rect rect) {
        this.mMask.setBounds(rect);
        this.mShadow.setBounds(rect);
    }

    public boolean getPadding(Rect rect) {
        return this.mMask.getPadding(rect);
    }
}
