package com.jelsat.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import com.jelsat.R;
import com.jelsat.interfaces.OnRangeSeekbarChangeListener;
import com.jelsat.interfaces.OnRangeSeekbarFinalValueListener;

public class PriceRangeSeekbar extends View {
    private static final int INVALID_POINTER_ID = 255;
    private final float NO_FIXED_GAP;
    private final float NO_STEP;
    private Paint _paint;
    private RectF _rect;
    private float absoluteMaxStartValue;
    private float absoluteMaxValue;
    private float absoluteMinStartValue;
    private float absoluteMinValue;
    private int barColor;
    private int barColorMode;
    private int barGradientEnd;
    private int barGradientStart;
    private float barHeight;
    private int barHighlightColor;
    private int barHighlightColorMode;
    private int barHighlightGradientEnd;
    private int barHighlightGradientStart;
    private float barPadding;
    private float cornerRadius;
    private int dataType;
    private float fixGap;
    private float gap;
    private Drawable leftDrawable;
    private Drawable leftDrawablePressed;
    private Bitmap leftThumb;
    private int leftThumbColor;
    private int leftThumbColorNormal;
    private int leftThumbColorPressed;
    private Bitmap leftThumbPressed;
    private int mActivePointerId;
    private boolean mIsDragging;
    private float maxStartValue;
    private float maxValue;
    private float minStartValue;
    private float minValue;
    private double normalizedMaxValue;
    private double normalizedMinValue;
    private OnRangeSeekbarChangeListener onRangeSeekbarChangeListener;
    private OnRangeSeekbarFinalValueListener onRangeSeekbarFinalValueListener;
    private int pointerIndex;
    private Thumb pressedThumb;
    private RectF rectLeftThumb;
    private RectF rectRightThumb;
    private Drawable rightDrawable;
    private Drawable rightDrawablePressed;
    private Bitmap rightThumb;
    private int rightThumbColor;
    private int rightThumbColorNormal;
    private int rightThumbColorPressed;
    private Bitmap rightThumbPressed;
    private float steps;
    private float thumbDiameter;
    private float thumbHeight;
    private float thumbWidth;

    public static final class ColorMode {
        public static final int GRADIENT = 1;
        public static final int SOLID = 0;
    }

    public static final class DataType {
        public static final int BYTE = 5;
        public static final int DOUBLE = 1;
        public static final int FLOAT = 3;
        public static final int INTEGER = 2;
        public static final int LONG = 0;
        public static final int SHORT = 4;
    }

    public enum Thumb {
        MIN,
        MAX
    }

    protected void touchDown(float f, float f2) {
    }

    protected void touchMove(float f, float f2) {
    }

    protected void touchUp(float f, float f2) {
    }

    public PriceRangeSeekbar(Context context) {
        this(context, null);
    }

