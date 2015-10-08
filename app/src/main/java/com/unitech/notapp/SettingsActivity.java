package com.unitech.notapp;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;
import java.io.File;
import java.util.List;

public class SettingsActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener
{
    Toolbar toolbar;
    Spinner spinner,spinner2;
    Button ok;
    EditText name;
    Button departmentPreferences, pushnotificationpreferences;
    boolean b;
    int bchoice,cchoice,flg=0;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        toolbar=(Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //name = (EditText)findViewById(R.id.Enter_Name);
        spinner=(Spinner)findViewById(R.id.spinner);
        spinner2=(Spinner)findViewById(R.id.spinner2);



        final SharedPreferences sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();


        ArrayAdapter adapter=ArrayAdapter.createFromResource(this, R.array.branch_array, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        ArrayAdapter adapter2=ArrayAdapter.createFromResource(this, R.array.class_array, android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);
        cchoice=spinner.getSelectedItemPosition();


        departmentPreferences=(Button)findViewById(R.id.dept_prio);
        departmentPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, DepartmentPreferences.class);
                startActivity(intent);
            }
        });

        ok=(Button)findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //editor.putString("name", name.getText().toString());
                //editor.commit();
                editor.putInt("bchoice", bchoice);
                editor.commit();
                editor.putInt("cchoice", cchoice);
                editor.commit();
                Toast.makeText(getBaseContext(), "Bio Updated ", Toast.LENGTH_SHORT).show();
            }
        });



        pushnotificationpreferences=(Button)findViewById(R.id.push_prio);
        pushnotificationpreferences.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(SettingsActivity.this,PushNotificationPreferences.class);
                startActivity(intent);
            }
        });

        //name.setText(sharedPreferences.getString("name",""));
        spinner.setSelection(sharedPreferences.getInt("bchoice", 0));
        spinner2.setSelection(sharedPreferences.getInt("cchoice", 0));

        //sharedPreferences.

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Spinner temp = (Spinner) parent;
        if(temp.getId() == spinner.getId())
        {
            bchoice = spinner.getSelectedItemPosition();
        }
        else
        {
            cchoice = spinner2.getSelectedItemPosition();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }
}
