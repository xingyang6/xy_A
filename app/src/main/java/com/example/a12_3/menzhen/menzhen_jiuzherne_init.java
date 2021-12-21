package com.example.a12_3.menzhen;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.a12_3.Adapter.menzhen_Adapter;
import com.example.a12_3.R;
import com.example.a12_3.bean.jiuehzenrne_bean;
import com.example.a12_3.bean.yiyuan_list_bean;
import com.example.a12_3.util.BaseActivity;
import com.example.a12_3.util.NetWork;
import com.example.a12_3.util.OkResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class menzhen_jiuzherne_init extends BaseActivity {
    public static int id;
    public static int type;
    public static int pos;//第几个
    private EditText nickname;
    private RadioButton jiuzhenrneIdcardSex0;
    private RadioButton jiuzhenrneIdcardSex1;
    private TextView textView26;
    private EditText jiuzhenrnePhone;
    private EditText jiuzhenrneIdcard;
    private EditText jiuzhenrneAdress;
    private TextView jiuzhenrneData;
    private Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menzhen_jiuzherne_init);
        setTitle("修改就诊人卡片");
        initView();
        if (type==0){
            initdata();
        }else{
            init_1();
        }
    }

    private void init_1() {
        button4.setText("添加");
        jiuzhenrneData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                DatePickerDialog dp=new DatePickerDialog(menzhen_jiuzherne_init.this,new Mydate(),
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dp.show();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nickname.getText().toString().isEmpty()|jiuzhenrnePhone.getText().toString().isEmpty()|jiuzhenrneIdcard.getText().toString().isEmpty()|jiuzhenrneAdress.getText().toString().isEmpty()|jiuzhenrneData.getText().toString().isEmpty()){
                    Toast.makeText(menzhen_jiuzherne_init.this, "请完善信息", Toast.LENGTH_SHORT).show();
                }else{
                    String a;
                    if (jiuzhenrneIdcardSex0.isChecked()){
                        a="0";
                    }else{
                        a="1";
                    }
                    JSONObject jsonObject=new JSONObject();
                    try {
                        jsonObject.put("address",jiuzhenrneAdress.getText().toString().trim())

                                .put("birthday",jiuzhenrneData.getText().toString().trim())
                                .put("cardId",jiuzhenrneIdcard.getText().toString().trim())
                                .put("name",nickname.getText().toString().trim())
                                .put("sex",a);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    NetWork.doPost("/prod-api/api/hospital/patient", jsonObject.toString(), new OkResult() {
                        @Override
                        public void succes(JSONObject jsonObject) {
                            if (jsonObject.optInt("code")==200){
                                Toast.makeText(menzhen_jiuzherne_init.this, "添加成功", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(menzhen_jiuzherne_init.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }


    private void initdata() {
        button4.setText("修改");
        jiuzhenrneData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                DatePickerDialog dp=new DatePickerDialog(menzhen_jiuzherne_init.this,new Mydate(),
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dp.show();
            }
        });
        NetWork.doGet("/prod-api/api/hospital/patient/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<jiuehzenrne_bean> jzr=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<jiuehzenrne_bean>>(){
                    }.getType());
                    nickname.setText( jzr.get(pos).getName());
                    if (jzr.get(pos).getSex().equals("0")){
                        jiuzhenrneIdcardSex0.setChecked(true);
                    }else{
                        jiuzhenrneIdcardSex1.setChecked(true);
                    }
                    jiuzhenrnePhone.setText(jzr.get(pos).getTel());
                    jiuzhenrneIdcard.setText(jzr.get(pos).getCardId());
                    jiuzhenrneAdress.setText(jzr.get(pos).getAddress());
                    jiuzhenrneData.setText(jzr.get(pos).getBirthday());
                }else{
                    Toast.makeText(menzhen_jiuzherne_init.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nickname.getText().toString().isEmpty()|jiuzhenrnePhone.getText().toString().isEmpty()|jiuzhenrneIdcard.getText().toString().isEmpty()|jiuzhenrneAdress.getText().toString().isEmpty()|jiuzhenrneData.getText().toString().isEmpty()){
                    Toast.makeText(menzhen_jiuzherne_init.this, "请完善信息", Toast.LENGTH_SHORT).show();
                }else{
                    String a;
                    if (jiuzhenrneIdcardSex0.isChecked()){
                        a="0";
                    }else{
                        a="1";
                    }
                    JSONObject jsonObject=new JSONObject();
                    try {
                        jsonObject.put("address",jiuzhenrneAdress.getText().toString().trim())
                                .put("birthday",jiuzhenrneData.getText().toString().trim())
                                .put("cardId",jiuzhenrneIdcard.getText().toString().trim())
                                .put("name",nickname.getText().toString().trim())
                                .put("id",id)
                                .put("sex",a);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    NetWork.doPut("/prod-api/api/hospital/patient", jsonObject.toString(), new OkResult() {
                        @Override
                        public void succes(JSONObject jsonObject) {
                            if (jsonObject.optInt("code")==200){
                                Toast.makeText(menzhen_jiuzherne_init.this, "修改成功", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(menzhen_jiuzherne_init.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private void initView() {
        nickname = findViewById(R.id.nickname);
        jiuzhenrneIdcardSex0 = findViewById(R.id.jiuzhenrne_idcard_sex_0);
        jiuzhenrneIdcardSex1 = findViewById(R.id.jiuzhenrne_idcard_sex_1);
        textView26 = findViewById(R.id.textView26);
        jiuzhenrnePhone = findViewById(R.id.jiuzhenrne_phone);
        jiuzhenrneIdcard = findViewById(R.id.jiuzhenrne_idcard);
        jiuzhenrneAdress = findViewById(R.id.jiuzhenrne_adress);
        jiuzhenrneData = findViewById(R.id.jiuzhenrne_data);
        button4 = findViewById(R.id.button4);
    }

    private class Mydate implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            jiuzhenrneData.setText(year+"-"+(month+1)+"-"+dayOfMonth);
        }
    }
}