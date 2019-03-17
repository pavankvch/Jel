package com.jelsat.widgets;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.text.TextUtilsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.animation.OvershootInterpolator;
import android.view.inputmethod.InputMethodManager;
import com.jelsat.R;
import java.util.Locale;

public class PinEntryEditText extends AppCompatEditText {
    private static final String XML_NAMESPACE_ANDROID = "http://schemas.android.com/apk/res/android";
    protected boolean mAnimate = false;
    protected int mAnimatedType = 0;
    protected float[] mCharBottom;
    protected Paint mCharPaint;
    protected float mCharSize;
    protected OnClickListener mClickListener;
    protected ColorStateList mColorStates;
    protected int[] mColors;
    protected boolean mHasError = false;
    protected boolean mIsDigitSquare = false;
    protected Paint mLastCharPaint;
    protected RectF[] mLineCoords;
    protected float mLineStroke = 1.0f;
    protected float mLineStrokeSelected = 2.0f;
    protected Paint mLinesPaint;
    protected String mMask = null;
    protected StringBuilder mMaskChars = null;
    protected int mMaxLength = 4;
    protected float mNumChars = 4.0f;
    protected OnPinEnteredListener mOnPinEnteredListener = null;
    protected ColorStateList mOriginalTextColors;
    protected Drawable mPinBackground;
    protected String mSingleCharHint = null;
    protected Paint mSingleCharPaint;
    protected float mSpace = 24.0f;
    protected int[][] mStates;
    protected float mTextBottomPadding = 8.0f;
    protected Rect mTextHeight = new Rect();

