package com.example.shayari.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shayari.Activity.List_Activity;
import com.example.shayari.Activity.Main_Activity;
import com.example.shayari.R;

public class Category_Adapter extends RecyclerView.Adapter<Category_Adapter.CategoryHolder>
{

    Main_Activity mainActivity;
    ListView listView;
    String[] name;
    int[] imgArr;

    public Category_Adapter(Main_Activity mainActivity, String[] name, int[] img) {
        this.mainActivity = mainActivity;
        this.name = name;
        this.imgArr = img;

    }


    @NonNull
    @Override
    public Category_Adapter.CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mainActivity).inflate(R.layout.first_item_list,parent,false);
        CategoryHolder holder=new CategoryHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Category_Adapter.CategoryHolder holder, int position) {
        holder.textView.setText(""+name[position]);
        holder.imageView.setImageResource(imgArr[position]);
    }

    @Override
    public int getItemCount() {
        return name.length;

    }

    public class CategoryHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.main_item_name);
            imageView=itemView.findViewById(R.id.main_item_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(mainActivity,List_Activity.class);
                    intent.putExtra("pos",getAdapterPosition());
                    intent.putExtra("name",name);

                    intent.putExtra("img",imgArr[getAdapterPosition()]);
                    mainActivity.startActivity(intent);
                }
            });
        }
    }
}