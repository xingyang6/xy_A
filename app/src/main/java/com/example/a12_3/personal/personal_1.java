package com.example.a12_3.personal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.a12_3.R;
import com.example.a12_3.bean.user_init_bean;
import com.example.a12_3.util.BaseActivity;
import com.example.a12_3.util.NetWork;
import com.example.a12_3.util.OkResult;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class personal_1 extends BaseActivity {

    private TextView textView13;
    private ImageView txImg;
    private TextView textView23;
    private EditText nickname;
    private TextView textView24;
    private RadioButton sex0;
    private RadioButton sex1;
    private TextView textView26;
    private EditText phone;
    private TextView 证件号;
    private TextView idcard;
    private Button button4;
    private ImageView imageView15;
    private TextView zhengjianh;
    private ImageView imageView16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_1);
        setTitle("个人信息");
        initView();
        initdata();
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickinit();
            }
        });

    }

    private void clickinit() {
        if (nickname.getText().toString().isEmpty() | phone.getText().toString().isEmpty()) {
            Toast.makeText(this, "请完善信息", Toast.LENGTH_SHORT).show();
        } else {
            String a;
            if (sex0.isChecked()) {
                a = "0";
            } else {
                a = "1";
            }

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("nickName", nickname.getText().toString().trim())
                        .put("phonenumber", phone.getText().toString().trim())
                        .put("sex", a);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            NetWork.doPut("/prod-api/api/common/user", jsonObject.toString(), new OkResult() {
                @Override
                public void succes(JSONObject jsonObject) {
                    if (jsonObject.optInt("code") == 200) {
                        Toast.makeText(personal_1.this, "修改成功", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(personal_1.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            Bundle ex = data.getExtras();
            Bitmap bitmap = (Bitmap) ex.get("data");
            imageView16.setImageBitmap(bitmap);
        }
    }

    private void initdata() {

        imageView16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent take = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (take.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(take, 0);
                }
            }
        });

        NetWork.doGet("/prod-api/api/common/user/getInfo", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code") == 200) {
                    user_init_bean init = new Gson().fromJson(jsonObject.optString("user"), user_init_bean.class);
//                    NetWork.doImager(personal_1.this, init.getAvatar(), imageView16);
                    imageView16.setImageResource(R.drawable.dj1);
                    nickname.setText(init.getNickName());
                    if (init.getSex().equals("0")) {
                        sex0.setChecked(true);
                    } else {
                        sex1.setChecked(true);
                    }
                    phone.setText(init.getPhonenumber());
                    idcard.setText(init.getIdCard().substring(1, 3) + "************" + init.getIdCard().substring(init.getIdCard().length() - 4, init.getIdCard().length()));
                } else {
                    Toast.makeText(personal_1.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void initView() {
        nickname = findViewById(R.id.nickname);
        sex0 = findViewById(R.id.sex_0);
        sex1 = findViewById(R.id.sex_1);
        phone = findViewById(R.id.phone);
        idcard = findViewById(R.id.idcard);
        button4 = findViewById(R.id.button4);
        zhengjianh = findViewById(R.id.zhengjianh);
        imageView16 = findViewById(R.id.imageView16);
    }
}