package com.example.a12_3.hours;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_3.Adapter.hourse_Adapter;
import com.example.a12_3.R;
import com.example.a12_3.bean.home_banner_bean;
import com.example.a12_3.bean.hourse_bean;
import com.example.a12_3.home.nes_init;
import com.example.a12_3.util.BaseActivity;
import com.example.a12_3.util.NetWork;
import com.example.a12_3.util.OkResult;
import com.example.a12_3.util.gongge;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONObject;

import java.util.List;

public class hours_home extends BaseActivity {

    private SearchView hourseSear;
    private Banner hourseBanner;
    private TabLayout hourseTab;
    private LinearLayout tuijain1;
    private LinearLayout tuijain2;
    private LinearLayout tuijain3;
    private LinearLayout tuijain4;
    private RecyclerView hourseRecy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hours_home);
        setTitle("找房子");
        initView();
        initdata();
        initlist("");
        search();

    }

    private void search() {
        hourseSear.setIconified(true);
        hourseSear.setImeOptions(EditorInfo.IME_ACTION_PREVIOUS);
        hourseSear.setSubmitButtonEnabled(true);
        hourseSear.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (gongge.isFirst()){
                    return false;
                }else{
                    initsear(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    private void initsear(String string) {
        NetWork.doGet("/prod-api/api/house/housing/list?sourceName="+string, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<hourse_bean> hourse=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<hourse_bean>>(){
                }.getType());
                hourseRecy.setLayoutManager(new LinearLayoutManager(hours_home.this));
                hourseRecy.setAdapter(new hourse_Adapter(hours_home.this,hourse));
            }
        });
    }

    private void initlist(String s) {
        NetWork.doGet("/prod-api/api/house/housing/list?houseType="+s, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                List<hourse_bean> hourse=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<hourse_bean>>(){
                }.getType());
                hourseRecy.setLayoutManager(new LinearLayoutManager(hours_home.this));
                hourseRecy.setAdapter(new hourse_Adapter(hours_home.this,hourse));
            }
        });
    }

    private void initdata() {
        //轮播图
        NetWork.doGet("/prod-api/api/rotation/list?pageNum=1&pageSize=8&type=2", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<home_banner_bean> banner=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<home_banner_bean>>(){
                    }.getType());
                    hourseBanner.setImages(banner).setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object o, ImageView imageView) {
                            home_banner_bean ban=(home_banner_bean)o;
                            NetWork.doImager(context,ban.getAdvImg(),imageView);
                        }
                    }).start();
                }else{
                    Toast.makeText(hours_home.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
        tuijain1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initlist("二手");
            }
        });
        tuijain2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initlist("租房");
            }
        });
        tuijain3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initlist("楼盘");
            }
        });
        tuijain4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initlist("中介");
            }
        });

    }

    private void initView() {
        hourseSear = findViewById(R.id.hpou);
        hourseBanner = findViewById(R.id.hourse_banner);
        tuijain1 = findViewById(R.id.tuijain_1);
        tuijain2 = findViewById(R.id.tuijain_2);
        tuijain3 = findViewById(R.id.tuijain_3);
        tuijain4 = findViewById(R.id.tuijain_4);
        hourseRecy = findViewById(R.id.hourse_recy);
    }
}