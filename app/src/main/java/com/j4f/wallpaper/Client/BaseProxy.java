package com.j4f.wallpaper.Client;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.j4f.wallpaper.Interfaces.AsyncTaskCompleteListener;

import org.json.JSONObject;

/**
 * Created by pham on 7/13/2015.
 */
public class BaseProxy {
    private AsyncTaskCompleteListener<JSONObject> result = null;
    public void callApi(String url,Context context, final AsyncTaskCompleteListener<JSONObject> result){
        this.result=result;
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                result.onTaskCompelete(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error callAPI:", error.toString());
            }
        });
        queue.add(jsonObjectRequest);
    }
}
