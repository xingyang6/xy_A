package com.example.a12_3.menzhen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a12_3.R;
import com.example.a12_3.bean.menzhen_home_banenr_bean;
import com.example.a12_3.bean.menzhen_init_bean;
import com.example.a12_3.util.BaseActivity;
import com.example.a12_3.util.NetWork;
import com.example.a12_3.util.OkResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONObject;

import java.util.List;

public class menzhen_init extends BaseActivity {
    public static int Hospital_id;
    private Banner wenzhenInitBanenr;
    private TextView menzhenInitText;
    private Button button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menzhen_init);
        setTitle("医院详情");

        initView();
        initdata();
    }

    private void initdata() {
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(menzhen_init.this,menzhen_jiuzhenrne.class));
            }
        });
        NetWork.doGet("/prod-api/api/hospital/banner/list?hospitalId="+Hospital_id, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<menzhen_home_banenr_bean> banner=new Gson().fromJson(jsonObject.optString("data"),new TypeToken<List<menzhen_home_banenr_bean>>(){
                    }.getType());
                    wenzhenInitBanenr.setImages(banner).setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object o, ImageView imageView) {
                            menzhen_home_banenr_bean ban=(menzhen_home_banenr_bean)o;
                            NetWork.doImager(context,ban.getImgUrl(),imageView);
                        }
                    }).start();
                }else{
                    Toast.makeText(menzhen_init.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });

        NetWork.doGet("/prod-api/api/hospital/hospital/" + Hospital_id, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    menzhen_init_bean init=new Gson().fromJson(jsonObject.optString("data"),menzhen_init_bean.class);
                    menzhenInitText.setText(Html.fromHtml(init.getBrief()));
                }else{
                    Toast.makeText(menzhen_init.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        wenzhenInitBanenr = findViewById(R.id.wenzhen_init_banenr);
        menzhenInitText = findViewById(R.id.menzhen_init_text);
        button6 = findViewById(R.id.button6);
    }
}