package com.example.a12_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a12_3.jianche.Adapter;
import com.example.a12_3.util.NetWork;
import com.example.a12_3.util.OkResult;
import com.example.a12_3.yindaoye.yindaoye;
import com.example.a12_3.zhihui1dnagjain.zh_A;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    private ImageView imageView;
    private EditText user;
    private ImageView imageView2;
    private EditText pass;
    private Button denglBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("登录");
        initView();
        initdata();
        denglBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getText().toString().trim().isEmpty()|pass.getText().toString().trim().isEmpty()){
                    Toast.makeText(Login.this, "账号或密码为空", Toast.LENGTH_SHORT).show();
                }else{
                    initdata();
                }

            }
        });

    }

    private void initdata() {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("username","text01")
                    .put("password","151407");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        NetWork.doPost("/prod-api/api/login", jsonObject.toString(), new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    NetWork.token=jsonObject.optString("token");
                    Toast.makeText(Login.this, "登录成功", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, MainActivity.class));
                }else {
                    Toast.makeText(Login.this,jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
//        JSONObject jsonObject=new JSONObject();
//        try {
//            jsonObject.put("username",user.getText().toString().trim())
//                    .put("password",pass.getText().toString().trim());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        NetWork.doPost("/prod-api/api/login", jsonObject.toString(), new OkResult() {
//            @Override
//            public void succes(JSONObject jsonObject) {
//                if (jsonObject.optInt("code")==200){
//                    Toast.makeText(Login.this, "登录成功", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(Login.this,MainActivity.class));
//                }else {
//                    Toast.makeText(Login.this,jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

    }

    private void initView() {
        imageView = findViewById(R.id.imageView);
        user = findViewById(R.id.user);
        imageView2 = findViewById(R.id.imageView2);
        pass = findViewById(R.id.pass);
        denglBtn = findViewById(R.id.dengl_btn);
    }
}