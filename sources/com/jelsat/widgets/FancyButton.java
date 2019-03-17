package com.jelsat.widgets;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build.VERSION;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.jelsat.R;
import com.jelsat.utils.Utils;
import java.util.ArrayList;
import java.util.Iterator;

public class FancyButton extends LinearLayout {
    public static final int POSITION_BOTTOM = 4;
    public static final int POSITION_LEFT = 1;
    public static final int POSITION_RIGHT = 2;
    public static final int POSITION_TOP = 3;
    public static final String TAG = "FancyButton";
    private int mBorderColor = 0;
    private int mBorderWidth = 0;
    private Context mContext;
    private int mDefaultBackgroundColor = ViewCompat.MEASURED_STATE_MASK;
    private int mDefaultIconColor = -1;
    private String mDefaultIconFont = "fontawesome.ttf";
    private int mDefaultTextColor = -1;
    private String mDefaultTextFont = "robotoregular.ttf";
    private int mDefaultTextGravity = 17;
    private int mDefaultTextSize = Utils.spToPx(getContext(), 15.0f);
    private int mDisabledBackgroundColor = Color.parseColor("#f6f7f9");
    private int mDisabledBorderColor = Color.parseColor("#dddfe2");
    private int mDisabledTextColor = Color.parseColor("#bec2c9");
    private boolean mEnabled = true;
    private int mFocusBackgroundColor = 0;
    private String mFontIcon = null;
    private int mFontIconSize = Utils.spToPx(getContext(), 15.0f);
    private TextView mFontIconView;
    private boolean mGhost = false;
    private int mIconPaddingBottom = 0;
    private int mIconPaddingLeft = 10;
    private int mIconPaddingRight = 10;
    private int mIconPaddingTop = 0;
    private int mIconPosition = 1;
    private Drawable mIconResource = null;
    private Typeface mIconTypeFace = null;
    private ImageView mIconView;
    private int mRadius = 0;
    private int mRadiusBottomLeft = 0;
    private int mRadiusBottomRight = 0;
    private int mRadiusTopLeft = 0;
    private int mRadiusTopRight = 0;
    private String mText = null;
    private boolean mTextAllCaps = false;
    private int mTextPosition = 1;
    private Typeface mTextTypeFace = null;
    private TextView mTextView;
    private boolean mUseRippleEffect = true;
    private boolean mUseSystemFont = false;

    @TargetApi(21)
    class CustomOutline extends ViewOutlineProvider {
        int height;
        int width;

        CustomOutline(int i, int i2) {
            this.width = i;
            this.height = i2;
        }

        public void getOutline(View view, Outline outline) {
            if (FancyButton.this.mRadius == null) {
                outline.setRect(null, 10, this.width, this.height);
                return;
            }
            outline.setRoundRect(0, 10, this.width, this.height, (float) FancyButton.this.mRadius);
        }
    }

    public FancyButton(Context context) {
        super(context);
        this.mContext = context;
        this.mTextTypeFace = Utils.findFont(this.mContext, this.mDefaultTextFont, null);
        this.mIconTypeFace = Utils.findFont(this.mContext, this.mDefaultIconFont, null);
        initializeFancyButton();
    }

