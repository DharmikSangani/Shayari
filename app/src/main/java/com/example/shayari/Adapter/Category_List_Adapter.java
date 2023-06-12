package com.example.shayari.Adapter;




import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shayari.Activity.List_Activity;
import com.example.shayari.R;


public class Category_List_Adapter extends BaseAdapter
{

    List_Activity list_activity;
    ImageView imageView;
    String[] name;
    int imgArr;

    public Category_List_Adapter(List_Activity list_activity, String[] name, int img, String[] temp1) {
        this.list_activity = list_activity;
        this.name = name;
        this.imgArr = img;

    }



    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view= LayoutInflater.from(list_activity).inflate(R.layout.sec_item_list,parent,false);

        ImageView imageView=view.findViewById(R.id.other_item_img);
        TextView txtName=view.findViewById(R.id.other_item_name);

        txtName.setText(""+name[position]);

        imageView.setImageResource(imgArr);


        return view;
    }
}