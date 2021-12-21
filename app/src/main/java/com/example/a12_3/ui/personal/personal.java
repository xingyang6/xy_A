package com.example.a12_3.ui.personal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.a12_3.Login;
import com.example.a12_3.R;
import com.example.a12_3.bean.user_init_bean;
import com.example.a12_3.personal.personal_1;
import com.example.a12_3.personal.personal_2;
import com.example.a12_3.personal.personal_3;
import com.example.a12_3.personal.personal_4;
import com.example.a12_3.util.NetWork;
import com.example.a12_3.util.OkResult;
import com.google.gson.Gson;

import org.json.JSONObject;

public class personal extends Fragment {

    private PersonalViewModel mViewModel;
    private ImageView personalHomeImg;
    private TextView personalHomeNickname;
    private LinearLayout liner1;
    private TextView textView19;
    private ImageView imageView11;
    private LinearLayout liner2;
    private TextView textView20;
    private ImageView imageView12;
    private LinearLayout liner3;
    private TextView textView21;
    private ImageView imageView13;
    private LinearLayout liner4;
    private TextView textView22;
    private ImageView imageView14;
    private Button button3;

    public static personal newInstance() {
        return new personal();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.personal_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initdata();
        initclick();
    }

    private void initclick() {
        liner1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), personal_1.class));
            }
        });
        liner2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), personal_2.class));
            }
        });
        liner3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), personal_3.class));
            }
        });
        liner4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), personal_4.class));
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Login.class));
            }
        });
    }

    private void initdata() {
        NetWork.doGet("/prod-api/api/common/user/getInfo", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    user_init_bean init=new Gson().fromJson(jsonObject.optString("user"),user_init_bean.class);
                    personalHomeImg.setImageResource(R.drawable.dj1);
                    personalHomeNickname.setText(init.getNickName());
                }else{
                    Toast.makeText(getActivity(), jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView(View view) {
        personalHomeImg = view.findViewById(R.id.personal_home_img);
        personalHomeNickname = view.findViewById(R.id.personal_home_nickname);
        liner1 = view.findViewById(R.id.liner_1);
        liner2 = view.findViewById(R.id.liner_2);
        liner3 = view.findViewById(R.id.liner_3);
        liner4 = view.findViewById(R.id.liner_4);

        button3 = view.findViewById(R.id.button3);
    }
}