    public FancyButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        context = context.obtainStyledAttributes(attributeSet, R.styleable.FancyButton, 0, 0);
        initAttributesArray(context);
        context.recycle();
        initializeFancyButton();
    }

    private void initializeFancyButton() {
        Iterator it;
        initializeButtonContainer();
        this.mTextView = setupTextView();
        this.mIconView = setupIconView();
        this.mFontIconView = setupFontIconView();
        removeAllViews();
        setupBackground();
        ArrayList arrayList = new ArrayList();
        if (this.mIconPosition != 1) {
            if (this.mIconPosition != 3) {
                if (this.mTextView != null) {
                    arrayList.add(this.mTextView);
                }
                if (this.mIconView != null) {
                    arrayList.add(this.mIconView);
                }
                if (this.mFontIconView != null) {
                    arrayList.add(this.mFontIconView);
                }
                it = arrayList.iterator();
                while (it.hasNext()) {
                    addView((View) it.next());
                }
            }
        }
        if (this.mIconView != null) {
            arrayList.add(this.mIconView);
        }
        if (this.mFontIconView != null) {
            arrayList.add(this.mFontIconView);
        }
        if (this.mTextView != null) {
            arrayList.add(this.mTextView);
        }
        it = arrayList.iterator();
        while (it.hasNext()) {
            addView((View) it.next());
        }
    }

    private TextView setupTextView() {
        if (this.mText == null) {
            this.mText = "Fancy Button";
        }
        TextView textView = new TextView(this.mContext);
        textView.setText(this.mText);
        textView.setGravity(this.mDefaultTextGravity);
        textView.setTextColor(this.mEnabled ? this.mDefaultTextColor : this.mDisabledTextColor);
        textView.setTextSize((float) Utils.pxToSp(getContext(), (float) this.mDefaultTextSize));
        textView.setLayoutParams(new LayoutParams(-2, -2));
        if (!(isInEditMode() || this.mUseSystemFont)) {
            textView.setTypeface(this.mTextTypeFace);
        }
        return textView;
    }

    private TextView setupFontIconView() {
        if (this.mFontIcon == null) {
            return null;
        }
        TextView textView = new TextView(this.mContext);
        textView.setTextColor(this.mEnabled ? this.mDefaultIconColor : this.mDisabledTextColor);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.rightMargin = this.mIconPaddingRight;
        layoutParams.leftMargin = this.mIconPaddingLeft;
        layoutParams.topMargin = this.mIconPaddingTop;
        layoutParams.bottomMargin = this.mIconPaddingBottom;
        if (this.mTextView != null) {
            if (this.mIconPosition != 3) {
                if (this.mIconPosition != 4) {
                    textView.setGravity(16);
                    layoutParams.gravity = 16;
                }
            }
            layoutParams.gravity = 17;
            textView.setGravity(17);
        } else {
            layoutParams.gravity = 17;
            textView.setGravity(16);
        }
        textView.setLayoutParams(layoutParams);
        if (isInEditMode()) {
            textView.setTextSize((float) Utils.pxToSp(getContext(), (float) this.mFontIconSize));
            textView.setText("O");
        } else {
            textView.setTextSize((float) Utils.pxToSp(getContext(), (float) this.mFontIconSize));
            textView.setText(this.mFontIcon);
            textView.setTypeface(this.mIconTypeFace);
        }
        return textView;
    }

    private ImageView setupIconView() {
        if (this.mIconResource == null) {
            return null;
        }
        ImageView imageView = new ImageView(this.mContext);
        imageView.setImageDrawable(this.mIconResource);
        imageView.setPadding(this.mIconPaddingLeft, this.mIconPaddingTop, this.mIconPaddingRight, this.mIconPaddingBottom);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
        if (this.mTextView != null) {
            if (this.mIconPosition != 3) {
                if (this.mIconPosition != 4) {
                    layoutParams.gravity = GravityCompat.START;
                    layoutParams.rightMargin = 10;
                    layoutParams.leftMargin = 10;
                }
            }
            layoutParams.gravity = 17;
            layoutParams.rightMargin = 10;
            layoutParams.leftMargin = 10;
        } else {
            layoutParams.gravity = 16;
        }
        imageView.setLayoutParams(layoutParams);
        return imageView;
    }

    private void initAttributesArray(android.content.res.TypedArray r7) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
