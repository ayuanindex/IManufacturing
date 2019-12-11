package com.lenovo.manufacture;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;

import static com.android.volley.Request.Method.POST;


public class HttpUtil {
    public static void doPost(String url, HashMap<Object, Object> map, Response.Listener<JSONObject> success) {
        new JsonObjectRequest(POST, url, new JSONObject(map), success, new Response.ErrorListener() {
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
}
