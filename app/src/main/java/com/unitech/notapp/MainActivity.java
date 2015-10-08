package com.unitech.notapp;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.AsyncTask;
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

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.unitech.notappbackend.registration.Registration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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

        new GcmRegistrationAsyncTask(this).execute();

        toolbar =(Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setElevation(10);

        getSupportActionBar().setTitle(" Notapp");
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

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

    static class GcmRegistrationAsyncTask extends AsyncTask<Void, Void, String>
    {
        private static Registration regService = null;
        private GoogleCloudMessaging gcm;
        private Context context;

        // TODO: change to your own sender ID to Google Developers Console project number, as per instructions above
        private static final String SENDER_ID = "190715092314";

        public GcmRegistrationAsyncTask(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(Void... params) {
            if (regService == null) {
                Registration.Builder builder = new Registration.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // Need setRootUrl and setGoogleClientRequestInitializer only for local testing,
                        // otherwise they can be skipped
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest)
                                    throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                // end of optional local run code

                regService = builder.build();
            }

            String msg = "worked!!!";
            try {
                if (gcm == null) {
                    gcm = GoogleCloudMessaging.getInstance(context);
                }
                String regId = gcm.register(SENDER_ID);
                msg = "Device registered, registration ID=" + regId;

                // You should send the registration ID to your server over HTTP,
                // so it can use GCM/HTTP or CCS to send messages to your app.
                // The request to your server should be authenticated if your app
                // is using accounts.
                regService.register(regId).execute();

            } catch (IOException ex) {
                ex.printStackTrace();
                msg = "Error: " + ex.getMessage();
            }
            return msg;
        }

        @Override
        protected void onPostExecute(String msg) {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            Logger.getLogger("REGISTRATION").log(Level.INFO, msg);
        }
    }
}
