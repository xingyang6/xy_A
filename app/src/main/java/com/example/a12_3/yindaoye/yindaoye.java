package com.example.a12_3.yindaoye;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.a12_3.Login;
import com.example.a12_3.MainActivity;
import com.example.a12_3.R;
import com.example.a12_3.jianche.Adapter;
import com.example.a12_3.util.NetWork;
import com.example.a12_3.util.OkResult;
import com.example.a12_3.zhihui1dnagjain.zh_A;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class yindaoye extends AppCompatActivity {
    private int current = 0;
    private int[] data = {R.drawable.yind_1, R.drawable.yind_2, R.drawable.yind_3, R.drawable.yind_4, R.drawable.yind_5};
    private ViewPager viewpager;
    private LinearLayout picture;
    private TextView textView;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yindaoye);
        getSupportActionBar().hide();
        Adapter.initdata();
        zh_A.initdata();
        initView();
        initdata();
        initclick();

    }

    private void initclick() {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(yindaoye.this,Ip.class));
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(yindaoye.this, "1", Toast.LENGTH_SHORT).show();
                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("username","text01")
                            .put("password","000000");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                NetWork.doPost("/prod-api/api/login", jsonObject.toString(), new OkResult() {
                    @Override
                    public void succes(JSONObject jsonObject) {
                        if (jsonObject.optInt("code")==200){
                            NetWork.token=jsonObject.optString("token");
                            Toast.makeText(yindaoye.this, "登录成功", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(yindaoye.this, MainActivity.class));
                        }else {
                            Toast.makeText(yindaoye.this, "2", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(yindaoye.this,Login.class));
                        }
                    }
                });
            }
        });
    }

    private void initdata() {
        ArrayList<ImageView> imageViews=new ArrayList<>();
        for (int img:data){
            //for循环执行图片滑动效果
            ImageView imageView=new ImageView(this);
            //设置图片
            imageView.setImageResource(img);
            //对图片进行等比缩放
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageViews.add(imageView);
        }
        viewpager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return data.length;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view==object;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                ImageView imageView=imageViews.get(position);
                container.addView(imageView);
               return imageView;
            }
        });
        //设置小圆点
        for (int i=0;i<imageViews.size();i++){
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(20,20);
            View view=new View(this);
            view.setBackgroundResource(R.drawable.select_brean);
            view.setEnabled(false);
            if (i!=0){
                params.leftMargin=10;
            }else view.setEnabled(true);{
                picture.addView(view,params);
            }
        }

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position==imageViews.size()-1){
                    textView.setVisibility( View.VISIBLE);
                    button.setVisibility( View.VISIBLE);
                }else{
                    textView.setVisibility( View.GONE);
                    button.setVisibility( View.GONE);
                }
                picture.getChildAt(current).setEnabled(false);
                picture.getChildAt(position).setEnabled(true);
                current=position;
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }

    private void initView() {
        viewpager = findViewById(R.id.viewpager);
        picture = findViewById(R.id.picture);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
    }
}