package com.jelsat.googlemap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import com.jelsat.R;
import com.jelsat.customclasses.CustomTextView;

public class IconGenerator {
    public static final int STYLE_BLUE = 4;
    public static final int STYLE_DEFAULT = 1;
    public static final int STYLE_GREEN = 5;
    public static final int STYLE_ORANGE = 7;
    public static final int STYLE_PURPLE = 6;
    public static final int STYLE_RED = 3;
    public static final int STYLE_WHITE = 2;
    private float mAnchorU = 0.5f;
    private float mAnchorV = 1.0f;
    private BubbleDrawable mBackground;
    private ViewGroup mContainer;
    private View mContentView;
    private final Context mContext;
    private int mRotation;
    private RotationLayout mRotationLayout;
    private CustomTextView mTextView;

    private static int getStyleColor(int i) {
        switch (i) {
            case 3:
                return -3407872;
            case 4:
                return -16737844;
            case 5:
                return -10053376;
            case 6:
                return -6736948;
            case 7:
                return -30720;
            default:
                return -1;
        }
    }

    private static int getTextStyle(int i) {
        switch (i) {
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                return R.style.amu_Bubble.TextAppearance.Light;
            default:
                return R.style.amu_Bubble.TextAppearance.Dark;
        }
    }

    public IconGenerator(Context context) {
        this.mContext = context;
        this.mBackground = new BubbleDrawable(this.mContext.getResources());
        this.mContainer = (ViewGroup) LayoutInflater.from(this.mContext).inflate(R.layout.amu_text_bubble, null);
        this.mRotationLayout = (RotationLayout) this.mContainer.getChildAt(0);
        CustomTextView customTextView = (CustomTextView) this.mRotationLayout.findViewById(R.id.amu_text);
        this.mTextView = customTextView;
        this.mContentView = customTextView;
        setStyle(1);
    }

    public Bitmap makeIcon(String str, int i) {
        if (this.mTextView != null) {
            this.mTextView.setText(str);
            setCompoundDrawableToText(i);
        }
        return makeIcon();
    }

    private void setCompoundDrawableToText(int i) {
        switch (i) {
            case 0:
                this.mTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_24hours_booking, 0);
                break;
            case 1:
                this.mTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_instant_booking, 0);
                return;
            default:
                break;
        }
    }

    public Bitmap makeIcon() {
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
        this.mContainer.measure(makeMeasureSpec, makeMeasureSpec);
        makeMeasureSpec = this.mContainer.getMeasuredWidth();
        int measuredHeight = this.mContainer.getMeasuredHeight();
        this.mContainer.layout(0, 0, makeMeasureSpec, measuredHeight);
        if (this.mRotation == 1 || this.mRotation == 3) {
            measuredHeight = this.mContainer.getMeasuredWidth();
            makeMeasureSpec = this.mContainer.getMeasuredHeight();
        }
        Bitmap createBitmap = Bitmap.createBitmap(makeMeasureSpec, measuredHeight, Config.ARGB_8888);
        createBitmap.eraseColor(0);
        Canvas canvas = new Canvas(createBitmap);
        switch (this.mRotation) {
            case 0:
                break;
            case 1:
                canvas.translate((float) makeMeasureSpec, 0.0f);
                canvas.rotate(90.0f);
                break;
            case 2:
                canvas.rotate(180.0f, (float) (makeMeasureSpec / 2), (float) (measuredHeight / 2));
                break;
            case 3:
                canvas.translate(0.0f, (float) measuredHeight);
                canvas.rotate(270.0f);
                break;
            default:
                break;
        }
        this.mContainer.draw(canvas);
        return createBitmap;
    }

    public void setContentView(View view) {
        this.mRotationLayout.removeAllViews();
        this.mRotationLayout.addView(view);
        this.mContentView = view;
        view = this.mRotationLayout.findViewById(R.id.amu_text);
        this.mTextView = view instanceof CustomTextView ? (CustomTextView) view : null;
    }

    public void setContentRotation(int i) {
        this.mRotationLayout.setViewRotation(i);
    }

    public void setRotation(int i) {
        this.mRotation = ((i + 360) % 360) / 90;
    }

    public float getAnchorU() {
        return rotateAnchor(this.mAnchorU, this.mAnchorV);
    }

    public float getAnchorV() {
        return rotateAnchor(this.mAnchorV, this.mAnchorU);
    }

    private float rotateAnchor(float f, float f2) {
        switch (this.mRotation) {
            case 0:
                return f;
            case 1:
                return 1.0f - f2;
            case 2:
                return 1.0f - f;
            case 3:
                return f2;
            default:
                throw new IllegalStateException();
        }
    }

    public void setTextAppearance(Context context, int i) {
        if (this.mTextView != null) {
            this.mTextView.setTextAppearance(context, i);
        }
    }

    public void setTextAppearance(int i) {
        setTextAppearance(this.mContext, i);
    }

    public void setStyle(int i) {
        setColor(getStyleColor(i));
        setTextAppearance(this.mContext, getTextStyle(i));
    }

    public void setColor(int i) {
        this.mBackground.setColor(i);
        setBackground(this.mBackground);
    }

    public void setBackground(Drawable drawable) {
        this.mContainer.setBackgroundDrawable(drawable);
        if (drawable != null) {
            Rect rect = new Rect();
            drawable.getPadding(rect);
            this.mContainer.setPadding(rect.left, rect.top, rect.right, rect.bottom);
            return;
        }
        this.mContainer.setPadding(0, 0, 0, 0);
    }

    public void setContentPadding(int i, int i2, int i3, int i4) {
        this.mContentView.setPadding(i, i2, i3, i4);
    }
}
