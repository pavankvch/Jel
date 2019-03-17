package com.jelsat.customclasses;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Transformation;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jelsat.R;

public class ExpandableTextView extends LinearLayout implements OnClickListener {
    private static final int DEFAULT_ANIM_DURATION = 300;
    private static final float DEFAULT_CONTENT_TEXT_LINE_SPACING_MULTIPLIER = 1.2f;
    private static final float DEFAULT_CONTENT_TEXT_SIZE = 16.0f;
    private static final int MAX_COLLAPSED_LINES = 8;
    private static final int STATE_TV_GRAVITY_CENTER = 1;
    private static final int STATE_TV_GRAVITY_LEFT = 0;
    private static final int STATE_TV_GRAVITY_RIGHT = 2;
    private boolean mAnimating;
    private int mAnimationDuration;
    private Drawable mCollapseDrawable;
    private boolean mCollapsed;
    private int mCollapsedHeight;
    private SparseBooleanArray mCollapsedStatus;
    private String mCollapsedString;
    private float mContentLineSpacingMultiplier;
    private int mContentTextColor;
    private float mContentTextSize;
    private Drawable mExpandDrawable;
    private String mExpandString;
    private OnExpandStateChangeListener mListener;
    private int mMarginBetweenTxtAndBottom;
    private int mMaxCollapsedLines;
    private int mPosition;
    private boolean mRelayout;
    private Runnable mRunnable;
    private int mStateTextColor;
    protected TextView mStateTv;
    private int mStateTvGravity;
    private int mTextHeightWithMaxLines;
    protected TextView mTv;

    class ExpandCollapseAnimation extends Animation {
        private final int mEndHeight;
        private final int mStartHeight;
        private final View mTargetView;

        public boolean willChangeBounds() {
            return true;
        }

        public ExpandCollapseAnimation(View view, int i, int i2) {
            this.mTargetView = view;
            this.mStartHeight = i;
            this.mEndHeight = i2;
            setDuration((long) ExpandableTextView.this.mAnimationDuration);
        }

        protected void applyTransformation(float f, Transformation transformation) {
            f = (int) ((((float) (this.mEndHeight - this.mStartHeight)) * f) + ((float) this.mStartHeight));
            ExpandableTextView.this.mTv.setMaxHeight(f - ExpandableTextView.this.mMarginBetweenTxtAndBottom);
            this.mTargetView.getLayoutParams().height = f;
            this.mTargetView.requestLayout();
        }

        public void initialize(int i, int i2, int i3, int i4) {
            super.initialize(i, i2, i3, i4);
        }
    }

    public interface OnExpandStateChangeListener {
        void onExpandStateChanged(TextView textView, boolean z);
    }

    public ExpandableTextView(Context context) {
        this(context, null);
    }

