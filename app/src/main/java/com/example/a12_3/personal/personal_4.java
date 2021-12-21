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

public class personal_4 extends BaseActivity {

    private TextView textView28;
    private EditText editTextTextPersonName3;
    private TextView textView29;
    private Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_4);
        setTitle("意见反馈");


        initView();
        initdata();
    }

    private void initdata() {
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextTextPersonName3.getText().toString().isEmpty()){
                    Toast.makeText(personal_4.this, "请输入意见", Toast.LENGTH_SHORT).show();
                }else{
                    JSONObject jsonObject=new JSONObject();
                    try {
                        jsonObject.put("content",editTextTextPersonName3.getText().toString().trim());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    NetWork.doPost("/prod-api/api/common/feedback", jsonObject.toString(), new OkResult() {
                        @Override
                        public void succes(JSONObject jsonObject) {
                            if (jsonObject.optInt("code")==200){
                                Toast.makeText(personal_4.this, "意见已提交", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(personal_4.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });
    }

    private void initView() {
        textView28 = findViewById(R.id.textView28);
        editTextTextPersonName3 = findViewById(R.id.editTextTextPersonName3);
        textView29 = findViewById(R.id.textView29);
        button5 = findViewById(R.id.button5);
    }
}