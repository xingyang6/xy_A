package com.example.a12_3.jianche;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.a12_3.Adapter.jianche_Adapter;
import com.example.a12_3.R;
import com.example.a12_3.util.BaseActivity;

import static com.example.a12_3.jianche.jianche_4.button2;
import static com.example.a12_3.jianche.jianche_4.jianche41Recy;

public class jianche_4_1 extends BaseActivity {

    private TextView textView11;
    private EditText jiahnche41Chepai;
    private TextView textView12;
    private EditText jiahnche41Chejia;
    private TextView textView14;
    private Spinner jianche41Spinner;
    private TextView textView15;
    private EditText jiahnche41Km;
    private TextView textView16;
    private EditText jiahnche41Hone;
    private Button jiahnche41Btn;

    private String[] chel_type = {"大型车辆", "小型车辆"};
    private TextView textView17;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jianche_4_1);
        setTitle("添加车辆信息");
        initView();
        initdata();

    }

    private void initdata() {
        ArrayAdapter<String> mspinner = new ArrayAdapter<>(this, R.layout.item, chel_type);
        mspinner.setDropDownViewResource(R.layout.item);
        jianche41Spinner.setAdapter(mspinner);

        jianche41Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textView17.setText(chel_type[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        jiahnche41Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jiahnche41Chepai.getText().toString().isEmpty() | jiahnche41Chejia.getText().toString().isEmpty() | jiahnche41Km.getText().toString().isEmpty() | jiahnche41Hone.getText().toString().isEmpty()) {
                    Toast.makeText(jianche_4_1.this, "请输入完整数据", Toast.LENGTH_SHORT).show();
                } else {
                    Adapter.chepaihao.add(jiahnche41Chepai.getText().toString());
                    Adapter.chejiahao.add(jiahnche41Chejia.getText().toString());
                    Adapter.cheliangtype.add(textView17.getText().toString().trim());
                    Adapter.km.add(jiahnche41Km.getText().toString());
                    Adapter.phone.add(jiahnche41Hone.getText().toString());

                    Toast.makeText(jianche_4_1.this, "添加成功", Toast.LENGTH_SHORT).show();
                    jianche41Recy.setLayoutManager(new LinearLayoutManager(jianche_4_1.this));
                    jianche41Recy.setAdapter(new jianche_Adapter(jianche_4_1.this, 0));
                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(jianche_4_1.this, jianche_4_1.class));
                        }
                    });
                    finish();
                }
            }
        });


    }

    private void initView() {
        jiahnche41Chepai = findViewById(R.id.jiahnche_4_1_chepai);
        jiahnche41Chejia = findViewById(R.id.jiahnche_4_1_chejia);
        jianche41Spinner = findViewById(R.id.jianche_4_1_spinner);
        jiahnche41Km = findViewById(R.id.jiahnche_4_1_km);
        jiahnche41Hone = findViewById(R.id.jiahnche_4_1_hone);
        jiahnche41Btn = findViewById(R.id.jiahnche_4_1_btn);
        textView17 = findViewById(R.id.textView17);
    }
}