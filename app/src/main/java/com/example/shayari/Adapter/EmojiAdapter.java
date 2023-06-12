package com.example.shayari.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.shayari.Activity.Edit_activity;
import com.example.shayari.R;


public class EmojiAdapter extends BaseAdapter
{
    Edit_activity edit_activity;
    String[] emojiArr;

    public EmojiAdapter(Edit_activity edit_activity,String[] emojiArr) {
        this.edit_activity = edit_activity;
        this.emojiArr = emojiArr;
    }

    @Override
    public int getCount() {
        return emojiArr.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        view= LayoutInflater.from(edit_activity).inflate(R.layout.activity_emoji_item,parent,false);

        TextView textView=view.findViewById(R.id.emoji_textview);
        textView.setText(""+emojiArr[i]);
        return view;
    }
}