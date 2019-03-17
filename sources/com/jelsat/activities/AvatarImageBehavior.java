package com.jelsat.activities;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CoordinatorLayout.Behavior;
import android.support.design.widget.CoordinatorLayout.LayoutParams;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import com.jelsat.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class AvatarImageBehavior extends Behavior<CircleImageView> {
    private static final int EXTRA_FINAL_AVATAR_PADDING = 80;
    private static final float MIN_AVATAR_PERCENTAGE_SIZE = 0.3f;
    private static final String TAG = "behavior";
    private float mAvatarMaxSize;
    private float mChangeBehaviorPoint;
    private Context mContext;
    private float mCustomFinalHeight;
    private float mCustomFinalYPosition;
    private float mCustomStartHeight;
    private float mCustomStartToolbarPosition;
    private float mCustomStartXPosition;
    private float mFinalLeftAvatarPadding;
    private int mFinalXPosition;
    private int mFinalYPosition;
    private int mStartHeight;
    private float mStartPosition;
    private float mStartToolbarPosition;
    private int mStartXPosition;
    private int mStartYPosition;

    public AvatarImageBehavior(Context context, AttributeSet attributeSet) {
        this.mContext = context;
        if (attributeSet != null) {
            attributeSet = context.obtainStyledAttributes(attributeSet, R.styleable.AvatarImageBehavior);
            this.mCustomFinalYPosition = attributeSet.getDimension(1, 0.0f);
            this.mCustomStartXPosition = attributeSet.getDimension(4, 0.0f);
            this.mCustomStartToolbarPosition = attributeSet.getDimension(3, 0.0f);
            this.mCustomStartHeight = attributeSet.getDimension(2, 0.0f);
            this.mCustomFinalHeight = attributeSet.getDimension(0, 0.0f);
            attributeSet.recycle();
        }
        init();
        this.mFinalLeftAvatarPadding = context.getResources().getDimension(R.dimen.spacing_normal);
    }

    private void init() {
        bindDimensions();
    }

    private void bindDimensions() {
        this.mAvatarMaxSize = this.mContext.getResources().getDimension(R.dimen.image_width);
    }

    public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, CircleImageView circleImageView, View view) {
        return view instanceof Toolbar;
    }

    public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, CircleImageView circleImageView, View view) {
        maybeInitProperties(circleImageView, view);
        view = view.getY() / ((float) ((int) this.mStartToolbarPosition));
        LayoutParams layoutParams;
        if (view < this.mChangeBehaviorPoint) {
            coordinatorLayout = (this.mChangeBehaviorPoint - view) / this.mChangeBehaviorPoint;
            float height = (((float) (this.mStartYPosition - this.mFinalYPosition)) * (1.0f - view)) + ((float) (circleImageView.getHeight() / 2));
            circleImageView.setX(((float) this.mStartXPosition) - ((((float) (this.mStartXPosition - this.mFinalXPosition)) * coordinatorLayout) + ((float) (circleImageView.getHeight() / 2))));
            circleImageView.setY(((float) this.mStartYPosition) - height);
            view = (((float) this.mStartHeight) - this.mCustomFinalHeight) * coordinatorLayout;
            layoutParams = (LayoutParams) circleImageView.getLayoutParams();
            layoutParams.width = (int) (((float) this.mStartHeight) - view);
            layoutParams.height = (int) (((float) this.mStartHeight) - view);
            circleImageView.setLayoutParams(layoutParams);
        } else {
            coordinatorLayout = (((float) (this.mStartYPosition - this.mFinalYPosition)) * (1.0f - view)) + ((float) (this.mStartHeight / 2));
            circleImageView.setX((float) (this.mStartXPosition - (circleImageView.getWidth() / 2)));
            circleImageView.setY(((float) this.mStartYPosition) - coordinatorLayout);
            layoutParams = (LayoutParams) circleImageView.getLayoutParams();
            layoutParams.width = this.mStartHeight;
            layoutParams.height = this.mStartHeight;
            circleImageView.setLayoutParams(layoutParams);
        }
        return true;
    }

    private void maybeInitProperties(CircleImageView circleImageView, View view) {
        if (this.mStartYPosition == 0) {
            this.mStartYPosition = (int) view.getY();
        }
        if (this.mFinalYPosition == 0) {
            this.mFinalYPosition = view.getHeight() / 2;
        }
        if (this.mStartHeight == 0) {
            this.mStartHeight = circleImageView.getHeight();
        }
        if (this.mStartXPosition == 0) {
            this.mStartXPosition = (int) (circleImageView.getX() + ((float) (circleImageView.getWidth() / 2)));
        }
        if (this.mFinalXPosition == 0) {
            this.mFinalXPosition = this.mContext.getResources().getDimensionPixelOffset(R.dimen.abc_action_bar_content_inset_material) + (((int) this.mCustomFinalHeight) / 2);
        }
        if (this.mStartToolbarPosition == 0.0f) {
            this.mStartToolbarPosition = view.getY();
        }
        if (this.mChangeBehaviorPoint == null) {
            this.mChangeBehaviorPoint = (((float) circleImageView.getHeight()) - this.mCustomFinalHeight) / (((float) (this.mStartYPosition - this.mFinalYPosition)) * 2.0f);
        }
    }

    public int getStatusBarHeight() {
        int identifier = this.mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return identifier > 0 ? this.mContext.getResources().getDimensionPixelSize(identifier) : 0;
    }
}
