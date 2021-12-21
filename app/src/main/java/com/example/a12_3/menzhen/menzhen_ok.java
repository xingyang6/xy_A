package com.example.a12_3.menzhen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a12_3.R;
import com.example.a12_3.util.BaseActivity;
import com.example.a12_3.util.NetWork;
import com.example.a12_3.util.OkResult;

import org.json.JSONException;
import org.json.JSONObject;

public class menzhen_ok extends BaseActivity {
    public static int keshi_id;
    public static int money;
    public static String keshi_name;
    public static String user_name;
    public static int keshi_type = 2;
    public static String keshi_data;
    public static String week;

    private TextView keshiName;
    private TextView kehsiType;
    private TextView keshiData;
    private Button keshiBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menzhen_ok);
        setTitle("预约");
        initView();
        initdata();
    }

    private void initdata() {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("categoryId",keshi_id)
                    .put("money",money)
                    .put("patientName",user_name)
                    .put("reserveTime",keshi_data)
                    .put("type","2");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        NetWork.doPost("/prod-api/api/hospital", jsonObject.toString(), new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    keshiName.setText("预约科室："+keshi_name);
                    kehsiType.setText("门诊类型："+"普通号");
                    keshiData.setText("预约时间："+week);
                    keshiBtn.setText("预约成功");
                    keshiBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                            startActivity(new Intent(menzhen_ok.this,menzhen_home.class));
                        }
                    });
                }else{
                    Toast.makeText(menzhen_ok.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        keshiName = findViewById(R.id.keshi_name);
        kehsiType = findViewById(R.id.kehsi_type);
        keshiData = findViewById(R.id.keshi_data);
        keshiBtn = findViewById(R.id.keshi_btn);
    }
}