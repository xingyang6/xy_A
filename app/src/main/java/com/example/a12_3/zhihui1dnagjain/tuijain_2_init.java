package com.example.a12_3.zhihui1dnagjain;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a12_3.R;
import com.example.a12_3.util.BaseActivity;
import com.example.a12_3.util.NetWork;

import java.util.Calendar;

public class tuijain_2_init extends BaseActivity {

    private EditText tijian2Name;
    private TextView textView47;
    private EditText tijian2Phone;
    private TextView textView49;
    private TextView tijian2Data;
    private TextView textView50;
    private TextView tijian2Adress;
    private TextView textView53;
    private Spinner tuijain2Spinner;
    private Button button11;
    private String[] aaa={"考场志愿者服务","交通维护员","疫情防控员"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuijain_2_init);
        setTitle("活动报名");
        initView();
        initdata();
    }

    private void initdata() {
        ArrayAdapter<String> mspinnere=new ArrayAdapter<>(this,R.layout.item,aaa);
        mspinnere.setDropDownViewResource(R.layout.item);
        tuijain2Spinner.setAdapter(mspinnere);
        tijian2Adress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetWork.doAdress(tuijain_2_init.this,tijian2Adress);
            }
        });


        tijian2Data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                DatePickerDialog sp=new DatePickerDialog(tuijain_2_init.this,new Mytuijian_2(),
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                sp.show();
            }
        });
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tijian2Name.getText().toString().isEmpty()|tijian2Phone.getText().toString().isEmpty()|tijian2Data.getText().toString().equals("选择时间")|tijian2Adress.getText().toString().equals("选择地点")){
                    Toast.makeText(tuijain_2_init.this, "请完善信息", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(tuijain_2_init.this, "报名成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private void initView() {
        tijian2Name = findViewById(R.id.tijian2_name);
        tijian2Phone = findViewById(R.id.tijian2_phone);
        tijian2Data = findViewById(R.id.tijian2_data);
        tijian2Adress = findViewById(R.id.tijian2_adress);
        tuijain2Spinner = findViewById(R.id.tuijain_2_spinner);
        button11 = findViewById(R.id.button11);
    }

    private class Mytuijian_2 implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            tijian2Data.setText(year+"-"+(month+1)+"-"+dayOfMonth);
        }
    }
}