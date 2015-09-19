package com.unitech.notapp;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

/**
 * Created by sarang on 11/9/15.
 */
public class PushNotificationPreferences extends PreferenceActivity
{
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class MyPreferenceFragment extends PreferenceFragment
    {
        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pprefs);

        }
    }
}
