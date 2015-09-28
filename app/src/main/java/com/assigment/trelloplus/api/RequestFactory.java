package com.assigment.trelloplus.api;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Juli on 28.09.2015.
 */
public class RequestFactory {
    private static String KEY = "key";
    private static String API_TAG = "volley";
    private static String RESPONSE_TYPE = "response_type";
    private static String EXPIRATION = "expiration";
    private static String SCOPE = "scope";

    public static JsonRequest getAuthorize() {
        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put(KEY, TrelloUrls.API_KEY);
            jsonRequest.put(RESPONSE_TYPE, "token");
            jsonRequest.put(EXPIRATION, "never");
            jsonRequest.put(SCOPE, "read,write");
        } catch (JSONException e) {
            e.printStackTrace();
        }
       return new JsonObjectRequest
                (Request.Method.GET, TrelloUrls.AUTHORIZE, jsonRequest, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(API_TAG, "Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(API_TAG, error.toString());

                    }
                });


    }
}