    public ExpandableTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCollapsed = true;
        this.mRunnable = new Runnable() {
            public void run() {
                ExpandableTextView.this.mMarginBetweenTxtAndBottom = ExpandableTextView.this.getHeight() - ExpandableTextView.this.mTv.getHeight();
            }
        };
        init(context, attributeSet);
    }

    @TargetApi(11)
    public ExpandableTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCollapsed = true;
        this.mRunnable = /* anonymous class already generated */;
        init(context, attributeSet);
    }

    public void setOrientation(int i) {
        if (i == 0) {
            throw new IllegalArgumentException("ExpandableTextView only supports Vertical Orientation.");
        }
        super.setOrientation(i);
    }

    public void onClick(View view) {
        if (this.mStateTv.getVisibility() == null) {
            this.mCollapsed ^= 1;
            this.mStateTv.setText(this.mCollapsed ? this.mExpandString : this.mCollapsedString);
            this.mStateTv.setCompoundDrawablesWithIntrinsicBounds(this.mCollapsed ? this.mExpandDrawable : this.mCollapseDrawable, null, null, null);
            if (this.mCollapsedStatus != null) {
                this.mCollapsedStatus.put(this.mPosition, this.mCollapsed);
            }
            this.mAnimating = true;
            if (this.mCollapsed != null) {
                view = new ExpandCollapseAnimation(this, getHeight(), this.mCollapsedHeight);
            } else {
                view = new ExpandCollapseAnimation(this, getHeight(), (getHeight() + this.mTextHeightWithMaxLines) - this.mTv.getHeight());
            }
            view.setFillAfter(true);
            view.setAnimationListener(new AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    ExpandableTextView.this.clearAnimation();
                    ExpandableTextView.this.mAnimating = false;
                    if (ExpandableTextView.this.mListener != null) {
                        ExpandableTextView.this.mListener.onExpandStateChanged(ExpandableTextView.this.mTv, ExpandableTextView.this.mCollapsed ^ 1);
                    }
                }
            });
            clearAnimation();
            startAnimation(view);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.mAnimating;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        findViews();
    }

    protected void onMeasure(int i, int i2) {
        if (this.mRelayout) {
            if (getVisibility() != 8) {
                this.mRelayout = false;
                this.mStateTv.setVisibility(8);
                if (VERSION.SDK_INT >= 23) {
                    this.mStateTv.setTextColor(getContext().getColor(R.color.colorAccent));
                }
                this.mTv.setMaxLines(Integer.MAX_VALUE);
                super.onMeasure(i, i2);
                if (this.mTv.getLineCount() > this.mMaxCollapsedLines) {
                    this.mTextHeightWithMaxLines = getRealTextViewHeight(this.mTv);
                    if (this.mCollapsed) {
                        this.mTv.setMaxLines(this.mMaxCollapsedLines);
                    }
                    this.mStateTv.setVisibility(0);
                    super.onMeasure(i, i2);
                    if (this.mCollapsed != 0) {
                        this.mTv.post(this.mRunnable);
                        this.mCollapsedHeight = getMeasuredHeight();
                    }
                    return;
                }
                return;
            }
        }
        super.onMeasure(i, i2);
    }

    public void setOnExpandStateChangeListener(@Nullable OnExpandStateChangeListener onExpandStateChangeListener) {
        this.mListener = onExpandStateChangeListener;
    }

    public void setText(@Nullable CharSequence charSequence) {
        this.mRelayout = true;
        this.mTv.setText(charSequence);
        setVisibility(TextUtils.isEmpty(charSequence) != null ? 8 : null);
    }

    public void setText(@Nullable CharSequence charSequence, @NonNull SparseBooleanArray sparseBooleanArray, int i) {
        this.mCollapsedStatus = sparseBooleanArray;
        this.mPosition = i;
        sparseBooleanArray = sparseBooleanArray.get(i, true);
        clearAnimation();
        this.mCollapsed = sparseBooleanArray;
        this.mStateTv.setText(this.mCollapsed != 0 ? this.mExpandString : this.mCollapsedString);
        this.mStateTv.setCompoundDrawablesWithIntrinsicBounds(this.mCollapsed != 0 ? this.mExpandDrawable : this.mCollapseDrawable, null, null, null);
        setText(charSequence);
        getLayoutParams().height = -2;
        requestLayout();
    }

    @Nullable
    public CharSequence getText() {
        if (this.mTv == null) {
            return "";
        }
        return this.mTv.getText();
    }

    private void init(Context context, AttributeSet attributeSet) {
        LayoutInflater.from(context).inflate(R.layout.expandabletextview, this, true);
        setOrientation(1);
        setVisibility(8);
        attributeSet = getContext().obtainStyledAttributes(attributeSet, R.styleable.ExpandableTextView);
        this.mMaxCollapsedLines = attributeSet.getInt(10, 8);
        this.mAnimationDuration = attributeSet.getInt(1, DEFAULT_ANIM_DURATION);
        this.mContentTextSize = attributeSet.getDimension(6, DEFAULT_CONTENT_TEXT_SIZE);
        this.mContentLineSpacingMultiplier = attributeSet.getFloat(4, DEFAULT_CONTENT_TEXT_LINE_SPACING_MULTIPLIER);
        this.mContentTextColor = attributeSet.getColor(5, ViewCompat.MEASURED_STATE_MASK);
        this.mExpandDrawable = attributeSet.getDrawable(8);
        this.mCollapseDrawable = attributeSet.getDrawable(2);
        this.mStateTvGravity = attributeSet.getInt(0, 2);
        this.mExpandString = attributeSet.getString(9);
        this.mCollapsedString = attributeSet.getString(3);
        this.mStateTextColor = attributeSet.getColor(7, ViewCompat.MEASURED_STATE_MASK);
        if (this.mExpandString == null) {
            this.mExpandString = getContext().getString(R.string.expand_string);
        }
        if (this.mCollapsedString == null) {
            this.mCollapsedString = getContext().getString(R.string.collapsed_string);
        }
        attributeSet.recycle();
    }

    private void findViews() {
        this.mTv = (TextView) findViewById(R.id.expandable_text);
        this.mTv.setTextColor(this.mContentTextColor);
        this.mTv.setTextSize(this.mContentTextSize);
        this.mTv.setLineSpacing(0.0f, this.mContentLineSpacingMultiplier);
        this.mTv.setOnClickListener(this);
        this.mStateTv = (TextView) findViewById(R.id.expand_collapse);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        if (this.mStateTvGravity == 0) {
            layoutParams.gravity = GravityCompat.START;
        } else if (this.mStateTvGravity == 1) {
            layoutParams.gravity = 1;
        } else if (this.mStateTvGravity == 2) {
            layoutParams.gravity = GravityCompat.END;
        }
        this.mStateTv.setLayoutParams(layoutParams);
        this.mStateTv.setText(this.mCollapsed ? this.mExpandString : this.mCollapsedString);
        this.mStateTv.setTextColor(this.mStateTextColor);
        this.mStateTv.setCompoundDrawablesWithIntrinsicBounds(this.mCollapsed ? this.mExpandDrawable : this.mCollapseDrawable, null, null, null);
        this.mStateTv.setCompoundDrawablePadding(10);
        this.mStateTv.setOnClickListener(this);
    }

    private static boolean isPostLolipop() {
        return VERSION.SDK_INT >= 21;
    }

    @TargetApi(21)
    private static Drawable getDrawable(@NonNull Context context, @DrawableRes int i) {
        Resources resources = context.getResources();
        if (isPostLolipop()) {
            return resources.getDrawable(i, context.getTheme());
        }
        return resources.getDrawable(i);
    }

    private static int getRealTextViewHeight(@NonNull TextView textView) {
        return textView.getLayout().getLineTop(textView.getLineCount()) + (textView.getCompoundPaddingTop() + textView.getCompoundPaddingBottom());
    }
}
