package com.example.shayari.Activity;

import static com.example.shayari.R.layout.font_size;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shayari.Adapter.EmojiAdapter;
import com.example.shayari.Adapter.FontAdapter;
import com.example.shayari.Adapter.Gradient_Edit_Adapter;
import com.example.shayari.Adapter.Gradient_colorAdapter;
import com.example.shayari.R;
import com.example.shayari.config;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;


public class Edit_activity extends AppCompatActivity implements View.OnClickListener {


    Gradient_Edit_Adapter adapter;
    TextView textView, bgd, textcolor, font, btnemoji,share,textSize;
    ListView listView;

    String temp1[];

    ImageView btn1, btn2;
    int progress=0;

    private int position;
    private Object emojiArr;
    private int i;
    private File downloadFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        textView = findViewById(R.id.txt1);
        btn1 = findViewById(R.id.b1);
        btn2 = findViewById(R.id.b2);
        bgd = findViewById(R.id.b_g);
        share = findViewById(R.id.share);
        font = findViewById(R.id.front);
        textcolor = findViewById(R.id.t_c);
        textSize=findViewById(R.id.t_s);
        btnemoji = findViewById(R.id.emoji);
        position = getIntent().getIntExtra("pos", 0);
        temp1 = getIntent().getStringArrayExtra("temp1");
        //textView.setText(temp1[position]);
        textView.setText(config.emojiArr[i] + "\n" + temp1[position] + "\n" + config.emojiArr[i]);



        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        bgd.setOnClickListener(this);
        textcolor.setOnClickListener(this);
        share.setOnClickListener(this);
        font.setOnClickListener(this);
        btnemoji.setOnClickListener(this);

        textSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BottomSheetDialog dialog=new BottomSheetDialog(Edit_activity.this);
                dialog.setContentView(font_size);
                SeekBar seekBar=dialog.findViewById(R.id.seekBar);
                seekBar.setProgress(progress);
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        if(progress==0) {
                            //seekBar.setProgress(0);
                            textView.setTextSize(2, 30 + i);
                        }
                        else {

                            textView.setTextSize(2, 30 + i);
                        }

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        //dialog.dismiss();
                        progress=seekBar.getProgress();
                    }

                });
                dialog.show();
                seekBar.setActivated(true);
            }
        });


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btn1.getId()) {
            BottomSheetDialog dialog = new BottomSheetDialog(Edit_activity.this);
            dialog.setContentView(R.layout.gradient_layout);
            dialog.findViewById(R.id.gridView_gradient);
            GridView gridView = dialog.findViewById(R.id.gridView_gradient);
            adapter = new Gradient_Edit_Adapter(Edit_activity.this, config.gradArr);
            gridView.setAdapter(adapter);

            gridView.setOnItemClickListener((parent, view, position, id) -> {
                textView.setBackgroundResource(config.gradArr[position]);
                dialog.dismiss();
            });
            dialog.show();
        }
        if (v.getId() == btn2.getId()) {
            int min = 0, max = config.gradArr.length;
            int r = new Random().nextInt(max - min) + min;
            textView.setBackgroundResource(config.gradArr[r]);
        }
        if (v.getId() == bgd.getId()) {
            BottomSheetDialog dialog = new BottomSheetDialog(Edit_activity.this);
            dialog.setContentView(R.layout.gradient_color);
            dialog.findViewById(R.id.gradcolor);
            GridView gridview = dialog.findViewById(R.id.gradcolor);
            Gradient_colorAdapter adapter = new Gradient_colorAdapter(Edit_activity.this, config.color);
            gridview.setAdapter(adapter);

            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    textView.setBackgroundResource(config.color[position]);
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
        if (v.getId() == textcolor.getId()) {
            BottomSheetDialog dialog = new BottomSheetDialog(Edit_activity.this);
            dialog.setContentView(R.layout.gradient_color);
            dialog.findViewById(R.id.gradcolor);
            GridView gridView = dialog.findViewById(R.id.gradcolor);
            Gradient_colorAdapter adapter = new Gradient_colorAdapter(Edit_activity.this, config.color);
            gridView.setAdapter(adapter);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    textView.setTextColor(getResources().getColor(config.color[position]));
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
        if (v.getId() == font.getId()) {
            BottomSheetDialog dialog = new BottomSheetDialog(Edit_activity.this);
            dialog.setContentView(R.layout.font_layout);
            GridView gridView = dialog.findViewById(R.id.fontlist);
            gridView.setNumColumns(config.fontsArr.length);
            FontAdapter adapter = new FontAdapter(Edit_activity.this);
            gridView.setAdapter(adapter);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //textView.setText("ShayriApp");
                    Typeface typeface = null;
                    Typeface face = Typeface.createFromAsset(getAssets(), config.fontsArr[position]);
                    textView.setTypeface(face);
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
        if (v.getId() == btnemoji.getId()) {
            BottomSheetDialog dialog = new BottomSheetDialog(Edit_activity.this);
            dialog.setContentView(R.layout.activity_emoji);
            ListView listView = dialog.findViewById(R.id.emoji_list);
            EmojiAdapter adapter = new EmojiAdapter(Edit_activity.this, config.emojiArr);

            Log.d("TTT", "onClick: Length=" + config.emojiArr.length);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                    //textView.setText("ShayriApp");
                    textView.setText(config.emojiArr[pos] + "\n" + temp1[position] + "\n" + config.emojiArr[pos]);
                }
            });
            dialog.show();
        }
        Intent ShareImg = null;
        if (v.getId() == share.getId()) {

            Bitmap icon = getBitmapFromView(textView);
            //Intent share = new Intent(Intent.ACTION_SEND);
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("image/jpeg");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            icon.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
            String currentDateandTime = sdf.format(new Date());

            downloadFile = new File(config.file.getAbsolutePath() + "/IMG_" + currentDateandTime + ".jpg");
            try {
                downloadFile.createNewFile();
                FileOutputStream fo = new FileOutputStream(downloadFile);
                fo.write(bytes.toByteArray());
                Toast.makeText(Edit_activity.this, "File Downloaded", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }


            share.putExtra(Intent.EXTRA_STREAM, Uri.parse(downloadFile.getAbsolutePath()));
            startActivity(Intent.createChooser(share, "Share Image"));


        }
    }
    private Bitmap getBitmapFromView(TextView textView)
    {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(textView.getWidth(), textView.getHeight(), Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable = textView.getBackground();
        if (bgDrawable != null)
        {
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        }
        else
        {
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        }
        // draw the view on the canvas
        textView.draw(canvas);
        //return the bitmap
        return returnedBitmap;

    }
}