package com.lenovo.manufacture;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.google.gson.JsonArray;
import com.lenovo.manufacture.bean.SalesReportBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

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
    private ArrayList<SalesReportBean> salesReportBeans;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
    }

    private void initView() {
        fragmentLayout = (FrameLayout) findViewById(R.id.fragmentLayout);
        webView = new TWebView(this);
        initWebView(webView, fragmentLayout, "file:///android_asset/one/SalesReport.html");
    }

    @SuppressLint("JavascriptInterface")
    @Override
    public void addJavaScriptInterface() {
        String localClassName = getLocalClassName();
        TestJavaScript obj = new TestJavaScript(TestActivity.this);
        obj.getDatas();
        webView.addJavascriptInterface(obj, localClassName);
    }

    private class TestJavaScript {
        private Activity activity;

        public TestJavaScript(Activity activity) {
            this.activity = activity;
        }

        /**
         * 模拟生成数据
         *
         * @return 返回转换成数组的集合
         */
        @JavascriptInterface
        public String[] getDatas() {
            salesReportBeans = new ArrayList<>();
            //生成对象集合
            for (int i = 1; i <= 10; i++) {
                salesReportBeans.add(new SalesReportBean(i + "哈哈汽车", Math.pow(i * 100, 2), new Date().toLocaleString(), i));
            }
            /*Utils.sort(salesReportBeans, new Utils.Com<SalesReportBean>() {
                @Override
                public int compare(SalesReportBean value1, SalesReportBean value2) {
                    return (int) (value2.getPrice() - value1.getPrice());
                }
            });玄学排序*/
            String[] saleReports = new String[salesReportBeans.size()];
            for (int i = 0; i < salesReportBeans.size(); i++) {
                saleReports[i] = salesReportBeans.get(i).toString();
                Log.i(TAG, "哈哈哈：" + salesReportBeans.get(i).toString());
            }
            return saleReports;
        }

        @JavascriptInterface
        public void onBack() {
            finish();
        }

        @JavascriptInterface
        public void printLog(String msg) {
            Log.i(TAG, "呵呵呵：" + msg);
        }
    }
}
