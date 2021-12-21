package com.example.a12_3.jianche;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.a12_3.MainActivity;
import com.example.a12_3.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class jianche_2 extends Fragment {
    private String selece_cai;
    private String jianche_adress="安庆师范大学";
    private RadioGroup radiogroup;
    private TextView jianche2Data;
    private TextView jianche2Adress;
    private Spinner chejian2Spinner;
    private Button btn;
    private List<String> jianchje_adress=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jianche_2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initpduan(view);



    }

    private void initpduan(View view) {
        if (Adapter.chejiahao.size()==0){
            Toast.makeText(getActivity(), "请先添加车辆信息", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(view).navigate(R.id.navigation_jianche_4);
        }else{
            initdata();
            initclick(view);
        }
    }

    private void initclick(View view) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jianche2Data.getText().toString().equals("选择时间")){
                    Toast.makeText(getActivity(), "请选择时间", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "预约成功", Toast.LENGTH_SHORT).show();
                        Adapter.chengg_adress.add(jianche_adress);
                        Adapter.chengg_chepaihao.add(selece_cai);
                        Adapter.chengg_date.add(jianche2Data.getText().toString().trim());
                    Navigation.findNavController(view).navigate(R.id.navigation_jianche_3);
                }
            }
        });
    }

    private void inittime() {
        Calendar mcalendar=Calendar.getInstance();
        TimePickerDialog tp=new TimePickerDialog(getActivity(),new Mytime(),
                mcalendar.get(Calendar.HOUR_OF_DAY),
                mcalendar.get(Calendar.MINUTE),
                true);
        tp.show();
    }

    private void initdata() {
        jianchje_adress.add("安庆师范大学");
        jianchje_adress.add("安庆职业技术学院");

        //动态添加单选按钮
        RadioButton mRadioButton;
        //必须用RadioGroup的LayoutParams  ，而不是LinearLayout的
        RadioGroup.LayoutParams mButtonLayoutParams = new  RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i <Adapter.chejiahao.size(); i++) {
            mRadioButton = new RadioButton(getActivity());
            mRadioButton.setId(i); //必须要设置一个ID
            mButtonLayoutParams.setMargins(0, 32, 0, 0);
            mRadioButton.setText(Adapter.chepaihao.get(i));
            if (i==0){

                selece_cai=mRadioButton.getText().toString().trim();
                mRadioButton.setChecked(true);
            }
            String string=mRadioButton.getText().toString().trim();
            mRadioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            radiogroup.addView(mRadioButton, mButtonLayoutParams);

        }


        jianche2Data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(),new Mydata(),
                            calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();

            }
        });

        ArrayAdapter<String> mspinner=new ArrayAdapter<>(getActivity(),R.layout.item,jianchje_adress);
        mspinner.setDropDownViewResource(R.layout.item);
        chejian2Spinner.setAdapter(mspinner);
        chejian2Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                jianche_adress=jianchje_adress.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initView(View view) {
        radiogroup = view.findViewById(R.id.radiogroup);
        jianche2Data = view.findViewById(R.id.jianche_2_data);
        jianche2Adress = view.findViewById(R.id.jianche_2_adress);
        chejian2Spinner = view.findViewById(R.id.chejian_2_spinner);
        btn = view.findViewById(R.id.btn);
    }

    private class Mydata implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            jianche2Data.setText(year+"-"+(month+1)+"-"+dayOfMonth);
            inittime();
        }
    }

    private class Mytime implements TimePickerDialog.OnTimeSetListener {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            jianche2Data.setText(jianche2Data.getText().toString().trim()+"\u3000"+hourOfDay+":"+minute);
        }
    }
}