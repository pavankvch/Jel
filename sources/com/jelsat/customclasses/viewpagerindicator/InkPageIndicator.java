package com.jelsat.customclasses.viewpagerindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Path.Op;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnAttachStateChangeListener;
import android.view.animation.Interpolator;
import com.jelsat.R;
import com.jelsat.utils.AnimationUtils;
import java.util.Arrays;

public class InkPageIndicator extends View implements OnPageChangeListener, OnAttachStateChangeListener {
    private static final int DEFAULT_ANIM_DURATION = 400;
    private static final int DEFAULT_DOT_SIZE = 8;
    private static final int DEFAULT_GAP = 12;
    private static final int DEFAULT_SELECTED_COLOUR = -1;
    private static final int DEFAULT_UNSELECTED_COLOUR = -2130706433;
    private static final float INVALID_FRACTION = -1.0f;
    private static final float MINIMAL_REVEAL = 1.0E-5f;
    private long animDuration;
    private long animHalfDuration;
    private final Path combinedUnselectedPath;
    float controlX1;
    float controlX2;
    float controlY1;
    float controlY2;
    private int currentPage;
    private float dotBottomY;
    private float[] dotCenterX;
    private float dotCenterY;
    private int dotDiameter;
    private float dotRadius;
    private float[] dotRevealFractions;
    private float dotTopY;
    float endX1;
    float endX2;
    float endY1;
    float endY2;
    private int gap;
    private float halfDotRadius;
    private final Interpolator interpolator;
    private boolean isAttachedToWindow;
    private float[] joiningFractions;
    private boolean measured;
    private boolean pageChanging;
    private int pageCount;
    private int previousPage;
    private final RectF rectF;
    private PendingRetreatAnimator retreatAnimation;
    private float retreatingJoinX1;
    private float retreatingJoinX2;
    private PendingRevealAnimator[] revealAnimations;
    private int selectedColour;
    private boolean selectedDotInPosition;
    private float selectedDotX;
    private final Paint selectedPaint;
    private int unselectedColour;
    private final Path unselectedDotLeftPath;
    private final Path unselectedDotPath;
    private final Path unselectedDotRightPath;
    private final Paint unselectedPaint;
    private ViewPager viewPager;

    public abstract class PendingStartAnimator extends ValueAnimator {
        protected boolean hasStarted = null;
        protected StartPredicate predicate;

        public PendingStartAnimator(StartPredicate startPredicate) {
            this.predicate = startPredicate;
        }

        public void startIfNecessary(float f) {
            if (!this.hasStarted && this.predicate.shouldStart(f) != null) {
                start();
                this.hasStarted = true;
            }
        }
    }

    public abstract class StartPredicate {
        protected float thresholdValue;

        abstract boolean shouldStart(float f);

        public StartPredicate(float f) {
            this.thresholdValue = f;
        }
    }

    public class LeftwardStartPredicate extends StartPredicate {
        public LeftwardStartPredicate(float f) {
            super(f);
        }

        boolean shouldStart(float f) {
            return f < this.thresholdValue;
        }
    }

