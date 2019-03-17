package com.jelsat.utils;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.graphics.drawable.AndroidResources;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import com.jelsat.R;

public class AnimationUtils {
    private static Interpolator fastOutSlowIn;

    public interface AnimationListener {
        void onFinish();
    }

    private AnimationUtils() {
    }

    public static void slideToUp(View view) {
        Animation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
        translateAnimation.setDuration(400);
        translateAnimation.setFillAfter(true);
        translateAnimation.setFillEnabled(true);
        view.startAnimation(translateAnimation);
        translateAnimation.setAnimationListener(new android.view.animation.Animation.AnimationListener() {
            public final void onAnimationEnd(Animation animation) {
            }

            public final void onAnimationRepeat(Animation animation) {
            }

            public final void onAnimationStart(Animation animation) {
            }
        });
    }

    public static void slideToDown(View view, final AnimationListener animationListener) {
        Animation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
        translateAnimation.setDuration(400);
        translateAnimation.setFillAfter(true);
        translateAnimation.setFillEnabled(true);
        view.startAnimation(translateAnimation);
        translateAnimation.setAnimationListener(new android.view.animation.Animation.AnimationListener() {
            public final void onAnimationRepeat(Animation animation) {
            }

            public final void onAnimationStart(Animation animation) {
            }

            public final void onAnimationEnd(Animation animation) {
                if (animationListener != null) {
                    animationListener.onFinish();
                }
            }
        });
    }

    public static Interpolator getFastOutSlowInInterpolator(Context context) {
        if (fastOutSlowIn == null) {
            if (VERSION.SDK_INT >= 21) {
                fastOutSlowIn = android.view.animation.AnimationUtils.loadInterpolator(context, AndroidResources.FAST_OUT_SLOW_IN);
            } else {
                fastOutSlowIn = new FastOutSlowInInterpolator();
            }
        }
        return fastOutSlowIn;
    }

    public static void applyShakeAnimation(Context context, View view) {
        view.startAnimation(android.view.animation.AnimationUtils.loadAnimation(context, R.anim.mi_shake));
    }
}
