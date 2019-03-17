package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.widgets.FancyButton;

public class SplashScreenActivity_ViewBinding implements Unbinder {
    private SplashScreenActivity target;
    private View view2131362517;

    @UiThread
    public SplashScreenActivity_ViewBinding(SplashScreenActivity splashScreenActivity) {
        this(splashScreenActivity, splashScreenActivity.getWindow().getDecorView());
    }

    @UiThread
    public SplashScreenActivity_ViewBinding(final SplashScreenActivity splashScreenActivity, View view) {
        this.target = splashScreenActivity;
        splashScreenActivity.progressBar = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.progressBar, "field 'progressBar'", ProgressBar.class);
        view = Utils.findRequiredView(view, R.id.retry_button, "field 'retryButton' and method 'clickOnRetryButton'");
        splashScreenActivity.retryButton = (FancyButton) Utils.castView(view, R.id.retry_button, "field 'retryButton'", FancyButton.class);
        this.view2131362517 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                splashScreenActivity.clickOnRetryButton();
            }
        });
    }

    @CallSuper
    public void unbind() {
        SplashScreenActivity splashScreenActivity = this.target;
        if (splashScreenActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        splashScreenActivity.progressBar = null;
        splashScreenActivity.retryButton = null;
        this.view2131362517.setOnClickListener(null);
        this.view2131362517 = null;
    }
}