    public interface OnPinEnteredListener {
        void onPinEntered(CharSequence charSequence);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public PinEntryEditText(android.content.Context r6) {
        /*
        r5 = this;
        r5.<init>(r6);
        r6 = 0;
        r5.mMask = r6;
        r5.mMaskChars = r6;
        r5.mSingleCharHint = r6;
        r0 = 0;
        r5.mAnimatedType = r0;
        r1 = 1103101952; // 0x41c00000 float:24.0 double:5.450047783E-315;
        r5.mSpace = r1;
        r1 = 1082130432; // 0x40800000 float:4.0 double:5.34643471E-315;
        r5.mNumChars = r1;
        r1 = 1090519040; // 0x41000000 float:8.0 double:5.38787994E-315;
        r5.mTextBottomPadding = r1;
        r1 = 4;
        r5.mMaxLength = r1;
        r2 = new android.graphics.Rect;
        r2.<init>();
        r5.mTextHeight = r2;
        r5.mIsDigitSquare = r0;
        r5.mOnPinEnteredListener = r6;
        r6 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r5.mLineStroke = r6;
        r6 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r5.mLineStrokeSelected = r6;
        r5.mAnimate = r0;
        r5.mHasError = r0;
        r6 = new int[r1][];
        r2 = 1;
        r3 = new int[r2];
        r4 = 16842913; // 0x10100a1 float:2.369401E-38 double:8.3215047E-317;
        r3[r0] = r4;
        r6[r0] = r3;
        r3 = new int[r2];
        r4 = 16842914; // 0x10100a2 float:2.3694012E-38 double:8.321505E-317;
        r3[r0] = r4;
        r6[r2] = r3;
        r3 = new int[r2];
        r4 = 16842908; // 0x101009c float:2.3693995E-38 double:8.321502E-317;
        r3[r0] = r4;
        r4 = 2;
        r6[r4] = r3;
        r2 = new int[r2];
        r3 = -16842908; // 0xfffffffffefeff64 float:-1.6947499E38 double:NaN;
        r2[r0] = r3;
        r0 = 3;
        r6[r0] = r2;
        r5.mStates = r6;
        r6 = new int[r1];
        r6 = {-16711936, -65536, -16777216, -7829368};
        r5.mColors = r6;
        r6 = new android.content.res.ColorStateList;
        r0 = r5.mStates;
        r1 = r5.mColors;
        r6.<init>(r0, r1);
        r5.mColorStates = r6;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jelsat.widgets.PinEntryEditText.<init>(android.content.Context):void");
    }

    public PinEntryEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        int[][] iArr = new int[4][];
        iArr[0] = new int[]{16842913};
        iArr[1] = new int[]{16842914};
        iArr[2] = new int[]{16842908};
        iArr[3] = new int[]{-16842908};
        this.mStates = iArr;
        this.mColors = new int[]{-16711936, SupportMenu.CATEGORY_MASK, ViewCompat.MEASURED_STATE_MASK, -7829368};
        this.mColorStates = new ColorStateList(this.mStates, this.mColors);
        init(context, attributeSet);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public PinEntryEditText(android.content.Context r6, android.util.AttributeSet r7, int r8) {
        /*
        r5 = this;
        r5.<init>(r6, r7, r8);
        r8 = 0;
        r5.mMask = r8;
        r5.mMaskChars = r8;
        r5.mSingleCharHint = r8;
        r0 = 0;
        r5.mAnimatedType = r0;
        r1 = 1103101952; // 0x41c00000 float:24.0 double:5.450047783E-315;
        r5.mSpace = r1;
        r1 = 1082130432; // 0x40800000 float:4.0 double:5.34643471E-315;
        r5.mNumChars = r1;
        r1 = 1090519040; // 0x41000000 float:8.0 double:5.38787994E-315;
        r5.mTextBottomPadding = r1;
        r1 = 4;
        r5.mMaxLength = r1;
        r2 = new android.graphics.Rect;
        r2.<init>();
        r5.mTextHeight = r2;
        r5.mIsDigitSquare = r0;
        r5.mOnPinEnteredListener = r8;
        r8 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r5.mLineStroke = r8;
        r8 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r5.mLineStrokeSelected = r8;
        r5.mAnimate = r0;
        r5.mHasError = r0;
        r8 = new int[r1][];
        r2 = 1;
        r3 = new int[r2];
        r4 = 16842913; // 0x10100a1 float:2.369401E-38 double:8.3215047E-317;
        r3[r0] = r4;
        r8[r0] = r3;
        r3 = new int[r2];
        r4 = 16842914; // 0x10100a2 float:2.3694012E-38 double:8.321505E-317;
        r3[r0] = r4;
        r8[r2] = r3;
        r3 = new int[r2];
        r4 = 16842908; // 0x101009c float:2.3693995E-38 double:8.321502E-317;
        r3[r0] = r4;
        r4 = 2;
        r8[r4] = r3;
        r2 = new int[r2];
        r3 = -16842908; // 0xfffffffffefeff64 float:-1.6947499E38 double:NaN;
        r2[r0] = r3;
        r0 = 3;
        r8[r0] = r2;
        r5.mStates = r8;
        r8 = new int[r1];
        r8 = {-16711936, -65536, -16777216, -7829368};
        r5.mColors = r8;
        r8 = new android.content.res.ColorStateList;
        r0 = r5.mStates;
        r1 = r5.mColors;
        r8.<init>(r0, r1);
        r5.mColorStates = r8;
        r5.init(r6, r7);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jelsat.widgets.PinEntryEditText.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void setMaxLength(int i) {
        this.mMaxLength = i;
        this.mNumChars = (float) i;
        setFilters(new InputFilter[]{new LengthFilter(i)});
        setText(0);
        invalidate();
    }

    private void init(Context context, AttributeSet attributeSet) {
        float f = context.getResources().getDisplayMetrics().density;
        this.mLineStroke *= f;
        this.mLineStrokeSelected *= f;
        this.mSpace *= f;
        this.mTextBottomPadding = f * this.mTextBottomPadding;
        boolean z = false;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PinEntryEditText, 0, 0);
        try {
            TypedValue typedValue = new TypedValue();
            obtainStyledAttributes.getValue(0, typedValue);
            this.mAnimatedType = typedValue.data;
            this.mMask = obtainStyledAttributes.getString(3);
            this.mSingleCharHint = obtainStyledAttributes.getString(8);
            this.mLineStroke = obtainStyledAttributes.getDimension(6, this.mLineStroke);
            this.mLineStrokeSelected = obtainStyledAttributes.getDimension(7, this.mLineStrokeSelected);
            this.mSpace = obtainStyledAttributes.getDimension(4, this.mSpace);
            this.mTextBottomPadding = obtainStyledAttributes.getDimension(9, this.mTextBottomPadding);
            this.mIsDigitSquare = obtainStyledAttributes.getBoolean(2, this.mIsDigitSquare);
            this.mPinBackground = obtainStyledAttributes.getDrawable(1);
            ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(5);
            if (colorStateList != null) {
                this.mColorStates = colorStateList;
            }
            obtainStyledAttributes.recycle();
            this.mCharPaint = new Paint(getPaint());
            this.mLastCharPaint = new Paint(getPaint());
            this.mSingleCharPaint = new Paint(getPaint());
            this.mLinesPaint = new Paint(getPaint());
            this.mLinesPaint.setStrokeWidth(this.mLineStroke);
            TypedValue typedValue2 = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.colorControlActivated, typedValue2, true);
            this.mColors[0] = typedValue2.data;
            int i = -7829368;
            this.mColors[1] = isInEditMode() ? -7829368 : ContextCompat.getColor(context, R.color.pin_normal);
            if (!isInEditMode()) {
                i = ContextCompat.getColor(context, R.color.pin_normal);
            }
            this.mColors[2] = i;
            setBackgroundResource(0);
            this.mMaxLength = attributeSet.getAttributeIntValue(XML_NAMESPACE_ANDROID, "maxLength", 4);
            this.mNumChars = (float) this.mMaxLength;
            super.setCustomSelectionActionModeCallback(new Callback() {
                public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                    return false;
                }

                public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                    return false;
                }

                public void onDestroyActionMode(ActionMode actionMode) {
                }

                public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                    return false;
                }
            });
            super.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    PinEntryEditText.this.setSelection(PinEntryEditText.this.getText().length());
                    if (PinEntryEditText.this.mClickListener != null) {
                        PinEntryEditText.this.mClickListener.onClick(view);
                    }
                }
            });
            super.setOnLongClickListener(new OnLongClickListener() {
                public boolean onLongClick(View view) {
                    PinEntryEditText.this.setSelection(PinEntryEditText.this.getText().length());
                    return true;
                }
            });
            if ((getInputType() & 128) == 128 && TextUtils.isEmpty(this.mMask) != null) {
                this.mMask = "●";
            } else if ((getInputType() & 16) == 16 && TextUtils.isEmpty(this.mMask) != null) {
                this.mMask = "●";
            }
            if (TextUtils.isEmpty(this.mMask) == null) {
                this.mMaskChars = getMaskChars();
            }
            getPaint().getTextBounds("|", 0, 1, this.mTextHeight);
            if (this.mAnimatedType >= null) {
                z = true;
            }
            this.mAnimate = z;
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mOriginalTextColors = getTextColors();
        if (this.mOriginalTextColors != 0) {
            this.mLastCharPaint.setColor(this.mOriginalTextColors.getDefaultColor());
            this.mCharPaint.setColor(this.mOriginalTextColors.getDefaultColor());
            this.mSingleCharPaint.setColor(getCurrentHintTextColor());
        }
        i = (getWidth() - ViewCompat.getPaddingEnd(this)) - ViewCompat.getPaddingStart(this);
        if (this.mSpace < 0) {
            this.mCharSize = ((float) i) / ((this.mNumChars * 1073741824) - 1065353216);
        } else {
            this.mCharSize = (((float) i) - (this.mSpace * (this.mNumChars - 1.0f))) / this.mNumChars;
        }
        this.mLineCoords = new RectF[((int) this.mNumChars)];
        this.mCharBottom = new float[((int) this.mNumChars)];
        i = getHeight() - getPaddingBottom();
        i4 = 0;
        int i5 = 1;
        if ((TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == 1 ? 1 : 0) != 0) {
            i5 = -1;
            i2 = (int) (((float) (getWidth() - ViewCompat.getPaddingStart(this))) - this.mCharSize);
        } else {
            i2 = ViewCompat.getPaddingStart(this);
        }
        while (((float) i4) < this.mNumChars) {
            i2 = (float) i2;
            float f = (float) i;
            this.mLineCoords[i4] = new RectF(i2, f, this.mCharSize + i2, f);
            if (this.mPinBackground != null) {
                if (this.mIsDigitSquare) {
                    this.mLineCoords[i4].top = (float) getPaddingTop();
                    this.mLineCoords[i4].right = this.mLineCoords[i4].height() + i2;
                } else {
                    RectF rectF = this.mLineCoords[i4];
                    rectF.top -= ((float) this.mTextHeight.height()) + (this.mTextBottomPadding * 2.0f);
                }
            }
            if (this.mSpace < 0.0f) {
                i2 = (int) (i2 + ((((float) i5) * this.mCharSize) * 2.0f));
            } else {
                i2 = (int) (i2 + (((float) i5) * (this.mCharSize + this.mSpace)));
            }
            this.mCharBottom[i4] = this.mLineCoords[i4].bottom - this.mTextBottomPadding;
            i4++;
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.mClickListener = onClickListener;
    }

    public void setCustomSelectionActionModeCallback(Callback callback) {
        throw new RuntimeException("setCustomSelectionActionModeCallback() not supported.");
    }

    protected void onDraw(Canvas canvas) {
        float f;
        CharSequence fullText = getFullText();
        int length = fullText.length();
        float[] fArr = new float[length];
        getPaint().getTextWidths(fullText, 0, length, fArr);
        if (this.mSingleCharHint != null) {
            float[] fArr2 = new float[this.mSingleCharHint.length()];
            getPaint().getTextWidths(this.mSingleCharHint, fArr2);
            float f2 = 0.0f;
            for (float f3 : fArr2) {
                f2 += f3;
            }
            f = f2;
        } else {
            f = 0.0f;
        }
        int i = 0;
        while (((float) i) < this.mNumChars) {
            boolean z = true;
            if (this.mPinBackground != null) {
                updateDrawableState(i < length, i == length);
                this.mPinBackground.setBounds((int) this.mLineCoords[i].left, (int) this.mLineCoords[i].top, (int) this.mLineCoords[i].right, (int) this.mLineCoords[i].bottom);
                this.mPinBackground.draw(canvas);
            }
            float f4 = this.mLineCoords[i].left + (this.mCharSize / 2.0f);
            if (length > i) {
                if (this.mAnimate) {
                    if (i == length - 1) {
                        canvas.drawText(fullText, i, i + 1, f4 - (fArr[i] / 2.0f), this.mCharBottom[i], this.mLastCharPaint);
                    }
                }
                canvas.drawText(fullText, i, i + 1, f4 - (fArr[i] / 2.0f), this.mCharBottom[i], this.mCharPaint);
            } else if (this.mSingleCharHint != null) {
                canvas.drawText(this.mSingleCharHint, f4 - (f / 2.0f), this.mCharBottom[i], this.mSingleCharPaint);
            }
            if (this.mPinBackground == null) {
                if (i > length) {
                    z = false;
                }
                updateColorForLines(z);
                canvas.drawLine(this.mLineCoords[i].left, this.mLineCoords[i].top, this.mLineCoords[i].right, this.mLineCoords[i].bottom, this.mLinesPaint);
            }
            i++;
        }
    }

    private CharSequence getFullText() {
        if (this.mMask == null) {
            return getText();
        }
        return getMaskChars();
    }

    private StringBuilder getMaskChars() {
        if (this.mMaskChars == null) {
            this.mMaskChars = new StringBuilder();
        }
        int length = getText().length();
        while (this.mMaskChars.length() != length) {
            if (this.mMaskChars.length() < length) {
                this.mMaskChars.append(this.mMask);
            } else {
                this.mMaskChars.deleteCharAt(this.mMaskChars.length() - 1);
            }
        }
        return this.mMaskChars;
    }

    private int getColorForState(int... iArr) {
        return this.mColorStates.getColorForState(iArr, -7829368);
    }

    protected void updateColorForLines(boolean z) {
        if (this.mHasError) {
            this.mLinesPaint.setColor(getColorForState(16842914));
            return;
        }
        if (isFocused()) {
            this.mLinesPaint.setStrokeWidth(this.mLineStrokeSelected);
            this.mLinesPaint.setColor(getColorForState(16842908));
            if (z) {
                this.mLinesPaint.setColor(getColorForState(16842913));
                return;
            }
        }
        this.mLinesPaint.setStrokeWidth(this.mLineStroke);
        this.mLinesPaint.setColor(getColorForState(-16842908));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void updateDrawableState(boolean r5, boolean r6) {
        /*
        r4 = this;
        r0 = r4.mHasError;
        r1 = 0;
        r2 = 1;
        if (r0 == 0) goto L_0x0013;
    L_0x0006:
        r5 = r4.mPinBackground;
        r6 = new int[r2];
        r0 = 16842914; // 0x10100a2 float:2.3694012E-38 double:8.321505E-317;
        r6[r1] = r0;
        r5.setState(r6);
        return;
    L_0x0013:
        r0 = r4.isFocused();
        if (r0 == 0) goto L_0x0040;
    L_0x0019:
        r0 = r4.mPinBackground;
        r2 = new int[r2];
        r3 = 16842908; // 0x101009c float:2.3693995E-38 double:8.321502E-317;
        r2[r1] = r3;
        r0.setState(r2);
        r0 = 2;
        if (r6 == 0) goto L_0x0033;
    L_0x0028:
        r5 = r4.mPinBackground;
        r6 = new int[r0];
        r6 = {16842908, 16842913};
        r5.setState(r6);
        return;
    L_0x0033:
        if (r5 == 0) goto L_0x004c;
    L_0x0035:
        r5 = r4.mPinBackground;
        r6 = new int[r0];
        r6 = {16842908, 16842912};
        r5.setState(r6);
        return;
    L_0x0040:
        r5 = r4.mPinBackground;
        r6 = new int[r2];
        r0 = -16842908; // 0xfffffffffefeff64 float:-1.6947499E38 double:NaN;
        r6[r1] = r0;
        r5.setState(r6);
    L_0x004c:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jelsat.widgets.PinEntryEditText.updateDrawableState(boolean, boolean):void");
    }

    public void setError(boolean z) {
        this.mHasError = z;
    }

    public boolean isError() {
        return this.mHasError;
    }

    public void focus() {
        requestFocus();
        ((InputMethodManager) getContext().getSystemService("input_method")).showSoftInput(this, 0);
    }

    protected void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        setError(false);
        if (this.mLineCoords != null) {
            if (this.mAnimate) {
                if (this.mAnimatedType == -1) {
                    invalidate();
                    return;
                }
                if (i3 > i2) {
                    if (this.mAnimatedType == 0) {
                        animatePopIn();
                        return;
                    }
                    animateBottomUp(charSequence, i);
                }
                return;
            }
        }
        if (this.mOnPinEnteredListener != 0 && charSequence.length() == this.mMaxLength) {
            this.mOnPinEnteredListener.onPinEntered(charSequence);
        }
    }

    private void animatePopIn() {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{1.0f, getPaint().getTextSize()});
        ofFloat.setDuration(200);
        ofFloat.setInterpolator(new OvershootInterpolator());
        ofFloat.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                PinEntryEditText.this.mLastCharPaint.setTextSize(((Float) valueAnimator.getAnimatedValue()).floatValue());
                PinEntryEditText.this.invalidate();
            }
        });
        if (getText().length() == this.mMaxLength && this.mOnPinEnteredListener != null) {
            ofFloat.addListener(new AnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    PinEntryEditText.this.mOnPinEnteredListener.onPinEntered(PinEntryEditText.this.getText());
                }
            });
        }
        ofFloat.start();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void animateBottomUp(java.lang.CharSequence r8, final int r9) {
        /*
        r7 = this;
        r0 = r7.mCharBottom;
        r1 = r7.mLineCoords;
        r1 = r1[r9];
        r1 = r1.bottom;
        r2 = r7.mTextBottomPadding;
        r1 = r1 - r2;
        r0[r9] = r1;
        r0 = 2;
        r1 = new float[r0];
        r2 = r7.mCharBottom;
        r2 = r2[r9];
        r3 = r7.getPaint();
        r3 = r3.getTextSize();
        r2 = r2 + r3;
        r3 = 0;
        r1[r3] = r2;
        r2 = r7.mCharBottom;
        r2 = r2[r9];
        r4 = 1;
        r1[r4] = r2;
        r1 = android.animation.ValueAnimator.ofFloat(r1);
        r5 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
        r1.setDuration(r5);
        r2 = new android.view.animation.OvershootInterpolator;
        r2.<init>();
        r1.setInterpolator(r2);
        r2 = new com.jelsat.widgets.PinEntryEditText$6;
        r2.<init>(r9);
        r1.addUpdateListener(r2);
        r9 = r7.mLastCharPaint;
        r2 = 255; // 0xff float:3.57E-43 double:1.26E-321;
        r9.setAlpha(r2);
        r9 = new int[r0];
        r9 = {0, 255};
        r9 = android.animation.ValueAnimator.ofInt(r9);
        r9.setDuration(r5);
        r2 = new com.jelsat.widgets.PinEntryEditText$7;
        r2.<init>();
        r9.addUpdateListener(r2);
        r2 = new android.animation.AnimatorSet;
        r2.<init>();
        r8 = r8.length();
        r5 = r7.mMaxLength;
        if (r8 != r5) goto L_0x0074;
    L_0x0068:
        r8 = r7.mOnPinEnteredListener;
        if (r8 == 0) goto L_0x0074;
    L_0x006c:
        r8 = new com.jelsat.widgets.PinEntryEditText$8;
        r8.<init>();
        r2.addListener(r8);
    L_0x0074:
        r8 = new android.animation.Animator[r0];
        r8[r3] = r1;
        r8[r4] = r9;
        r2.playTogether(r8);
        r2.start();
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jelsat.widgets.PinEntryEditText.animateBottomUp(java.lang.CharSequence, int):void");
    }

    public void setAnimateText(boolean z) {
        this.mAnimate = z;
    }

    public void setOnPinEnteredListener(OnPinEnteredListener onPinEnteredListener) {
        this.mOnPinEnteredListener = onPinEnteredListener;
    }
}
