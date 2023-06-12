package com.example.shayari.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.shayari.Activity.Full_activity;
import com.example.shayari.R;



public class ShayriPagerAdapter extends PagerAdapter
{
    Full_activity full_activity;
    String[] temp1;
    TextView textView;

    public ShayriPagerAdapter(Full_activity full_activity, String[] temp1, TextView textView) {
        this.full_activity = full_activity;
        this.temp1 = temp1;
        this.textView = textView;
    }


    @Override
    public int getCount() {
        return temp1.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view= LayoutInflater.from(full_activity).inflate(R.layout.pager_item,container,false);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}