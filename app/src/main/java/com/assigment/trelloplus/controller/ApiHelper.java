package com.assigment.trelloplus.controller;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Juli on 28.09.2015.
 * Singleton for access volley netwofking methods
 */
public class ApiHelper {
    private static ApiHelper mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    private ApiHelper(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();


    }

    public static synchronized ApiHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new ApiHelper(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        HttpsTrustManager.allowAllSSL();
        getRequestQueue().add(req);
    }


}


