package com.lenovo.manufacture;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

/**
 * @author Amoly
 * @date 2019/10/24.
 * GitHub：
 * email：
 * description：
 */
public class TestActivity extends BaseActivity {
    private static final String TAG = "TestActivity";
    private FrameLayout fragmentLayout;
    private TWebView webView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
    }

    private void initView() {
        fragmentLayout = (FrameLayout) findViewById(R.id.fragmentLayout);
        webView = new TWebView(this);

        initWebView(webView, fragmentLayout, "file:///android_asset/index.html");
    }

    @SuppressLint("JavascriptInterface")
    @Override
    public void addJavaScriptInterface() {
        String localClassName = getLocalClassName();
        Log.i(TAG, "哈哈哈：" + localClassName);
        webView.addJavascriptInterface(new TestJavaScript(TestActivity.this), localClassName);
    }

    private class TestJavaScript {
        private Activity activity;

        public TestJavaScript(Activity activity) {
            this.activity = activity;
        }

        public void onBack() {
            finish();
        }
    }
}
