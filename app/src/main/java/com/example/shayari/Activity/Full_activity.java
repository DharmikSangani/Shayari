package com.example.shayari.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shayari.Adapter.GradientAdapter;
import com.example.shayari.Adapter.ShayriPagerAdapter;
import com.example.shayari.R;
import com.example.shayari.config;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Random;


public class Full_activity extends AppCompatActivity implements  OnClickListener {

    GradientAdapter adapter;
    ImageView next,back,btn1,btn2,Edit,btncopy,btnshare;
    GridView gridView;
    TextView textView;
    int position;
    String temp1[];
    TextView t1;
    private int i;
    ViewPager viewPager;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full);
        textView = findViewById(R.id.txt1);
        next = findViewById(R.id.b_right);
        back = findViewById(R.id.b_lift);
        t1 = findViewById(R.id.t1);
        btn1=findViewById(R.id.b1);
        btn2=findViewById(R.id.b2);
        Edit=findViewById(R.id.edit);
        gridView=findViewById(R.id.gridView_gradient);
        btncopy=findViewById(R.id.copy);
        btnshare=findViewById(R.id.share1);
        viewPager = findViewById(R.id.viewPager);

        next.setOnClickListener(this);
        back.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        Edit.setOnClickListener(this);
        btncopy.setOnClickListener(this);
        btnshare.setOnClickListener(this);

        position = getIntent().getIntExtra("pos", 0);
        temp1 = getIntent().getStringArrayExtra("temp1");
//        textView.setText(temp1[position]);
        textView.setText(config.emojiArr[i] + "\n" + temp1[position] + "\n" + config.emojiArr[i]);
        t1.setText((position + 1) + "/" + temp1.length);

        ShayriPagerAdapter adapter=new ShayriPagerAdapter(Full_activity.this,temp1,textView);

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                textView.setText(""+temp1[position]);
                if(position<temp1.length-1)
                {
                    t1.setText((position+1)+"/"+temp1.length);
                    textView.setText(""+temp1[position]);
                    textView.setText(config.emojiArr[i] + "\n" + temp1[position] + "\n" + config.emojiArr[i]);
                }
                else if(position > 0)
                {
                    t1.setText((position+1)+"/"+temp1.length);
                    textView.setText(""+temp1[position]);
                    textView.setText(config.emojiArr[i] + "\n" + temp1[position] + "\n" + config.emojiArr[i]);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }



    @Override
    public void onClick(View v)
    {

        if (v.getId() == next.getId()) {
            if (position < temp1.length - 1) {
                position++;
                t1.setText((position + 1) + "/" + temp1.length);
//                textView.setText(temp1[position]);
                textView.setText(config.emojiArr[i] + "\n" + temp1[position] + "\n" + config.emojiArr[i]);
            }
        }
        if (v.getId() == back.getId()) {
            if (position > 0) {
                position--;
                t1.setText((position + 1) + "/" + temp1.length);
                textView.setText(config.emojiArr[i] + "\n" + temp1[position] + "\n" + config.emojiArr[i]);
            }
        }
        if (v.getId() == btn1.getId())
        {

            BottomSheetDialog dialog = new BottomSheetDialog(Full_activity.this);
            dialog.setContentView(R.layout.gradient_layout);
            GridView gridView = dialog.findViewById(R.id.gridView_gradient);
            adapter = new GradientAdapter(Full_activity.this, config.gradArr);
            gridView.setAdapter(adapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    textView.setBackgroundResource(config.gradArr[i]);
                    dialog.dismiss();
                }
            });
            dialog.show();


        }
        if(v.getId()==btn2.getId())
        {
            int min=0,max=config.gradArr.length;
            int r=new Random().nextInt(max-min)+min;
            textView.setBackgroundResource(config.gradArr[r]);
        }
        if(v.getId()==Edit.getId())
        {
            Intent intent=new Intent(Full_activity.this, Edit_activity.class);
            intent.putExtra("pos",position);
            intent.putExtra("temp1", temp1);
            startActivity(intent);
        }
        if(v.getId()==btncopy.getId())
        {
            String txt=textView.getText().toString();
            ClipboardManager clipboardManager=(ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clipData=ClipData.newPlainText("copy",txt);
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(this,"Copy",Toast.LENGTH_SHORT).show();
        }
        if(v.getId()==btnshare.getId())
        {
            Intent intent=new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT,"Cheak");
            intent.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id=com.loveshayarisn");
            startActivity(Intent.createChooser(intent,"Share Vie"));
        }



    }
}