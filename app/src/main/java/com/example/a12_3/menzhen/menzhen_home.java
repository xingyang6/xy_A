package com.example.a12_3.menzhen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_3.Adapter.menzhen_Adapter;
import com.example.a12_3.R;
import com.example.a12_3.bean.home_banner_bean;
import com.example.a12_3.bean.jiuehzenrne_bean;
import com.example.a12_3.bean.menzhen_home_banenr_bean;
import com.example.a12_3.bean.menzhen_keshi_bean;
import com.example.a12_3.bean.yiyuan_list_bean;
import com.example.a12_3.home.nes_init;
import com.example.a12_3.util.BaseActivity;
import com.example.a12_3.util.NetWork;
import com.example.a12_3.util.OkResult;
import com.example.a12_3.util.gongge;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.a12_3.jianche.Adapter.initdata;

public class menzhen_home extends BaseActivity {

    private SearchView wenzhenSear;
    private Banner wenzhenBanenr;
    private RecyclerView menzhenHomeRecy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menzhen_home);
        setTitle("门诊预约首页");
        initView();
        init();
        initserar();
    }

    private void initsear_lsit(String s) {
        NetWork.doGet("/prod-api/api/hospital/hospital/list?hospitalName="+s, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<yiyuan_list_bean> llist=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<yiyuan_list_bean>>(){
                    }.getType());
                    if (llist.size()==0){
                        Toast.makeText(menzhen_home.this, "搜索结果为空", Toast.LENGTH_SHORT).show();
                    }else{
                        List<jiuehzenrne_bean> jzr=new ArrayList<>();
                        List<menzhen_keshi_bean> keshi=new ArrayList<>();
                        menzhenHomeRecy.setLayoutManager(new LinearLayoutManager(menzhen_home.this));
                        menzhenHomeRecy.setAdapter(new menzhen_Adapter(menzhen_home.this,llist,jzr,keshi,0));
                    }
                }else{
                    Toast.makeText(menzhen_home.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void initserar() {
        wenzhenSear.setSubmitButtonEnabled(true);
        wenzhenSear.setImeOptions(EditorInfo.IME_ACTION_PREVIOUS);
        wenzhenSear.setIconified(true);
        wenzhenSear.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (gongge.isFirst()){
                    return false;
                }else{
                    initsear_lsit(query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    private void init() {
        NetWork.doGet("/prod-api/api/hospital/banner/list?hospitalId=1", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<menzhen_home_banenr_bean> banner=new Gson().fromJson(jsonObject.optString("data"),new TypeToken<List<menzhen_home_banenr_bean>>(){
                    }.getType());
                    wenzhenBanenr.setImages(banner).setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object o, ImageView imageView) {
                            menzhen_home_banenr_bean ban=(menzhen_home_banenr_bean)o;
                            NetWork.doImager(context,ban.getImgUrl(),imageView);
                        }
                    }).start();
                    wenzhenBanenr.setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int i) {
                            startActivity(new Intent(menzhen_home.this,menzhen_init.class));
                        }
                    }).start();
                }else{
                    Toast.makeText(menzhen_home.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });

        NetWork.doGet("/prod-api/api/hospital/hospital/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<yiyuan_list_bean> llist=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<yiyuan_list_bean>>(){
                    }.getType());
                    List<jiuehzenrne_bean> jzr=new ArrayList<>();
                    List<menzhen_keshi_bean> keshi=new ArrayList<>();
                    menzhenHomeRecy.setLayoutManager(new LinearLayoutManager(menzhen_home.this));
                    menzhenHomeRecy.setAdapter(new menzhen_Adapter(menzhen_home.this,llist,jzr,keshi,0));
                }else{
                    Toast.makeText(menzhen_home.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void initView() {
        wenzhenSear = findViewById(R.id.wenzhen_sear);
        wenzhenBanenr = findViewById(R.id.wenzhen_banenr);
        menzhenHomeRecy = findViewById(R.id.menzhen_home_recy);
    }
}