package com.example.a12_3.menzhen;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.YuvImage;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a12_3.R;
import com.example.a12_3.bean.jiuehzenrne_bean;
import com.example.a12_3.bean.menzhen_keshi_bean;
import com.example.a12_3.bean.yiyuan_list_bean;
import com.example.a12_3.util.BaseActivity;
import com.example.a12_3.util.NetWork;
import com.example.a12_3.util.OkResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class menzhen_keshi_type_1 extends BaseActivity {
    public static String keshi_naem;
    private String type_1_type;
    private String week;
    private TextView put;
    private TextView zj;
    private TextView textView34;
    private TextView data;
    private LinearLayout learSum;
    private TextView time1;
    private TextView time2;
    private TextView time3;
    private TextView time4;
    private TextView time5;
    private TextView time6;
    private TextView time7;
    private TextView time8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menzhen_keshi_type_1);
        setTitle("选择时间");
        initView();
        initdata();
        getdata();
        dangqian_time();
        initclick();

    }

    private void initclick() {
        time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menzhen_ok.keshi_data=type_1_type+" "+"8:00";
                menzhen_ok.week=data.getText().toString().trim()+"\u3000"+"8:00";
                startActivity(new Intent(menzhen_keshi_type_1.this,menzhen_ok.class));
            }
        });
        time2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menzhen_ok.keshi_data=type_1_type+" "+"9:00";
                menzhen_ok.week=data.getText().toString().trim()+"\u3000"+"9:00";

                startActivity(new Intent(menzhen_keshi_type_1.this,menzhen_ok.class));
            }
        });
        time3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menzhen_ok.keshi_data=type_1_type+" "+"10:00";
                menzhen_ok.week=data.getText().toString().trim()+"\u3000"+"10:00";
                startActivity(new Intent(menzhen_keshi_type_1.this,menzhen_ok.class));
            }
        });
        time4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menzhen_ok.keshi_data=type_1_type+" "+"11:00";
                menzhen_ok.week=data.getText().toString().trim()+"\u3000"+"11:00";
                startActivity(new Intent(menzhen_keshi_type_1.this,menzhen_ok.class));
            }
        });
        time5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menzhen_ok.keshi_data=type_1_type+" "+"14:00";
                menzhen_ok.week=data.getText().toString().trim()+"\u3000"+"14:00";
                startActivity(new Intent(menzhen_keshi_type_1.this,menzhen_ok.class));
            }
        });
        time6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menzhen_ok.keshi_data=type_1_type+" "+"15:00";
                menzhen_ok.week=data.getText().toString().trim()+"\u3000"+"15:00";
                startActivity(new Intent(menzhen_keshi_type_1.this,menzhen_ok.class));
            }
        });
        time7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menzhen_ok.keshi_data=type_1_type+" "+"16:00";
                menzhen_ok.week=data.getText().toString().trim()+"\u3000"+"16:00";
                startActivity(new Intent(menzhen_keshi_type_1.this,menzhen_ok.class));
            }
        });
        time8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menzhen_ok.keshi_data=type_1_type+" "+"17:00";
                menzhen_ok.week=data.getText().toString().trim()+"\u3000"+"17:00";
                startActivity(new Intent(menzhen_keshi_type_1.this,menzhen_ok.class));
            }
        });
    }

    private void dangqian_time() {
        Calendar calendar=Calendar.getInstance();
        String year=String.valueOf(calendar.get(Calendar.YEAR));
        String month=String.valueOf(calendar.get(Calendar.MONTH)+1);
        String day=String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        type_1_type=year+"-"+month+"-"+day;
        week=String.valueOf(calendar.get(Calendar.DAY_OF_WEEK));
        switch (week){
            case "1":
                week="周日";
                break;
            case "2":
                week="周一";
                break;
            case "3":
                week="周二";
                break;
            case "4":
                week="周三";
                break;
            case "5":
                week="周四";
                break;
            case "6":
                week="周五";
                break;
            case "7":
                week="周六";
                break;
        }
        data.setText(year+"-"+month+"-"+day+"\u3000"+week);
    }

    private void getdata() {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date(System.currentTimeMillis());
        data.setText(simpleDateFormat.format(date));
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                DatePickerDialog dp=new DatePickerDialog(menzhen_keshi_type_1.this,new Mykeshidata(),
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dp.show();
            }
        });
    }

    public static String getWeek(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        String week = sdf.format(date);
        return week;
    }
    private void init_put() {


    }

    private void initdata() {
        put.setBackgroundColor(Color.rgb(63, 81, 181));
        put.setTextColor(Color.rgb(255, 255, 255));
        zj.setBackgroundColor(Color.rgb(255, 255, 255));
        zj.setTextColor(Color.rgb(63, 81, 181));
        put.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init_put();
                learSum.setVisibility(View.VISIBLE);
                data.setVisibility(View.VISIBLE);
                textView34.setVisibility(View.GONE);
                put.setBackgroundColor(Color.rgb(63, 81, 181));
                put.setTextColor(Color.rgb(255, 255, 255));
                zj.setBackgroundColor(Color.rgb(255, 255, 255));
                zj.setTextColor(Color.rgb(63, 81, 181));

            }
        });
        zj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                learSum.setVisibility(View.GONE);
                data.setVisibility(View.GONE);
                textView34.setVisibility(View.VISIBLE);
                zj.setBackgroundColor(Color.rgb(63, 81, 181));
                zj.setTextColor(Color.rgb(255, 255, 255));
                put.setBackgroundColor(Color.rgb(255, 255, 255));
                put.setTextColor(Color.rgb(63, 81, 181));
            }
        });


    }

    private void initView() {
        put = findViewById(R.id.put);
        zj = findViewById(R.id.zj);
        textView34 = findViewById(R.id.textView34);
        data = findViewById(R.id.data);
        learSum = findViewById(R.id.lear_sum);
        time1 = findViewById(R.id.time_1);
        time2 = findViewById(R.id.time_2);
        time3 = findViewById(R.id.time_3);
        time4 = findViewById(R.id.time_4);
        time5 = findViewById(R.id.time_5);
        time6 = findViewById(R.id.time_6);
        time7 = findViewById(R.id.time_7);
        time8 = findViewById(R.id.time_8);
    }

    private class Mykeshidata implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            data.setText(year+"-"+(month+1)+"-"+dayOfMonth);
            type_1_type=data.getText().toString().trim();
            //判断周几
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");// 日期格式
            try {
                Date date = format.parse(data.getText().toString());
                SimpleDateFormat sdf = new SimpleDateFormat("E");
               week = sdf.format(date);
                switch (week){
                    case "1":
                        week="周日";
                        break;
                    case "2":
                        week="周一";
                        break;
                    case "3":
                        week="周二";
                        break;
                    case "4":
                        week="周三";
                        break;
                    case "5":
                        week="周四";
                        break;
                    case "6":
                        week="周五";
                        break;
                    case "7":
                        week="周六";
                        break;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            data.setText(data.getText().toString()+"\u3000"+week);
        }
    }
}