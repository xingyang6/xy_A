package com.example.a12_3.zhihui1dnagjain;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_3.Adapter.zhihui_Adapter;
import com.example.a12_3.R;
import com.example.a12_3.bean.user_init_bean;
import com.example.a12_3.util.BaseActivity;
import com.example.a12_3.util.NetWork;
import com.example.a12_3.util.OkResult;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class wenz_init extends BaseActivity {
    public static int wenzen_type;
    public static int wenz_id;
    private TextView textView38;
    private ImageView pinglImg;
    private TextView pinglCount;
    private RecyclerView pinglRecy;
    private ImageView imageView36;
    private EditText pinglText;
    private Button pinglBtn;
    private String name;
    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wenz_init);
        setTitle("文章详情");
        initView();
        inituser();
        initdata();
        initpl();
    }

    private void initpl() {
        pinglRecy.setLayoutManager(new LinearLayoutManager(wenz_init.this));
        pinglRecy.setAdapter(new zhihui_Adapter(wenz_init.this,2));
    }

    private void inituser() {
        NetWork.doGet("/prod-api/api/common/user/getInfo", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    user_init_bean init=new Gson().fromJson(jsonObject.optString("user"),user_init_bean.class);
                    name=init.getNickName();
                }else{
                    Toast.makeText(wenz_init.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat();
        Date date=new Date(System.currentTimeMillis());
        data=simpleDateFormat.format(date);
    }

    private void initdata() {
        switch (wenzen_type){
            case 1:
                switch (wenz_id){
                    case 0:
                        textView38.setText(zh_A.news_1_title.get(0));
                        pinglImg.setImageResource(zh_A.news_1_img.get(0));
                        pinglCount.setText(zh_A.news_1_count.get(0));
                        break;
                    case 1:
                        textView38.setText(zh_A.news_1_title.get(1));
                        pinglImg.setImageResource(zh_A.news_1_img.get(1));
                        pinglCount.setText(zh_A.news_1_count.get(1));
                        break;
                }
                break;
            case 2:
                switch (wenz_id){
                    case 0:
                        textView38.setText(zh_A.news_2_title.get(0));
                        pinglImg.setImageResource(zh_A.news_2_img.get(0));
                        pinglCount.setText(zh_A.news_2_count.get(0));
                        break;
                    case 1:
                        textView38.setText(zh_A.news_2_title.get(1));
                        pinglImg.setImageResource(zh_A.news_2_img.get(1));
                        pinglCount.setText(zh_A.news_2_count.get(1));
                        break;
                }
                break;
        }
        pinglBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pinglText.getText().toString().trim().isEmpty()){
                    Toast.makeText(wenz_init.this, "请输入评论", Toast.LENGTH_SHORT).show();
                }else{
                    switch (wenzen_type){
                        case 1:
                            switch (wenz_id){
                                case 0:
                                zh_A.news_1_pingl_count.add(pinglText.getText().toString().trim());
                                zh_A.news_1_pingl_name.add(name);
                                zh_A.news_1_pingl_data.add(data);
                                    Toast.makeText(wenz_init.this, "评论成功", Toast.LENGTH_SHORT).show();
                                break;
                                case 1:
                                    zh_A.news_1_pingl_count_1.add(pinglText.getText().toString().trim());
                                    zh_A.news_1_pingl_name_1.add(name);
                                    zh_A.news_1_pingl_data_1.add(data);
                                    Toast.makeText(wenz_init.this, "评论成功", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                            break;
                        case 2:
                            switch (wenz_id){
                                case 0:
                                    zh_A.news_2_pingl_count.add(pinglText.getText().toString().trim());
                                    zh_A.news_2_pingl_name.add(name);
                                    zh_A.news_2_pingl_data.add(data);
                                    Toast.makeText(wenz_init.this, "评论成功", Toast.LENGTH_SHORT).show();
                                    break;
                                case 1:
                                    zh_A.news_2_pingl_count_2.add(pinglText.getText().toString().trim());
                                    zh_A.news_2_pingl_name.add(name);
                                    zh_A.news_2_pingl_data.add(data);
                                    Toast.makeText(wenz_init.this, "评论成功", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                            break;
                    }
                    initpl();
                }
            }
        });
    }

    private void initView() {
        textView38 = findViewById(R.id.textView38);
        pinglImg = findViewById(R.id.pingl_img);
        pinglCount = findViewById(R.id.pingl_count);
        pinglRecy = findViewById(R.id.pingl_recy);
        imageView36 = findViewById(R.id.imageView36);
        pinglText = findViewById(R.id.pingl_text);
        pinglBtn = findViewById(R.id.pingl_btn);
    }
}