    public PriceRangeSeekbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PriceRangeSeekbar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.NO_STEP = -1.0f;
        this.NO_FIXED_GAP = -1.0f;
        this.mActivePointerId = 255;
        this.normalizedMinValue = 0.0d;
        this.normalizedMaxValue = 100.0d;
        if (isInEditMode() == 0) {
            context = context.obtainStyledAttributes(attributeSet, R.styleable.PriceRangeSeekbar);
            try {
                this.cornerRadius = getCornerRadius(context);
                this.minValue = getMinValue(context);
                this.maxValue = getMaxValue(context);
                this.minStartValue = getMinStartValue(context);
                this.maxStartValue = getMaxStartValue(context);
                this.steps = getSteps(context);
                this.gap = getGap(context);
                this.fixGap = getFixedGap(context);
                this.barColorMode = getBarColorMode(context);
                this.barColor = getBarColor(context);
                this.barGradientStart = getBarGradientStart(context);
                this.barGradientEnd = getBarGradientEnd(context);
                this.barHighlightColorMode = getBarHighlightColorMode(context);
                this.barHighlightColor = getBarHighlightColor(context);
                this.barHighlightGradientStart = getBarHighlightGradientStart(context);
                this.barHighlightGradientEnd = getBarHighlightGradientEnd(context);
                this.leftThumbColorNormal = getLeftThumbColor(context);
                this.rightThumbColorNormal = getRightThumbColor(context);
                this.leftThumbColorPressed = getLeftThumbColorPressed(context);
                this.rightThumbColorPressed = getRightThumbColorPressed(context);
                this.leftDrawable = getLeftDrawable(context);
                this.rightDrawable = getRightDrawable(context);
                this.leftDrawablePressed = getLeftDrawablePressed(context);
                this.rightDrawablePressed = getRightDrawablePressed(context);
                this.thumbDiameter = getDiameter(context);
                this.dataType = getDataType(context);
                init();
            } finally {
                context.recycle();
            }
        }
    }

    protected void init() {
        this.absoluteMinValue = this.minValue;
        this.absoluteMaxValue = this.maxValue;
        this.leftThumbColor = this.leftThumbColorNormal;
        this.rightThumbColor = this.rightThumbColorNormal;
        this.leftThumb = getBitmap(this.leftDrawable);
        this.rightThumb = getBitmap(this.rightDrawable);
        this.leftThumbPressed = getBitmap(this.leftDrawablePressed);
        this.rightThumbPressed = getBitmap(this.rightDrawablePressed);
        this.leftThumbPressed = this.leftThumbPressed == null ? this.leftThumb : this.leftThumbPressed;
        this.rightThumbPressed = this.rightThumbPressed == null ? this.rightThumb : this.rightThumbPressed;
        this.gap = Math.max(0.0f, Math.min(this.gap, this.absoluteMaxValue - this.absoluteMinValue));
        this.gap = (this.gap / (this.absoluteMaxValue - this.absoluteMinValue)) * 100.0f;
        if (this.fixGap != -1.0f) {
            this.fixGap = Math.min(this.fixGap, this.absoluteMaxValue);
            this.fixGap = (this.fixGap / (this.absoluteMaxValue - this.absoluteMinValue)) * 100.0f;
            addFixGap(true);
        }
        this.thumbWidth = getThumbWidth();
        this.thumbHeight = getThumbHeight();
        this.barHeight = getBarHeight();
        this.barPadding = getBarPadding();
        this._paint = new Paint(1);
        this._rect = new RectF();
        this.rectLeftThumb = new RectF();
        this.rectRightThumb = new RectF();
        this.pressedThumb = null;
        setMinStartValue();
        setMaxStartValue();
        setWillNotDraw(false);
    }

    public PriceRangeSeekbar setCornerRadius(float f) {
        this.cornerRadius = f;
        return this;
    }

    public PriceRangeSeekbar setMinValue(float f) {
        this.minValue = f;
        this.absoluteMinValue = f;
        return this;
    }

    public PriceRangeSeekbar setMaxValue(float f) {
        this.maxValue = f;
        this.absoluteMaxValue = f;
        return this;
    }

    public PriceRangeSeekbar setMinStartValue(float f) {
        this.minStartValue = f;
        this.absoluteMinStartValue = f;
        return this;
    }

    public PriceRangeSeekbar setMaxStartValue(float f) {
        this.maxStartValue = f;
        this.absoluteMaxStartValue = f;
        return this;
    }

    public PriceRangeSeekbar setSteps(float f) {
        this.steps = f;
        return this;
    }

    public PriceRangeSeekbar setGap(float f) {
        this.gap = f;
        return this;
    }

    public PriceRangeSeekbar setFixGap(float f) {
        this.fixGap = f;
        return this;
    }

    public PriceRangeSeekbar setBarColorMode(int i) {
        this.barColorMode = i;
        return this;
    }

    public PriceRangeSeekbar setBarColor(int i) {
        this.barColor = i;
        return this;
    }

    public PriceRangeSeekbar setBarGradientStart(int i) {
        this.barGradientStart = i;
        return this;
    }

    public PriceRangeSeekbar setBarGradientEnd(int i) {
        this.barGradientEnd = i;
        return this;
    }

    public PriceRangeSeekbar setBarHighlightColorMode(int i) {
        this.barHighlightColorMode = i;
        return this;
    }

    public PriceRangeSeekbar setBarHighlightColor(int i) {
        this.barHighlightColor = i;
        return this;
    }

    public PriceRangeSeekbar setBarHighlightGradientStart(int i) {
        this.barHighlightGradientStart = i;
        return this;
    }

    public PriceRangeSeekbar setBarHighlightGradientEnd(int i) {
        this.barHighlightGradientEnd = i;
        return this;
    }

    public PriceRangeSeekbar setLeftThumbColor(int i) {
        this.leftThumbColorNormal = i;
        return this;
    }

    public PriceRangeSeekbar setLeftThumbHighlightColor(int i) {
        this.leftThumbColorPressed = i;
        return this;
    }

    public PriceRangeSeekbar setLeftThumbDrawable(int i) {
        setLeftThumbDrawable(ContextCompat.getDrawable(getContext(), i));
        return this;
    }

    public PriceRangeSeekbar setLeftThumbDrawable(Drawable drawable) {
        setLeftThumbBitmap(getBitmap(drawable));
        return this;
    }

    public PriceRangeSeekbar setLeftThumbBitmap(Bitmap bitmap) {
        this.leftThumb = bitmap;
        return this;
    }

    public PriceRangeSeekbar setLeftThumbHighlightDrawable(int i) {
        setLeftThumbHighlightDrawable(ContextCompat.getDrawable(getContext(), i));
        return this;
    }

    public PriceRangeSeekbar setLeftThumbHighlightDrawable(Drawable drawable) {
        setLeftThumbHighlightBitmap(getBitmap(drawable));
        return this;
    }

    public PriceRangeSeekbar setLeftThumbHighlightBitmap(Bitmap bitmap) {
        this.leftThumbPressed = bitmap;
        return this;
    }

    public PriceRangeSeekbar setRightThumbColor(int i) {
        this.rightThumbColorNormal = i;
        return this;
    }

    public PriceRangeSeekbar setRightThumbHighlightColor(int i) {
        this.rightThumbColorPressed = i;
        return this;
    }

    public PriceRangeSeekbar setRightThumbDrawable(int i) {
        setRightThumbDrawable(ContextCompat.getDrawable(getContext(), i));
        return this;
    }

    public PriceRangeSeekbar setRightThumbDrawable(Drawable drawable) {
        setRightThumbBitmap(getBitmap(drawable));
        return this;
    }

    public PriceRangeSeekbar setRightThumbBitmap(Bitmap bitmap) {
        this.rightThumb = bitmap;
        return this;
    }

    public PriceRangeSeekbar setRightThumbHighlightDrawable(int i) {
        setRightThumbHighlightDrawable(ContextCompat.getDrawable(getContext(), i));
        return this;
    }

    public PriceRangeSeekbar setRightThumbHighlightDrawable(Drawable drawable) {
        setRightThumbHighlightBitmap(getBitmap(drawable));
        return this;
    }

    public PriceRangeSeekbar setRightThumbHighlightBitmap(Bitmap bitmap) {
        this.rightThumbPressed = bitmap;
        return this;
    }

    public PriceRangeSeekbar setDataType(int i) {
        this.dataType = i;
        return this;
    }

    public void setOnRangeSeekbarChangeListener(OnRangeSeekbarChangeListener onRangeSeekbarChangeListener) {
        this.onRangeSeekbarChangeListener = onRangeSeekbarChangeListener;
        if (this.onRangeSeekbarChangeListener != null) {
            this.onRangeSeekbarChangeListener.valueChanged(getSelectedMinValue(), getSelectedMaxValue());
        }
    }

    public void setOnRangeSeekbarFinalValueListener(OnRangeSeekbarFinalValueListener onRangeSeekbarFinalValueListener) {
        this.onRangeSeekbarFinalValueListener = onRangeSeekbarFinalValueListener;
    }

    public Number getSelectedMinValue() {
        double d = this.normalizedMinValue;
        if (this.steps > 0.0f && this.steps <= Math.abs(this.absoluteMaxValue) / 2.0f) {
            float f = (this.steps / (this.absoluteMaxValue - this.absoluteMinValue)) * 100.0f;
            double d2 = (double) f;
            double d3 = d % d2;
            d = d3 > ((double) (f / 2.0f)) ? (d - d3) + d2 : d - d3;
        } else if (this.steps != -1.0f) {
            StringBuilder stringBuilder = new StringBuilder("steps out of range ");
            stringBuilder.append(this.steps);
            throw new IllegalStateException(stringBuilder.toString());
        }
        return formatValue(Double.valueOf(normalizedToValue(d)));
    }

    public Number getSelectedMaxValue() {
        double d = this.normalizedMaxValue;
        if (this.steps > 0.0f && this.steps <= Math.abs(this.absoluteMaxValue) / 2.0f) {
            float f = (this.steps / (this.absoluteMaxValue - this.absoluteMinValue)) * 100.0f;
            double d2 = (double) f;
            double d3 = d % d2;
            d = d3 > ((double) (f / 2.0f)) ? (d - d3) + d2 : d - d3;
        } else if (this.steps != -1.0f) {
            StringBuilder stringBuilder = new StringBuilder("steps out of range ");
            stringBuilder.append(this.steps);
            throw new IllegalStateException(stringBuilder.toString());
        }
        return formatValue(Double.valueOf(normalizedToValue(d)));
    }

    public void apply() {
        this.normalizedMinValue = 0.0d;
        this.normalizedMaxValue = 100.0d;
        this.gap = Math.max(0.0f, Math.min(this.gap, this.absoluteMaxValue - this.absoluteMinValue));
        this.gap = (this.gap / (this.absoluteMaxValue - this.absoluteMinValue)) * 100.0f;
        if (this.fixGap != -1.0f) {
            this.fixGap = Math.min(this.fixGap, this.absoluteMaxValue);
            this.fixGap = (this.fixGap / (this.absoluteMaxValue - this.absoluteMinValue)) * 100.0f;
            addFixGap(true);
        }
        this.thumbWidth = getThumbWidth();
        this.thumbHeight = getThumbHeight();
        this.barHeight = getBarHeight();
        this.barPadding = this.thumbWidth * 0.5f;
        if (this.minStartValue <= this.absoluteMinValue) {
            this.minStartValue = 0.0f;
            setNormalizedMinValue((double) this.minStartValue);
        } else {
            if (this.minStartValue >= this.absoluteMaxValue) {
                this.minStartValue = this.absoluteMaxValue;
            }
            setMinStartValue();
        }
        if (this.maxStartValue >= this.absoluteMinStartValue) {
            if (this.maxStartValue > this.absoluteMinValue) {
                if (this.maxStartValue >= this.absoluteMaxValue) {
                    this.maxStartValue = this.absoluteMaxValue;
                }
                setMaxStartValue();
                invalidate();
                if (this.onRangeSeekbarChangeListener != null) {
                    this.onRangeSeekbarChangeListener.valueChanged(getSelectedMinValue(), getSelectedMaxValue());
                }
            }
        }
        this.maxStartValue = 0.0f;
        setNormalizedMaxValue((double) this.maxStartValue);
        invalidate();
        if (this.onRangeSeekbarChangeListener != null) {
            this.onRangeSeekbarChangeListener.valueChanged(getSelectedMinValue(), getSelectedMaxValue());
        }
    }

    protected Thumb getPressedThumb() {
        return this.pressedThumb;
    }

    protected float getThumbWidth() {
        return this.leftThumb != null ? (float) this.leftThumb.getWidth() : getThumbDiameter();
    }

    protected float getThumbHeight() {
        return this.leftThumb != null ? (float) this.leftThumb.getHeight() : getThumbDiameter();
    }

    protected float getThumbDiameter() {
        return this.thumbDiameter > 0.0f ? this.thumbDiameter : getResources().getDimension(R.dimen.thumb_width);
    }

    protected float getBarHeight() {
        return (this.thumbHeight * 0.5f) * 0.3f;
    }

    protected float getBarPadding() {
        return this.thumbWidth * 0.5f;
    }

    protected Bitmap getBitmap(Drawable drawable) {
        return drawable != null ? ((BitmapDrawable) drawable).getBitmap() : null;
    }

    protected float getCornerRadius(TypedArray typedArray) {
        return typedArray.getFloat(8, 0.0f);
    }

    protected float getMinValue(TypedArray typedArray) {
        return typedArray.getFloat(19, 0.0f);
    }

    protected float getMaxValue(TypedArray typedArray) {
        return typedArray.getFloat(17, 100.0f);
    }

    protected float getMinStartValue(TypedArray typedArray) {
        return typedArray.getFloat(18, this.minValue);
    }

    protected float getMaxStartValue(TypedArray typedArray) {
        return typedArray.getFloat(16, this.maxValue);
    }

    protected float getSteps(TypedArray typedArray) {
        return typedArray.getFloat(25, -1.0f);
    }

    protected float getGap(TypedArray typedArray) {
        return typedArray.getFloat(11, 0.0f);
    }

    protected float getFixedGap(TypedArray typedArray) {
        return typedArray.getFloat(10, -1.0f);
    }

    protected int getBarColorMode(TypedArray typedArray) {
        return typedArray.getInt(1, 0);
    }

    protected int getBarColor(TypedArray typedArray) {
        return typedArray.getColor(0, getResources().getColor(R.color.textview_background_color));
    }

    protected int getBarGradientStart(TypedArray typedArray) {
        return typedArray.getColor(3, getResources().getColor(R.color.textview_background_color));
    }

    protected int getBarGradientEnd(TypedArray typedArray) {
        return typedArray.getColor(2, getResources().getColor(R.color.textview_background_color));
    }

    protected int getBarHighlightColorMode(TypedArray typedArray) {
        return typedArray.getInt(5, 0);
    }

    protected int getBarHighlightColor(TypedArray typedArray) {
        return typedArray.getColor(4, getResources().getColor(R.color.textview_background_color));
    }

    protected int getBarHighlightGradientStart(TypedArray typedArray) {
        return typedArray.getColor(7, getResources().getColor(R.color.textview_background_color));
    }

    protected int getBarHighlightGradientEnd(TypedArray typedArray) {
        return typedArray.getColor(6, getResources().getColor(R.color.textview_background_color));
    }

    protected int getLeftThumbColor(TypedArray typedArray) {
        return typedArray.getColor(12, ViewCompat.MEASURED_STATE_MASK);
    }

    protected int getRightThumbColor(TypedArray typedArray) {
        return typedArray.getColor(21, ViewCompat.MEASURED_STATE_MASK);
    }

    protected int getLeftThumbColorPressed(TypedArray typedArray) {
        return typedArray.getColor(13, getResources().getColor(R.color.textview_background_color));
    }

    protected int getRightThumbColorPressed(TypedArray typedArray) {
        return typedArray.getColor(22, getResources().getColor(R.color.textview_background_color));
    }

    protected Drawable getLeftDrawable(TypedArray typedArray) {
        return typedArray.getDrawable(14);
    }

    protected Drawable getRightDrawable(TypedArray typedArray) {
        return typedArray.getDrawable(23);
    }

    protected Drawable getLeftDrawablePressed(TypedArray typedArray) {
        return typedArray.getDrawable(15);
    }

    protected Drawable getRightDrawablePressed(TypedArray typedArray) {
        return typedArray.getDrawable(24);
    }

    protected int getDataType(TypedArray typedArray) {
        return typedArray.getInt(9, 2);
    }

    protected float getDiameter(TypedArray typedArray) {
        return (float) typedArray.getDimensionPixelSize(26, getResources().getDimensionPixelSize(R.dimen.thumb_height));
    }

    protected RectF getLeftThumbRect() {
        return this.rectLeftThumb;
    }

    protected RectF getRightThumbRect() {
        return this.rectRightThumb;
    }

    protected void setupBar(Canvas canvas, Paint paint, RectF rectF) {
        rectF.left = this.barPadding;
        rectF.top = (((float) getHeight()) - this.barHeight) * 0.5f;
        rectF.right = ((float) getWidth()) - this.barPadding;
        rectF.bottom = (((float) getHeight()) + this.barHeight) * 0.5f;
        paint.setStyle(Style.FILL);
        paint.setAntiAlias(true);
        if (this.barColorMode == 0) {
            paint.setColor(this.barColor);
            drawBar(canvas, paint, rectF);
            return;
        }
        paint.setShader(new LinearGradient(rectF.left, rectF.bottom, rectF.right, rectF.top, this.barGradientStart, this.barGradientEnd, TileMode.MIRROR));
        drawBar(canvas, paint, rectF);
        paint.setShader(null);
    }

    protected void drawBar(Canvas canvas, Paint paint, RectF rectF) {
        canvas.drawRoundRect(rectF, this.cornerRadius, this.cornerRadius, paint);
    }

    protected void setupHighlightBar(Canvas canvas, Paint paint, RectF rectF) {
        rectF.left = normalizedToScreen(this.normalizedMinValue) + (getThumbWidth() / 2.0f);
        rectF.right = normalizedToScreen(this.normalizedMaxValue) + (getThumbWidth() / 2.0f);
        paint.setStyle(Style.FILL);
        paint.setAntiAlias(true);
        if (this.barHighlightColorMode == 0) {
            paint.setColor(this.barHighlightColor);
            drawHighlightBar(canvas, paint, rectF);
            return;
        }
        paint.setShader(new LinearGradient(rectF.left, rectF.bottom, rectF.right, rectF.top, this.barHighlightGradientStart, this.barHighlightGradientEnd, TileMode.MIRROR));
        drawHighlightBar(canvas, paint, rectF);
        paint.setShader(null);
    }

    protected void drawHighlightBar(Canvas canvas, Paint paint, RectF rectF) {
        canvas.drawRoundRect(rectF, this.cornerRadius, this.cornerRadius, paint);
    }

    protected void setupLeftThumb(Canvas canvas, Paint paint, RectF rectF) {
        this.leftThumbColor = Thumb.MIN.equals(this.pressedThumb) != null ? this.leftThumbColorPressed : this.leftThumbColorNormal;
        paint.setColor(this.leftThumbColor);
        this.rectLeftThumb.left = normalizedToScreen(this.normalizedMinValue);
        this.rectLeftThumb.right = Math.min((this.rectLeftThumb.left + (getThumbWidth() / 2.0f)) + this.barPadding, (float) getWidth());
        this.rectLeftThumb.top = 0.0f;
        this.rectLeftThumb.bottom = this.thumbHeight;
        if (this.leftThumb != null) {
            drawLeftThumbWithImage(canvas, paint, this.rectLeftThumb, Thumb.MIN.equals(this.pressedThumb) != null ? this.leftThumbPressed : this.leftThumb);
        } else {
            drawLeftThumbWithColor(canvas, paint, this.rectLeftThumb);
        }
    }

    protected void drawLeftThumbWithColor(Canvas canvas, Paint paint, RectF rectF) {
        canvas.drawOval(rectF, paint);
    }

    protected void drawLeftThumbWithImage(Canvas canvas, Paint paint, RectF rectF, Bitmap bitmap) {
        canvas.drawBitmap(bitmap, rectF.left, rectF.top, paint);
    }

    protected void setupRightThumb(Canvas canvas, Paint paint, RectF rectF) {
        this.rightThumbColor = Thumb.MAX.equals(this.pressedThumb) != null ? this.rightThumbColorPressed : this.rightThumbColorNormal;
        paint.setColor(this.rightThumbColor);
        this.rectRightThumb.left = normalizedToScreen(this.normalizedMaxValue);
        this.rectRightThumb.right = Math.min((this.rectRightThumb.left + (getThumbWidth() / 2.0f)) + this.barPadding, (float) getWidth());
        this.rectRightThumb.top = 0.0f;
        this.rectRightThumb.bottom = this.thumbHeight;
        if (this.rightThumb != null) {
            drawRightThumbWithImage(canvas, paint, this.rectRightThumb, Thumb.MAX.equals(this.pressedThumb) != null ? this.rightThumbPressed : this.rightThumb);
        } else {
            drawRightThumbWithColor(canvas, paint, this.rectRightThumb);
        }
    }

    protected void drawRightThumbWithColor(Canvas canvas, Paint paint, RectF rectF) {
        canvas.drawOval(rectF, paint);
    }

    protected void drawRightThumbWithImage(Canvas canvas, Paint paint, RectF rectF, Bitmap bitmap) {
        canvas.drawBitmap(bitmap, rectF.left, rectF.top, paint);
    }

    protected void trackTouchEvent(android.view.MotionEvent r3) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
