package com.jelsat.widgets;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.TypedValue;

public class TextDrawable extends Drawable {
    private static final int MONOSPACE = 3;
    private static final int SANS = 1;
    private static final int SERIF = 2;
    private static final int[] appearanceAttributes = new int[]{16842901, 16842902, 16842903, 16842904};
    private static final int[] themeAttributes = new int[]{16842804};
    private Resources mResources;
    private CharSequence mText = "";
    private Alignment mTextAlignment = Alignment.ALIGN_NORMAL;
    private Rect mTextBounds;
    private ColorStateList mTextColors;
    private StaticLayout mTextLayout;
    private TextPaint mTextPaint;
    private Path mTextPath;

    public int getOpacity() {
        return -3;
    }

    public TextDrawable(Context context) {
        ColorStateList colorStateList;
        int i;
        int i2;
        this.mResources = context.getResources();
        this.mTextBounds = new Rect();
        this.mTextPaint = new TextPaint(1);
        this.mTextPaint.density = this.mResources.getDisplayMetrics().density;
        this.mTextPaint.setDither(true);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(themeAttributes);
        int i3 = 0;
        int resourceId = obtainStyledAttributes.getResourceId(0, -1);
        obtainStyledAttributes.recycle();
        Typeface typeface = null;
        context = resourceId != -1 ? context.obtainStyledAttributes(resourceId, appearanceAttributes) : null;
        resourceId = 15;
        if (context != null) {
            colorStateList = null;
            i = -1;
            i2 = -1;
            while (i3 < context.getIndexCount()) {
                int index = context.getIndex(i3);
                switch (index) {
                    case 0:
                        resourceId = obtainStyledAttributes.getDimensionPixelSize(index, resourceId);
                        break;
                    case 1:
                        i = obtainStyledAttributes.getInt(index, i);
                        break;
                    case 2:
                        i2 = obtainStyledAttributes.getInt(index, i2);
                        break;
                    case 3:
                        colorStateList = obtainStyledAttributes.getColorStateList(index);
                        break;
                    default:
                        break;
                }
                i3++;
            }
            context.recycle();
        } else {
            colorStateList = null;
            i = -1;
            i2 = -1;
        }
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(ViewCompat.MEASURED_STATE_MASK);
        }
        setTextColor(colorStateList);
        setRawTextSize((float) resourceId);
        switch (i) {
            case 1:
                typeface = Typeface.SANS_SERIF;
                break;
            case 2:
                typeface = Typeface.SERIF;
                break;
            case 3:
                typeface = Typeface.MONOSPACE;
                break;
            default:
                break;
        }
        setTypeface(typeface, i2);
    }

    public void setText(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "";
        }
        this.mText = charSequence;
        measureContent();
    }

    public CharSequence getText() {
        return this.mText;
    }

    public float getTextSize() {
        return this.mTextPaint.getTextSize();
    }

    public void setTextSize(float f) {
        setTextSize(2, f);
    }

    public void setTextSize(int i, float f) {
        setRawTextSize(TypedValue.applyDimension(i, f, this.mResources.getDisplayMetrics()));
    }

    private void setRawTextSize(float f) {
        if (f != this.mTextPaint.getTextSize()) {
            this.mTextPaint.setTextSize(f);
            measureContent();
        }
    }

    public float getTextScaleX() {
        return this.mTextPaint.getTextScaleX();
    }

    public void setTextScaleX(float f) {
        if (f != this.mTextPaint.getTextScaleX()) {
            this.mTextPaint.setTextScaleX(f);
            measureContent();
        }
    }

    public Alignment getTextAlign() {
        return this.mTextAlignment;
    }

    public void setTextAlign(Alignment alignment) {
        if (this.mTextAlignment != alignment) {
            this.mTextAlignment = alignment;
            measureContent();
        }
    }

    public void setTypeface(Typeface typeface) {
        if (this.mTextPaint.getTypeface() != typeface) {
            this.mTextPaint.setTypeface(typeface);
            measureContent();
        }
    }

    public void setTypeface(Typeface typeface, int i) {
        float f = 0.0f;
        boolean z = false;
        if (i > 0) {
            if (typeface == null) {
                typeface = Typeface.defaultFromStyle(i);
            } else {
                typeface = Typeface.create(typeface, i);
            }
            setTypeface(typeface);
            typeface = ((typeface != null ? typeface.getStyle() : null) ^ -1) & i;
            i = this.mTextPaint;
            if ((typeface & 1) != 0) {
                z = true;
            }
            i.setFakeBoldText(z);
            i = this.mTextPaint;
            if ((typeface & 2) != null) {
                f = -0.25f;
            }
            i.setTextSkewX(f);
            return;
        }
        this.mTextPaint.setFakeBoldText(false);
        this.mTextPaint.setTextSkewX(0.0f);
        setTypeface(typeface);
    }

    public Typeface getTypeface() {
        return this.mTextPaint.getTypeface();
    }

    public void setTextColor(int i) {
        setTextColor(ColorStateList.valueOf(i));
    }

    public void setTextColor(ColorStateList colorStateList) {
        this.mTextColors = colorStateList;
        updateTextColors(getState());
    }

    public void setTextPath(Path path) {
        if (this.mTextPath != path) {
            this.mTextPath = path;
            measureContent();
        }
    }

    private void measureContent() {
        if (this.mTextPath != null) {
            this.mTextLayout = null;
            this.mTextBounds.setEmpty();
        } else {
            this.mTextLayout = new StaticLayout(this.mText, this.mTextPaint, (int) Math.ceil((double) Layout.getDesiredWidth(this.mText, this.mTextPaint)), this.mTextAlignment, 1.0f, 0.0f, false);
            this.mTextBounds.set(0, 0, this.mTextLayout.getWidth(), this.mTextLayout.getHeight());
        }
        invalidateSelf();
    }

    private boolean updateTextColors(int[] iArr) {
        iArr = this.mTextColors.getColorForState(iArr, -1);
        if (this.mTextPaint.getColor() == iArr) {
            return null;
        }
        this.mTextPaint.setColor(iArr);
        return 1;
    }

    protected void onBoundsChange(Rect rect) {
        this.mTextBounds.set(rect);
    }

    public boolean isStateful() {
        return this.mTextColors.isStateful();
    }

    protected boolean onStateChange(int[] iArr) {
        return updateTextColors(iArr);
    }

    public int getIntrinsicHeight() {
        if (this.mTextBounds.isEmpty()) {
            return -1;
        }
        return this.mTextBounds.bottom - this.mTextBounds.top;
    }

    public int getIntrinsicWidth() {
        if (this.mTextBounds.isEmpty()) {
            return -1;
        }
        return this.mTextBounds.right - this.mTextBounds.left;
    }

    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        int save = canvas.save();
        canvas.translate((float) bounds.left, (float) bounds.top);
        if (this.mTextPath == null) {
            this.mTextLayout.draw(canvas);
        } else {
            canvas.drawTextOnPath(this.mText.toString(), this.mTextPath, 0.0f, 0.0f, this.mTextPaint);
        }
        canvas.restoreToCount(save);
    }

    public void setAlpha(int i) {
        if (this.mTextPaint.getAlpha() != i) {
            this.mTextPaint.setAlpha(i);
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        if (this.mTextPaint.getColorFilter() != colorFilter) {
            this.mTextPaint.setColorFilter(colorFilter);
        }
    }
}
