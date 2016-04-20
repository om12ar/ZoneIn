package com.swe.zonein.zonein.Activities;

import android.app.Application;
import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by malsa on 20/4/2016.
 */
public class ZoneInApp extends Application {


    private static  ZoneInApp mInstance;

    @Override
    public void onCreate(){
        super.onCreate();

        mInstance = this;
    }

    public static synchronized ZoneInApp getInstance(){

        return mInstance;
    }



}
