package com.example.a12_3.zhihui1dnagjain;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a12_3.R;
import com.example.a12_3.util.BaseActivity;

public class tuijian_3 extends BaseActivity {

    private EditText jianyan3Text;
    private Button jianyan3Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuijian_3);
        setTitle("建言献策");
        initView();
        initdata();
    }

    private void initdata() {
        jianyan3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jianyan3Text.getText().toString().trim().isEmpty()){
                    Toast.makeText(tuijian_3.this, "请输入", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(tuijian_3.this, "提交成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        jianyan3Text = findViewById(R.id.jianyan_3_text);
        jianyan3Btn = findViewById(R.id.jianyan_3_btn);
    }
}