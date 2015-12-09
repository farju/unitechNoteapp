package com.unitech.notapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity
{
    Toolbar toolbar;
    TextView help;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        toolbar=(Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setElevation(10);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        help=(TextView)findViewById(R.id.help);
        help.setText("\tOn Install, set your Branch, Class and Department preferences as well as Push Notification Preferences(can be changed later).\n\tTo select preferences of Departments, just set the switches of the departments of your choices though Settings-> Department Preferences and restart the app. Notices of only selected departments would be received and even the start screen will show the same logos.\n\tIf you don’t want push notifications for any department, just as Department Preferences , set your Push Notification Preferences through Settings->Push Notification Preferences.\nNote: Only departments selected in Departments Preferences can be set in Push Notification Preferences.\nFor any other grievances feel free to contact\nsarang@unitechstudios.com\nmanish@unitechstudios.com");
        help.setTextSize(16);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_help, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }
}
