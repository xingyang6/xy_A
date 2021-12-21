package com.example.a12_3.home;

import android.os.Bundle;
import android.view.View;
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

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class home_search extends BaseActivity {
    public static String secach_text;

    private TextView textView5;
    private RecyclerView searchRecy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_search);
        setTitle("搜索结果");
        initView();
        initdata();
    }

    private void initdata() {
        NetWork.doGet("/prod-api/press/press/list?title=" + secach_text, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<home_remen_bean> news=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<home_remen_bean>>(){
                    }.getType());
                    if (news.size()==0){
                        textView5.setVisibility(View.VISIBLE);
                    }else {
                        textView5.setVisibility(View.GONE);
                        List<home_tuijian_bean> mtuijain=new ArrayList<>();
                        List<news_pingl_bean> pl=new ArrayList<>();
                        searchRecy.setLayoutManager(new LinearLayoutManager(home_search.this));
                        searchRecy.setAdapter(new home_Adapter(home_search.this,mtuijain,news,pl,5));
                    }
                }else{
                    Toast.makeText(home_search.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void initView() {
        textView5 = findViewById(R.id.textView5);
        searchRecy = findViewById(R.id.search_recy);
    }
}