package com.example.a12_3.hours;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a12_3.R;
import com.example.a12_3.bean.hourse_init_bean;
import com.example.a12_3.util.BaseActivity;
import com.example.a12_3.util.NetWork;
import com.example.a12_3.util.OkResult;
import com.google.gson.Gson;

import org.json.JSONObject;

public class house_init extends BaseActivity {
    public static int hourse_id;
    public static String phone;
    private ImageView initPic;
    private TextView initName;
    private TextView initPrice;
    private TextView initAreaSize;
    private TextView initHouseType;
    private TextView initDescription;
    private Button button7;
    private Button button8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_init);
        setTitle("详情");
        initView();
        initdata();
    }

    private void initdata() {
        NetWork.doGet("/prod-api/api/house/housing/"+hourse_id, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    hourse_init_bean init=new Gson().fromJson(jsonObject.optString("data"),hourse_init_bean.class);
                    NetWork.doImager(house_init.this,init.getPic(),initPic);
                    initName.setText(init.getSourceName());
                    initPrice.setText(init.getPrice());
                    initAreaSize.setText(init.getAreaSize()+"M*M");
                    initHouseType.setText("房源类型："+init.getHouseType());
                    initDescription.setText(init.getDescription());
                }else{
                    Toast.makeText(house_init.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone));
                startActivity(intent);
            }
        });
    }

    private void initView() {
        initPic = findViewById(R.id.init_pic);
        initName = findViewById(R.id.init_name);
        initPrice = findViewById(R.id.init_price);
        initAreaSize = findViewById(R.id.init_areaSize);
        initHouseType = findViewById(R.id.init_houseType);
        initDescription = findViewById(R.id.init_description);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
    }
}