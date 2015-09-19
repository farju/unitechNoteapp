package com.unitech.notapp;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.widget.CheckBox;
import android.widget.Toast;

/**
 * Created by sarang on 10/9/15.
 */
public class DepartmentPreferences extends PreferenceActivity
{
    //String[] arr = {"CSE", "IT", "ELN", "ELE", "CIV", "MECH", "Scholarship_Section", "Rector","Exam_Cell", "Sports"};
    Context mContext = this;
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //mContext = this;
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public class MyPreferenceFragment extends PreferenceFragment
    {
        String[] arr = {"CSE", "IT", "ELN", "ELE", "CIV", "MECH", "Scholarship_Section", "Rector","Exam_Cell", "Sports"};
        //CheckBoxPreference[] chbx = new CheckBoxPreference[11];

        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.dprefs);

            /*CheckBoxPreference chbk = (CheckBoxPreference) findPreference("CSE");

            if(chbk.isChecked())
            {
                ImageAdapter.mThumbIds[0] = R.drawable.cse;
                Toast.makeText(getBaseContext(), "yay", Toast.LENGTH_SHORT).show();
            }
            else
            {
                ImageAdapter.mThumbIds[0] = R.drawable.it;
                Toast.makeText(getBaseContext(), "no", Toast.LENGTH_SHORT).show();
            }*/
        }



        /*@Override
        public void onResume() {
            super.onResume();
            //Context mContext = ;
            final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);

            Preference.OnPreferenceChangeListener onChange=new Preference.OnPreferenceChangeListener()
            {
                @Override public boolean onPreferenceChange(Preference preference, Object newValue)
                {
                    CheckBoxPreference chbx = (CheckBoxPreference) preference;

                    if (chbx.getKey().equals("CSE") && chbx.isChecked())
                    {
                        //ImageAdapter.mThumbIds[0] = R.drawable.it;
                        Toast.makeText(getBaseContext(), "Bio Updated", Toast.LENGTH_SHORT).show();
                        return true;
                    }

                    return false;

                }
            };
        }
        */
        /*@Override
        public void onDestroy() {
            super.onDestroy();

            Intent i = getBaseContext().getPackageManager()
                    .getLaunchIntentForPackage( getBaseContext().getPackageName() );
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //Intent intent=new Intent(DepartmentPreferences.this,MainActivity.class);
            startActivity(i);
            //startActivity();
            /*final SharedPreferences sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
            final SharedPreferences.Editor editor = sharedPreferences.edit();

            Preference.OnPreferenceChangeListener onChange=new Preference.OnPreferenceChangeListener()
            {
                @Override public boolean onPreferenceChange(Preference preference, Object newValue)
                {
                    CheckBoxPreference chbx = (CheckBoxPreference) preference;

                    for (int i = 0; i<11; i++)
                    {
                        if (chbx.)
                    }

                    return false;
                }
            };
        }*/
    }
}
