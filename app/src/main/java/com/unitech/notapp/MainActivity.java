package com.unitech.notapp;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private Toolbar toolbar;
    GridView gridView;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //finish();
        //startActivity(getIntent());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setTitleColor(int color);
        toolbar =(Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        final SharedPreferences sharedPreferences=getSharedPreferences("UserData", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor=sharedPreferences.edit();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            Intent intent=new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
