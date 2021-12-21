package com.example.a12_3.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a12_3.Adapter.zhihui_Adapter;
import com.example.a12_3.R;
import com.example.a12_3.util.NetWork;
import com.example.a12_3.zhihui1dnagjain.tuijain_4;
import com.example.a12_3.zhihui1dnagjain.tuijian_1;
import com.example.a12_3.zhihui1dnagjain.tuijian_2;
import com.example.a12_3.zhihui1dnagjain.tuijian_3;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private TextView zhAdress;
    private Banner zhBanner;
    private LinearLayout zh1;
    private LinearLayout zh2;
    private LinearLayout zh3;
    private LinearLayout zh4;
    private TabLayout tab1;
    private RecyclerView zhRecy;
    private List<Integer> imgs=new ArrayList<>();
    private String[] aa={"红色新闻","抗疫精神"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initdata();
    }

    private void initdata() {
        zh4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), tuijain_4.class));
            }
        });
        zh3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), tuijian_3.class));
            }
        });
        zh2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), tuijian_2.class));
            }
        });
        zh1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), tuijian_1.class));
            }
        });
        zhAdress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetWork.doAdress(getActivity(),zhAdress);
            }
        });
        imgs.add(R.drawable.dj1);
        imgs.add(R.drawable.dj2);
        imgs.add(R.drawable.dj3);
        zhRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
        zhRecy.setAdapter(new zhihui_Adapter(getActivity(),0));
        for (int i=0;i<aa.length;i++){
            tab1.addTab(tab1.newTab().setText(aa[i]).setTag(i));
        }
        tab1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                lay(tab.getTag().toString().trim());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        zhBanner.setImages(imgs).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object o, ImageView imageView) {
                Glide.with(context).load(o).into(imageView);
            }
        });
        zhBanner.start();

    }
    private void lay(String trim) {
        switch (trim){
            case "0":
                zhRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
                zhRecy.setAdapter(new zhihui_Adapter(getActivity(),0));
                break;
            case "1":
                zhRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
                zhRecy.setAdapter(new zhihui_Adapter(getActivity(),1));
                break;
        }

    }

    private void initView(View view) {
        zhAdress = view.findViewById(R.id.zh_adress);
        zhBanner = view.findViewById(R.id.zh_banner);
        zh1 = view.findViewById(R.id.zh_1);
        zh2 = view.findViewById(R.id.zh_2);
        zh3 = view.findViewById(R.id.zh_3);
        zh4 = view.findViewById(R.id.zh_4);
        tab1 = view.findViewById(R.id.tab1);
        zhRecy = view.findViewById(R.id.zh_recy);
    }
}