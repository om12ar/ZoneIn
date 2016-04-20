package com.swe.zonein.zonein.Controllers;

import android.nfc.Tag;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.swe.zonein.zonein.Activities.ZoneInApp;

import java.security.PublicKey;

/**
 * Created by malsa on 20/4/2016.
 */
public class VolleyController {
    public static final String baseURL = "http://zonein-zonein.rhcloud.com/ZoneIn_server/rest/";
  // public static final String baseURL = "http://localhost:8080/ZoneIn_server/rest/";
    private static VolleyController mInstance;
    private RequestQueue mRequestQueue;

    private VolleyController(){

        mRequestQueue = mRequestQueue = Volley.newRequestQueue(ZoneInApp.getInstance().getApplicationContext());

    }

    public static VolleyController getInstance(){

        if(mInstance == null){

            mInstance = new VolleyController();
        }

        return mInstance;
    }

    public RequestQueue getRequestQueue(){

        if (mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(ZoneInApp.getInstance().getApplicationContext());
        }

        return  mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request, String TAG){
        request.setTag(TAG);
        getRequestQueue().add(request);

    }

    public <T> void addToRequestQueue(Request<T> request){
        request.setTag("Default");
        getRequestQueue().add(request);

    }

}
