package com.example.a12_3.ui.news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_3.Adapter.home_Adapter;
import com.example.a12_3.R;
import com.example.a12_3.bean.home_banner_bean;
import com.example.a12_3.bean.home_news_type_bean;
import com.example.a12_3.bean.home_remen_bean;
import com.example.a12_3.bean.home_tuijian_bean;
import com.example.a12_3.bean.news_pingl_bean;
import com.example.a12_3.home.nes_init;
import com.example.a12_3.util.NetWork;
import com.example.a12_3.util.OkResult;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class news extends Fragment {


    private Banner newsBanner;
    private TabLayout newsTable;
    private RecyclerView newsRecy;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.news_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initdata();
        inittab();
    }

    private void inittab() {
        NetWork.doGet("/prod-api/press/category/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<home_news_type_bean> news_type=new Gson().fromJson(jsonObject.optString("data"),new TypeToken<List<home_news_type_bean>>(){
                    }.getType());
                    for (int i=0;i<news_type.size();i++){
                        newsTable.addTab(newsTable.newTab().setText(news_type.get(i).getName()).setTag(news_type.get(i).getId()));
                    }
                    loast(news_type.get(0).getId());

                }else{
                    Toast.makeText(getActivity(), jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
        newsTable.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String string=tab.getTag().toString().trim();
                loast(string);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void loast(Object id) {
//        Comparator<home_remen_bean>
        NetWork.doGet("/prod-api/press/press/list?type=" + id, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<home_remen_bean> news=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<home_remen_bean>>(){
                    }.getType());
                    List<home_tuijian_bean> mtuijain=new ArrayList<>();
                    newsRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
                    List<news_pingl_bean> pl=new ArrayList<>();
                    newsRecy.setAdapter(new home_Adapter(getActivity(),mtuijain,news,pl,6));

                }else{
                    Toast.makeText(getActivity(), jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initdata() {
        NetWork.doGet("/prod-api/api/rotation/list?pageNum=1&pageSize=8&type=2", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<home_banner_bean> banner=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<home_banner_bean>>(){
                    }.getType());
                    newsBanner.setImages(banner).setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object o, ImageView imageView) {
                            home_banner_bean ban=(home_banner_bean)o;
                            NetWork.doImager(context,ban.getAdvImg(),imageView);
                        }
                    }).start();
                    newsBanner.setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int i) {
                            nes_init.news_id=banner.get(i).getId();
                            startActivity(new Intent(getActivity(),nes_init.class));
                        }
                    }).start();
                }else{
                    Toast.makeText(getActivity(), jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void initView(View view) {
        newsBanner = view.findViewById(R.id.news_banner);
        newsTable = view.findViewById(R.id.news_table);
        newsRecy = view.findViewById(R.id.news_recy);
    }
}