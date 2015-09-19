package com.unitech.notapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by sarang on 8/9/15.
 */
public class ImageAdapter extends BaseAdapter
{

    private Context mContext;
    // references to our images
    //public static Integer[] mThumbIds = new Integer[11];

    public static Integer[] mThumbIds = {
            R.drawable.cse,R.drawable.it,
            R.drawable.eln,R.drawable.ele,
            R.drawable.civ,R.drawable.mech,
            R.drawable.dmin,R.drawable.schlshp,
            R.drawable.exam,R.drawable.rector,
            R.drawable.sports,R.drawable.tpo
    };

    String[] deptNames = {"CSE", "IT", "ELN", "ELE", "CIV", "MECH", "Scholarship_Section", "Rector", "Exam_Cell", "Sports", };

    public ImageAdapter(Context c)
    {
        mContext = c;
    }


    public int getCount()
    {
        return mThumbIds.length;
    }

    public Object getItem(int position)
    {
        return null;
    }

    public long getItemId(int position)
    {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent)
    {
        MyImageView imageView;


        //

        if (convertView == null)
        {
            // if it's not recycled, initialize some attributes
            imageView = new MyImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.WRAP_CONTENT, GridView.LayoutParams.WRAP_CONTENT));
            imageView.setPadding(10, 10, 10, 10);
        }
        else
        {
            imageView = (MyImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }
}