    public class PendingRetreatAnimator extends PendingStartAnimator {
        public PendingRetreatAnimator(int i, int i2, int i3, StartPredicate startPredicate) {
            float access$1000;
            super(startPredicate);
            setDuration(InkPageIndicator.this.animHalfDuration);
            setInterpolator(InkPageIndicator.this.interpolator);
            if (i2 > i) {
                startPredicate = Math.min(InkPageIndicator.this.dotCenterX[i], InkPageIndicator.this.selectedDotX) - InkPageIndicator.this.dotRadius;
            } else {
                startPredicate = InkPageIndicator.this.dotCenterX[i2] - InkPageIndicator.this.dotRadius;
            }
            final float f = startPredicate;
            startPredicate = InkPageIndicator.this.dotCenterX[i2] - InkPageIndicator.this.dotRadius;
            if (i2 > i) {
                access$1000 = InkPageIndicator.this.dotCenterX[i2] + InkPageIndicator.this.dotRadius;
            } else {
                access$1000 = Math.max(InkPageIndicator.this.dotCenterX[i], InkPageIndicator.this.selectedDotX) + InkPageIndicator.this.dotRadius;
            }
            final float f2 = access$1000;
            i2 = InkPageIndicator.this.dotCenterX[i2] + InkPageIndicator.this.dotRadius;
            InkPageIndicator.this.revealAnimations = new PendingRevealAnimator[i3];
            final int[] iArr = new int[i3];
            int i4 = 0;
            int i5;
            if (f != startPredicate) {
                setFloatValues(new float[]{f, startPredicate});
                while (i4 < i3) {
                    i5 = i + i4;
                    InkPageIndicator.this.revealAnimations[i4] = new PendingRevealAnimator(i5, new RightwardStartPredicate(InkPageIndicator.this.dotCenterX[i5]));
                    iArr[i4] = i5;
                    i4++;
                }
                addUpdateListener(new AnimatorUpdateListener(InkPageIndicator.this) {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        InkPageIndicator.this.retreatingJoinX1 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        if (VERSION.SDK_INT >= 16) {
                            InkPageIndicator.this.postInvalidateOnAnimation();
                        } else {
                            InkPageIndicator.this.postInvalidate();
                        }
                        for (PendingRevealAnimator startIfNecessary : InkPageIndicator.this.revealAnimations) {
                            startIfNecessary.startIfNecessary(InkPageIndicator.this.retreatingJoinX1);
                        }
                    }
                });
            } else {
                setFloatValues(new float[]{f2, i2});
                while (i4 < i3) {
                    i5 = i - i4;
                    InkPageIndicator.this.revealAnimations[i4] = new PendingRevealAnimator(i5, new LeftwardStartPredicate(InkPageIndicator.this.dotCenterX[i5]));
                    iArr[i4] = i5;
                    i4++;
                }
                addUpdateListener(new AnimatorUpdateListener(InkPageIndicator.this) {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        InkPageIndicator.this.retreatingJoinX2 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        if (VERSION.SDK_INT >= 16) {
                            InkPageIndicator.this.postInvalidateOnAnimation();
                        } else {
                            InkPageIndicator.this.postInvalidate();
                        }
                        for (PendingRevealAnimator startIfNecessary : InkPageIndicator.this.revealAnimations) {
                            startIfNecessary.startIfNecessary(InkPageIndicator.this.retreatingJoinX2);
                        }
                    }
                });
            }
            final InkPageIndicator inkPageIndicator = InkPageIndicator.this;
            addListener(new AnimatorListenerAdapter() {
                public void onAnimationStart(Animator animator) {
                    InkPageIndicator.this.clearJoiningFractions();
                    for (int access$1500 : iArr) {
                        InkPageIndicator.this.setDotRevealFraction(access$1500, InkPageIndicator.MINIMAL_REVEAL);
                    }
                    InkPageIndicator.this.retreatingJoinX1 = f;
                    InkPageIndicator.this.retreatingJoinX2 = f2;
                    if (VERSION.SDK_INT >= 16) {
                        InkPageIndicator.this.postInvalidateOnAnimation();
                    } else {
                        InkPageIndicator.this.postInvalidate();
                    }
                }

                public void onAnimationEnd(Animator animator) {
                    InkPageIndicator.this.retreatingJoinX1 = -1.0f;
                    InkPageIndicator.this.retreatingJoinX2 = -1.0f;
                    if (VERSION.SDK_INT >= 16) {
                        InkPageIndicator.this.postInvalidateOnAnimation();
                    } else {
                        InkPageIndicator.this.postInvalidate();
                    }
                }
            });
        }
    }

    public class PendingRevealAnimator extends PendingStartAnimator {
        private int dot;

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public PendingRevealAnimator(int r2, com.jelsat.customclasses.viewpagerindicator.InkPageIndicator.StartPredicate r3) {
            /*
            r0 = this;
            com.jelsat.customclasses.viewpagerindicator.InkPageIndicator.this = r1;
            r0.<init>(r3);
            r3 = 2;
            r3 = new float[r3];
            r3 = {925353388, 1065353216};
            r0.setFloatValues(r3);
            r0.dot = r2;
            r2 = r1.animHalfDuration;
            r0.setDuration(r2);
            r2 = r1.interpolator;
            r0.setInterpolator(r2);
            r2 = new com.jelsat.customclasses.viewpagerindicator.InkPageIndicator$PendingRevealAnimator$1;
            r2.<init>(r1);
            r0.addUpdateListener(r2);
            r2 = new com.jelsat.customclasses.viewpagerindicator.InkPageIndicator$PendingRevealAnimator$2;
            r2.<init>(r1);
            r0.addListener(r2);
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jelsat.customclasses.viewpagerindicator.InkPageIndicator.PendingRevealAnimator.<init>(com.jelsat.customclasses.viewpagerindicator.InkPageIndicator, int, com.jelsat.customclasses.viewpagerindicator.InkPageIndicator$StartPredicate):void");
        }
    }

    public class RightwardStartPredicate extends StartPredicate {
        public RightwardStartPredicate(float f) {
            super(f);
        }

        boolean shouldStart(float f) {
            return f > this.thresholdValue;
        }
    }

    public void onPageScrollStateChanged(int i) {
    }

    public InkPageIndicator(Context context) {
        this(context, null, 0);
    }

    public InkPageIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public InkPageIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.pageCount = 0;
        this.currentPage = 0;
        this.measured = false;
        int i2 = (int) context.getResources().getDisplayMetrics().density;
        attributeSet = getContext().obtainStyledAttributes(attributeSet, R.styleable.InkPageIndicator, i, 0);
        this.dotDiameter = attributeSet.getDimensionPixelSize(2, i2 * 8);
        this.dotRadius = (float) (this.dotDiameter / 2);
        this.halfDotRadius = this.dotRadius / 1073741824;
        this.gap = attributeSet.getDimensionPixelSize(3, i2 * 12);
        this.animDuration = (long) attributeSet.getInteger(0, 400);
        this.animHalfDuration = this.animDuration / 2;
        this.unselectedColour = attributeSet.getColor(4, DEFAULT_UNSELECTED_COLOUR);
        this.selectedColour = attributeSet.getColor(1, -1);
        attributeSet.recycle();
        this.unselectedPaint = new Paint(1);
        this.unselectedPaint.setColor(this.unselectedColour);
        this.selectedPaint = new Paint(1);
        this.selectedPaint.setColor(this.selectedColour);
        this.interpolator = AnimationUtils.getFastOutSlowInInterpolator(context);
        this.combinedUnselectedPath = new Path();
        this.unselectedDotPath = new Path();
        this.unselectedDotLeftPath = new Path();
        this.unselectedDotRightPath = new Path();
        this.rectF = new RectF();
        addOnAttachStateChangeListener(this);
    }

    @ColorInt
    public int getPageIndicatorColor() {
        return this.unselectedPaint.getColor();
    }

    public void setPageIndicatorColor(@ColorInt int i) {
        this.unselectedPaint.setColor(i);
        invalidate();
    }

    @ColorInt
    public int getCurrentPageIndicatorColor() {
        return this.selectedPaint.getColor();
    }

    public void setCurrentPageIndicatorColor(@ColorInt int i) {
        this.selectedPaint.setColor(i);
        invalidate();
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        viewPager.addOnPageChangeListener(this);
        setPageCount(viewPager.getAdapter().getCount());
        viewPager.getAdapter().registerDataSetObserver(new DataSetObserver() {
            public void onChanged() {
                InkPageIndicator.this.setPageCount(InkPageIndicator.this.viewPager.getAdapter().getCount());
                InkPageIndicator.this.invalidate();
            }
        });
    }

    public void onPageScrolled(int i, float f, int i2) {
        if (this.isAttachedToWindow != 0) {
            i2 = this.pageChanging != 0 ? this.previousPage : this.currentPage;
            if (i2 != i) {
                f = 1.0f - f;
                if (f == 1.0f) {
                    i = Math.min(i2, i);
                }
            }
            setJoiningFraction(i, f);
        }
    }

    public void onPageSelected(int i) {
        if (this.isAttachedToWindow) {
            setSelectedPage(i);
        } else {
            setCurrentPageImmediate();
        }
    }

    private void setPageCount(int i) {
        this.pageCount = i;
        calculateDotPositions(getWidth(), getHeight());
        resetState();
        requestLayout();
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        calculateDotPositions(i, i2);
    }

    private void calculateDotPositions(int i, int i2) {
        if (this.measured) {
            int paddingLeft = getPaddingLeft();
            float paddingTop = ((float) getPaddingTop()) + (((float) (i2 - getPaddingBottom())) / 1073741824);
            i2 = ((((float) paddingLeft) + (((float) (i - getPaddingRight())) / 1073741824)) - (((float) getRequiredWidth()) / 1073741824)) + this.dotRadius;
            this.dotCenterX = new float[Math.max(1, this.pageCount)];
            for (i = 0; i < this.pageCount; i++) {
                this.dotCenterX[i] = ((float) ((this.dotDiameter + this.gap) * i)) + i2;
            }
            this.dotTopY = paddingTop - this.dotRadius;
            this.dotCenterY = paddingTop;
            this.dotBottomY = paddingTop + this.dotRadius;
            setCurrentPageImmediate();
        }
    }

    private void setCurrentPageImmediate() {
        if (this.viewPager != null) {
            this.currentPage = this.viewPager.getCurrentItem();
        } else {
            this.currentPage = 0;
        }
        if (this.dotCenterX != null) {
            this.selectedDotX = this.dotCenterX[Math.max(0, Math.min(this.currentPage, this.dotCenterX.length - 1))];
        }
    }

    private void resetState() {
        this.joiningFractions = new float[Math.max(this.pageCount - 1, 0)];
        Arrays.fill(this.joiningFractions, 0.0f);
        this.dotRevealFractions = new float[this.pageCount];
        Arrays.fill(this.dotRevealFractions, 0.0f);
        this.retreatingJoinX1 = -1.0f;
        this.retreatingJoinX2 = -1.0f;
        this.selectedDotInPosition = true;
    }

    protected void onMeasure(int i, int i2) {
        int desiredHeight = getDesiredHeight();
        int mode = MeasureSpec.getMode(i2);
        if (mode == Integer.MIN_VALUE) {
            desiredHeight = Math.min(desiredHeight, MeasureSpec.getSize(i2));
        } else if (mode == 1073741824) {
            desiredHeight = MeasureSpec.getSize(i2);
        }
        i2 = getDesiredWidth();
        mode = MeasureSpec.getMode(i);
        if (mode == Integer.MIN_VALUE) {
            i2 = Math.min(i2, MeasureSpec.getSize(i));
        } else if (mode == 1073741824) {
            i2 = MeasureSpec.getSize(i);
        }
        setMeasuredDimension(i2, desiredHeight);
        if (this.measured == 0) {
            this.measured = true;
        }
    }

    private int getDesiredHeight() {
        return (getPaddingTop() + this.dotDiameter) + getPaddingBottom();
    }

    private int getRequiredWidth() {
        return (this.pageCount * this.dotDiameter) + ((this.pageCount - 1) * this.gap);
    }

    private int getDesiredWidth() {
        return (getPaddingLeft() + getRequiredWidth()) + getPaddingRight();
    }

    public void onViewAttachedToWindow(View view) {
        this.isAttachedToWindow = true;
    }

    public void onViewDetachedFromWindow(View view) {
        this.isAttachedToWindow = null;
    }

    protected void onDraw(Canvas canvas) {
        if (this.viewPager != null) {
            if (this.pageCount != 0) {
                drawUnselected(canvas);
                drawSelected(canvas);
            }
        }
    }

    private void drawUnselected(Canvas canvas) {
        this.combinedUnselectedPath.rewind();
        int i = 0;
        while (i < this.pageCount) {
            Path unselectedPath = getUnselectedPath(i, this.dotCenterX[i], this.dotCenterX[i == this.pageCount + -1 ? i : i + 1], i == this.pageCount + -1 ? -1.0f : this.joiningFractions[i], this.dotRevealFractions[i]);
            if (VERSION.SDK_INT >= 19) {
                this.combinedUnselectedPath.op(unselectedPath, Op.UNION);
            } else {
                this.combinedUnselectedPath.addPath(unselectedPath);
            }
            i++;
        }
        if (this.retreatingJoinX1 != -1.0f) {
            Path retreatingJoinPath = getRetreatingJoinPath();
            if (VERSION.SDK_INT >= 19) {
                this.combinedUnselectedPath.op(retreatingJoinPath, Op.UNION);
            } else {
                this.combinedUnselectedPath.addPath(retreatingJoinPath);
            }
        }
        canvas.drawPath(this.combinedUnselectedPath, this.unselectedPaint);
    }

    private Path getUnselectedPath(int i, float f, float f2, float f3, float f4) {
        int i2 = i;
        float f5 = f;
        float f6 = f2;
        this.unselectedDotPath.rewind();
        if ((f3 == 0.0f || f3 == -1.0f) && f4 == 0.0f && !(i2 == r0.currentPage && r0.selectedDotInPosition)) {
            r0.unselectedDotPath.addCircle(r0.dotCenterX[i2], r0.dotCenterY, r0.dotRadius, Direction.CW);
        }
        if (f3 > 0.0f && f3 <= 0.5f && r0.retreatingJoinX1 == -1.0f) {
            r0.unselectedDotLeftPath.rewind();
            r0.unselectedDotLeftPath.moveTo(f5, r0.dotBottomY);
            r0.rectF.set(f5 - r0.dotRadius, r0.dotTopY, r0.dotRadius + f5, r0.dotBottomY);
            r0.unselectedDotLeftPath.arcTo(r0.rectF, 90.0f, 180.0f, true);
            r0.endX1 = (r0.dotRadius + f5) + (((float) r0.gap) * f3);
            r0.endY1 = r0.dotCenterY;
            r0.controlX1 = r0.halfDotRadius + f5;
            r0.controlY1 = r0.dotTopY;
            r0.controlX2 = r0.endX1;
            r0.controlY2 = r0.endY1 - r0.halfDotRadius;
            r0.unselectedDotLeftPath.cubicTo(r0.controlX1, r0.controlY1, r0.controlX2, r0.controlY2, r0.endX1, r0.endY1);
            r0.endX2 = f5;
            r0.endY2 = r0.dotBottomY;
            r0.controlX1 = r0.endX1;
            r0.controlY1 = r0.endY1 + r0.halfDotRadius;
            r0.controlX2 = r0.halfDotRadius + f5;
            r0.controlY2 = r0.dotBottomY;
            r0.unselectedDotLeftPath.cubicTo(r0.controlX1, r0.controlY1, r0.controlX2, r0.controlY2, r0.endX2, r0.endY2);
            if (VERSION.SDK_INT >= 19) {
                r0.unselectedDotPath.op(r0.unselectedDotLeftPath, Op.UNION);
            } else {
                r0.unselectedDotPath.addPath(r0.unselectedDotLeftPath);
            }
            r0.unselectedDotRightPath.rewind();
            r0.unselectedDotRightPath.moveTo(f6, r0.dotBottomY);
            r0.rectF.set(f6 - r0.dotRadius, r0.dotTopY, r0.dotRadius + f6, r0.dotBottomY);
            r0.unselectedDotRightPath.arcTo(r0.rectF, 90.0f, -180.0f, true);
            r0.endX1 = (f6 - r0.dotRadius) - (((float) r0.gap) * f3);
            r0.endY1 = r0.dotCenterY;
            r0.controlX1 = f6 - r0.halfDotRadius;
            r0.controlY1 = r0.dotTopY;
            r0.controlX2 = r0.endX1;
            r0.controlY2 = r0.endY1 - r0.halfDotRadius;
            r0.unselectedDotRightPath.cubicTo(r0.controlX1, r0.controlY1, r0.controlX2, r0.controlY2, r0.endX1, r0.endY1);
            r0.endX2 = f6;
            r0.endY2 = r0.dotBottomY;
            r0.controlX1 = r0.endX1;
            r0.controlY1 = r0.endY1 + r0.halfDotRadius;
            r0.controlX2 = r0.endX2 - r0.halfDotRadius;
            r0.controlY2 = r0.dotBottomY;
            r0.unselectedDotRightPath.cubicTo(r0.controlX1, r0.controlY1, r0.controlX2, r0.controlY2, r0.endX2, r0.endY2);
            if (VERSION.SDK_INT >= 19) {
                r0.unselectedDotPath.op(r0.unselectedDotRightPath, Op.UNION);
            } else {
                r0.unselectedDotPath.addPath(r0.unselectedDotRightPath);
            }
        }
        if (f3 > 0.5f && f3 < 1.0f && r0.retreatingJoinX1 == -1.0f) {
            float f7 = (f3 - 0.2f) * 1.25f;
            r0.unselectedDotPath.moveTo(f5, r0.dotBottomY);
            r0.rectF.set(f5 - r0.dotRadius, r0.dotTopY, r0.dotRadius + f5, r0.dotBottomY);
            r0.unselectedDotPath.arcTo(r0.rectF, 90.0f, 180.0f, true);
            r0.endX1 = (r0.dotRadius + f5) + ((float) (r0.gap / 2));
            r0.endY1 = r0.dotCenterY - (r0.dotRadius * f7);
            r0.controlX1 = r0.endX1 - (r0.dotRadius * f7);
            r0.controlY1 = r0.dotTopY;
            float f8 = 1.0f - f7;
            r0.controlX2 = r0.endX1 - (r0.dotRadius * f8);
            r0.controlY2 = r0.endY1;
            r0.unselectedDotPath.cubicTo(r0.controlX1, r0.controlY1, r0.controlX2, r0.controlY2, r0.endX1, r0.endY1);
            r0.endX2 = f6;
            r0.endY2 = r0.dotTopY;
            r0.controlX1 = r0.endX1 + (r0.dotRadius * f8);
            r0.controlY1 = r0.endY1;
            r0.controlX2 = r0.endX1 + (r0.dotRadius * f7);
            r0.controlY2 = r0.dotTopY;
            r0.unselectedDotPath.cubicTo(r0.controlX1, r0.controlY1, r0.controlX2, r0.controlY2, r0.endX2, r0.endY2);
            r0.rectF.set(f6 - r0.dotRadius, r0.dotTopY, r0.dotRadius + f6, r0.dotBottomY);
            r0.unselectedDotPath.arcTo(r0.rectF, 270.0f, 180.0f, true);
            r0.endY1 = r0.dotCenterY + (r0.dotRadius * f7);
            r0.controlX1 = r0.endX1 + (r0.dotRadius * f7);
            r0.controlY1 = r0.dotBottomY;
            r0.controlX2 = r0.endX1 + (r0.dotRadius * f8);
            r0.controlY2 = r0.endY1;
            r0.unselectedDotPath.cubicTo(r0.controlX1, r0.controlY1, r0.controlX2, r0.controlY2, r0.endX1, r0.endY1);
            r0.endX2 = f5;
            r0.endY2 = r0.dotBottomY;
            r0.controlX1 = r0.endX1 - (f8 * r0.dotRadius);
            r0.controlY1 = r0.endY1;
            r0.controlX2 = r0.endX1 - (f7 * r0.dotRadius);
            r0.controlY2 = r0.endY2;
            r0.unselectedDotPath.cubicTo(r0.controlX1, r0.controlY1, r0.controlX2, r0.controlY2, r0.endX2, r0.endY2);
        }
        if (f3 == 1.0f && r0.retreatingJoinX1 == -1.0f) {
            r0.rectF.set(f5 - r0.dotRadius, r0.dotTopY, f6 + r0.dotRadius, r0.dotBottomY);
            r0.unselectedDotPath.addRoundRect(r0.rectF, r0.dotRadius, r0.dotRadius, Direction.CW);
        }
        if (f4 > MINIMAL_REVEAL) {
            r0.unselectedDotPath.addCircle(f5, r0.dotCenterY, r0.dotRadius * f4, Direction.CW);
        }
        return r0.unselectedDotPath;
    }

    private Path getRetreatingJoinPath() {
        this.unselectedDotPath.rewind();
        this.rectF.set(this.retreatingJoinX1, this.dotTopY, this.retreatingJoinX2, this.dotBottomY);
        this.unselectedDotPath.addRoundRect(this.rectF, this.dotRadius, this.dotRadius, Direction.CW);
        return this.unselectedDotPath;
    }

    private void drawSelected(Canvas canvas) {
        canvas.drawCircle(this.selectedDotX, this.dotCenterY, this.dotRadius, this.selectedPaint);
    }

    private void setSelectedPage(int i) {
        i = Math.min(i, this.pageCount - 1);
        if (i != this.currentPage) {
            this.pageChanging = true;
            this.previousPage = this.currentPage;
            this.currentPage = i;
            int abs = Math.abs(i - this.previousPage);
            if (abs > 1) {
                int i2;
                if (i > this.previousPage) {
                    for (i2 = 0; i2 < abs; i2++) {
                        setJoiningFraction(this.previousPage + i2, 1.0f);
                    }
                } else {
                    for (i2 = -1; i2 > (-abs); i2--) {
                        setJoiningFraction(this.previousPage + i2, 1.0f);
                    }
                }
            }
            if (getVisibility() == 0) {
                createMoveSelectedAnimator(this.dotCenterX[i], this.previousPage, i, abs).start();
            }
        }
    }

    private ValueAnimator createMoveSelectedAnimator(float f, int i, int i2, int i3) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{this.selectedDotX, f});
        this.retreatAnimation = new PendingRetreatAnimator(i, i2, i3, i2 > i ? new RightwardStartPredicate(f - ((f - this.selectedDotX) * 0.25f)) : new LeftwardStartPredicate(f + ((this.selectedDotX - f) * 0.25f)));
        this.retreatAnimation.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                InkPageIndicator.this.resetState();
                InkPageIndicator.this.pageChanging = false;
            }
        });
        ofFloat.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                InkPageIndicator.this.selectedDotX = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                InkPageIndicator.this.retreatAnimation.startIfNecessary(InkPageIndicator.this.selectedDotX);
                if (VERSION.SDK_INT >= 16) {
                    InkPageIndicator.this.postInvalidateOnAnimation();
                } else {
                    InkPageIndicator.this.postInvalidate();
                }
            }
        });
        ofFloat.addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                InkPageIndicator.this.selectedDotInPosition = false;
            }

            public void onAnimationEnd(Animator animator) {
                InkPageIndicator.this.selectedDotInPosition = true;
            }
        });
        ofFloat.setStartDelay(this.selectedDotInPosition != null ? this.animDuration / 4 : 0);
        ofFloat.setDuration((this.animDuration * 3) / 4);
        ofFloat.setInterpolator(this.interpolator);
        return ofFloat;
    }

    private void setJoiningFraction(int i, float f) {
        if (i < this.joiningFractions.length) {
            this.joiningFractions[i] = f;
            if (VERSION.SDK_INT >= 16) {
                postInvalidateOnAnimation();
                return;
            }
            postInvalidate();
        }
    }

    private void clearJoiningFractions() {
        Arrays.fill(this.joiningFractions, 0.0f);
        if (VERSION.SDK_INT >= 16) {
            postInvalidateOnAnimation();
        } else {
            postInvalidate();
        }
    }

    private void setDotRevealFraction(int i, float f) {
        this.dotRevealFractions[i] = f;
        if (VERSION.SDK_INT >= 16) {
            postInvalidateOnAnimation();
        } else {
            postInvalidate();
        }
    }
}
