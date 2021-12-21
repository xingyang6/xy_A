package com.example.a12_3.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_3.Adapter.home_Adapter;
import com.example.a12_3.R;
import com.example.a12_3.bean.home_remen_bean;
import com.example.a12_3.bean.home_tuijian_bean;
import com.example.a12_3.bean.news_init_2_bean;
import com.example.a12_3.bean.news_pingl_bean;
import com.example.a12_3.util.BaseActivity;
import com.example.a12_3.util.NetWork;
import com.example.a12_3.util.OkResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class nes_init extends BaseActivity {
    public static int news_id;
    private TextView newsInitTitle;
    private ImageView newsInitImg;
    private TextView newsInitCount;
    private TextView textView8;
    private RecyclerView tuijainNewsRecy;
    private LinearLayout pinglLinear;
    private ImageView imageView8;
    private EditText editTextTextPersonName2;
    private ImageView imageView7;
    private TextView pinglCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nes_init);
        setTitle("新闻详情");
        initView();
        initdata();
    }

    private void initdata() {
        NetWork.doGet("/prod-api/press/press/" + news_id, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                news_init_2_bean init=new Gson().fromJson(jsonObject.optString("data"),news_init_2_bean.class);
                NetWork.doImager(nes_init.this,init.getCover(),newsInitImg);
                newsInitTitle.setText(init.getTitle());
                newsInitCount.setText(Html.fromHtml(init.getContent()));
                String s=init.getCommentNum().toString();
                s=s.substring(0,s.length()-2);
                pinglCount.setText(s+"");


            }
        });
        NetWork.doGet("/prod-api/press/press/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<home_remen_bean> tuijain=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<home_remen_bean>>(){
                    }.getType());
                    List<home_tuijian_bean> mtuijain=new ArrayList<>();
                    List<news_pingl_bean> pl=new ArrayList<>();
                    tuijainNewsRecy.setLayoutManager(new LinearLayoutManager(nes_init.this));
                    tuijainNewsRecy.setAdapter(new home_Adapter(nes_init.this,mtuijain,tuijain,pl,3));
                }else{
                    Toast.makeText(nes_init.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
        pinglLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(nes_init.this,news_init_pingl.class));
            }
        });
        editTextTextPersonName2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(nes_init.this,news_init_pingl.class));

            }
        });
    }

    private void initView() {
        newsInitTitle = findViewById(R.id.news_init_title);
        newsInitImg = findViewById(R.id.news_init_img);
        newsInitCount = findViewById(R.id.news_init_count);
        textView8 = findViewById(R.id.textView8);
        tuijainNewsRecy = findViewById(R.id.tuijain_news_recy);
        pinglLinear = findViewById(R.id.pingl_linear);
        imageView8 = findViewById(R.id.imageView8);
        editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);
        imageView7 = findViewById(R.id.imageView7);
        pinglCount = findViewById(R.id.pingl_count);
    }
}