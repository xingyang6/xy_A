package com.example.a12_3.jianche;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.a12_3.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends AppCompatActivity {
    public static List<String> chepaihao=new ArrayList<>();
    public static List<String> chejiahao=new ArrayList<>();
    public static List<String> cheliangtype=new ArrayList<>();
    public static List<String> km=new ArrayList<>();
    public static List<String> phone=new ArrayList<>();

    public static  List<String> ok_chepaihao=new ArrayList<>();
    public static  List<String> ok_data=new ArrayList<>();
    public static  List<String> ok_adress=new ArrayList<>();

    public static  List<String> chengg_chepaihao=new ArrayList<>();
    public static  List<String> chengg_date=new ArrayList<>();
    public static  List<String> chengg_adress=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);
        initdata();


    }

    public static void initdata() {
        chepaihao.add("A1063警");
        chepaihao.add("GK12001");

        chejiahao.add("111111111111111111111111");
        chejiahao.add("522222222222222222222222");

        cheliangtype.add("大型车辆");
        cheliangtype.add("小型车辆");

        km.add("20000km");
        km.add("66660km");

        phone.add("130350256455");
        phone.add("157984588555");

    }
}