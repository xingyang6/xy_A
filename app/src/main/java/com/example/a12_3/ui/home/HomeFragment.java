package com.example.a12_3.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_3.Adapter.home_Adapter;
import com.example.a12_3.R;
import com.example.a12_3.bean.home_banner_bean;
import com.example.a12_3.bean.home_news_type_bean;
import com.example.a12_3.bean.home_remen_bean;
import com.example.a12_3.bean.home_tuijian_bean;
import com.example.a12_3.bean.news_pingl_bean;
import com.example.a12_3.home.home_search;
import com.example.a12_3.home.nes_init;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private SearchView homeSearchView;
    private Banner homeBanner;
    private TextView textView2;
    private RecyclerView hoemTuijianRecy;
    private TextView textView3;
    private RecyclerView hoemRemen;
    private TabLayout table;
    private RecyclerView hoemNewsRecy;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initdata();
        initbannner();
        inittuijian();
        initremen();
        inittable();
    }

    private void inittable() {
        NetWork.doGet("/prod-api/press/category/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<home_news_type_bean> news_type=new Gson().fromJson(jsonObject.optString("data"),new TypeToken<List<home_news_type_bean>>(){
                    }.getType());
                    for (int i=0;i<news_type.size();i++){
                        table.addTab(table.newTab().setText(news_type.get(i).getName()).setTag(news_type.get(i).getId()));
                    }
                    loast(news_type.get(0).getId());

                }else{
                    Toast.makeText(getActivity(), jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
        table.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        NetWork.doGet("/prod-api/press/press/list?type=" + id, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<home_remen_bean> news=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<home_remen_bean>>(){
                    }.getType());
                    List<home_tuijian_bean> mtuijain=new ArrayList<>();
                    hoemNewsRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
                    List<news_pingl_bean> pl=new ArrayList<>();
                    hoemNewsRecy.setAdapter(new home_Adapter(getActivity(),mtuijain,news,pl,2));

                }else{
                    Toast.makeText(getActivity(), jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initremen() {
        NetWork.doGet("/prod-api/press/press/list?hot=Y", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<home_remen_bean> tuijain=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<home_remen_bean>>(){
                    }.getType());
                    List<home_tuijian_bean> mtuijain=new ArrayList<>();
                    List<news_pingl_bean> pl=new ArrayList<>();
                    hoemRemen.setLayoutManager(new GridLayoutManager(getActivity(),2));
                    hoemRemen.setAdapter(new home_Adapter(getActivity(),mtuijain,tuijain,pl,1));

                }else{
                    Toast.makeText(getActivity(), jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void inittuijian() {
        Comparator<home_tuijian_bean> comparator=new Comparator<home_tuijian_bean>() {
            @Override
            public int compare(home_tuijian_bean o1, home_tuijian_bean o2) {
                if (o1.getId()!=o2.getId()){
                    return o2.getId() - o1.getId();
                }else{
                    return o1.getId() - o2.getId();
                }
            }
        };
        NetWork.doGet("/prod-api/api/service/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<home_tuijian_bean> tuijain=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<home_tuijian_bean>>(){
                    }.getType());
                    Collections.sort(tuijain,comparator);
                    List<home_remen_bean> remen=new ArrayList<>();
                    hoemTuijianRecy.setLayoutManager(new GridLayoutManager(getActivity(),5));
                    List<news_pingl_bean> pl=new ArrayList<>();
                    hoemTuijianRecy.setAdapter(new home_Adapter(getActivity(),tuijain,remen,pl,0));

                }else{
                    Toast.makeText(getActivity(), jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initbannner() {
        NetWork.doGet("/prod-api/api/rotation/list?pageNum=1&pageSize=8&type=2", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<home_banner_bean> banner=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<home_banner_bean>>(){
                    }.getType());
                    homeBanner.setImages(banner).setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object o, ImageView imageView) {
                            home_banner_bean ban=(home_banner_bean)o;
                            NetWork.doImager(context,ban.getAdvImg(),imageView);
                        }
                    }).start();
                    homeBanner.setOnBannerListener(new OnBannerListener() {
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

    private void initdata() {
        homeSearchView.setIconified(true);
        homeSearchView.setSubmitButtonEnabled(true);
        homeSearchView.setImeOptions(EditorInfo.IME_ACTION_PREVIOUS);
        homeSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (gongge.isFirst()){
                    return false;
                }else{
                    home_search.secach_text=query;
                    startActivity(new Intent(getActivity(),home_search.class));
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    private void initView(View view) {
        homeSearchView = view.findViewById(R.id.home_searchView);
        homeBanner = view.findViewById(R.id.home_banner);
        textView2 = view.findViewById(R.id.textView2);
        hoemTuijianRecy = view.findViewById(R.id.hoem_tuijian_recy);
        textView3 = view.findViewById(R.id.textView3);
        hoemRemen = view.findViewById(R.id.hoem_remen);
        table = view.findViewById(R.id.table);
        hoemNewsRecy = view.findViewById(R.id.hoem_news_recy);
    }
}