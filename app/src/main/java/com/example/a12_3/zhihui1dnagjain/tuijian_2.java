package com.example.a12_3.zhihui1dnagjain;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a12_3.Adapter.zhihui_Adapter;
import com.example.a12_3.R;
import com.example.a12_3.util.BaseActivity;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class tuijian_2 extends BaseActivity {

    private Banner tuijianBanner;
    private TextView textView45;
    private RecyclerView tuijain2Recy;
    private Button button10;
    private List<Integer> img=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuijian_2);
        setTitle("活动");
        initView();
        initdata();
    }

    private void initdata() {
        img.add(R.drawable.hd1);
        img.add(R.drawable.hd2);
        img.add(R.drawable.hd3);
        tuijianBanner.setImages(img).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object o, ImageView imageView) {
                Glide.with(context).load(o).into(imageView);
            }
        }).start();
        tuijain2Recy.setLayoutManager(new LinearLayoutManager(tuijian_2.this));
        tuijain2Recy.setAdapter(new zhihui_Adapter(tuijian_2.this,0));
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(tuijian_2.this,tuijain_2_init.class));
            }
        });
    }

    private void initView() {
        tuijianBanner = findViewById(R.id.tuijian_banner);
        textView45 = findViewById(R.id.textView45);
        tuijain2Recy = findViewById(R.id.tuijain_2_recy);
        button10 = findViewById(R.id.button10);
    }
}