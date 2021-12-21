package com.example.a12_3.menzhen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class menzhen_jiuzhenrne extends BaseActivity {

    private RecyclerView jiuhznerneRecy;
    private LinearLayout addJiuzhenrne;
    private ImageView imageView15;
    private TextView textView30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menzhen_jiuzhenrne);
        setTitle("就诊人卡片");
        initView();
        inirdata();
    }

    private void inirdata() {
        NetWork.doGet("/prod-api/api/hospital/patient/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code") == 200) {
                    List<jiuehzenrne_bean> jzr = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<jiuehzenrne_bean>>() {
                    }.getType());
                    List<yiyuan_list_bean> lsit = new ArrayList<>();
                    List<menzhen_keshi_bean> keshi=new ArrayList<>();
                    jiuhznerneRecy.setLayoutManager(new LinearLayoutManager(menzhen_jiuzhenrne.this));
                    jiuhznerneRecy.setAdapter(new menzhen_Adapter(menzhen_jiuzhenrne.this, lsit, jzr, keshi,1));
                } else {
                    Toast.makeText(menzhen_jiuzhenrne.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
        addJiuzhenrne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menzhen_jiuzherne_init.type=1;
                startActivity(new Intent(menzhen_jiuzhenrne.this,menzhen_jiuzherne_init.class));
            }
        });
    }

    private void initView() {
        jiuhznerneRecy = findViewById(R.id.jiuhznerne_recy);
        addJiuzhenrne = findViewById(R.id.add_jiuzhenrne);
    }
}