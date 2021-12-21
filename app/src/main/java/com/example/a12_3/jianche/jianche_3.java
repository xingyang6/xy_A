package com.example.a12_3.jianche;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_3.Adapter.jianche_Adapter;
import com.example.a12_3.R;

public class jianche_3 extends Fragment {


    private TextView textView9;
    private RecyclerView jianche3Recy;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jianche_3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initdata();
    }

    private void initdata() {
        if (Adapter.chengg_chepaihao.size()==0){
            textView9.setVisibility(View.VISIBLE);
        }else{
            textView9.setVisibility(View.GONE);
            jianche3Recy.setLayoutManager(new LinearLayoutManager(getActivity()));
            jianche3Recy.setAdapter(new jianche_Adapter(getActivity(),1));
        }

    }

    private void initView(View view) {
        textView9 = view.findViewById(R.id.textView9);
        jianche3Recy = view.findViewById(R.id.jianche_3_recy);
    }
}