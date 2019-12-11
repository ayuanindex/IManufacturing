package com.lenovo.manufacture.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.lenovo.manufacture.BaseActivity;
import com.lenovo.manufacture.R;
import com.lenovo.manufacture.TWebView;

public class BannerActivity extends BaseActivity {
    private FrameLayout fragmentLayout;
    private TWebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        initView();
    }

    private void initView() {
        fragmentLayout = (FrameLayout) findViewById(R.id.fragmentLayout);
        webView = new TWebView(this);
        initWebView(webView, fragmentLayout, "file:///android_asset/banner/banner.html");
        webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
    }

    @SuppressLint("JavascriptInterface")
    @Override
    public void addJavaScriptInterface() {
        webView.addJavascriptInterface(this, getLocalClassName());
    }

    private class BannerJavaScript {
        private Activity activity;

        public BannerJavaScript(Activity activity) {
            this.activity = activity;
        }

        public void jumpHome() {
            finish();
        }
    }
}
