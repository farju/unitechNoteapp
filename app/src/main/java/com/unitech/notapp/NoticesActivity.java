package com.unitech.notapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NoticesActivity extends ActionBarActivity {


    private Menu optionsMenu;
    GridView noticesGrid;
    Toolbar toolbar;
    ImageView imageview;
    Boolean isImageFitToScreen=true;
    Intent intent;
    String deptName;
    String[] urls;
    JSONArray user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notices);

        toolbar=(Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        intent=getIntent();
        deptName=intent.getStringExtra("deptName");

        //urls = new String[10];
        //urls[0]="http://unitechstudios.com/notapp/notices/"+deptName+"/ty/1.jpg";
        //urls[1]="http://unitechstudios.com/notapp/notices/"+deptName+"/ty/2.jpg";
        //urls[2]="http://unitechstudios.com/notapp/notices/"+deptName+"/ty/3.JPG";

        //NoticesAdapter.reference = urls;


        noticesGrid=(GridView)findViewById(R.id.noticesGrid);
        noticesGrid.setAdapter(new NoticesAdapter(this));


        SharedPreferences sharedPreferences=getSharedPreferences("UserData", Context.MODE_PRIVATE);
        //Toast.makeText(getBaseContext(),"You have pressed "+deptName,Toast.LENGTH_SHORT).show();
        //Toast.makeText(NoticesActivity.this,, Toast.LENGTH_SHORT).show();

        if(isOnline())
            new AsyncTaskParseJson().execute();
        else
        {
            Toast.makeText(getBaseContext(),"Check Your Connection",Toast.LENGTH_SHORT).show();
           // finish();
        }

        if(MainActivity.flag == 0)
        {
            finish();
            startActivity(new Intent(NoticesActivity.this, NoticesActivity.class));
            MainActivity.flag ++;
        }


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

    public class AsyncTaskParseJson extends AsyncTask<Void, Void, Void> {

        final String TAG = "AsyncTaskParseJson.java";

        ArrayList<String> temp = new ArrayList<String>();

        // set your json string url here
        String yourJsonStringUrl = "http://unitechstudios.com/notapp/website/json/index.php";

        // contacts JSONArray
        JSONArray dataJsonArr = null;

        @Override
        protected void onPreExecute() {}

        @Override
        protected Void doInBackground(Void... params) {

            try {

                // instantiate our json parser
                JsonParser jParser = new JsonParser();

                // get json string from url
                JSONObject json = jParser.getJSONFromUrl(yourJsonStringUrl, deptName);

                // get the array of users
                dataJsonArr = json.getJSONArray("result");

                // loop through all users
                for (int i = 0; i < dataJsonArr.length(); i++) {

                    JSONObject c = dataJsonArr.getJSONObject(i);

                    // Storing each json item in variable
                    String path = c.getString("path");

                    temp.add("http://unitechstudios.com/notapp/"+path);


                }

            } catch (JSONException e)
            {
                Toast.makeText(getBaseContext(),"Check Your Connection",Toast.LENGTH_SHORT);
                startActivity(new Intent(NoticesActivity.this,MainActivity.class));
                e.printStackTrace();
            }

            NoticesAdapter.reference = temp.toArray(new String[temp.size()]);
            return null;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
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
               // Toast.makeText(getBaseContext(),"Refreshed",Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(NoticesActivity.this, NoticesActivity.class));


        }

        return super.onOptionsItemSelected(item);
    }

    public boolean isOnline()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }


}
