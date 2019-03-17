package com.jelsat.customclasses;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.support.annotation.NonNull;

public class BadgeDrawable extends Drawable {
    public static final float ALPHA_MAX = 255.0f;
    public static final float FADE_DURATION = 100.0f;
    private static final String TAG = "BadgeDrawable";
    private boolean animating;
    private final Paint paint = new Paint(1);
    private final int size;
    private long startTimeMillis;

    public int getOpacity() {
        return -3;
    }

    public boolean isStateful() {
        return false;
    }

    public BadgeDrawable(int i, int i2) {
        this.paint.setColor(i);
        this.size = i2;
        this.animating = true;
        this.startTimeMillis = 0;
    }

    public void setIsAnimating(boolean z) {
        this.animating = z;
    }

    public void draw(@NonNull Canvas canvas) {
        if (this.animating) {
            if (this.startTimeMillis == 0) {
                this.startTimeMillis = SystemClock.uptimeMillis();
            }
            float uptimeMillis = ((float) (SystemClock.uptimeMillis() - this.startTimeMillis)) / 100.0f;
            if (uptimeMillis >= 1.0f) {
                this.animating = false;
                this.paint.setAlpha(255);
                drawInternal(canvas);
                return;
            }
            setAlpha((int) (uptimeMillis * 255.0f));
            drawInternal(canvas);
            return;
        }
        this.paint.setAlpha(255);
        drawInternal(canvas);
    }

    private void drawInternal(Canvas canvas) {
        Rect bounds = getBounds();
        int width = bounds.width();
        width /= 2;
        canvas.drawCircle((float) (bounds.centerX() + width), (float) (bounds.centerY() - (bounds.height() / 2)), (float) width, this.paint);
    }

    public void setAlpha(int i) {
        this.paint.setAlpha(i);
        invalidateSelf();
    }

    public int getAlpha() {
        return this.paint.getAlpha();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.paint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public int getIntrinsicHeight() {
        return this.size;
    }

    public int getIntrinsicWidth() {
        return this.size;
    }
}
