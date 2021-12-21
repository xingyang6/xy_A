package com.example.a12_3.jianche;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_3.Adapter.jianche_Adapter;
import com.example.a12_3.R;


public class jianche_4 extends Fragment {

    public static Button button2;
    public static RecyclerView jianche41Recy;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_jianche_4, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initdata();
    }

    private void initdata() {
        jianche41Recy.setLayoutManager(new LinearLayoutManager(getActivity()));
        jianche41Recy.setAdapter(new jianche_Adapter(getActivity(),0));
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),jianche_4_1.class));
            }
        });
    }

    private void initView(View view) {
        button2 = view.findViewById(R.id.button2);
        jianche41Recy = view.findViewById(R.id.jianche_4_1_recy);
    }
}