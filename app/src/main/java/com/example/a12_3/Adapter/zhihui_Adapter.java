package com.example.a12_3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_3.R;
import com.example.a12_3.zhihui1dnagjain.wenz_init;
import com.example.a12_3.zhihui1dnagjain.zh_A;

public class zhihui_Adapter extends RecyclerView.Adapter<zhihui_Adapter.LearviewHolder> {
    private Context context;
    private int type;
    public zhihui_Adapter(Context context,int a){
        this.context=context;
        this.type=a;
    }
    @NonNull
    @Override
    public LearviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LearviewHolder learviewHolder=null;
        switch (type){
            case 0:
                learviewHolder=new LearviewHolder(LayoutInflater.from(context).inflate(R.layout.zh_home_item,parent,false));
                break;
            case 1:
                learviewHolder=new LearviewHolder(LayoutInflater.from(context).inflate(R.layout.zh_home_item,parent,false));
                break;
            case 2:
                learviewHolder=new LearviewHolder(LayoutInflater.from(context).inflate(R.layout.pl_item,parent,false));
                break;
        }
        return learviewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LearviewHolder holder, int position) {
        switch (type){
            case 0:
                holder.zhImg.setImageResource(zh_A.news_1_img.get(position));
                holder.zhTitle.setText(zh_A.news_1_title.get(position));
                holder.zhCount.setText(zh_A.news_1_count.get(position));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        wenz_init.wenzen_type=1;
                        wenz_init.wenz_id=position;
                        context.startActivity(new Intent(context,wenz_init.class));
                    }
                });
                break;
            case 1:
                holder.zhImg.setImageResource(zh_A.news_2_img.get(position));
                holder.zhTitle.setText(zh_A.news_2_title.get(position));
                holder.zhCount.setText(zh_A.news_2_count.get(position));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        wenz_init.wenzen_type=2;
                        wenz_init.wenz_id=position;
                        context.startActivity(new Intent(context,wenz_init.class));
                    }
                });
                break;
            case 2:
                switch ( wenz_init.wenzen_type){
                    case 1:
                        if (wenz_init.wenz_id==0){
                            holder.plName.setText(  zh_A.news_1_pingl_name.get(position));
                            holder.plData.setText(  zh_A.news_1_pingl_data.get(position));
                            holder.plCount.setText(  zh_A.news_1_pingl_count.get(position));
                        }else{
                            holder.plName.setText(  zh_A.news_1_pingl_name_1.get(position));
                            holder.plData.setText(  zh_A.news_1_pingl_data_1.get(position));
                            holder.plCount.setText(  zh_A.news_1_pingl_count_1.get(position));
                        }
                        break;
                    case 2:
                        if (wenz_init.wenz_id==0){
                            holder.plName.setText(  zh_A.news_2_pingl_name.get(position));
                            holder.plData.setText(  zh_A.news_2_pingl_data.get(position));
                            holder.plCount.setText(  zh_A.news_2_pingl_count.get(position));
                        }else{
                            holder.plName.setText(  zh_A.news_2_pingl_name_2.get(position));
                            holder.plData.setText(  zh_A.news_2_pingl_data_2.get(position));
                            holder.plCount.setText(  zh_A.news_2_pingl_count_2.get(position));
                        }
                        break;

                }

        }

    }

    @Override
    public int getItemCount() {
        int a=0;
        switch (type){
            case 0:
                a=2;
                break;
            case 1:
                a=2;
                break;
            case 2:
                a=zh_A.news_1_pingl_name.size();
                break;
        }
        return a;
    }

    public class LearviewHolder extends RecyclerView.ViewHolder {
        private ImageView zhImg;
        private TextView zhTitle;
        private TextView zhCount;

        private ImageView imageView35;
        private TextView plName;
        private TextView plData;
        private TextView plCount;
        public LearviewHolder(@NonNull View itemView) {
            super(itemView);
            switch (type){
                case 0:
                    zhImg = itemView.findViewById(R.id.zh_img);
                    zhTitle = itemView.findViewById(R.id.zh_title);
                    zhCount = itemView.findViewById(R.id.zh_count);
                    break;
                case 1:
                    zhImg = itemView.findViewById(R.id.zh_img);
                    zhTitle = itemView.findViewById(R.id.zh_title);
                    zhCount = itemView.findViewById(R.id.zh_count);
                    break;
                case 2:
                    imageView35 = itemView.findViewById(R.id.imageView35);
                    plName = itemView.findViewById(R.id.pl_name);
                    plData = itemView.findViewById(R.id.pl_data);
                    plCount = itemView.findViewById(R.id.pl_count);
                    break;
            }
        }
    }
}
