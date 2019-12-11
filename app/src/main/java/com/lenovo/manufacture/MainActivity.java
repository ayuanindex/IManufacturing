package com.lenovo.manufacture;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.lenovo.manufacture.activity.BannerActivity;

/**
 * @author Amoly
 * @date 2019/10/24.
 * GitHub：
 * email：
 * description：
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, BannerActivity.class);
        startActivity(intent);
        setContentView(R.layout.activity_main);
//        initView();
        initWebView();
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void initWebView() {

        TWebView webView = new TWebView(this, null);
        ViewGroup viewParent = findViewById(R.id.webView1);
        viewParent.addView(webView, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));

        //载入网址
        webView.loadUrl("file:///android_asset/index.html");
        //设置Web View Client
        webView.setWebViewClient(new WebViewClient() {
            /**
             * 应该覆盖网址加载
             * @param view
             * @param url
             * @return
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            /**
             * 在页面上完成
             * @param view
             * @param url
             */
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                /* mWebView.showLog("test Log"); */
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return true;
            }
        });

        //设置启用Java脚本
        webView.getSettings().setJavaScriptEnabled(true);
        //设置Java脚本可以自动打开Windows
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        //设置背景色
        webView.setBackgroundColor(0);
        //请求重点
        webView.requestFocus();
        //添加Javascript接口
        webView.addJavascriptInterface(new JavaScriptInterface(this), "nativeMethod");
        WebSettings webSetting = webView.getSettings();
        //设置允许文件访问
        webSetting.setAllowFileAccess(true);
        //设置布局算法
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        //设置支持缩放
        webSetting.setSupportZoom(true);
        //设置内置缩放控件
        webSetting.setBuiltInZoomControls(false);
        //设置使用广角端口
        webSetting.setUseWideViewPort(true);
        //设置支持多个Windows
        webSetting.setSupportMultipleWindows(false);
        //设置启用应用缓存
        webSetting.setAppCacheEnabled(true);
        //设置Dom存储已启用
        webSetting.setDomStorageEnabled(true);
        //设置默认文本编码名称
        webSetting.setDefaultTextEncodingName("utf-8");
        //设置应用缓存最大大小
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);


        int screenDensity = getResources().getDisplayMetrics().densityDpi;
        WebSettings.ZoomDensity zoomDensity = WebSettings.ZoomDensity.MEDIUM;
        switch (screenDensity) {
            case DisplayMetrics.DENSITY_LOW:
                zoomDensity = WebSettings.ZoomDensity.CLOSE;
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                zoomDensity = WebSettings.ZoomDensity.MEDIUM;
                break;
            case DisplayMetrics.DENSITY_HIGH:
                zoomDensity = WebSettings.ZoomDensity.FAR;
                break;
        }
        webSetting.setDefaultZoom(zoomDensity);
    }


    public class JavaScriptInterface {
        Activity mActivity;

        JavaScriptInterface(Activity mActivity) {
            this.mActivity = mActivity;
        }

        /**
         * 与js交互时用到的方法，在js里直接调用的
         */
        @JavascriptInterface
        public void startActivity() {
            Intent intent = new Intent();
//            intent.putExtra("fromWhich", "webViewUrl");
//            intent.putExtra("replyID", "replyID");
            intent.setClass(mActivity, TestActivity.class);
            mActivity.startActivity(intent);
        }

    }

}
