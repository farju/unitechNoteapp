package com.unitech.notapp;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private Toolbar toolbar;
    GridView gridView;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar =(Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setElevation(10);

        getSupportActionBar().setTitle(" Notapp");
        //getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        final SharedPreferences sharedPreferences=getSharedPreferences("UserData", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor=sharedPreferences.edit();

        SharedPreferences settingPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        shortlistDepts(settingPrefs);

        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new ImageAdapter(this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent(MainActivity.this, NoticesActivity.class);
                editor.putInt("ClickedViewPos", position);
                editor.commit();
                startActivity(intent);
            }
        });

        /*SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        if(sp.getBoolean("CSE", false))
        {
            Toast.makeText(getBaseContext(), "yay", Toast.LENGTH_SHORT).show();
            ImageAdapter.mThumbIds[0] = R.drawable.it;

        }

        else
        {
            Toast.makeText(getBaseContext(), "opps", Toast.LENGTH_SHORT).show();
            ImageAdapter.mThumbIds[0] = R.drawable.cse;
        }*/

        //CheckBoxPreference chbk = findPreference()





    }

    public void shortlistDepts(SharedPreferences sp)
    {
        ArrayList<Integer> temp = new ArrayList<Integer>();
        SharedPreferences.Editor editor=sp.edit();
        SwitchPreference switchPreference;
        for(int i = 0; i< 11; i++)
        {
            if(sp.getBoolean(ImageAdapter.deptNames[i],false))
            {
                temp.add(ImageAdapter.reference[i]);
            }
            else
            {
                //Toast.makeText(getBaseContext(), "Editing Push "+ImageAdapter.deptNames[i]+sp.getBoolean(ImageAdapter.deptNames[i]+""+1,false), Toast.LENGTH_SHORT).show();
                editor.putBoolean(ImageAdapter.deptNames[i] + "1", false);
                //Toast.makeText(getBaseContext(), "Edited Push "+ImageAdapter.deptNames[i]+sp.getBoolean(ImageAdapter.deptNames[i]+""+1,false),500).show();

            }
        }
        temp.add(R.drawable.dmin);
        ImageAdapter.mThumbIds = temp.toArray(new Integer[temp.size()]);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent;

        //noinspection SimplifiableIfStatement

        switch (id)
        {
            case R.id.action_settings:
                intent=new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.action_help:
                intent=new Intent(MainActivity.this, HelpActivity.class);
                startActivity(intent);
                break;
            case R.id.action_about:
                intent=new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
}
