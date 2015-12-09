package com.unitech.notapp;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.unitech.notapp.MyImageView;
import com.unitech.notapp.R;

/**
 * Created by sarang on 17/11/15.
 */
public class NoticesAdapter extends BaseAdapter
{

    private Context mContext;

    // references to our images
    //public static Integer[] mThumbIds = new Integer[11];

    public static Integer[] mThumbIds;


    public static String[] reference = {
            "http://unitechstudios.com/notapp/notices/cse/ty/1.jpg",
            "http://unitechstudios.com/notapp/notices/cse/ty/2.jpg",
            "http://unitechstudios.com/notapp/notices/cse/ty/3.JPG",
            "http://unitechstudios.com/notapp/notices/cse/ty/4.JPG",
            "http://unitechstudios.com/notapp/notices/cse/ty/6.jpg",
            "http://unitechstudios.com/notapp/notices/cse/ty/2.jpg"
           /* "http://unitechstudios.com/notapp/notices/cse/ty/3.jpg",
            "http://unitechstudios.com/notapp/notices/cse/ty/4.jpg",
            "http://unitechstudios.com/notapp/notices/cse/ty/1.jpg",
            "http://unitechstudios.com/notapp/notices/cse/ty/6.jpg",
            "http://unitechstudios.com/notapp/notices/cse/ty/3.jpg",
            "http://unitechstudios.com/notapp/notices/cse/ty/4.jpg",
            "http://unitechstudios.com/notapp/notices/cse/ty/1.jpg",
            "http://unitechstudios.com/notapp/notices/cse/ty/6.jpg",
            "http://unitechstudios.com/notapp/notices/cse/ty/3.jpg",
            "http://unitechstudios.com/notapp/notices/cse/ty/4.jpg "*/
    };

    //public static String[] deptNames = {"CSE", "IT", "ELN", "ELE", "CIV", "MECH", "Scholarship_Section", "Rector", "Exam_Cell", "Sports", "Tpo" };

    public NoticesAdapter(Context context)
    {
        mContext=context;
    }

    public int getCount() { return reference.length;}

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

        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;


        //

        if (convertView == null)
        {
            // if it's not recycled, initialize some attributes
            imageView = new MyImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(width/3,width/3);
            layoutParams.gravity= Gravity.CENTER;
            imageView.setLayoutParams(new GridView.LayoutParams(width/3, width/3));
            imageView.setPadding(25, 25, 25, 25);
           // imageView.setLayoutParams(layoutParams);
        }
        else
        {
            imageView = (MyImageView) convertView;
        }

        try
        {
            Picasso.with(mContext).load(reference[position]).resize(width/3,width/3).centerCrop().placeholder(R.drawable.placeholder).error(R.drawable.ic_error).into(imageView);
        }
        catch (Exception e)
        {
            Toast.makeText(mContext,"Check Your Connection",Toast.LENGTH_SHORT);
        }
        //imageView.setImageResource(reference[position]);

        return imageView;
    }
}

