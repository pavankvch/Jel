package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class WebViewActivity_ViewBinding implements Unbinder {
    private WebViewActivity target;
    private View view2131361892;

    @UiThread
    public WebViewActivity_ViewBinding(WebViewActivity webViewActivity) {
        this(webViewActivity, webViewActivity.getWindow().getDecorView());
    }

    @UiThread
    public WebViewActivity_ViewBinding(final WebViewActivity webViewActivity, View view) {
        this.target = webViewActivity;
        webViewActivity.webView = (WebView) Utils.findRequiredViewAsType(view, R.id.webView, "field 'webView'", WebView.class);
        webViewActivity.progressBar = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.progressBar, "field 'progressBar'", ProgressBar.class);
        view = Utils.findRequiredView(view, R.id.back_arrow_IV, "method 'clickOnBack'");
        this.view2131361892 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                webViewActivity.clickOnBack();
            }
        });
    }

    @CallSuper
    public void unbind() {
        WebViewActivity webViewActivity = this.target;
        if (webViewActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        webViewActivity.webView = null;
        webViewActivity.progressBar = null;
        this.view2131361892.setOnClickListener(null);
        this.view2131361892 = null;
    }
}
