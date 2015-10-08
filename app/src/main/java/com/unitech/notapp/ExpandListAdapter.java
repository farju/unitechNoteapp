package com.unitech.notapp;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;


public class ExpandListAdapter extends BaseExpandableListAdapter
{

    private Context context;
    private ArrayList<ExpandListGroup> groups;

    public ExpandListAdapter(Context context, ArrayList<ExpandListGroup> groups)
    {
        this.context = context;
        this.groups = groups;
    }

    public void addItem(ExpandListChild item, ExpandListGroup group) {
        if (!groups.contains(group)) {
            groups.add(group);
        }
        int index = groups.indexOf(group);
        ArrayList<ExpandListChild> ch = groups.get(index).getItems();
        ch.add(item);
        groups.get(index).setItems(ch);
    }

    public Object getChild(int groupPosition, int childPosition)
    {
        // TODO Auto-generated method stub
        ArrayList<ExpandListChild> chList = groups.get(groupPosition).getItems();
        return chList.get(childPosition);
    }

    public long getChildId(int groupPosition, int childPosition)
    {
        // TODO Auto-generated method stub
        return childPosition;
    }

    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent)
    {

        ExpandListChild child = (ExpandListChild) getChild(groupPosition, childPosition);
        if (view == null)
        {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.notices_child_item, null);
        }
        final ImageView tv = (ImageView) view.findViewById(R.id.noticechild);

        tv.setOnClickListener(new View.OnClickListener()
        {
            Boolean isImageFitToScreen;
            @Override
            public void onClick(View v)
            {
                tv.setScaleType(ImageView.ScaleType.FIT_CENTER);
                //tv.setImageBitmap(cached.thumb);
                //saveDataInSdCard(cached.thumb,position);

                //somewhat here

                // and start a new activity and put file path , and your next activity there is

                tv.setOnClickListener(new View.OnClickListener()
                {

                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "manish", Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(context,MainNoticeActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  ;
                    //intent.putExtra("file_path",PATH);
                    context.startActivity(intent);
                }

                });
            }
        });
        //tv.setText(child.getName().toString());
        //tv.setTag(child.getTag());
        // TODO Auto-generated method stub
        return view;
    }


    public int getChildrenCount(int groupPosition)
    {
        // TODO Auto-generated method stub
        ArrayList<ExpandListChild> chList = groups.get(groupPosition).getItems();

        return chList.size();
    }

    public Object getGroup(int groupPosition)
    {
        // TODO Auto-generated method stub
        return groups.get(groupPosition);
    }

    public int getGroupCount()
    {
        // TODO Auto-generated method stub
        return groups.size();
    }

    public long getGroupId(int groupPosition)
    {
        // TODO Auto-generated method stub
        return groupPosition;
    }

    public View getGroupView(int groupPosition, boolean isLastChild, View view, ViewGroup parent)
    {
        ExpandListGroup group = (ExpandListGroup) getGroup(groupPosition);
        if (view == null)
        {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.notices_group_item, null);
        }
        TextView tv = (TextView) view.findViewById(R.id.noticehead);
        tv.setText(group.getName());

        // TODO Auto-generated method stub
        return view;
    }

    public boolean hasStableIds()
    {
        // TODO Auto-generated method stub
        return true;
    }

    public boolean isChildSelectable(int arg0, int arg1)
    {
        // TODO Auto-generated method stub
        return true;
    }

}
