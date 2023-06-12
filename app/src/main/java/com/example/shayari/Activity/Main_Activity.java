package com.example.shayari.Activity;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.shayari.Adapter.Category_Adapter;
import com.example.shayari.R;


public class Main_Activity extends AppCompatActivity {
    ListView listView;
    ImageView imageView;

    Category_Adapter adapter;

    String name[]={"   શુભસકામના શાયરી","   દોસ્તી શાયરી","   મજેદાર શાયરી","   ભગવાન શાયરી","   પ્રેરણા સ્તોત્ર શાયરી","   જીવન શાયરી","   મોહબ્બત શાયરી","   યાદ શાયરી","   અન્ય શાયરી","   રાજનીતિ શાયરી","   પ્રેમ શાયરી","   દર્દ શાયરી","   શરાબ શાયરી","   બેવફા શાયરી","   જન્મદિવસ શાયરી","   હોળી શાયરી"};

    int imgArr[] ={R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5,R.drawable.img6,R.drawable.img7,R.drawable.img8,R.drawable.img9,R.drawable.img10,R.drawable.img11,R.drawable.img12,R.drawable.img13,R.drawable.img14,R.drawable.img15,R.drawable.img16,};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.list);
        adapter = new Category_Adapter(Main_Activity.this,name,imgArr);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


                Intent intent=new Intent(Main_Activity.this, List_Activity.class);
                intent.putExtra("pos",position);
                intent.putExtra("name",name);

                intent.putExtra("img",imgArr[position]);
                startActivity(intent);
            }
        });
    }
}