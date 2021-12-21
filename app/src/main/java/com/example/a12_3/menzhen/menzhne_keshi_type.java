package com.example.a12_3.menzhen;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_3.Adapter.menzhen_Adapter;
import com.example.a12_3.R;
import com.example.a12_3.bean.jiuehzenrne_bean;
import com.example.a12_3.bean.menzhen_keshi_bean;
import com.example.a12_3.bean.yiyuan_list_bean;
import com.example.a12_3.util.BaseActivity;
import com.example.a12_3.util.NetWork;
import com.example.a12_3.util.OkResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class menzhne_keshi_type extends BaseActivity {

    private TextView put;
    private TextView zj;
    private RecyclerView yuyuekeshiRecy;
    private TextView textView34;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menzhne_keshi_type);
        setTitle("预约科室");
        initView();
        init_put();
    }


    private void init_put() {
        NetWork.doGet("/prod-api/api/hospital/category/list?type=2", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<menzhen_keshi_bean> keshi=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<menzhen_keshi_bean>>(){
                    }.getType());
                     List<yiyuan_list_bean> lsit=new ArrayList<>();
                     List<jiuehzenrne_bean> jzr=new ArrayList<>();
                    yuyuekeshiRecy.setLayoutManager(new LinearLayoutManager(menzhne_keshi_type.this));
                    yuyuekeshiRecy.setAdapter(new menzhen_Adapter(menzhne_keshi_type.this,lsit,jzr,keshi,2));
                }else{
                    Toast.makeText(menzhne_keshi_type.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    private void initView() {
        put = findViewById(R.id.put);
        zj = findViewById(R.id.zj);
        yuyuekeshiRecy = findViewById(R.id.yuyuekeshi_recy);
        textView34 = findViewById(R.id.textView34);
    }
}