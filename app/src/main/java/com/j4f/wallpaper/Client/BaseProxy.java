package com.j4f.wallpaper.Client;

import android.app.Activity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.j4f.wallpaper.Helpers.Commons.Common;
import com.j4f.wallpaper.Interfaces.AsyncTaskCompleteListener;
import com.j4f.wallpaper.R;

import org.json.JSONObject;

/**
 * Created by pham on 7/13/2015.
 */
public class BaseProxy {
    private AsyncTaskCompleteListener<JSONObject> result = null;

    public void callApi(final Activity activity, String url, final AsyncTaskCompleteListener<JSONObject> result) {
        this.result = result;
       // Common.showDialog(activity);
        RequestQueue queue = Volley.newRequestQueue(activity.getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                result.onTaskCompelete(response);
                Common.dismissDialog(activity);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                result.onError(0);
                Common.dismissDialog(activity);
                Common.showToast(activity, activity.getResources().getString(R.string.NoData));
            }
        });
        int socketTimeout = 10000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjectRequest.setRetryPolicy(policy);
        queue.add(jsonObjectRequest);
    }
}
