package com.unitech.notapp;

/**
 * Created by sarang on 15/10/15.
 */
import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.support.multidex.MultiDex;


public class MyApplication extends MultiDexApplication
{

    // ......

    @Override
    protected void attachBaseContext(Context base)
    {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
