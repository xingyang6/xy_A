package com.example.a12_3.personal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a12_3.R;
import com.example.a12_3.util.BaseActivity;
import com.example.a12_3.util.NetWork;
import com.example.a12_3.util.OkResult;

import org.json.JSONException;
import org.json.JSONObject;

public class personal_3 extends BaseActivity {

    private TextView textView27;
    private EditText oldpass;
    private TextView textView25;
    private EditText newspass;
    private Button personal3Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_3);
        setTitle("修改密码");
        initView();
        personal3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (oldpass.getText().toString().trim().isEmpty()|newspass.getText().toString().trim().isEmpty()){
                    Toast.makeText(personal_3.this, "请输入完整信息", Toast.LENGTH_SHORT).show();
                }else{
                    initdata();
                }
            }
        });

    }

    private void initdata() {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("oldPassword",oldpass.getText().toString().trim())
                    .put("newPassword",newspass.getText().toString().trim());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        NetWork.doPut("/prod-api/api/common/user/resetPwd", jsonObject.toString(), new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    Toast.makeText(personal_3.this, "操作成功", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(personal_3.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        oldpass = findViewById(R.id.oldpass);
        newspass = findViewById(R.id.newspass);
        personal3Btn = findViewById(R.id.personal_3_btn);
    }
}