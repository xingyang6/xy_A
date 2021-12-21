package com.example.a12_3.home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_3.Adapter.home_Adapter;
import com.example.a12_3.R;
import com.example.a12_3.bean.home_remen_bean;
import com.example.a12_3.bean.home_tuijian_bean;
import com.example.a12_3.bean.news_pingl_bean;
import com.example.a12_3.util.BaseActivity;
import com.example.a12_3.util.NetWork;
import com.example.a12_3.util.OkResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class news_init_pingl extends BaseActivity {

    private LinearLayout pinglLinear;
    private ImageView imageView8;
    private EditText plText;
    private Button fb;
    private TextView textView4;
    private RecyclerView newsPinglRecy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_init_pingl);
        setTitle("新闻评论");
        initView();
        initdata();
    }

    private void initdata() {
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (plText.getText().toString().isEmpty()) {
                    Toast.makeText(news_init_pingl.this, "评论内容不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("newsId", nes_init.news_id)
                                .put("content", plText.getText().toString().trim());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    NetWork.doPost("/prod-api/press/pressComment", jsonObject.toString(), new OkResult() {
                        @Override
                        public void succes(JSONObject jsonObject) {
                            if (jsonObject.optInt("code") == 200) {
                                Toast.makeText(news_init_pingl.this, "发表成功", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(news_init_pingl.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        NetWork.doGet("/prod-api/press/comments/list?newsId=" + nes_init.news_id, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code") == 200) {
                    List<news_pingl_bean> pl = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<news_pingl_bean>>() {
                    }.getType());
                    List<home_tuijian_bean> tuijain=new ArrayList<>();
                    List<home_remen_bean> remen=new ArrayList<>();
                    newsPinglRecy.setLayoutManager(new LinearLayoutManager(news_init_pingl.this));
                    newsPinglRecy.setAdapter(new home_Adapter(news_init_pingl.this,tuijain,remen,pl,4));
                } else {
                    Toast.makeText(news_init_pingl.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        imageView8 = findViewById(R.id.imageView8);
        plText = findViewById(R.id.pl_text);
        fb = findViewById(R.id.fb);
        textView4 = findViewById(R.id.textView4);
        newsPinglRecy = findViewById(R.id.news_pingl_recy);
    }
}