*/
        /*
        r2 = this;
        r0 = r2.mActivePointerId;
        r0 = r3.findPointerIndex(r0);
        r3 = r3.getX(r0);	 Catch:{ Exception -> 0x002e }
        r0 = com.jelsat.widgets.PriceRangeSeekbar.Thumb.MIN;	 Catch:{ Exception -> 0x002e }
        r1 = r2.pressedThumb;	 Catch:{ Exception -> 0x002e }
        r0 = r0.equals(r1);	 Catch:{ Exception -> 0x002e }
        if (r0 == 0) goto L_0x001c;	 Catch:{ Exception -> 0x002e }
    L_0x0014:
        r0 = r2.screenToNormalized(r3);	 Catch:{ Exception -> 0x002e }
        r2.setNormalizedMinValue(r0);	 Catch:{ Exception -> 0x002e }
        return;	 Catch:{ Exception -> 0x002e }
    L_0x001c:
        r0 = com.jelsat.widgets.PriceRangeSeekbar.Thumb.MAX;	 Catch:{ Exception -> 0x002e }
        r1 = r2.pressedThumb;	 Catch:{ Exception -> 0x002e }
        r0 = r0.equals(r1);	 Catch:{ Exception -> 0x002e }
        if (r0 == 0) goto L_0x002d;	 Catch:{ Exception -> 0x002e }
    L_0x0026:
        r0 = r2.screenToNormalized(r3);	 Catch:{ Exception -> 0x002e }
        r2.setNormalizedMaxValue(r0);	 Catch:{ Exception -> 0x002e }
    L_0x002d:
        return;
    L_0x002e:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jelsat.widgets.PriceRangeSeekbar.trackTouchEvent(android.view.MotionEvent):void");
    }

    protected int getMeasureSpecWith(int i) {
        return MeasureSpec.getMode(i) != 0 ? MeasureSpec.getSize(i) : 200;
    }

    protected int getMeasureSpecHeight(int i) {
        int round = Math.round(this.thumbHeight);
        return MeasureSpec.getMode(i) != 0 ? Math.min(round, MeasureSpec.getSize(i)) : round;
    }

    protected final void log(Object obj) {
        Log.d("CRS=>", String.valueOf(obj));
    }

    private void setMinStartValue() {
        if (this.minStartValue > this.minValue && this.minStartValue <= this.maxValue) {
            this.minStartValue = Math.min(this.minStartValue, this.absoluteMaxValue);
            this.minStartValue -= this.absoluteMinValue;
            this.minStartValue = (this.minStartValue / (this.absoluteMaxValue - this.absoluteMinValue)) * 100.0f;
            setNormalizedMinValue((double) this.minStartValue);
        }
    }

    private void setMaxStartValue() {
        if (this.maxStartValue <= this.absoluteMaxValue && this.maxStartValue > this.absoluteMinValue && this.maxStartValue >= this.absoluteMinStartValue) {
            this.maxStartValue = Math.max(this.absoluteMaxStartValue, this.absoluteMinValue);
            this.maxStartValue -= this.absoluteMinValue;
            this.maxStartValue = (this.maxStartValue / (this.absoluteMaxValue - this.absoluteMinValue)) * 100.0f;
            setNormalizedMaxValue((double) this.maxStartValue);
        }
    }

    private Thumb evalPressedThumb(float f) {
        boolean isInThumbRange = isInThumbRange(f, this.normalizedMinValue);
        boolean isInThumbRange2 = isInThumbRange(f, this.normalizedMaxValue);
        if (isInThumbRange && isInThumbRange2) {
            return f / ((float) getWidth()) > 0.5f ? Thumb.MIN : Thumb.MAX;
        } else {
            if (isInThumbRange) {
                return Thumb.MIN;
            }
            return isInThumbRange2 ? Thumb.MAX : 0.0f;
        }
    }

    private boolean isInThumbRange(float f, double d) {
        d = normalizedToScreen(d);
        float thumbWidth = d - (getThumbWidth() / 2.0f);
        float thumbWidth2 = (getThumbWidth() / 2.0f) + d;
        float thumbWidth3 = f - (getThumbWidth() / 2.0f);
        if (d <= ((float) getWidth()) - this.thumbWidth) {
            f = thumbWidth3;
        }
        return f >= thumbWidth && f <= thumbWidth2;
    }

    private void onStartTrackingTouch() {
        this.mIsDragging = true;
    }

    private void onStopTrackingTouch() {
        this.mIsDragging = false;
    }

    private float normalizedToScreen(double d) {
        return (((float) d) / 100.0f) * (((float) getWidth()) - (this.barPadding * 2.0f));
    }

    private double screenToNormalized(float f) {
        double width = (double) getWidth();
        if (width <= ((double) (this.barPadding * 2.0f))) {
            return 0.0d;
        }
        width -= (double) (this.barPadding * 2.0f);
        return Math.min(100.0d, Math.max(0.0d, ((((double) f) / width) * 100.0d) - ((((double) this.barPadding) / width) * 100.0d)));
    }

    private void setNormalizedMinValue(double d) {
        this.normalizedMinValue = Math.max(0.0d, Math.min(100.0d, Math.min(d, this.normalizedMaxValue)));
        if (this.fixGap == -1.0f || this.fixGap <= 0.0f) {
            addMinGap();
        } else {
            addFixGap(Double.MIN_VALUE);
        }
        invalidate();
    }

    private void setNormalizedMaxValue(double d) {
        this.normalizedMaxValue = Math.max(0.0d, Math.min(100.0d, Math.max(d, this.normalizedMinValue)));
        if (this.fixGap == -1.0f || this.fixGap <= 0.0f) {
            addMaxGap();
        } else {
            addFixGap(0.0d);
        }
        invalidate();
    }

    private void addFixGap(boolean z) {
        if (z) {
            this.normalizedMaxValue = this.normalizedMinValue + ((double) this.fixGap);
            if (this.normalizedMaxValue >= 100.0d) {
                this.normalizedMaxValue = 100.0d;
                this.normalizedMinValue = this.normalizedMaxValue - ((double) this.fixGap);
                return;
            }
        }
        this.normalizedMinValue = this.normalizedMaxValue - ((double) this.fixGap);
        if (this.normalizedMinValue <= 0.0d) {
            this.normalizedMinValue = 0.0d;
            this.normalizedMaxValue = this.normalizedMinValue + ((double) this.fixGap);
        }
    }

    private void addMinGap() {
        if (this.normalizedMinValue + ((double) this.gap) > this.normalizedMaxValue) {
            double d = this.normalizedMinValue + ((double) this.gap);
            this.normalizedMaxValue = d;
            this.normalizedMaxValue = Math.max(0.0d, Math.min(100.0d, Math.max(d, this.normalizedMinValue)));
            if (this.normalizedMinValue >= this.normalizedMaxValue - ((double) this.gap)) {
                this.normalizedMinValue = this.normalizedMaxValue - ((double) this.gap);
            }
        }
    }

    private void addMaxGap() {
        if (this.normalizedMaxValue - ((double) this.gap) < this.normalizedMinValue) {
            double d = this.normalizedMaxValue - ((double) this.gap);
            this.normalizedMinValue = d;
            this.normalizedMinValue = Math.max(0.0d, Math.min(100.0d, Math.min(d, this.normalizedMaxValue)));
            if (this.normalizedMaxValue <= this.normalizedMinValue + ((double) this.gap)) {
                this.normalizedMaxValue = this.normalizedMinValue + ((double) this.gap);
            }
        }
    }

    private double normalizedToValue(double d) {
        return ((d / 100.0d) * ((double) (this.maxValue - this.minValue))) + ((double) this.minValue);
    }

    private void attemptClaimDrag() {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
    }

    private <T extends Number> Number formatValue(T t) throws IllegalArgumentException {
        Double d = (Double) t;
        if (this.dataType == 0) {
            return Long.valueOf(d.longValue());
        }
        if (this.dataType == 1) {
            return d;
        }
        if (this.dataType == 2) {
            return Long.valueOf(Math.round(d.doubleValue()));
        }
        if (this.dataType == 3) {
            return Float.valueOf(d.floatValue());
        }
        if (this.dataType == 4) {
            return Short.valueOf(d.shortValue());
        }
        if (this.dataType == 5) {
            return Byte.valueOf(d.byteValue());
        }
        StringBuilder stringBuilder = new StringBuilder("Number class '");
        stringBuilder.append(t.getClass().getName());
        stringBuilder.append("' is not supported");
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isInEditMode()) {
            setupBar(canvas, this._paint, this._rect);
            setupHighlightBar(canvas, this._paint, this._rect);
            setupLeftThumb(canvas, this._paint, this._rect);
            setupRightThumb(canvas, this._paint, this._rect);
        }
    }

    protected synchronized void onMeasure(int i, int i2) {
        setMeasuredDimension(getMeasureSpecWith(i), getMeasureSpecHeight(i2));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean onTouchEvent(android.view.MotionEvent r4) {
        /*
        r3 = this;
        monitor-enter(r3);
        r0 = r3.isEnabled();	 Catch:{ all -> 0x00fe }
        r1 = 0;
        if (r0 != 0) goto L_0x000a;
    L_0x0008:
        monitor-exit(r3);
        return r1;
    L_0x000a:
        r0 = r4.getAction();	 Catch:{ all -> 0x00fe }
        r0 = r0 & 255;
        r2 = 1;
        switch(r0) {
            case 0: goto L_0x00b5;
            case 1: goto L_0x0066;
            case 2: goto L_0x0039;
            case 3: goto L_0x001b;
            case 4: goto L_0x0014;
            case 5: goto L_0x00fc;
            case 6: goto L_0x0016;
            default: goto L_0x0014;
        };	 Catch:{ all -> 0x00fe }
    L_0x0014:
        goto L_0x00fc;
    L_0x0016:
        r3.invalidate();	 Catch:{ all -> 0x00fe }
        goto L_0x00fc;
    L_0x001b:
        r0 = r3.mIsDragging;	 Catch:{ all -> 0x00fe }
        if (r0 == 0) goto L_0x0034;
    L_0x001f:
        r3.onStopTrackingTouch();	 Catch:{ all -> 0x00fe }
        r3.setPressed(r1);	 Catch:{ all -> 0x00fe }
        r0 = r3.pointerIndex;	 Catch:{ all -> 0x00fe }
        r0 = r4.getX(r0);	 Catch:{ all -> 0x00fe }
        r1 = r3.pointerIndex;	 Catch:{ all -> 0x00fe }
        r4 = r4.getY(r1);	 Catch:{ all -> 0x00fe }
        r3.touchUp(r0, r4);	 Catch:{ all -> 0x00fe }
    L_0x0034:
        r3.invalidate();	 Catch:{ all -> 0x00fe }
        goto L_0x00fc;
    L_0x0039:
        r0 = r3.pressedThumb;	 Catch:{ all -> 0x00fe }
        if (r0 == 0) goto L_0x00fc;
    L_0x003d:
        r0 = r3.mIsDragging;	 Catch:{ all -> 0x00fe }
        if (r0 == 0) goto L_0x0053;
    L_0x0041:
        r0 = r3.pointerIndex;	 Catch:{ all -> 0x00fe }
        r0 = r4.getX(r0);	 Catch:{ all -> 0x00fe }
        r1 = r3.pointerIndex;	 Catch:{ all -> 0x00fe }
        r1 = r4.getY(r1);	 Catch:{ all -> 0x00fe }
        r3.touchMove(r0, r1);	 Catch:{ all -> 0x00fe }
        r3.trackTouchEvent(r4);	 Catch:{ all -> 0x00fe }
    L_0x0053:
        r4 = r3.onRangeSeekbarChangeListener;	 Catch:{ all -> 0x00fe }
        if (r4 == 0) goto L_0x00fc;
    L_0x0057:
        r4 = r3.onRangeSeekbarChangeListener;	 Catch:{ all -> 0x00fe }
        r0 = r3.getSelectedMinValue();	 Catch:{ all -> 0x00fe }
        r1 = r3.getSelectedMaxValue();	 Catch:{ all -> 0x00fe }
        r4.valueChanged(r0, r1);	 Catch:{ all -> 0x00fe }
        goto L_0x00fc;
    L_0x0066:
        r0 = r3.mIsDragging;	 Catch:{ all -> 0x00fe }
        if (r0 == 0) goto L_0x0094;
    L_0x006a:
        r3.trackTouchEvent(r4);	 Catch:{ all -> 0x00fe }
        r3.onStopTrackingTouch();	 Catch:{ all -> 0x00fe }
        r3.setPressed(r1);	 Catch:{ all -> 0x00fe }
        r0 = r3.pointerIndex;	 Catch:{ all -> 0x00fe }
        r0 = r4.getX(r0);	 Catch:{ all -> 0x00fe }
        r1 = r3.pointerIndex;	 Catch:{ all -> 0x00fe }
        r4 = r4.getY(r1);	 Catch:{ all -> 0x00fe }
        r3.touchUp(r0, r4);	 Catch:{ all -> 0x00fe }
        r4 = r3.onRangeSeekbarFinalValueListener;	 Catch:{ all -> 0x00fe }
        if (r4 == 0) goto L_0x009d;
    L_0x0086:
        r4 = r3.onRangeSeekbarFinalValueListener;	 Catch:{ all -> 0x00fe }
        r0 = r3.getSelectedMinValue();	 Catch:{ all -> 0x00fe }
        r1 = r3.getSelectedMaxValue();	 Catch:{ all -> 0x00fe }
        r4.finalValue(r0, r1);	 Catch:{ all -> 0x00fe }
        goto L_0x009d;
    L_0x0094:
        r3.onStartTrackingTouch();	 Catch:{ all -> 0x00fe }
        r3.trackTouchEvent(r4);	 Catch:{ all -> 0x00fe }
        r3.onStopTrackingTouch();	 Catch:{ all -> 0x00fe }
    L_0x009d:
        r4 = 0;
        r3.pressedThumb = r4;	 Catch:{ all -> 0x00fe }
        r3.invalidate();	 Catch:{ all -> 0x00fe }
        r4 = r3.onRangeSeekbarChangeListener;	 Catch:{ all -> 0x00fe }
        if (r4 == 0) goto L_0x00fc;
    L_0x00a7:
        r4 = r3.onRangeSeekbarChangeListener;	 Catch:{ all -> 0x00fe }
        r0 = r3.getSelectedMinValue();	 Catch:{ all -> 0x00fe }
        r1 = r3.getSelectedMaxValue();	 Catch:{ all -> 0x00fe }
        r4.valueChanged(r0, r1);	 Catch:{ all -> 0x00fe }
        goto L_0x00fc;
    L_0x00b5:
        r0 = r4.getPointerCount();	 Catch:{ all -> 0x00fe }
        r0 = r0 - r2;
        r0 = r4.getPointerId(r0);	 Catch:{ all -> 0x00fe }
        r3.mActivePointerId = r0;	 Catch:{ all -> 0x00fe }
        r0 = r3.mActivePointerId;	 Catch:{ all -> 0x00fe }
        r0 = r4.findPointerIndex(r0);	 Catch:{ all -> 0x00fe }
        r3.pointerIndex = r0;	 Catch:{ all -> 0x00fe }
        r0 = r3.pointerIndex;	 Catch:{ all -> 0x00fe }
        r0 = r4.getX(r0);	 Catch:{ all -> 0x00fe }
        r0 = r3.evalPressedThumb(r0);	 Catch:{ all -> 0x00fe }
        r3.pressedThumb = r0;	 Catch:{ all -> 0x00fe }
        r0 = r3.pressedThumb;	 Catch:{ all -> 0x00fe }
        if (r0 != 0) goto L_0x00de;
    L_0x00d8:
        r4 = super.onTouchEvent(r4);	 Catch:{ all -> 0x00fe }
        monitor-exit(r3);
        return r4;
    L_0x00de:
        r0 = r3.pointerIndex;	 Catch:{ all -> 0x00fe }
        r0 = r4.getX(r0);	 Catch:{ all -> 0x00fe }
        r1 = r3.pointerIndex;	 Catch:{ all -> 0x00fe }
        r1 = r4.getY(r1);	 Catch:{ all -> 0x00fe }
        r3.touchDown(r0, r1);	 Catch:{ all -> 0x00fe }
        r3.setPressed(r2);	 Catch:{ all -> 0x00fe }
        r3.invalidate();	 Catch:{ all -> 0x00fe }
        r3.onStartTrackingTouch();	 Catch:{ all -> 0x00fe }
        r3.trackTouchEvent(r4);	 Catch:{ all -> 0x00fe }
        r3.attemptClaimDrag();	 Catch:{ all -> 0x00fe }
    L_0x00fc:
        monitor-exit(r3);
        return r2;
    L_0x00fe:
        r4 = move-exception;
        monitor-exit(r3);
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jelsat.widgets.PriceRangeSeekbar.onTouchEvent(android.view.MotionEvent):boolean");
    }
}
