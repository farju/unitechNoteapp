package com.unitech.notapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class NoticesActivity extends ActionBarActivity {

    private ExpandListAdapter ExpAdapter;
    private ArrayList<ExpandListGroup> ExpListItems;
    private ExpandableListView ExpandList;
    private Menu optionsMenu;
    Toolbar toolbar;
    ImageView imageview;
    Boolean isImageFitToScreen=true;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notices);

        toolbar=(Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ExpandList = (ExpandableListView) findViewById(R.id.expandableListView);
        ExpListItems = SetStandardGroups();

        ExpAdapter = new ExpandListAdapter(NoticesActivity.this, ExpListItems);
        ExpandList.setAdapter(ExpAdapter);

        SharedPreferences sharedPreferences=getSharedPreferences("UserData", Context.MODE_PRIVATE);
        Toast.makeText(getBaseContext(),"You have pressed "+sharedPreferences.getInt("ClickedViewPos",1),Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.optionsMenu=menu;
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_notices,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (item.getItemId())
        {
            case R.id.action_settings:
                break;
            case R.id.action_refresh:
                Toast.makeText(getBaseContext(),"Refreshed",Toast.LENGTH_SHORT).show();

        }

        return super.onOptionsItemSelected(item);
    }

    public ArrayList<ExpandListGroup> SetStandardGroups()
    {
        ArrayList<ExpandListGroup> list = new ArrayList<ExpandListGroup>();
        ArrayList<ExpandListChild> list2 = new ArrayList<ExpandListChild>();

        ExpandListGroup gru1 = new ExpandListGroup();
        gru1.setName("Admin | Required Documents");

        ExpandListChild ch1_1 = new ExpandListChild();
        ch1_1.setName("A movie");
        ch1_1.setTag(null);
        list2.add(ch1_1);

        ExpandListChild ch1_2 = new ExpandListChild();
        ch1_2.setName("A movie");
        ch1_2.setTag(null);
        list2.add(ch1_2);

        gru1.setItems(list2);
        list2 = new ArrayList<ExpandListChild>();


        list.add(gru1);


        return list;
    }

}
