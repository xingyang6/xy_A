package com.example.a12_3.zhihui1dnagjain;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.a12_3.R;
import com.example.a12_3.util.BaseActivity;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class tuijain_1_init extends BaseActivity {
    public static int sp_type;
    private int play_type = 1;  //
    private VideoView video;
    private ImageView tuijain1Img;
    private TextView tuijain1Text;
    private Banner tuijian1InitBanner;
    private List<Integer> list = new ArrayList<>();
    private ImageView play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuijain_1_init);
        setTitle("视频播放");
        initView();
        initdata();
    }

    private void initdata() {
        //公益轮播图
        list.add(R.drawable.xc1);
        list.add(R.drawable.xc2);
        list.add(R.drawable.xc3);
        tuijian1InitBanner.setImages(list).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object o, ImageView imageView) {
                Glide.with(context).load(o).into(imageView);
            }
        }).start();
        switch (sp_type) {
            case 1:
                if (zh_A.sp1 == 0) {
                    tuijain1Img.setImageResource(R.drawable.unsc);
                    tuijain1Text.setText("收藏");
                } else {
                    tuijain1Img.setImageResource(R.drawable.sc);
                    tuijain1Text.setText("已收藏");
                }
                String videour = "android.resource://com.example.a12_3/" + R.raw.video01;
                Uri uri = Uri.parse(videour);
                //设置视频控制器
                video.setMediaController(new MediaController(tuijain_1_init.this));
                //播放完成回调
                video.setOnCompletionListener(new MyPlayerOnCompletionListener());
                //设置视频路径
                video.setVideoURI(uri);
                //开始播放视频
                video.start();
                tuijain1Img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (zh_A.sp1 == 0) {
                            tuijain1Img.setImageResource(R.drawable.sc);
                            tuijain1Text.setText("已收藏");
                            zh_A.sp1 = 1;
                        } else {
                            tuijain1Img.setImageResource(R.drawable.unsc);
                            zh_A.sp1 = 0;
                            tuijain1Text.setText("收藏");
                        }
                    }
                });
                play.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (video.isPlaying()){
                            video.pause();
                            play.setImageResource(R.drawable.play_hei);
                        }else{
                            video.start();
                            play.setImageResource(R.drawable.play_1);
                        }
                    }
                });
                break;
            case 2:
                if (zh_A.sp2 == 0) {
                    tuijain1Img.setImageResource(R.drawable.unsc);
                    tuijain1Text.setText("收藏");
                } else {
                    tuijain1Img.setImageResource(R.drawable.sc);
                    tuijain1Text.setText("已收藏");
                }
                String videour2 = "android.resource://com.example.a12_3/" + R.raw.video02;
                Uri uri2 = Uri.parse(videour2);
                //设置视频控制器
                video.setMediaController(new MediaController(tuijain_1_init.this));
                //播放完成回调
                video.setOnCompletionListener(new MyPlayerOnCompletionListener());
                //设置视频路径
                video.setVideoURI(uri2);
                //开始播放视频
                video.start();
                tuijain1Img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (zh_A.sp2 == 0) {
                            tuijain1Img.setImageResource(R.drawable.sc);
                            tuijain1Text.setText("已收藏");
                            zh_A.sp2 = 1;
                        } else {
                            tuijain1Img.setImageResource(R.drawable.unsc);
                            zh_A.sp2 = 0;
                            tuijain1Text.setText("收藏");
                        }
                    }
                });
                play.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (video.isPlaying()){
                            video.pause();
                            play.setImageResource(R.drawable.play_hei);
                        }else{
                            video.start();
                            play.setImageResource(R.drawable.play_1);
                        }
                    }
                });
                break;
            case 3:
                if (zh_A.sp3 == 0) {
                    tuijain1Img.setImageResource(R.drawable.unsc);
                    tuijain1Text.setText("收藏");
                } else {
                    tuijain1Img.setImageResource(R.drawable.sc);
                    tuijain1Text.setText("已收藏");
                }
                String videour3 = "android.resource://com.example.a12_3/" + R.raw.video03;
                Uri uri3 = Uri.parse(videour3);
                //设置视频控制器
                video.setMediaController(new MediaController(tuijain_1_init.this));
                //播放完成回调
                video.setOnCompletionListener(new MyPlayerOnCompletionListener());
                //设置视频路径
                video.setVideoURI(uri3);
                //开始播放视频
                video.start();
                tuijain1Img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (zh_A.sp3 == 0) {
                            tuijain1Img.setImageResource(R.drawable.sc);
                            tuijain1Text.setText("已收藏");
                            zh_A.sp3 = 1;
                        } else {
                            tuijain1Img.setImageResource(R.drawable.unsc);
                            zh_A.sp3 = 0;
                            tuijain1Text.setText("收藏");
                        }
                    }
                });
                play.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (video.isPlaying()){
                            video.pause();
                            play.setImageResource(R.drawable.play_hei);
                        }else{
                            video.start();
                            play.setImageResource(R.drawable.play_1);
                        }
                    }
                });
                break;
        }

    }

    private void initView() {
        video = findViewById(R.id.video);
        tuijain1Img = findViewById(R.id.tuijain_1_img);
        tuijain1Text = findViewById(R.id.tuijain_1_text);
        tuijian1InitBanner = findViewById(R.id.tuijian_1_init_banner);
        play = findViewById(R.id.play);
    }

    private class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {
        @Override
        public void onCompletion(MediaPlayer mp) {
            tuijain1Img.setImageResource(R.drawable.unsc);
            Toast.makeText(tuijain_1_init.this, "播放完成", Toast.LENGTH_SHORT).show();
        }
    }
}