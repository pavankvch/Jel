package com.jelsat.activities;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.OnClick;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;

public class WebViewActivity extends BaseAppCompactActivity {
    private float m_downX;
    @BindView(2131362437)
    public ProgressBar progressBar;
    private String url;
    @BindView(2131362838)
    public WebView webView;

    class MyWebChromeClient extends WebChromeClient {
        Context context;

        public MyWebChromeClient(Context context) {
            this.context = context;
        }
    }

    public int getActivityLayout() {
        return R.layout.activity_webview;
    }

    @OnClick({2131361892})
    public void clickOnBack() {
        finish();
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.url = getIntent().getStringExtra(StringConstants.TERMS_URL);
        if (TextUtils.isEmpty(this.url) != null) {
            finish();
        }
        initWebView();
        this.webView.loadUrl(this.url);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void initWebView() {
        this.webView.setWebChromeClient(new MyWebChromeClient(this));
        this.webView.setWebViewClient(new WebViewClient() {
            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                super.onPageStarted(webView, str, bitmap);
                if (WebViewActivity.this.progressBar != null) {
                    WebViewActivity.this.progressBar.setVisibility(null);
                }
                WebViewActivity.this.invalidateOptionsMenu();
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                webView.loadUrl(str);
                return true;
            }

            @TargetApi(24)
            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                webView.loadUrl(webResourceRequest.getUrl().toString());
                return true;
            }

            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                if (WebViewActivity.this.progressBar != null) {
                    WebViewActivity.this.progressBar.setVisibility(8);
                }
            }

            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                super.onReceivedError(webView, webResourceRequest, webResourceError);
                if (WebViewActivity.this.progressBar != null) {
                    WebViewActivity.this.progressBar.setVisibility(8);
                }
            }
        });
        this.webView.clearCache(true);
        this.webView.clearHistory();
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.setHorizontalScrollBarEnabled(false);
        this.webView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getPointerCount() > 1) {
                    return true;
                }
                switch (motionEvent.getAction()) {
                    case null:
                        WebViewActivity.this.m_downX = motionEvent.getX();
                        break;
                    case 1:
                    case 2:
                    case 3:
                        motionEvent.setLocation(WebViewActivity.this.m_downX, motionEvent.getY());
                        break;
                    default:
                        break;
                }
                return null;
            }
        });
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || !this.webView.canGoBack()) {
            return super.onKeyDown(i, keyEvent);
        }
        this.webView.goBack();
        return true;
    }
}
