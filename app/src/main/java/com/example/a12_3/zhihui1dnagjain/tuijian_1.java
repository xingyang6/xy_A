package com.example.a12_3.zhihui1dnagjain;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a12_3.R;
import com.example.a12_3.util.BaseActivity;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;

public class tuijian_1 extends BaseActivity {
    private Banner tuijian1Banner;
    private LinearLayout tuijian1Tuijain1;
    private LinearLayout tuijian1Tuijain2;
    private ImageView sp1;
    private ImageView sp2;
    private ImageView sp3;
    private ImageView pay;
    private ImageView imageView52;
    private MediaPlayer mediaPlayer;
    private List<Integer> imgs = new ArrayList<>();
    private TextView scText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuijian_1);
        setTitle("党员学习");
        initView();
        try {
            initdata();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void initdata() throws IOException {
        if (zh_A.yp1 == 0) {
            imageView52.setImageResource(R.drawable.unsc);
            scText.setText("收藏");
        } else {
            imageView52.setImageResource(R.drawable.sc);
            scText.setText("已收藏");
        }
        //轮播图
        imgs.add(R.drawable.dy1);
        imgs.add(R.drawable.dy2);
        imgs.add(R.drawable.dy3);
        imgs.add(R.drawable.dy4);
        tuijian1Banner.setImages(imgs).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object o, ImageView imageView) {
                Glide.with(context).load(o).into(imageView);
            }
        }).start();

        //音频收藏
        imageView52.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (zh_A.yp1 == 0) {
                    imageView52.setImageResource(R.drawable.sc);
                    scText.setText("已收藏");
                    zh_A.yp1 = 1;
                } else {
                    imageView52.setImageResource(R.drawable.unsc);
                    scText.setText("收藏");
                    zh_A.yp1 = 0;
                }
            }
        });

        //音频播放
        mediaPlayer= MediaPlayer.create(tuijian_1.this,R.raw.yp);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (zh_A.yp1==0){
                    mediaPlayer.start();
                    pay.setImageResource(R.drawable.play_1);
                    zh_A.yp1=1;
                }else{
                    mediaPlayer.pause();
                    pay.setImageResource(R.drawable.play_hei);
                    zh_A.yp1=0;
                }
            }
        });
        //播放完成的方法
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                pay.setImageResource(R.drawable.play_hei);
            }
        });


        //视频点击事件
        sp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tuijain_1_init.sp_type = 1;
                startActivity(new Intent(tuijian_1.this, tuijain_1_init.class));
            }
        });
        sp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tuijain_1_init.sp_type = 2;
                startActivity(new Intent(tuijian_1.this, tuijain_1_init.class));
            }
        });
        sp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tuijain_1_init.sp_type = 3;
                startActivity(new Intent(tuijian_1.this, tuijain_1_init.class));
            }
        });
        //推荐课程点击事件
        tuijian1Tuijain1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wenz_init.wenzen_type = 1;
                wenz_init.wenz_id = 0;
                startActivity(new Intent(tuijian_1.this, wenz_init.class));
            }
        });
        tuijian1Tuijain2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wenz_init.wenzen_type = 1;
                wenz_init.wenz_id = 1;
                startActivity(new Intent(tuijian_1.this, wenz_init.class));
            }
        });
    }

    private void initView() {
        tuijian1Banner = findViewById(R.id.tuijian_1_banner);
        tuijian1Tuijain1 = findViewById(R.id.tuijian_1_tuijain_1);
        tuijian1Tuijain2 = findViewById(R.id.tuijian_1_tuijain_2);
        sp1 = findViewById(R.id.sp1);
        sp2 = findViewById(R.id.sp2);
        sp3 = findViewById(R.id.sp3);
        pay = findViewById(R.id.pay);
        imageView52 = findViewById(R.id.imageView52);
        scText = findViewById(R.id.sc_text);
    }
}