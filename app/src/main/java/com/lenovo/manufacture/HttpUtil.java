package com.lenovo.manufacture;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import static com.android.volley.Request.Method.POST;

public class HttpUtil {
    private static final String TAG = "HttpUtil";

    /**
     * volley请求
     *
     * @param url
     * @param map
     * @param success
     */
    public static void doPost(String url, HashMap<Object, Object> map, Response.Listener<JSONObject> success) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(POST, url, new JSONObject(map), success, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

    }

    public static String getGeneralUrl(String url) {
        return "http://10.119.183.241:8085/dataInterface/" + url;
    }

    public static String getSpecialUrl(String url) {
        return "http://10.119.183.241:8085/Interface/" + url;
    }

    /**
     * okHttp请求
     *
     * @param url
     * @param key
     * @param value
     */
    public static void doPost(String url, String[] key, String[] value) {
        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .connectTimeout(1000, TimeUnit.SECONDS)
                .readTimeout(2000, TimeUnit.SECONDS)
                .build();

        FormBody.Builder builder = new FormBody.Builder();
        for (int i = 0; i < key.length; i++) {
            builder.add(key[i], value[i]);
        }
        FormBody formBody = builder.build();

        Request.Builder builder2 = new Request.Builder().post(formBody).url(getGeneralUrl(url));
        Call call = okHttpClient.newCall(builder2.build());
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: 网络请求发生错误" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                String jsonString = response.body().string();
                //通过gsonformat生成承载数据的javaBean
                //通过Gson解析数据放进承载的JavaBean中
                //代码示例：T t = new Gson.fromJson(jsonString,T.class);
            }
        });
    }
}