*/
        /*
        r6 = this;
        r0 = r6.mDefaultBackgroundColor;
        r1 = 6;
        r0 = r7.getColor(r1, r0);
        r6.mDefaultBackgroundColor = r0;
        r0 = r6.mFocusBackgroundColor;
        r1 = 10;
        r0 = r7.getColor(r1, r0);
        r6.mFocusBackgroundColor = r0;
        r0 = r6.mDisabledBackgroundColor;
        r1 = 8;
        r0 = r7.getColor(r1, r0);
        r6.mDisabledBackgroundColor = r0;
        r0 = 1;
        r1 = 0;
        r2 = r7.getBoolean(r1, r0);
        r6.mEnabled = r2;
        r2 = r6.mDisabledTextColor;
        r3 = 9;
        r2 = r7.getColor(r3, r2);
        r6.mDisabledTextColor = r2;
        r2 = r6.mDisabledBorderColor;
        r3 = 7;
        r2 = r7.getColor(r3, r2);
        r6.mDisabledBorderColor = r2;
        r2 = r6.mDefaultTextColor;
        r3 = 29;
        r2 = r7.getColor(r3, r2);
        r6.mDefaultTextColor = r2;
        r2 = r6.mDefaultTextColor;
        r3 = 14;
        r2 = r7.getColor(r3, r2);
        r6.mDefaultIconColor = r2;
        r2 = r6.mDefaultTextSize;
        r2 = (float) r2;
        r3 = 33;
        r2 = r7.getDimension(r3, r2);
        r2 = (int) r2;
        r6.mDefaultTextSize = r2;
        r2 = r6.mDefaultTextSize;
        r2 = (float) r2;
        r0 = r7.getDimension(r0, r2);
        r0 = (int) r0;
        r6.mDefaultTextSize = r0;
        r0 = r6.mDefaultTextGravity;
        r2 = 31;
        r0 = r7.getInt(r2, r0);
        r6.mDefaultTextGravity = r0;
        r0 = r6.mBorderColor;
        r2 = 4;
        r0 = r7.getColor(r2, r0);
        r6.mBorderColor = r0;
        r0 = r6.mBorderWidth;
        r0 = (float) r0;
        r2 = 5;
        r0 = r7.getDimension(r2, r0);
        r0 = (int) r0;
        r6.mBorderWidth = r0;
        r0 = r6.mRadius;
        r0 = (float) r0;
        r2 = 22;
        r0 = r7.getDimension(r2, r0);
        r0 = (int) r0;
        r6.mRadius = r0;
        r0 = r6.mRadius;
        r0 = (float) r0;
        r2 = 25;
        r0 = r7.getDimension(r2, r0);
        r0 = (int) r0;
        r6.mRadiusTopLeft = r0;
        r0 = r6.mRadius;
        r0 = (float) r0;
        r2 = 26;
        r0 = r7.getDimension(r2, r0);
        r0 = (int) r0;
        r6.mRadiusTopRight = r0;
        r0 = r6.mRadius;
        r0 = (float) r0;
        r2 = 23;
        r0 = r7.getDimension(r2, r0);
        r0 = (int) r0;
        r6.mRadiusBottomLeft = r0;
        r0 = r6.mRadius;
        r0 = (float) r0;
        r2 = 24;
        r0 = r7.getDimension(r2, r0);
        r0 = (int) r0;
        r6.mRadiusBottomRight = r0;
        r0 = r6.mFontIconSize;
        r0 = (float) r0;
        r2 = 12;
        r0 = r7.getDimension(r2, r0);
        r0 = (int) r0;
        r6.mFontIconSize = r0;
        r0 = r6.mIconPaddingLeft;
        r0 = (float) r0;
        r2 = 17;
        r0 = r7.getDimension(r2, r0);
        r0 = (int) r0;
        r6.mIconPaddingLeft = r0;
        r0 = r6.mIconPaddingRight;
        r0 = (float) r0;
        r2 = 18;
        r0 = r7.getDimension(r2, r0);
        r0 = (int) r0;
        r6.mIconPaddingRight = r0;
        r0 = r6.mIconPaddingTop;
        r0 = (float) r0;
        r2 = 19;
        r0 = r7.getDimension(r2, r0);
        r0 = (int) r0;
        r6.mIconPaddingTop = r0;
        r0 = r6.mIconPaddingBottom;
        r0 = (float) r0;
        r2 = 16;
        r0 = r7.getDimension(r2, r0);
        r0 = (int) r0;
        r6.mIconPaddingBottom = r0;
        r0 = 28;
        r0 = r7.getBoolean(r0, r1);
        r6.mTextAllCaps = r0;
        r0 = 3;
        r0 = r7.getBoolean(r0, r1);
        r6.mTextAllCaps = r0;
        r0 = r6.mGhost;
        r1 = 13;
        r0 = r7.getBoolean(r1, r0);
        r6.mGhost = r0;
        r0 = r6.mUseSystemFont;
        r1 = 34;
        r0 = r7.getBoolean(r1, r0);
        r6.mUseSystemFont = r0;
        r0 = 27;
        r0 = r7.getString(r0);
        if (r0 != 0) goto L_0x0128;
    L_0x0123:
        r0 = 2;
        r0 = r7.getString(r0);
    L_0x0128:
        r1 = 20;
        r2 = r6.mIconPosition;
        r1 = r7.getInt(r1, r2);
        r6.mIconPosition = r1;
        r1 = 11;
        r1 = r7.getString(r1);
        r2 = 15;
        r2 = r7.getString(r2);
        r3 = 30;
        r3 = r7.getString(r3);
        r4 = 21;
        r5 = 0;
        r7 = r7.getDrawable(r4);	 Catch:{ Exception -> 0x014e }
        r6.mIconResource = r7;	 Catch:{ Exception -> 0x014e }
        goto L_0x0150;
    L_0x014e:
        r6.mIconResource = r5;
    L_0x0150:
        if (r1 == 0) goto L_0x0154;
    L_0x0152:
        r6.mFontIcon = r1;
    L_0x0154:
        if (r0 == 0) goto L_0x0160;
    L_0x0156:
        r7 = r6.mTextAllCaps;
        if (r7 == 0) goto L_0x015e;
    L_0x015a:
        r0 = r0.toUpperCase();
    L_0x015e:
        r6.mText = r0;
    L_0x0160:
        r7 = r6.isInEditMode();
        if (r7 != 0) goto L_0x0194;
    L_0x0166:
        if (r2 == 0) goto L_0x0173;
    L_0x0168:
        r7 = r6.mContext;
        r0 = r6.mDefaultIconFont;
        r7 = com.jelsat.utils.Utils.findFont(r7, r2, r0);
        r6.mIconTypeFace = r7;
        goto L_0x017d;
    L_0x0173:
        r7 = r6.mContext;
        r0 = r6.mDefaultIconFont;
        r7 = com.jelsat.utils.Utils.findFont(r7, r0, r5);
        r6.mIconTypeFace = r7;
    L_0x017d:
        if (r3 == 0) goto L_0x018a;
    L_0x017f:
        r7 = r6.mContext;
        r0 = r6.mDefaultTextFont;
        r7 = com.jelsat.utils.Utils.findFont(r7, r3, r0);
        r6.mTextTypeFace = r7;
        return;
    L_0x018a:
        r7 = r6.mContext;
        r0 = r6.mDefaultTextFont;
        r7 = com.jelsat.utils.Utils.findFont(r7, r0, r5);
        r6.mTextTypeFace = r7;
    L_0x0194:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jelsat.widgets.FancyButton.initAttributesArray(android.content.res.TypedArray):void");
    }

    @TargetApi(21)
    private Drawable getRippleDrawable(Drawable drawable, Drawable drawable2, Drawable drawable3) {
        if (this.mEnabled) {
            return new RippleDrawable(ColorStateList.valueOf(this.mFocusBackgroundColor), drawable, drawable2);
        }
        return drawable3;
    }

    private void applyRadius(GradientDrawable gradientDrawable) {
        if (this.mRadius > 0) {
            gradientDrawable.setCornerRadius((float) this.mRadius);
            return;
        }
        gradientDrawable.setCornerRadii(new float[]{(float) this.mRadiusTopLeft, (float) this.mRadiusTopLeft, (float) this.mRadiusTopRight, (float) this.mRadiusTopRight, (float) this.mRadiusBottomRight, (float) this.mRadiusBottomRight, (float) this.mRadiusBottomLeft, (float) this.mRadiusBottomLeft});
    }

    @SuppressLint({"NewApi"})
    private void setupBackground() {
        Drawable gradientDrawable = new GradientDrawable();
        applyRadius(gradientDrawable);
        if (this.mGhost) {
            gradientDrawable.setColor(getResources().getColor(17170445));
        } else {
            gradientDrawable.setColor(this.mDefaultBackgroundColor);
        }
        Drawable gradientDrawable2 = new GradientDrawable();
        applyRadius(gradientDrawable2);
        gradientDrawable2.setColor(this.mFocusBackgroundColor);
        Drawable gradientDrawable3 = new GradientDrawable();
        applyRadius(gradientDrawable3);
        gradientDrawable3.setColor(this.mDisabledBackgroundColor);
        gradientDrawable3.setStroke(this.mBorderWidth, this.mDisabledBorderColor);
        if (this.mBorderColor != 0) {
            gradientDrawable.setStroke(this.mBorderWidth, this.mBorderColor);
        }
        if (!this.mEnabled) {
            gradientDrawable.setStroke(this.mBorderWidth, this.mDisabledBorderColor);
            if (this.mGhost) {
                gradientDrawable3.setColor(getResources().getColor(17170445));
            }
        }
        if (!this.mUseRippleEffect || VERSION.SDK_INT < 21) {
            gradientDrawable2 = new StateListDrawable();
            Drawable gradientDrawable4 = new GradientDrawable();
            applyRadius(gradientDrawable4);
            if (this.mGhost) {
                gradientDrawable4.setColor(getResources().getColor(17170445));
            } else {
                gradientDrawable4.setColor(this.mFocusBackgroundColor);
            }
            if (this.mBorderColor != 0) {
                if (this.mGhost) {
                    gradientDrawable4.setStroke(this.mBorderWidth, this.mFocusBackgroundColor);
                } else {
                    gradientDrawable4.setStroke(this.mBorderWidth, this.mBorderColor);
                }
            }
            if (!this.mEnabled) {
                gradientDrawable4.setStroke(this.mBorderWidth, this.mDisabledBorderColor);
            }
            if (this.mFocusBackgroundColor != 0) {
                gradientDrawable2.addState(new int[]{16842919}, gradientDrawable4);
                gradientDrawable2.addState(new int[]{16842908}, gradientDrawable4);
                gradientDrawable2.addState(new int[]{-16842910}, gradientDrawable3);
            }
            gradientDrawable2.addState(new int[0], gradientDrawable);
            if (VERSION.SDK_INT < 16) {
                setBackgroundDrawable(gradientDrawable2);
                return;
            } else {
                setBackground(gradientDrawable2);
                return;
            }
        }
        setBackground(getRippleDrawable(gradientDrawable, gradientDrawable2, gradientDrawable3));
    }

    private void initializeButtonContainer() {
        if (this.mIconPosition != 3) {
            if (this.mIconPosition != 4) {
                setOrientation(0);
                if (getLayoutParams() == null) {
                    setLayoutParams(new LayoutParams(-2, -2));
                }
                setGravity(17);
                if (this.mIconResource == null && this.mFontIcon == null && getPaddingLeft() == 0 && getPaddingRight() == 0 && getPaddingTop() == 0 && getPaddingBottom() == 0) {
                    setPadding(20, 0, 20, 0);
                    return;
                }
                return;
            }
        }
        setOrientation(1);
        if (getLayoutParams() == null) {
            setLayoutParams(new LayoutParams(-2, -2));
        }
        setGravity(17);
        if (this.mIconResource == null) {
        }
    }

    public void setText(String str) {
        if (this.mTextAllCaps) {
            str = str.toUpperCase();
        }
        this.mText = str;
        if (this.mTextView == null) {
            initializeFancyButton();
        } else {
            this.mTextView.setText(str);
        }
    }

    public void setTextAllCaps(boolean z) {
        this.mTextAllCaps = z;
        setText(this.mText);
    }

    public void setTextColor(int i) {
        this.mDefaultTextColor = i;
        if (this.mTextView == null) {
            initializeFancyButton();
        } else {
            this.mTextView.setTextColor(i);
        }
    }

    public void setIconColor(int i) {
        if (this.mFontIconView != null) {
            this.mFontIconView.setTextColor(i);
        }
    }

    public void setBackgroundColor(int i) {
        this.mDefaultBackgroundColor = i;
        if (this.mIconView != 0 || this.mFontIconView != 0 || this.mTextView != 0) {
            setupBackground();
        }
    }

    public void setFocusBackgroundColor(int i) {
        this.mFocusBackgroundColor = i;
        if (this.mIconView != 0 || this.mFontIconView != 0 || this.mTextView != 0) {
            setupBackground();
        }
    }

    public void setDisableBackgroundColor(int i) {
        this.mDisabledBackgroundColor = i;
        if (this.mIconView != 0 || this.mFontIconView != 0 || this.mTextView != 0) {
            setupBackground();
        }
    }

    public void setDisableTextColor(int i) {
        this.mDisabledTextColor = i;
        if (this.mTextView == null) {
            initializeFancyButton();
            return;
        }
        if (!this.mEnabled) {
            this.mTextView.setTextColor(i);
        }
    }

    public void setDisableBorderColor(int i) {
        this.mDisabledBorderColor = i;
        if (this.mIconView != 0 || this.mFontIconView != 0 || this.mTextView != 0) {
            setupBackground();
        }
    }

    public void setTextSize(int i) {
        i = (float) i;
        this.mDefaultTextSize = Utils.spToPx(getContext(), i);
        if (this.mTextView != null) {
            this.mTextView.setTextSize(i);
        }
    }

    public void setTextGravity(int i) {
        this.mDefaultTextGravity = i;
        if (this.mTextView != null) {
            setGravity(i);
        }
    }

    public void setIconPadding(int i, int i2, int i3, int i4) {
        this.mIconPaddingLeft = i;
        this.mIconPaddingTop = i2;
        this.mIconPaddingRight = i3;
        this.mIconPaddingBottom = i4;
        if (this.mIconView != 0) {
            this.mIconView.setPadding(this.mIconPaddingLeft, this.mIconPaddingTop, this.mIconPaddingRight, this.mIconPaddingBottom);
        }
        if (this.mFontIconView != 0) {
            this.mFontIconView.setPadding(this.mIconPaddingLeft, this.mIconPaddingTop, this.mIconPaddingRight, this.mIconPaddingBottom);
        }
    }

    public void setIconResource(int i) {
        this.mIconResource = this.mContext.getResources().getDrawable(i);
        if (this.mIconView != 0) {
            if (this.mFontIconView == 0) {
                this.mIconView.setImageDrawable(this.mIconResource);
                return;
            }
        }
        this.mFontIconView = 0;
        initializeFancyButton();
    }

    public void setIconResource(Drawable drawable) {
        this.mIconResource = drawable;
        if (this.mIconView != null) {
            if (this.mFontIconView == null) {
                this.mIconView.setImageDrawable(this.mIconResource);
                return;
            }
        }
        this.mFontIconView = null;
        initializeFancyButton();
    }

    public void setIconResource(String str) {
        this.mFontIcon = str;
        if (this.mFontIconView == null) {
            this.mIconView = null;
            initializeFancyButton();
            return;
        }
        this.mFontIconView.setText(str);
    }

    public void setFontIconSize(int i) {
        i = (float) i;
        this.mFontIconSize = Utils.spToPx(getContext(), i);
        if (this.mFontIconView != null) {
            this.mFontIconView.setTextSize(i);
        }
    }

    public void setIconPosition(int i) {
        if (i <= 0 || i >= 5) {
            this.mIconPosition = 1;
        } else {
            this.mIconPosition = i;
        }
        initializeFancyButton();
    }

    public void setBorderColor(int i) {
        this.mBorderColor = i;
        if (this.mIconView != 0 || this.mFontIconView != 0 || this.mTextView != 0) {
            setupBackground();
        }
    }

    public void setBorderWidth(int i) {
        this.mBorderWidth = i;
        if (this.mIconView != 0 || this.mFontIconView != 0 || this.mTextView != 0) {
            setupBackground();
        }
    }

    public void setRadius(int i) {
        this.mRadius = i;
        if (this.mIconView != 0 || this.mFontIconView != 0 || this.mTextView != 0) {
            setupBackground();
        }
    }

    public void setRadius(int[] iArr) {
        this.mRadiusTopLeft = iArr[0];
        this.mRadiusTopRight = iArr[1];
        this.mRadiusBottomLeft = iArr[2];
        this.mRadiusBottomRight = iArr[3];
        if (this.mIconView != null || this.mFontIconView != null || this.mTextView != null) {
            setupBackground();
        }
    }

    public void setCustomTextFont(String str) {
        this.mTextTypeFace = Utils.findFont(this.mContext, str, this.mDefaultTextFont);
        if (this.mTextView == null) {
            initializeFancyButton();
        } else {
            this.mTextView.setTypeface(this.mTextTypeFace);
        }
    }

    public void setCustomIconFont(String str) {
        this.mIconTypeFace = Utils.findFont(this.mContext, str, this.mDefaultIconFont);
        if (this.mFontIconView == null) {
            initializeFancyButton();
        } else {
            this.mFontIconView.setTypeface(this.mIconTypeFace);
        }
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.mEnabled = z;
        initializeFancyButton();
    }

    public void setGhost(boolean z) {
        this.mGhost = z;
        if (this.mIconView || this.mFontIconView || this.mTextView) {
            setupBackground();
        }
    }

    public void setUsingSystemFont(boolean z) {
        this.mUseSystemFont = z;
    }

    public CharSequence getText() {
        return this.mTextView != null ? this.mTextView.getText() : "";
    }

    public TextView getTextViewObject() {
        return this.mTextView;
    }

    public TextView getIconFontObject() {
        return this.mFontIconView;
    }

    public ImageView getIconImageObject() {
        return this.mIconView;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (VERSION.SDK_INT >= 21) {
            setOutlineProvider(new CustomOutline(i, i2));
        }
    }
}
