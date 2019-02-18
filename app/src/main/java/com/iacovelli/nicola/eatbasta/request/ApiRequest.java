package com.iacovelli.nicola.eatbasta.request;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class ApiRequest {

    private static ApiRequest instance;
    private RequestQueue queue;
    private String baseUrl = "https://api-eat-basta.herokuapp.com/";

    private ApiRequest(Context c) {
        queue = Volley.newRequestQueue(c);
    }

    public static synchronized ApiRequest getInstance(Context c) {
        if (instance == null) {
            instance = new ApiRequest(c);

        }
        return instance;
    }

    public void request(int method, String url, Response.Listener<String> onSuccess, Response.ErrorListener onError) {
        StringRequest stringRequest = new StringRequest(method, baseUrl + url, onSuccess, onError);
        queue.add(stringRequest);
    }

}
