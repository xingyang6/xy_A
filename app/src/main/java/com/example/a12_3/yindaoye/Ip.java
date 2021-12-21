package com.example.a12_3.yindaoye;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a12_3.R;
import com.example.a12_3.util.BaseActivity;
import com.example.a12_3.util.NetWork;

public class Ip extends BaseActivity {

    private EditText idDaress;
    private Button ipBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip);
        setTitle("IP设置");
        initView();
        initdata();
    }

    private void initdata() {
        ipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idDaress.getText().toString().isEmpty()){
                    Toast.makeText(Ip.this, "请输入ip", Toast.LENGTH_SHORT).show();
                }else{
                    NetWork.baseurl="http://"+idDaress.getText().toString().trim();
                    Toast.makeText(Ip.this, "保存成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private void initView() {
        idDaress = findViewById(R.id.id_daress);
        ipBtn = findViewById(R.id.ip_btn);
    }
}